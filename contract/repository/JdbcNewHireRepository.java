package contract.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import contract.gui.view.swing.table.HireEntry;
import contract.logging.ContractLogger;
import contract.model.HireAbstractModel;
import contract.model.HireModel;

public class JdbcNewHireRepository implements NewHire {

	private Connection conn;
	private ComboOptions opts;
	private PreparedStatement INSERT_EMPLOYEE;
	private PreparedStatement SELECT_EMPLOYEE;
	private PreparedStatement UPDATE_EMPLOYEE;
	private PreparedStatement LIST_NEW_HIRES;
	private static final Logger logger = new ContractLogger(JdbcNewHireRepository.class.getName()).getLogger();
	
	public JdbcNewHireRepository(DataSource dataSource) throws SQLException{
		this.conn = dataSource.getConnection();
		INSERT_EMPLOYEE = conn.prepareStatement("INSERT INTO Hires VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
		SELECT_EMPLOYEE = conn.prepareStatement("SELECT * FROM Hires WHERE ID = ?");
		UPDATE_EMPLOYEE = conn.prepareStatement("UPDATE Hires SET contract_ref = ?, eeid = ?, surname = ?, "
				+"forename = ?, address_line1 = ?, address_line2 = ?, city = ?, postal_code = ?, country = ?, "
				+"line_manager = ?, line_manager_phone = ?, signatory_name_required = ?, signatory_name = ?, "
				+"position_number = ?, position_title = ?, work_contract = ?, contract_type = ?, job_grade = ?, "
				+"salary = ?, additional_wage_type1 = ?, additional_wage_typeAmount1 = ?, additional_wage_type2 = ?, "
				+"additional_wage_typeAmount2 = ?, business_area = ?, CTS = ?, competition_compliance = ?, "
				+"contract_start_date = ?, contract_end_date = ?, date_TBC = ?, hours_of_work = ?, "
				+"probation = ?, probation_duration = ?, sign_on_bonus = ?, sign_on_bonus_value = ?, "
				+"company_credit_card = ?, travel_supp = ?, travel_supp_date = ?, travel_supp_duration = ?, "
				+"travel_supp_amount = ?, pence_per_mile = ?, relocation = ?, relocation_amount = ?, "
				+"relocation_area = ?, personal_qualification = ?, mobile_phone = ?, professional_subs = ?, "
				+"company_car = ?, sharps = ?, next_salary_review = ?, employee_group = ?, reason_for_contract = ?, "
				+"location = ?, working_visa = ?, line_manager_position = ?, py_area = ?, mop_fse = ?, work_pattern = ? WHERE ID = ?");
		LIST_NEW_HIRES = conn.prepareStatement("SELECT ID, contract_ref, eeid, surname, forename, position_title, "
				+"contract_start_date FROM Hires");
	}

	
	//ADD WORK PATTERNS SUPPORT!!!!!!!!!!!!!!!!!!!!!!!
	
	/**
	 * Combo Option source setter.
	 * @param ComboOptions
	 */
	public void setOpts(ComboOptions opts) {
		this.opts = opts;
	}

	/**
	 * This method is responsible for adding new entry to database.
	 * 
	 * @param hire HireAbstractModel
	 * @return long Database ID of newly added entry
	 */
	@Override
	public long add(HireAbstractModel hire) throws EntityAlreadyExistsException {
		//check if entry is already in DB. If yes - throw exception
		try {
			Long cID = (long) 0;
			if(!hire.getDetail("ID").isEmpty()){
				cID = Long.parseLong(hire.getDetail("ID"), 10);
			} 
			HireAbstractModel entryExists = get(cID);
			if(entryExists != null){
				throw new EntityAlreadyExistsException();
			}
			
		} catch (NumberFormatException | NoSuchElementException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, e.getMessage());
			return 0;
		} catch (EntityNotFoundException e1){
		}
		
		try {
			//Fill prepared statement with data
			INSERT_EMPLOYEE.setString(1, hire.getDetail("contract_ref"));
			if(hire.getDetail("eeid").isEmpty()){
				INSERT_EMPLOYEE.setNull(2, Types.INTEGER);
			} else {
				INSERT_EMPLOYEE.setInt(2, Integer.parseInt(hire.getDetail("eeid")));
			}
			INSERT_EMPLOYEE.setString(3, hire.getDetail("surname"));
			INSERT_EMPLOYEE.setString(4, hire.getDetail("forenames"));
			INSERT_EMPLOYEE.setString(5, hire.getDetail("address_line1"));
			if(hire.getDetail("address_line2").isEmpty()){
				INSERT_EMPLOYEE.setNull(6, Types.VARCHAR);
			} else {
				INSERT_EMPLOYEE.setString(6, hire.getDetail("address_line2"));
			}
			INSERT_EMPLOYEE.setString(7,  hire.getDetail("city"));
			INSERT_EMPLOYEE.setString(8, hire.getDetail("postal_code"));
			try {
				INSERT_EMPLOYEE.setInt(9, opts.findID(opts.getCountries(), hire.getDetail("country")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				logger.log(Level.WARNING, "Couldn't find country element. Defaulted to: UK.");
				INSERT_EMPLOYEE.setInt(9, 2);
			}
			if(hire.getDetail("lm_name").isEmpty()){
				INSERT_EMPLOYEE.setNull(10, Types.VARCHAR);
			} else {
				INSERT_EMPLOYEE.setString(10, hire.getDetail("lm_name"));
			}
			if(hire.getDetail("lm_phone_no").isEmpty()){
				INSERT_EMPLOYEE.setNull(11, Types.VARCHAR);
			} else {
				INSERT_EMPLOYEE.setString(11, hire.getDetail("lm_phone_no"));
			}
			INSERT_EMPLOYEE.setString(12, hire.getDetail("signatory_name_req"));
			if(hire.getDetail("signatory_name").isEmpty()){
				INSERT_EMPLOYEE.setNull(13, Types.VARCHAR);
			} else {
				INSERT_EMPLOYEE.setString(13, hire.getDetail("signatory_name"));
			}
			INSERT_EMPLOYEE.setInt(14, Integer.parseInt(hire.getDetail("position_number")));
			INSERT_EMPLOYEE.setString(15, hire.getDetail("position_title"));
			try {
				INSERT_EMPLOYEE.setInt(16, opts.findID(opts.getWork_contracts(), hire.getDetail("work_contract")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				logger.log(Level.WARNING, "Couldn't find work contract element. Defaulted to: Full Time");
				INSERT_EMPLOYEE.setInt(16, 1);
			}
			try {
				INSERT_EMPLOYEE.setInt(17, opts.findID(opts.getContract_types(), hire.getDetail("contract_type")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				logger.log(Level.WARNING, "Couldn't find contract type element. Defaulted to: Permanent");
				INSERT_EMPLOYEE.setInt(17, 1);
			}
			INSERT_EMPLOYEE.setString(18, hire.getDetail("job_grade"));
			String[] salArr = hire.getDetail("salary").split(",");
			INSERT_EMPLOYEE.setFloat(19, Float.parseFloat(salArr[0] + salArr[1]));
			//add splitting string on comma
			if(hire.getDetail("addWageType1").isEmpty()){
				INSERT_EMPLOYEE.setNull(20, Types.VARCHAR);
			} else {
				INSERT_EMPLOYEE.setString(20, hire.getDetail("addWageType1"));
			}
			if(hire.getDetail("addWageTypeAmount1").isEmpty()){
				INSERT_EMPLOYEE.setNull(21, Types.FLOAT);
			} else {
				INSERT_EMPLOYEE.setFloat(21, Float.parseFloat(hire.getDetail("addWageTypeAmount1")));
			}
			if(hire.getDetail("addWageType2").isEmpty()){
				INSERT_EMPLOYEE.setNull(22, Types.VARCHAR);
			} else {
				INSERT_EMPLOYEE.setString(22, hire.getDetail("addWageType1"));
			}
			if(hire.getDetail("addWageTypeAmount2").isEmpty()){
				INSERT_EMPLOYEE.setNull(23, Types.FLOAT);
			} else {
				INSERT_EMPLOYEE.setFloat(23, Float.parseFloat(hire.getDetail("addWageTypeAmount1")));
			}
			INSERT_EMPLOYEE.setString(24, hire.getDetail("business_area"));
			INSERT_EMPLOYEE.setString(25, hire.getDetail("cts"));
			INSERT_EMPLOYEE.setString(26, hire.getDetail("competition_compliance"));
			
			//CAST DATE STRING TO ISO FORMAT AND ADD TO DB!!!
			if(!hire.getDetail("date_tbc").toLowerCase().equals("true")){
				try {
					INSERT_EMPLOYEE.setString(27, new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(hire.getDetail("start_date")).getTime()).toString());
				} catch (NoSuchElementException | ParseException e) {
					logger.log(Level.WARNING, "Could not convert start date string to date.");
					INSERT_EMPLOYEE.setNull(27, Types.DATE);
				}
			} else {
				INSERT_EMPLOYEE.setNull(27, Types.DATE);
			}
			if(!hire.getDetail("contract_end_date").isEmpty()){
				try {
					INSERT_EMPLOYEE.setString(28, new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(hire.getDetail("contract_end_date")).getTime()).toString());
				} catch (NoSuchElementException | ParseException e) {
					INSERT_EMPLOYEE.setNull(28, Types.DATE);
					logger.log(Level.WARNING, "Could not convert end date string to date.");
				}
			} else {
				INSERT_EMPLOYEE.setNull(28, Types.DATE);
			}
			INSERT_EMPLOYEE.setString(29, hire.getDetail("date_tbc"));
			INSERT_EMPLOYEE.setFloat(30, Float.parseFloat(hire.getDetail("hours_of_work")));
			INSERT_EMPLOYEE.setString(31, hire.getDetail("probation_period"));
			if(!hire.getDetail("duration_of_probation").isEmpty()){
				INSERT_EMPLOYEE.setInt(32, Integer.parseInt(hire.getDetail("duration_of_probation")));
			} else {
				INSERT_EMPLOYEE.setNull(32, Types.INTEGER);
			}
			INSERT_EMPLOYEE.setString(33, hire.getDetail("sign_on_bonus"));
			if(!hire.getDetail("sign_on_bonus_amount").isEmpty()){
				INSERT_EMPLOYEE.setFloat(34, Float.parseFloat(hire.getDetail("sign_on_bonus_amount")));
			} else {
				INSERT_EMPLOYEE.setNull(34, Types.FLOAT);
			}
			INSERT_EMPLOYEE.setString(35, hire.getDetail("company_credit_card"));
			INSERT_EMPLOYEE.setString(36, hire.getDetail("travel_supplement"));
			if(!hire.getDetail("travel_supplement_start").isEmpty()){
				try {
					INSERT_EMPLOYEE.setString(37, new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(hire.getDetail("travel_supplement_start")).getTime()).toString());
				} catch (NoSuchElementException | ParseException e) {
					INSERT_EMPLOYEE.setNull(37, Types.DATE);
					logger.log(Level.WARNING, "Could not convert travel supplement start date string to date format.");
				}
			} else {
				INSERT_EMPLOYEE.setNull(37, Types.DATE);
			}
			if(!hire.getDetail("travel_supplement_duration").isEmpty()){
				INSERT_EMPLOYEE.setInt(38, Integer.parseInt(hire.getDetail("travel_supplement_duration")));
			} else {
				INSERT_EMPLOYEE.setNull(38, Types.INTEGER);
			}
			if(!hire.getDetail("travel_supplement_amount").isEmpty()){
				INSERT_EMPLOYEE.setFloat(39, Float.parseFloat(hire.getDetail("travel_supplement_amount")));
			} else {
				INSERT_EMPLOYEE.setNull(39, Types.FLOAT);
			}
			if(!hire.getDetail("pence_per_mile").isEmpty()){
				INSERT_EMPLOYEE.setFloat(40, Float.parseFloat(hire.getDetail("pence_per_mile")));
			} else {
				INSERT_EMPLOYEE.setNull(40, Types.FLOAT);
			}
			INSERT_EMPLOYEE.setString(41, hire.getDetail("relocation"));
			if(!hire.getDetail("relocation_amount").isEmpty()){
				INSERT_EMPLOYEE.setFloat(42, Float.parseFloat(hire.getDetail("relocation_amount")));
			} else {
				INSERT_EMPLOYEE.setNull(42, Types.FLOAT);
			}
			if(!hire.getDetail("relocation_area").isEmpty()){
				INSERT_EMPLOYEE.setString(43, hire.getDetail("relocation_area"));
			} else {
				INSERT_EMPLOYEE.setNull(43, Types.VARCHAR);
			}
			INSERT_EMPLOYEE.setString(44, hire.getDetail("personal_qualification"));
			INSERT_EMPLOYEE.setString(45, hire.getDetail("mobile_phone"));
			INSERT_EMPLOYEE.setString(46, hire.getDetail("professional_subs"));
			try {
				INSERT_EMPLOYEE.setInt(47, opts.findID(opts.getCar_options(), hire.getDetail("company_car")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				INSERT_EMPLOYEE.setInt(47, 1);
				logger.log(Level.WARNING, "Couldn't find company car value. Used 'None' as default.");
			}
			
			if(!hire.getDetail("mcbc_sharps").isEmpty()){
				INSERT_EMPLOYEE.setString(48, hire.getDetail("mcbc_sharps"));
			} else {
				INSERT_EMPLOYEE.setNull(48, Types.VARCHAR);
			}
			
			INSERT_EMPLOYEE.setString(49, hire.getDetail("next_salary_review"));
			
			try {
				INSERT_EMPLOYEE.setInt(50, opts.findID(opts.getEe_groups(), hire.getDetail("non_negotiated")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				INSERT_EMPLOYEE.setInt(50, 1);
				logger.log(Level.WARNING, "Couldn't determine employee group value. Non-negotiated used instead.");
			}
			if(!hire.getDetail("reason_for_contract").isEmpty()){
				INSERT_EMPLOYEE.setString(51, hire.getDetail("reason_for_contract"));
			} else {
				INSERT_EMPLOYEE.setNull(51, Types.VARCHAR);
			}
			INSERT_EMPLOYEE.setString(52, hire.getDetail("location"));
			INSERT_EMPLOYEE.setString(53, hire.getDetail("working_visa_paragraph"));
			INSERT_EMPLOYEE.setString(54, hire.getDetail("lm_pos_title"));
			INSERT_EMPLOYEE.setString(55, hire.getDetail("payroll_area"));
			INSERT_EMPLOYEE.setString(56, hire.getDetail("mop_fse"));
			INSERT_EMPLOYEE.setInt(57, opts.findID(opts.getWork_patterns(), hire.getDetail("work_pattern")));
			
			//Execute SQL query
			INSERT_EMPLOYEE.executeUpdate();
			
			//Find ID of newly added entry
			ResultSet result = INSERT_EMPLOYEE.getGeneratedKeys();
			result.next();
			return result.getLong(1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, e.getMessage());
			return 0;
		}
	}

	@Override
	public HireAbstractModel get(long id) throws EntityNotFoundException {
		try {
			SELECT_EMPLOYEE.setLong(1, id);
			ResultSet result = SELECT_EMPLOYEE.executeQuery();
			if(result.next()){
				HireAbstractModel hireModel = new HireModel();
				hireModel.addField("contract_ref", result.getString("contract_ref"));
				hireModel.addField("ID", result.getString("ID"));
				if(result.getInt("eeid") != 0){
					hireModel.addField("eeid", new Integer(result.getInt("eeid")).toString());
				} else {
					hireModel.addField("eeid", "");
				}
				hireModel.addField("surname", result.getString("surname"));
				hireModel.addField("forenames", result.getString("forename"));
				hireModel.addField("address_line1", result.getString("address_line1"));
				hireModel.addField("address_line2", result.getString("address_line2"));
				hireModel.addField("city", result.getString("city"));
				hireModel.addField("postal_code", result.getString("postal_code"));
				try {
					hireModel.addField("country", opts.findName(opts.getCountries(), result.getInt("country")));
				} catch (ItemNotFoundException e) {
					logger.log(Level.WARNING, "Couldn't convert country value - used UK instead.");
					hireModel.addField("country", "UK");
				}
				hireModel.addField("lm_name", result.getString("line_manager"));
				hireModel.addField("lm_phone_no", result.getString("line_manager_phone"));
				hireModel.addField("signatory_name_req", new Boolean(result.getBoolean("signatory_name_required")).toString());
				hireModel.addField("lm_pos_title", result.getString("line_manager_position"));
				hireModel.addField("signatory_name", result.getString("signatory_name"));
				hireModel.addField("position_number", new Integer(result.getInt("position_number")).toString());
				hireModel.addField("position_title", result.getString("position_title"));
				try {
					hireModel.addField("work_contract", opts.findName(opts.getWork_contracts(), result.getInt("work_contract")));
				} catch (ItemNotFoundException e) {
					logger.log(Level.WARNING, "Couldn't convert work contract value. Used Full Time instead.");
					hireModel.addField("work_contract", "Full Time");
				}
				try {
					hireModel.addField("contract_type", opts.findName(opts.getContract_types(), result.getInt("contract_type")));
				} catch (ItemNotFoundException e) {
					logger.log(Level.WARNING, "Couldn't convert contract type value. Used Permanent instead.");
					hireModel.addField("contract_type", "Permanent");
				}
				hireModel.addField("job_grade", result.getString("job_grade"));
				hireModel.addField("salary", new Float(result.getFloat("salary")).toString());
				if(result.getString("additional_wage_type1") != null){
					hireModel.addField("addWageType1", result.getString("additional_wage_type1"));
				} else {
					hireModel.addField("addWageType1", "");
				}
				if(result.getFloat("additional_wage_typeAmount1") != 0.0){
					hireModel.addField("addWageTypeAmount1", new Float(result.getFloat("additional_wage_typeAmount1")).toString());
				} else {
					hireModel.addField("addWageType1", "");
				}
				if(result.getString("additional_wage_type1") != null){
					hireModel.addField("addWageType1", result.getString("additional_wage_type1"));
				} else {
					hireModel.addField("addWageType1", "");
				}
				if(result.getFloat("additional_wage_typeAmount2") != 0.0){
					hireModel.addField("addWageTypeAmount2", new Float(result.getFloat("additional_wage_typeAmount2")).toString());
				} else {
					hireModel.addField("addWageType2", "");
				}
				hireModel.addField("business_area", result.getString("business_area"));
				hireModel.addField("cts", new Boolean(result.getBoolean("CTS")).toString());
				hireModel.addField("competition_compliance", new Boolean(result.getBoolean("competition_compliance")).toString());
				if(result.getDate("contract_start_date") != null){
					hireModel.addField("start_date", new SimpleDateFormat("dd-MM-yyyy").format(result.getDate("contract_start_date")));
				} else {
					hireModel.addField("start_date", "");
				}
				if(result.getDate("contract_end_date") != null){
					hireModel.addField("contract_end_date", new SimpleDateFormat("dd-MM-yyyy").format(result.getDate("contract_end_date")));
				} else {
					hireModel.addField("contract_end_date", "");
				}
				hireModel.addField("date_tbc", new Boolean(result.getBoolean("date_TBC")).toString());
				hireModel.addField("hours_of_work", new Float(result.getFloat("hours_of_work")).toString());
				hireModel.addField("probation_period", new Boolean(result.getBoolean("probation")).toString());
				hireModel.addField("duration_of_probation", new Integer(result.getInt("probation_duration")).toString());
				hireModel.addField("sign_on_bonus", new Boolean(result.getBoolean("sign_on_bonus")).toString());
				hireModel.addField("sign_on_bonus_amount", new Float(result.getFloat("sign_on_bonus_value")).toString());
				hireModel.addField("company_credit_card", new Boolean(result.getBoolean("company_credit_card")).toString());
				hireModel.addField("travel_supplement", new Boolean(result.getBoolean("travel_supp")).toString());
				if(result.getDate("travel_supp_date") != null){
					hireModel.addField("travel_supplement_start", new SimpleDateFormat("dd-MM-yyyy").format(result.getDate("travel_supp_date")));
				} else {
					hireModel.addField("travel_supplement_start", "");
				}
				hireModel.addField("travel_supplement_duration", new Integer(result.getInt("travel_supp_duration")).toString());
				hireModel.addField("travel_supplement_amount", new Float(result.getFloat("travel_supp_amount")).toString());
				hireModel.addField("pence_per_mile", new Float(result.getFloat("pence_per_mile")).toString());
				hireModel.addField("relocation", new Boolean(result.getBoolean("relocation")).toString());
				hireModel.addField("relocation_amount", new Float(result.getFloat("relocation_amount")).toString());
				hireModel.addField("relocation_area", result.getString("relocation_area"));
				hireModel.addField("personal_qualification", new Boolean(result.getBoolean("personal_qualification")).toString());
				hireModel.addField("mobile_phone", new Boolean(result.getBoolean("mobile_phone")).toString());
				hireModel.addField("professional_subs", new Boolean(result.getBoolean("professional_subs")).toString());
				try{
					hireModel.addField("company_car", opts.findName(opts.getCar_options(), result.getInt("company_car")));
				} catch (ItemNotFoundException e){
					logger.log(Level.WARNING, "Failed to convert company car value. None used instead.");
					hireModel.addField("company_care", "None");
				}
				hireModel.addField("mcbc_sharps", result.getString("sharps"));
				hireModel.addField("next_salary_review", new SimpleDateFormat("dd-MM-yyyy").format(result.getDate("next_salary_review")));
				try {
					hireModel.addField("non_negotiated", opts.findName(opts.getEe_groups(), result.getInt("employee_group")));
				} catch (ItemNotFoundException e) {
					logger.log(Level.WARNING, "Failed to convert employee group value. Non-negotiated used instead.");
					hireModel.addField("non_negotiated", "Non-negotiated");
				}
				hireModel.addField("reason_for_contract", result.getString("reason_for_contract"));
				hireModel.addField("location", result.getString("location"));
				hireModel.addField("working_visa_paragraph", result.getString("working_visa"));
				hireModel.addField("payroll_area", result.getString("py_area"));
				hireModel.addField("mop_fse", result.getString("mop_fse"));
				try {
					hireModel.addField("work_pattern", opts.findName(opts.getWork_patterns(), result.getInt("work_pattern")));
				} catch (ItemNotFoundException e) {
					logger.log(Level.WARNING, "Couldn't convert work pattern value. Used blank instead.");
					hireModel.addField("work_pattern", " ");
				}
				
				return hireModel;
			} else {
				throw new EntityNotFoundException();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	@Override
	public boolean update(HireAbstractModel hire) throws EntityNotFoundException  {
		//check if entry is already in DB. If not - throw exception
		try {
			HireAbstractModel entryExists = get(Long.parseLong(hire.getDetail("ID"), 10));
			if(entryExists.equals(null)){
				throw new EntityNotFoundException();
			}
		} catch (NumberFormatException | NoSuchElementException | EntityNotFoundException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return false;
		}
		
		try {
			//Fill prepared statement with data
			UPDATE_EMPLOYEE.setString(1, hire.getDetail("contract_ref"));
			if(hire.getDetail("eeid").isEmpty()){
				UPDATE_EMPLOYEE.setNull(2, Types.INTEGER);
			} else {
				UPDATE_EMPLOYEE.setInt(2, Integer.parseInt(hire.getDetail("eeid")));
			}
			UPDATE_EMPLOYEE.setString(3, hire.getDetail("surname"));
			UPDATE_EMPLOYEE.setString(4, hire.getDetail("forenames"));
			UPDATE_EMPLOYEE.setString(5, hire.getDetail("address_line1"));
			if(hire.getDetail("address_line2").isEmpty()){
				UPDATE_EMPLOYEE.setNull(6, Types.VARCHAR);
			} else {
				UPDATE_EMPLOYEE.setString(6, hire.getDetail("address_line2"));
			}
			UPDATE_EMPLOYEE.setString(7,  hire.getDetail("city"));
			UPDATE_EMPLOYEE.setString(8, hire.getDetail("postal_code"));
			try {
				UPDATE_EMPLOYEE.setInt(9, opts.findID(opts.getCountries(), hire.getDetail("country")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				logger.log(Level.WARNING, "Couldn't find country element. Defaulted to: UK.");
				UPDATE_EMPLOYEE.setInt(9, 2);
			}
			if(hire.getDetail("lm_name").isEmpty()){
				UPDATE_EMPLOYEE.setNull(10, Types.VARCHAR);
			} else {
				UPDATE_EMPLOYEE.setString(10, hire.getDetail("lm_name"));
			}
			if(hire.getDetail("lm_phone_no").isEmpty()){
				UPDATE_EMPLOYEE.setNull(11, Types.VARCHAR);
			} else {
				UPDATE_EMPLOYEE.setString(11, hire.getDetail("lm_phone_no"));
			}
			UPDATE_EMPLOYEE.setString(12, hire.getDetail("signatory_name_req"));
			if(hire.getDetail("signatory_name").isEmpty()){
				UPDATE_EMPLOYEE.setNull(13, Types.VARCHAR);
			} else {
				UPDATE_EMPLOYEE.setString(13, hire.getDetail("signatory_name"));
			}
			UPDATE_EMPLOYEE.setInt(14, Integer.parseInt(hire.getDetail("position_number")));
			UPDATE_EMPLOYEE.setString(15, hire.getDetail("position_title"));
			try {
				UPDATE_EMPLOYEE.setInt(16, opts.findID(opts.getWork_contracts(), hire.getDetail("work_contract")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				logger.log(Level.WARNING, "Couldn't find work contract element. Defaulted to: Full Time");
				UPDATE_EMPLOYEE.setInt(16, 1);
			}
			try {
				UPDATE_EMPLOYEE.setInt(17, opts.findID(opts.getContract_types(), hire.getDetail("contract_type")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				logger.log(Level.WARNING, "Couldn't find contract type element. Defaulted to: Permanent");
				UPDATE_EMPLOYEE.setInt(17, 1);
			}
			UPDATE_EMPLOYEE.setString(18, hire.getDetail("job_grade"));
			if(hire.getDetail("salary").contains(",")){
				String[] salArr = hire.getDetail("salary").split(",");
				UPDATE_EMPLOYEE.setFloat(19, Float.parseFloat(salArr[0] + salArr[1]));
			} else {
				UPDATE_EMPLOYEE.setFloat(19, Float.parseFloat(hire.getDetail("salary")));
			}
			//add splitting string on comma
			if(hire.getDetail("addWageType1").isEmpty()){
				UPDATE_EMPLOYEE.setNull(20, Types.VARCHAR);
			} else {
				UPDATE_EMPLOYEE.setString(20, hire.getDetail("addWageType1"));
			}
			if(hire.getDetail("addWageTypeAmount1").isEmpty()){
				UPDATE_EMPLOYEE.setNull(21, Types.FLOAT);
			} else {
				UPDATE_EMPLOYEE.setFloat(21, Float.parseFloat(hire.getDetail("addWageTypeAmount1")));
			}
			if(hire.getDetail("addWageType2").isEmpty()){
				UPDATE_EMPLOYEE.setNull(22, Types.VARCHAR);
			} else {
				UPDATE_EMPLOYEE.setString(22, hire.getDetail("addWageType1"));
			}
			if(hire.getDetail("addWageTypeAmount2").isEmpty()){
				UPDATE_EMPLOYEE.setNull(23, Types.FLOAT);
			} else {
				UPDATE_EMPLOYEE.setFloat(23, Float.parseFloat(hire.getDetail("addWageTypeAmount1")));
			}
			UPDATE_EMPLOYEE.setString(24, hire.getDetail("business_area"));
			UPDATE_EMPLOYEE.setString(25, hire.getDetail("cts"));
			UPDATE_EMPLOYEE.setString(26, hire.getDetail("competition_compliance"));
			
			//CAST DATE STRING TO ISO FORMAT AND ADD TO DB!!!
			if(!hire.getDetail("date_tbc").toLowerCase().equals("true")){
				try {
					UPDATE_EMPLOYEE.setString(27, new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(hire.getDetail("start_date")).getTime()).toString());
				} catch (NoSuchElementException | ParseException e) {
					logger.log(Level.WARNING, "Could not convert start date string to date.");
					UPDATE_EMPLOYEE.setNull(27, Types.DATE);
				}
			} else {
				UPDATE_EMPLOYEE.setNull(27, Types.DATE);
			}
			if(!hire.getDetail("contract_end_date").isEmpty()){
				try {
					UPDATE_EMPLOYEE.setString(28, new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(hire.getDetail("contract_end_date")).getTime()).toString());
				} catch (NoSuchElementException | ParseException e) {
					UPDATE_EMPLOYEE.setNull(28, Types.DATE);
					logger.log(Level.WARNING, "Could not convert end date string to date.");
				}
			} else {
				UPDATE_EMPLOYEE.setNull(28, Types.DATE);
			}
			UPDATE_EMPLOYEE.setString(29, hire.getDetail("date_tbc"));
			UPDATE_EMPLOYEE.setFloat(30, Float.parseFloat(hire.getDetail("hours_of_work")));
			UPDATE_EMPLOYEE.setString(31, hire.getDetail("probation_period"));
			if(!hire.getDetail("duration_of_probation").isEmpty()){
				UPDATE_EMPLOYEE.setInt(32, Integer.parseInt(hire.getDetail("duration_of_probation")));
			} else {
				UPDATE_EMPLOYEE.setNull(32, Types.INTEGER);
			}
			UPDATE_EMPLOYEE.setString(33, hire.getDetail("sign_on_bonus"));
			if(!hire.getDetail("sign_on_bonus_amount").isEmpty()){
				UPDATE_EMPLOYEE.setFloat(34, Float.parseFloat(hire.getDetail("sign_on_bonus_amount")));
			} else {
				UPDATE_EMPLOYEE.setNull(34, Types.FLOAT);
			}
			UPDATE_EMPLOYEE.setString(35, hire.getDetail("company_credit_card"));
			UPDATE_EMPLOYEE.setString(36, hire.getDetail("travel_supplement"));
			if(!hire.getDetail("travel_supplement_start").isEmpty()){
				try {
					UPDATE_EMPLOYEE.setString(37, new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(hire.getDetail("travel_supplement_start")).getTime()).toString());
				} catch (NoSuchElementException | ParseException e) {
					UPDATE_EMPLOYEE.setNull(37, Types.DATE);
					logger.log(Level.WARNING, "Could not convert travel supplement start date string to date format.");
				}
			} else {
				UPDATE_EMPLOYEE.setNull(37, Types.DATE);
			}
			if(!hire.getDetail("travel_supplement_duration").isEmpty()){
				UPDATE_EMPLOYEE.setInt(38, Integer.parseInt(hire.getDetail("travel_supplement_duration")));
			} else {
				UPDATE_EMPLOYEE.setNull(38, Types.INTEGER);
			}
			if(!hire.getDetail("travel_supplement_amount").isEmpty()){
				UPDATE_EMPLOYEE.setFloat(39, Float.parseFloat(hire.getDetail("travel_supplement_amount")));
			} else {
				UPDATE_EMPLOYEE.setNull(39, Types.FLOAT);
			}
			if(!hire.getDetail("pence_per_mile").isEmpty()){
				UPDATE_EMPLOYEE.setFloat(40, Float.parseFloat(hire.getDetail("pence_per_mile")));
			} else {
				UPDATE_EMPLOYEE.setNull(40, Types.FLOAT);
			}
			UPDATE_EMPLOYEE.setString(41, hire.getDetail("relocation"));
			if(!hire.getDetail("relocation_amount").isEmpty()){
				UPDATE_EMPLOYEE.setFloat(42, Float.parseFloat(hire.getDetail("relocation_amount")));
			} else {
				UPDATE_EMPLOYEE.setNull(42, Types.FLOAT);
			}
			if(!hire.getDetail("relocation_area").isEmpty()){
				UPDATE_EMPLOYEE.setString(43, hire.getDetail("relocation_area"));
			} else {
				UPDATE_EMPLOYEE.setNull(43, Types.VARCHAR);
			}
			UPDATE_EMPLOYEE.setString(44, hire.getDetail("personal_qualification"));
			UPDATE_EMPLOYEE.setString(45, hire.getDetail("mobile_phone"));
			UPDATE_EMPLOYEE.setString(46, hire.getDetail("professional_subs"));
			try {
				UPDATE_EMPLOYEE.setInt(47, opts.findID(opts.getCar_options(), hire.getDetail("company_car")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				UPDATE_EMPLOYEE.setInt(47, 1);
				logger.log(Level.WARNING, "Couldn't find company car value. Used 'None' as default.");
			}
			
			if(!hire.getDetail("mcbc_sharps").isEmpty()){
				UPDATE_EMPLOYEE.setString(48, hire.getDetail("mcbc_sharps"));
			} else {
				UPDATE_EMPLOYEE.setNull(48, Types.VARCHAR);
			}
			
			UPDATE_EMPLOYEE.setString(49, hire.getDetail("next_salary_review"));
			
			try {
				UPDATE_EMPLOYEE.setInt(50, opts.findID(opts.getEe_groups(), hire.getDetail("non_negotiated")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				UPDATE_EMPLOYEE.setInt(50, 1);
				logger.log(Level.WARNING, "Couldn't determine employee group value. Non-negotiated used instead.");
			}
			if(!hire.getDetail("reason_for_contract").isEmpty()){
				UPDATE_EMPLOYEE.setString(51, hire.getDetail("reason_for_contract"));
			} else {
				UPDATE_EMPLOYEE.setNull(51, Types.VARCHAR);
			}
			UPDATE_EMPLOYEE.setString(52, hire.getDetail("location"));
			UPDATE_EMPLOYEE.setString(53, hire.getDetail("working_visa_paragraph"));
			UPDATE_EMPLOYEE.setString(54, hire.getDetail("lm_pos_title"));
			UPDATE_EMPLOYEE.setString(55, hire.getDetail("payroll_area"));
			UPDATE_EMPLOYEE.setString(56, hire.getDetail("mop_fse"));
			try {
				UPDATE_EMPLOYEE.setInt(57, opts.findID(opts.getWork_patterns(), hire.getDetail("work_pattern")));
			} catch (NoSuchElementException | ItemNotFoundException e) {
				UPDATE_EMPLOYEE.setInt(57, 1);
				logger.log(Level.WARNING, "Couldn't convert work pattern value. Used blank instead.");
			}
			UPDATE_EMPLOYEE.setLong(58, Long.parseLong(hire.getDetail("ID")));
			
			if(UPDATE_EMPLOYEE.executeUpdate() == 0){
				return false;
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method return array of new hire entries for table model.
	 * 
	 * @return HireEntry[] list of entries
	 */
	@Override
	public List<HireEntry> list(){
		try {
			ResultSet result = LIST_NEW_HIRES.executeQuery();
			List<HireEntry> entries = new ArrayList<HireEntry>();
			while(result.next()){
				entries.add(new HireEntry(result.getInt("ID"),
							result.getString("contract_ref"),
							result.getInt("eeid"),
							result.getString("surname"),
							result.getString("forename"),
							result.getString("position_title"),
							result.getDate("contract_start_date")));
			}
			return entries;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

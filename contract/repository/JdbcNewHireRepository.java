package contract.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import contract.logging.ContractLogger;
import contract.model.HireAbstractModel;
import contract.model.HireModel;

public class JdbcNewHireRepository implements NewHire {

	private Connection conn;
	private ComboOptions opts;
	private PreparedStatement INSERT_EMPLOYEE;
	private PreparedStatement SELECT_EMPLOYEE;
	private PreparedStatement UPDATE_EMPLOYEE;
	private static final Logger logger = new ContractLogger(JdbcNewHireRepository.class.getName()).getLogger();
	
	public JdbcNewHireRepository(DataSource dataSource) throws SQLException{
		this.conn = dataSource.getConnection();
		INSERT_EMPLOYEE = conn.prepareStatement("INSERT INTO Hires VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
		SELECT_EMPLOYEE = conn.prepareStatement("SELECT * FROM Hires WHERE ContractID = ?");
		UPDATE_EMPLOYEE = conn.prepareStatement("UPDATE Hires SET WHERE ID = ?");
	}

	
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
			
		} catch (NumberFormatException | NoSuchElementException | EntityNotFoundException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return 0;
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
			INSERT_EMPLOYEE.setInt(18, Integer.parseInt(hire.getDetail("job_grade")));
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
			if(!hire.getDetail("end_date").isEmpty()){
				try {
					INSERT_EMPLOYEE.setString(28, new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(hire.getDetail("end_date")).getTime()).toString());
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
				INSERT_EMPLOYEE.setFloat(34, Float.parseFloat(hire.getDetail("sign_on_bonus")));
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
				INSERT_EMPLOYEE.setFloat(39, Float.parseFloat(hire.getDetail("trvel_supplement_duration")));
			} else {
				INSERT_EMPLOYEE.setNull(39, Types.FLOAT);
			}
			if(!hire.getDetail("pence_per_mile").isEmpty()){
				INSERT_EMPLOYEE.setFloat(40, Float.parseFloat(hire.getDetail("pene_per_mile")));
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
			
			try {
				INSERT_EMPLOYEE.setString(49, new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(
						hire.getDetail("next_salary_review")).getTime()).toString());
			} catch (NoSuchElementException | ParseException e) {
				logger.log(Level.SEVERE, "Couldn't parse Next Slary Review date.");
				return 0;
			}
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
			
			//Execute SQL query
			INSERT_EMPLOYEE.executeUpdate();
			
			//Find ID of newly added entry
			ResultSet result = INSERT_EMPLOYEE.getGeneratedKeys();
			result.next();
			return result.getLong(1);
		} catch (Exception e) {
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
				//ADD NewHire Object fill
				
				return hireModel;
			} else {
				throw new EntityNotFoundException();
			}
		} catch (SQLException e) {
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
			if(UPDATE_EMPLOYEE.executeUpdate() == 0){
				return false;
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return false;
		}
		
		return true;
	}

}

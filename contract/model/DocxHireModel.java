package contract.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class DocxHireModel extends HireAbstractModel {
	private String filename;
	private List<XWPFTable> tables;
	
	public DocxHireModel(String filepath) throws FileNotFoundException, InvalidFormatException, IOException {
		super();
		this.filename = filepath;
		InputStream file = new FileInputStream(filename);
		XWPFDocument doc = new XWPFDocument(OPCPackage.open(file));
		tables = doc.getTables();
	}

	@Override
	public void readDataFromFile() {
		//Iterate through document elements to get data stored in rows.
		for(int tabCount = 0; tabCount<tables.size(); tabCount++){
			XWPFTable table = tables.get(tabCount);
			List<XWPFTableRow> rows = table.getRows();
			for(int rowCount = 0; rowCount<rows.size(); rowCount++){
				XWPFTableRow row = rows.get(rowCount);
				List<XWPFTableCell> cells = row.getTableCells();
				
				//Add model fields, that are not present on the source document
				addField("ID", "");
				addField("contract_ref", "");
				addField("eeid", "");
				addField("signatory_name_req", "");
				addField("signatory_name", "");
				addField("cts", "");
				addField("end_date", "");
				addField("date_tbc", "");
				addField("hours_of_work", "");
				addField("travel_supplement_start", "");
				addField("travel_supplement_duration", "");
				addField("pence_per_mile", "");
				addField("personal_qualification", "");
				addField("work_pattern", "");
				
				//add data from document, basing on which row we're in now.
				switch(rowCount){
					case 2: addField("start_date", filterTrailingSpaces(cells.get(1).getText()));
							break;
					case 3: addField("position_number", filterTrailingSpaces(cells.get(1).getText()));
							addField("position_title", filterTrailingSpaces(cells.get(3).getText()));
							break;
					case 4:	addField("work_contract", filterTrailingSpaces(cells.get(1).getText()));
							break;
					case 5:	addField("contract_type", filterTrailingSpaces(cells.get(1).getText()));
							addField("contract_end_date", filterTrailingSpaces(cells.get(3).getText()));
							break;
					case 6: 
							addField("job_grade", filterTrailingSpaces(cells.get(1).getText()));
							break;
					case 7: addField("mop_fse", filterTrailingSpaces(cells.get(1).getText()));
					break;		
					case 8: addField("mcbc_sharps", filterTrailingSpaces(cells.get(1).getText()));
							addField("business_area", filterTrailingSpaces(cells.get(3).getText()));
							break;
					case 11: addField("forenames", filterTrailingSpaces(cells.get(1).getText()));
							addField("address_line1", filterTrailingSpaces(cells.get(3).getText()));
							break;
					case 12: addField("surname", filterTrailingSpaces(cells.get(1).getText()));
							addField("address_line2", filterTrailingSpaces(cells.get(3).getText()));
							break;
					case 13: addField("city", filterTrailingSpaces(cells.get(3).getText()));
							break;
					case 14: addField("postal_code", filterTrailingSpaces(cells.get(3).getText()));
							break;	
					case 15: addField("country", filterTrailingSpaces(cells.get(3).getText()));
							break;
					case 20: addField("salary", getSalary(cells.get(3).getText()));
							if(cells.get(1).getText().contains("Four Weekly")){
								addField("payroll_area", "4W");
							} else if(cells.get(1).getText().contains("Monthly")) {
								addField("payroll_area", "MO");
							} else {	
								addField("payroll_area", "DE");
							}
							break;
					case 23: addField("addWageType1", filterTrailingSpaces(cells.get(0).getText()));
							addField("addWageTypeAmount1", getSalary(cells.get(1).getText()));
							addField("addWageType2", filterTrailingSpaces(cells.get(2).getText()));
							addField("addWageTypeAmount2", getSalary(cells.get(3).getText()));
							break;
					case 25: addField("lm_name", filterTrailingSpaces(cells.get(1).getText()));
							addField("lm_pos_title", filterTrailingSpaces(cells.get(3).getText()));
							break;
					case 26: addField("lm_phone_no", filterTrailingSpaces(cells.get(1).getText()));
							//Find if there's probation period
							if(filterTrailingSpaces(cells.get(3).getText()).equals("Yes")){
								addField("probation_period", "true");
							} else {
								addField("probation_period", "false");
							}
							break;
					case 27: 
							//Find if competition compliance paragraph is needed
							if(filterTrailingSpaces(cells.get(1).getText()).equals("Yes")){
								addField("competition_compliance", "true");
							} else {
								addField("competition_compliance", "false");
							}
							
							findProbationDuration(filterTrailingSpaces(cells.get(3).getText()));
							break;
					case 28: 
							//Find in there's a sign on bonus
							if(filterTrailingSpaces(cells.get(1).getText()).equals("Yes")){
								addField("sign_on_bonus", "true");
							} else {
								addField("sign_on_bonus", "false");
							}
							//Find in there's a company credit card
							if(filterTrailingSpaces(cells.get(3).getText()).equals("Yes")){
								addField("company_credit_card", "true");
							} else {
								addField("company_credit_card", "false");
							}
							break;
					case 29: addField("sign_on_bonus_amount", getSalary(cells.get(1).getText()));
							if(filterTrailingSpaces(cells.get(3).getText()).toLowerCase().equals("yes")){
								addField("travel_supplement", "true");
							} else {
								addField("travel_supplement", "false");
							}
							break;
					case 30: 
							//Find in there's a relocation
							if(filterTrailingSpaces(cells.get(1).getText()).equals("Yes")){
								addField("relocation", "true");
							} else {
								addField("relocation", "false");
							}
							addField("travel_supplement_amount", getSalary(cells.get(3).getText()));
							break;
					case 31: addField("relocation_amount", getSalary(cells.get(1).getText()));
							//Find correct company car value
							if(cells.get(3).getText().toLowerCase().contains("company car") && !cells.get(3).getText().toLowerCase().contains("cash")){
								addField("company_car", "Company Car");
							} else if (cells.get(3).getText().toLowerCase().contains("company van")){
								addField("company_car", "Company Van");
							} else if (cells.get(3).getText().toLowerCase().contains("cash allowance")){
								addField("company_car", "Company Car Cash Allowance");
							} else {
								addField("company_car", "None");
							}
					
							break;
					case 32: addField("relocation_area", filterTrailingSpaces(cells.get(1).getText()));
							if(filterTrailingSpaces(cells.get(3).getText()).toLowerCase().equals("yes")){
								addField("mobile_phone", "true");
							} else {
								addField("mobile_phone", "false");
							}
							break;
					case 33: 
							//Find if there are professional subscriptions
							if(filterTrailingSpaces(cells.get(3).getText()).equals("Yes")){
								addField("professional_subs", "true");
							} else {
								addField("professional_subs", "false");
							}
							break;
					case 35: addField("next_salary_review", filterTrailingSpaces(cells.get(1).getText()));
						break;
					case 36: addField("non_negotiated", filterTrailingSpaces(cells.get(1).getText()));
						break;
					case 37: addField("reason_for_contract", filterTrailingSpaces(cells.get(1).getText()));
						break;
					case 38: 
				
						addField("location", cells.get(1).getText().split(" ")[1]);
						break;
					case 39: 
							//Find if working visa paragraph is needed
							if(filterTrailingSpaces(cells.get(1).getText()).equals("No") || cells.get(1).getText().equals("")){
								addField("working_visa_paragraph", "false");
							} else {
								addField("working_visa_paragraph", "true");
							}
						break;	
				}
			}
		}

	}
	
	/**
	 * This method is parsing duration of probation input and cuts month value out of it, adding it to model.
	 * If it's empty, or no months are found, adds empty string to model.
	 * 
	 * @param String input
	 */
	private void findProbationDuration(String input){
		Pattern pat = Pattern.compile("\\d{1,}");
		Matcher match = pat.matcher(input);
		
		if(match.find()){
			addField("duration_of_probation", match.group(0));
		} else {
			addField("duration_of_probation", "");
		}
	}
}
;
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
				
				//add data from document, basing on which row we're in now.
				switch(rowCount){
					case 2: addField("start_date", cells.get(1).getText());
							break;
					case 3: addField("position_number", cells.get(1).getText());
							addField("position_title", cells.get(3).getText());
							break;
					case 4:	addField("work_contract", cells.get(1).getText());
							break;
					case 5:	addField("contract_type", cells.get(1).getText());
							addField("contract_end_date", cells.get(3).getText());
							break;
					case 6: addField("job_grade", cells.get(1).getText());
							break;
					case 8: addField("mcbc_sharps", cells.get(1).getText());
							addField("business_area", cells.get(3).getText());
							break;
					case 11: addField("forenames", cells.get(1).getText());
							addField("address_line1", cells.get(3).getText());
							break;
					case 12: addField("surname", cells.get(1).getText());
							addField("address_line2", cells.get(3).getText());
							break;
					case 13: addField("city", cells.get(3).getText());
							break;
					case 14: addField("postal_code", cells.get(3).getText());
							break;	
					case 15: 
							if(cells.get(3).getText().substring(cells.get(3).getText().length() - 1).equals(" ")){
								addField("country", cells.get(3).getText().substring(0, cells.get(3).getText().length()-1));
							} else {
								addField("country", cells.get(3).getText());
							}
							break;
					case 20: addField("salary", getSalary(cells.get(3).getText()));
							break;
					case 23: addField("addWageType1", cells.get(0).getText());
							addField("addWageTypeAmount1", getSalary(cells.get(1).getText()));
							addField("addWageType2", cells.get(2).getText());
							addField("addWageTypeAmount2", getSalary(cells.get(3).getText()));
							break;
					case 25: addField("lm_name", cells.get(1).getText());
							break;
					case 26: addField("lm_phone_no", cells.get(1).getText());
							//Find if there's probation period
							if(cells.get(3).getText().equals("Yes")){
								addField("probation_period", "true");
							} else {
								addField("probation_period", "false");
							}
							break;
					case 27: 
							//Find if competition compliance paragraph is needed
							if(cells.get(1).getText().equals("Yes")){
								addField("competition_compliance", "true");
							} else {
								addField("competition_compliance", "false");
							}
							addField("duration_of_probation", cells.get(3).getText());
							break;
					case 28: 
							//Find in there's a sign on bonus
							if(cells.get(1).getText().equals("Yes")){
								addField("sign_on_bonus", "true");
							} else {
								addField("sign_on_bonus", "false");
							}
							//Find in there's a company credit card
							if(cells.get(3).getText().equals("Yes")){
								addField("company_credit_card", "true");
							} else {
								addField("company_credit_card", "false");
							}
							break;
					case 29: addField("sign_on_bonus_amount", getSalary(cells.get(1).getText()));
							if(cells.get(3).getText().toLowerCase().equals("yes")){
								addField("travel_supplement", "true");
							} else {
								addField("travel_supplement", "false");
							}
							break;
					case 30: 
							//Find in there's a relocation
							if(cells.get(1).getText().equals("Yes")){
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
					case 32: addField("relocation_area", cells.get(1).getText());
							if(cells.get(3).getText().toLowerCase().equals("yes")){
								addField("mobile_phone", "true");
							} else {
								addField("mobile_phone", "false");
							}
							break;
					case 33: 
							//Find if there are professional subscriptions
							if(cells.get(1).getText().equals("Yes")){
								addField("professional_subs", "true");
							} else {
								addField("professional_subs", "false");
							}
							break;
					case 35: addField("next_salary_review", cells.get(1).getText());
						break;
					case 36: addField("non_negotiated", cells.get(1).getText());
						break;
					case 37: addField("reason_for_contract", cells.get(1).getText());
						break;
					case 38: 
				
						addField("location", cells.get(1).getText().split(" ")[1]);
						break;
					case 39: 
							//Find if working visa paragraph is needed
							if(cells.get(1).getText().equals("No") || cells.get(1).getText().equals("")){
								addField("working_visa_paragraph", "false");
							} else {
								addField("working_visa_paragraph", "true");
							}
						break;	
				}
			}
		}

	}
	
	private String getSalary(String salary){
		Pattern pattern = Pattern.compile("(\\d{0,3},{0,1}\\d{3}.{0,1}\\d{0,2})");
		Matcher match = pattern.matcher(salary);
		Boolean found = false;
		while (match.find()) {
			salary = match.group();
			found = true;
		}
		
		if(!found){
			return "";
		} else {
			return salary;
		}
	}

	@Override
	public void readDataFromForm() {
		// TODO Auto-generated method stub
		
	}

}
;
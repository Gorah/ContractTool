package contract.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
		for(int tabCount = 0; tabCount<tables.size(); tabCount++){
			XWPFTable table = tables.get(tabCount);
			List<XWPFTableRow> rows = table.getRows();
			for(int rowCount = 0; rowCount<rows.size(); rowCount++){
				XWPFTableRow row = rows.get(rowCount);
				List<XWPFTableCell> cells = row.getTableCells();
				
				switch(rowCount){
					case 2: addField("start_date", cells.get(1).getText());
							break;
					case 3: addField("position_number", cells.get(1).getText());
							addField("position_title", cells.get(3).getText());
							break;
					case 4:	addField("work_contract", cells.get(1).getText());
							addField("work_schedule", cells.get(3).getText());
							break;
					case 5:	addField("contract_type", cells.get(1).getText());
							addField("contract_end_date", cells.get(3).getText());
							break;
					case 6: addField("job_grade", cells.get(1).getText());
							addField("hrbp", cells.get(3).getText());
							break;
					case 7: addField("notice_period", cells.get(1).getText());
							break;
					case 8: addField("mcbc_sharps", cells.get(1).getText());
							addField("business_area", cells.get(3).getText());
							break;
					case 10: addField("title", cells.get(1).getText());
							addField("known_as", cells.get(3).getText());
							break;
					case 11: addField("forenames", cells.get(1).getText());
							addField("address_line1", cells.get(3).getText());
							break;
					case 12: addField("surname", cells.get(1).getText());
							addField("address_line2", cells.get(3).getText());
							break;
					case 13: addField("national_id", cells.get(1).getText());
							addField("city", cells.get(3).getText());
							break;
					case 14: addField("telephone_no", cells.get(1).getText());
							addField("postal_code", cells.get(3).getText());
							break;
					case 15: addField("date_of_birth", cells.get(1).getText());
							addField("country", cells.get(3).getText());
							break;
					case 16: addField("gender", cells.get(1).getText());
							addField("marital_status", cells.get(3).getText());
							break;
					case 17: addField("id_check", cells.get(1).getText());
							break;
					case 18: addField("personal_email", cells.get(1).getText());
							break;
					case 20: addField("wage_type", cells.get(1).getText());
							addField("salary", cells.get(3).getText());
							break;
					case 23: addField("addWagType1", cells.get(0).getText());
							addField("addWageTypeAmount1", cells.get(1).getText());
							addField("addWageType2", cells.get(2).getText());
							addField("addWageTypeAmount2", cells.get(3).getText());
							break;
					case 25: addField("lm_name", cells.get(1).getText());
							addField("lm_job_title", cells.get(3).getText());
							break;
					case 26: addField("lm_phone_no", cells.get(1).getText());
							addField("probation_period", cells.get(3).getText());
							break;
					case 27: addField("competition_compliance", cells.get(1).getText());
							addField("duration_of_probation", cells.get(3).getText());
							break;
					case 28: addField("sign_on_bonus", cells.get(1).getText());
							addField("company_credit_card", cells.get(3).getText());
							break;
					case 29: addField("sign_on_bonus_amount", cells.get(1).getText());
							addField("travel_supplement", cells.get(3).getText());
							break;
					case 30: addField("relocation", cells.get(1).getText());
							addField("travel_supplement_amount", cells.get(3).getText());
							break;
					case 31: addField("relocation_amount", cells.get(1).getText());
							addField("company_car", cells.get(3).getText());
							break;
					case 32: addField("relocation_area", cells.get(1).getText());
							addField("mobile_phone", cells.get(3).getText());
							break;
					case 33: addField("salary_post_trial", cells.get(1).getText());
							addField("professional_subs", cells.get(3).getText());
							break;
					case 34: addField("transferred_in_serv_date", cells.get(1).getText());
						break;
					case 35: addField("next_salary_review", cells.get(1).getText());
						break;
					case 36: addField("non_negotiated", cells.get(1).getText());
						break;
					case 37: addField("reason_for_contract", cells.get(1).getText());
						break;
					case 38: addField("location", cells.get(1).getText());
						break;
					case 39: addField("working_visa_paragraph", cells.get(1).getText());
						break;	
				}
			}
		}

	}

	@Override
	public void readDataFromForm() {
		// TODO Auto-generated method stub
		
	}

}
;
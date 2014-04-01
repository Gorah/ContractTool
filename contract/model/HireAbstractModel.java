package contract.model;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * HireAbstractModel is an abstract class used to template new hire data models.
 * @author Bartosz Kratochwil (bartosz.kratochwil@hp.com)
 * @version 1.0
 */

public abstract class HireAbstractModel {
	private Map<String, String> hireDetails = new HashMap<String, String>();
	
	/**
	 * Constructor for the class. Populates hashmap with fields corresponding to hire data sheet.
	 * @version: 1.0  
	 */
	public HireAbstractModel() {
		hireDetails.put("start_date", "");
		hireDetails.put("position_number", "");
		hireDetails.put("position_title", "");
		hireDetails.put("work_contract", "");
		hireDetails.put("work_schedule", "");
		hireDetails.put("contract_type", "");
		hireDetails.put("contract_end_date", "");
		hireDetails.put("job_grade", "");
		hireDetails.put("hrbp", "");
		hireDetails.put("notice_period", "");
		hireDetails.put("mcbc_sharps", "");
		hireDetails.put("business_area", "");
		hireDetails.put("title", "");
		hireDetails.put("known_as", "");
		hireDetails.put("forenames", "");
		hireDetails.put("surname", "");
		hireDetails.put("address_line1", "");
		hireDetails.put("address_line2", "");
		hireDetails.put("national_id", "");
		hireDetails.put("city", "");
		hireDetails.put("telephone_no", "");
		hireDetails.put("Postal Code", "");
		hireDetails.put("date_of_birth", "");
		hireDetails.put("country", "");
		hireDetails.put("gender", "");
		hireDetails.put("marital_status", "");
		hireDetails.put("id_check", "");
		hireDetails.put("personal_email", "");
		hireDetails.put("wage_type", "");
		hireDetails.put("salary", "");
		hireDetails.put("addWageType1", ""); //needs separate function to fill
		hireDetails.put("addWageTypeAmount1", ""); //needs separate function to fill
		hireDetails.put("addWageType2", ""); //needs separate function to fill
		hireDetails.put("addWageTypeAmount2", ""); //needs separate function to fill
		hireDetails.put("lm_name", "");
		hireDetails.put("lm_job_title", "");
		hireDetails.put("lm_phone_no", "");
		hireDetails.put("probation_period", "");
		hireDetails.put("competition_compliance", "");
		hireDetails.put("duration_of_probation", "");
		hireDetails.put("signon_bonus?", "");
		hireDetails.put("signon_bonus_amount", "");
		hireDetails.put("company_credit_card", "");
		hireDetails.put("travel_supplement", "");
		hireDetails.put("relocation", "");
		hireDetails.put("travel_supplement_amount", "");
		hireDetails.put("relocation_amount", "");
		hireDetails.put("company_Car", "");
		hireDetails.put("relocation_rea", "");
		hireDetails.put("mobile_phone", "");
		hireDetails.put("salary_post_trial", "");
		hireDetails.put("professional_subs", "");
		hireDetails.put("transferred_in_serv_date", "");
		hireDetails.put("next_salary_review", "");
		hireDetails.put("non_negotiated", "");
		hireDetails.put("reason_for_contract", "");
		hireDetails.put("location", "");
		hireDetails.put("working_visa_paragraph", "");
		hireDetails.put("new_hire_pack", "");
		hireDetails.put("name", "");
		hireDetails.put("phone_no", "");
		hireDetails.put("date", "");
	}
	
	
	/**
	 * Getter for selected detail by the key from the class.
	 * Throws exception if key is not found in the hashmap.
	 * @param key String key value
	 * @return String value
	 */
	public String getDetail(String key) throws NoSuchElementException {
		if (hireDetails.containsKey(key)){
			return hireDetails.get(key);
		} else {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * readData method stub.
	 */
	public abstract void readData();

}

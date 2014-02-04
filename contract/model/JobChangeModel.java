package contract.model;

import java.util.HashMap;
import java.util.Map;

/**
 * JobChangeModel is a class holding model for Job Change data.
 * Data is stored in Hash Map containing string key and object type 
 * values (those usually are limited to Integer and String).
 * 
 * @author Bartosz Kratochwil (bartosz.kratochwil@hp.com)
 * @version 0.4
 *
 */

public class JobChangeModel {
	private Map<String, Object> details = new HashMap<String, Object>();
	
	/**
	 * Constructor for the class. Fills hash map with key value pairs 
	 * representing all the details provided with the Job Change PCR in SAP.
	 * 
	 * @param data List of string values representing entries in PCR.
	 * @version 0.7
	 */
	public JobChangeModel(String[] data){
		inputStringData(data[1], "Name of Employee", 17);
		inputIntData(data[2], "Personnel Number", 17);
		inputStringData(data[3], "Personnel Area", 15);
		inputStringData(data[4], "Position", 9);
		inputStringData(data[6], "Effective Date", 15);
		inputStringData(data[7], "Personnel Action", 17);
		inputStringData(data[8], "Organizational Unit in Header", 30);
		inputStringData(data[9], "Reason for Action", 18);
		inputStringData(data[10], "Position_Curr", 14);
		inputStringData(data[11], "Position_New", 13);
		inputStringData(data[12], "Organizational Unit_Curr", 25);
		inputStringData(data[13], "Organizational Unit_New", 24);
		inputStringData(data[14], "Job_Curr", 9);
		inputStringData(data[15], "Job_New", 8);
		inputIntData(data[16], "New Company Code", 17);
		inputStringData(data[20], "Personnel Area_Curr", 20);
		inputStringData(data[21], "Personnel Area_New", 19);
		inputStringData(data[22], "Employee Group_Curr", 20);
		inputStringData(data[23], "Employee Group_New", 19);
		inputStringData(data[24], "Employee Subgroup_Curr", 23);
		inputStringData(data[25], "Employee Subgroup_New", 22);
		inputStringData(data[26], "Personnel Subarea_Curr", 22);
		inputStringData(data[27], "Personnel Subarea_New", 21);
		inputStringData(data[28], "Work Schedule Rule_Curr", 24);
		inputStringData(data[29], "Work Schedule Rule_New", 23);
		inputStringData(data[30], "Reason_Curr", 12);
		inputStringData(data[31], "Reason_New", 11);
		inputStringData(data[32], "Next Increase_Curr", 19);
		inputStringData(data[33], "Next Increase_New", 18);
		inputStringData(data[34], "Salary Type_Curr", 17);
		inputStringData(data[35], "Salary Type_New", 16);
		inputStringData(data[36], "Amount_Curr", 12);
		inputStringData(data[37], "Amount_New", 11);
		inputStringData(data[60], "Contract Type", 14);
		inputStringData(data[61], "Contract Type_New", 18);
		inputStringData(data[62], "Contract End Date", 18);
		inputStringData(data[63], "Contract End Date_New", 22);
		inputStringData(data[73], "GGS_Curr", 30);
		inputStringData(data[75], "GGS_New", 30);
		
	}
	
	/**
	 * Getter for details attribute.
	 * @return Returns Map of String keys and Object values.
	 */
	public Map<String, Object> getDetails(){
		return details;
	}
	
	/**
	 * Setter for details attribute. It takes string with PCR entry line 
	 * and substrings only desired details out of it. Value is set as String.
	 * 
	 * @param data String containing one line of PCR text.
	 * @param key String with key under which data will be added to a map.
	 * @param offset int value pointing where desired detail starts within PCR entry string.
	 */
	private void inputStringData(String data, String key, int offset){
		if(data.length() > offset){
			details.put(key, data.substring(offset, data.length()-1));
		} else {
			details.put(key, "");
		}
	}
	
	/**
	 * Setter for details attribute. It takes string with PCR entry line 
	 * and substrings only desired details out of it. Value is converted 
	 * to Integer type.
	 * 
	 * @param data String containing one line of PCR text.
	 * @param key String with key under which data will be added to a map.
	 * @param offset int value pointing where desired detail starts within PCR entry string.
	 */
	private void inputIntData(String data, String key, int offset){
		if(data.length() > offset){
			details.put(key, Integer.parseInt(data.substring(offset, data.length()-1)));
		} else {
			details.put(key, "");
		}
	}
	
	/**
	 * Getter returning value by key from details attribute.
	 * @param key
	 * @return Object value found under provided key (Integer or String).
	 */
	public Object getVal(String key){
		return details.get(key);
	}

}

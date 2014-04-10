package contract.model;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HireAbstractModel is an abstract class used to template new hire data models.
 * @author Bartosz Kratochwil (bartosz.kratochwil@hp.com)
 * @version 1.0
 */

public abstract class HireAbstractModel {
	private Map<String, String> hireDetails = new HashMap<String, String>();
	
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
	 * Getter for details map.
	 * 
	 * @return Map of String key->value pairs.
	 */
	public Map<String, String> getHireDetails(){
		return hireDetails;
	}
	
	
	/**
	 * This method adds/updates a key in hire details map.
	 * @param key String key identifier.
	 * @param val String value.
	 */
	public void addField(String key, String val) {
		this.hireDetails.put(key, val);
	}

	
	/**
	 * This method extracts salary from the string, using provided regex format.
	 * 
	 * @param salary String
	 * @return String
	 */
	protected String getSalary(String salary){
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
	
	/**
	 * This method removes trailing spaces from the end of the string.
	 * 
	 * @param text String
	 * @return String
	 */
	protected String filterTrailingSpaces(String text){
		String result = text.replaceAll("\\s+$", "");
		return result;
	}

	/**
	 * readData method stub.
	 */
	public abstract void readDataFromFile();
	
	/**
	 * Reads data from form fields and puts them in the model
	 */
	public abstract void readDataFromForm();

}

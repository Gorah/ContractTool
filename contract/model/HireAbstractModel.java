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
	 * This method adds/updates a key in hire details map.
	 * @param key String key identifier.
	 * @param val String value.
	 */
	public void addField(String key, String val) {
		this.hireDetails.put(key, val);
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

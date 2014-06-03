package contract.model;

/**
 * This is user Type class
 * 
 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
 * @version 1.0
 *
 */
public class User {

	private String usr_name;
	private String initials;
	
	public User(String usr_name) {
		this.usr_name = usr_name;
		
		String[] split_name = usr_name.split("\\.");
		//Get initials from user name
		String inits = String.valueOf(split_name[0].toCharArray()[0]) + String.valueOf(split_name[1].toCharArray()[0]);
		this.initials = inits.toUpperCase();
	}

	/**
	 * User name getter
	 * 
	 * @return String user name.
	 */
	public String getUsr_name() {
		return usr_name;
	}

	
	/**
	 * Initials getter
	 * 
	 * @return String initials
	 */
	public String getInitials() {
		return initials;
	}
	
	
	

}

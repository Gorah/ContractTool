package contract.model;

/**
 * ComboItem is a class defining ComboItem type used to represent id - value pairs
 * found in database helper tables and make them compatible with combo box.
 * 
 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
 * @version 1.0
 *
 */

public class ComboItem {
	private int id;
	private String label;

	/**
	 * Constructor for class.
	 * 
	 * @param id int
	 * @param label String
	 */
	public ComboItem(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	/**
	 * toString is overriden to return text from label field.
	 * 
	 * @return String label text
	 */
	@Override
	public String toString() {
		return this.label;
	}

}


package contract.repository;

import contract.model.ComboItem;

/**
 * This is interface for combo box items classes.
 * 
 * @author Bartosz Kratochwil (bartosz.lratochwil@hp.com)
 *
 */
public interface ComboOptions {

	ComboItem[] getWork_contracts();
	ComboItem[] getContract_types();
	ComboItem[] getCountries();
	ComboItem[] getCar_options();
	ComboItem[] getEe_groups();
	int findID(ComboItem[] items, String name) throws ItemNotFoundException;
	String findName(ComboItem[] items, int id) throws ItemNotFoundException;
}

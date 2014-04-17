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

}

package contract.gui.view;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import contract.controller.AppController;

/**
 * AbstractView is an abstract class templating all the properties 
 * and methods necessary for a successful view that can be used 
 * with Contract Tool app.
 * 
 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
 * @version 1.0
 *
 */
public abstract class AbstractView {
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected AppController appController;
	protected Name name;
	protected Map<String, String> model = new HashMap<>();
	
	/**
	 * Name is an enum containing all the possible template names for the app.
	 * Newly created views should be registered here before they can be used.
	 */
	public enum Name {
		MAIN, OPEN_CONTRACT, NEW_HIRE
	}
	
	/**
	 * Only constructor for the class.
	 * @param name Enumed Name value specifying which view this instance represents.
	 */
	public AbstractView(Name name) {
		this.name = name;
	}
	
	/**
	 * getModel is used to return model.
	 * @return Map of String view name and model object.
	 */
	public Map<String, String> getModel() {
		return model;
	}

	/**
	 * Sets model for this instance.
	 * @param model Map containing String view name and the model Object.
	 */
	public void setModel(Map<String, String> model) {
		this.model = model;
	}
	
	/**
	 * Returns name of the View.
	 * @return Enum name value.
	 */
	public Name getName() {
		return name;
	}

	/**
	 * This method sets controller for view.
	 * @param appController AppControlles controller instance.
	 */
	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	public abstract void extractDataFromModel();
	
	/**
	 * This is rendering method stub.
	 */
	public abstract void render();
}

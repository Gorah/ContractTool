package contract.controller;
import java.util.ArrayList;
import java.util.List;

import contract.ContractTool;
import contract.gui.view.AbstractView;
import contract.gui.view.AbstractView.Name;

/**
 * AppController controls the flow of the application. Here views are 
 * registered and fetched. Here are the calls to render specific views 
 * stored and all the hooks are present.
 * Here middle ware layer is stored.
 * 
 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
 * @version 1.0
 */
public class AppController {
	private List<AbstractView> views = new ArrayList<>();
	private ContractTool contractTool;
	
	/**
	 * Only constructor for the class.
	 *
	 * @param tool	Instance of ContractTool class, which provides communication
	 * 				with data layer.
	 * @see contract.ContractTool
	 */
	public AppController(ContractTool tool){
		this.contractTool = tool;
	}
	
	/**
	 * Registers provided views within the class.
	 * 
	 * @param views	list of views extending AbstractView.
	 * @see contract.gui.view.AbstractView
	 */
	public void addViews(AbstractView ... views) {
		for (AbstractView view : views) {
			view.setAppController(this);
			this.views.add(view);
		}
	}
	
	/**
	 * Method returning an instance of specified view.
	 * 
	 * @param name enum with view name
	 * @return returns view instance that extends AbstractView
	 * @see contract.gui.view.AbstractView
	 */
	private AbstractView getView(Name name) {
		for (AbstractView view : views) {
			if (view.getName().equals(name)) {
				return view;
			}
		}
		throw new IllegalStateException("View " + name + " not found");
	}
	
	
	/**
	 * Initialisation method for app. Fires rendering of main view.
	 */
	public void init() {
		showMainView();
	}

	/**
	 * Method fetching and rendering Main view.
	 */
	public void showMainView() {
		getView(Name.MAIN).render();
	}
	
	
	/**
	 * Method fetching and rendering New Contract view.
	 */
	public void showNewHire(){
		getView(Name.NEW_HIRE).render();
	}
	
	/**
	 * Method fetching and rendering Open Contract view.
	 */
	public void showOpenContract(){
		getView(Name.OPEN_CONTRACT).render();
	}
	
	/**
	 * Method closing app.
	 */
	public void exit() {
		System.exit(0);
	}
}

package contract.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import contract.ContractTool;
import contract.gui.view.AbstractView;
import contract.gui.view.AbstractView.Name;
import contract.logging.ContractLogger;
import contract.model.DocxHireModel;
import contract.model.HireModel;
import contract.repository.EntityAlreadyExistsException;

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
	private Logger logger = new ContractLogger(AppController.class.getName()).getLogger();
	
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
	 * Method handling reading the file and adding data to fields
	 * 
	 * @param String file name
	 */
	public void readFile(String fileName){
		//get the source file extension 
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1).trim();
		//basing on file extension create appropriate model object
		if(ext.toLowerCase().contains("docx")){
			try {
				DocxHireModel nhData = new DocxHireModel(fileName);
				//read file data and load it to model
				nhData.readDataFromFile();
				//assign model to the form
				getView(Name.NEW_HIRE).setModel(nhData.getHireDetails());
				//populate form fields taking data from model
				getView(Name.NEW_HIRE).extractDataFromModel();
			} catch (InvalidFormatException	| IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method handles saving new hire data to data source
	 */
	public void saveNewHire(){
		//if form verification passed, save the data
		if(getView(Name.NEW_HIRE).verifyForm()){
			try {
				//build model from form data
				getView(Name.NEW_HIRE).saveDataToModel();
				//execute save and set ID of the record in the ID field of the form
				getView(Name.NEW_HIRE).setID((this.contractTool.getNewHireRepository().add(new HireModel(getView(Name.NEW_HIRE).getModel()))));;
			} catch (EntityAlreadyExistsException e) {
				logger.log(Level.WARNING, "Couldn't save hire - entity already exists in DB.");
				System.out.println("entry already exists");
			}
		}
	}
		
	/**
	 * Method closing app.
	 */
	public void exit() {
		System.exit(0);
	}
}

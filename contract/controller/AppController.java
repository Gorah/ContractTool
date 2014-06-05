package contract.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.ezware.dialog.task.TaskDialogs;

import word._Application;
import com4j.Variant;
import contract.ContractTool;
import contract.gui.view.AbstractView;
import contract.gui.view.AbstractView.Name;
import contract.gui.view.swing.LoginForm;
import contract.gui.view.swing.Main;
import contract.gui.view.swing.NewHire;
import contract.logging.ContractLogger;
import contract.model.DocxHireModel;
import contract.model.HireAbstractModel;
import contract.model.HireModel;
import contract.model.User;
import contract.repository.EntityAlreadyExistsException;
import contract.repository.EntityNotFoundException;

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
	private LoginForm loginForm;
	
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
	 * Setter for login form
	 * @param LoginForm loginForm
	 */
	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
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
		NewHire nhf = (NewHire)getView(Name.NEW_HIRE);
		nhf.render();
		nhf.genRefID();
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
				NewHire nh = (NewHire) getView(Name.NEW_HIRE);
				nhData.addField("contract_ref", nh.getRefID());
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
			//check if entry with this ID exists already if not, add new one. Otherwise update entry contents.
			try {
				//build model from form data
				getView(Name.NEW_HIRE).saveDataToModel();
				//execute save and set ID of the record in the ID field of the form
				getView(Name.NEW_HIRE).setID((this.contractTool.getNewHireRepository().add(new HireModel(getView(Name.NEW_HIRE).getModel()))));;
			} catch (EntityAlreadyExistsException e) {
				try {
					this.contractTool.getNewHireRepository().update(new HireModel(getView(Name.NEW_HIRE).getModel()));
				} catch (EntityNotFoundException e1) {
					logger.log(Level.SEVERE, "Could not save data!");
				}
			}
		}
	}
	
	/**
	 * This method opens hire data in form
	 * @param long id of the record
	 */
	public void open_newHire_from_list(long id){
		HireAbstractModel hireModel;
		try {
			//build a model from DB data
			hireModel = this.contractTool.getNewHireRepository().get(id);
			Main mainvw = (Main) getView(Name.MAIN);
			mainvw.buildMenuForNewHireForm();
			mainvw.buildEditMenuForNewHire();
			getView(Name.NEW_HIRE).setModel(hireModel.getHireDetails());
			//populate form fields taking data from model
			getView(Name.NEW_HIRE).render();
			getView(Name.NEW_HIRE).extractDataFromModel();
			
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * This method opens MS Word template and runs macro to create and save document.
	 * 
	 * @param int id - id number of DB entry for which contract is being made
	 */
	public void run_newHire_contract_gen(int id){
		try {
			_Application app = word.ClassFactory.createApplication();
			
	    	app.documents().open(System.getProperty("user.dir") + "\\src\\template.docm", Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), 
	    			Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), 
	    			Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing());
	    	app.visible(true);
	    	app.run("run", id, Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), 
	    			Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), 
	    			Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), 
	    			Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), 
	    			Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), 
	    			Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing(), Variant.getMissing());
	    	app.documents().close(Variant.getMissing(), Variant.getMissing(), Variant.getMissing());
	    	app.quit(Variant.getMissing(), Variant.getMissing(), Variant.getMissing());
		} catch (Exception | Error e){
			System.out.println(e.getMessage());
			TaskDialogs.error(new JFrame(), "Error", e.getMessage());
		}
	}
	
	/**
	 * This method is used to verify user credentials and logging in.
	 * 
	 * @param String user
	 * @param String password
	 */
	public void validate_login(String user, String password){
		//validate user credentials
		String result = this.contractTool.getLoginRepo().validate_user(user, password);
		
		//depending on result of validation
		if(result.equals("success")){
			//log in and show main window
			this.contractTool.setUsr(new User(user));
			init();
		} else if (result.equals("Wrong user name or password!")) {
			//give warning about wrong name or pass
			loginForm.showWrongCreds();
			loginForm.showDialog();
		} else {
			//give warning about user being disabled
			loginForm.showUserDisabled();
			loginForm.showDialog();
		}
	}
	
	
	/**
	 * Contract Tool object getter	
	 * @return Contract Tool object
	 */
	public ContractTool getContractTool() {
		return contractTool;
	}

	/**
	 * Method closing app.
	 */
	public void exit() {
		System.exit(0);
	}
}

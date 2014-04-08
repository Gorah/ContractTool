package contract.controller;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import contract.ContractTool;
import contract.gui.view.AbstractView;
import contract.gui.view.AbstractView.Name;
import contract.gui.view.swing.NewHire;
import contract.model.DocxHireModel;

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
		//readFile("C:\\Sean_O'Carroll.docx");
	}
	
	/**
	 * Method fetching and rendering Open Contract view.
	 */
	public void showOpenContract(){
		getView(Name.OPEN_CONTRACT).render();
	}
	
	public void readFile(String fileName){
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1).trim();
		if(ext.toLowerCase().contains("docx")){
			try {
				DocxHireModel nhData = new DocxHireModel(fileName);
				nhData.readDataFromFile();
				populateNewHireForm(nhData);
			} catch (InvalidFormatException	| IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void populateNewHireForm(DocxHireModel data){
		//get reference to new hire form
		NewHire nhForm = (NewHire) views.get(2);
		//Fill out form fields with data
		nhForm.name.setText(data.getDetail("forenames"));
		nhForm.lName.setText(data.getDetail("surname"));
		nhForm.addressLine1.setText(data.getDetail("address_line1"));
		nhForm.addressLine2.setText(data.getDetail("address_line2"));
		nhForm.city.setText(data.getDetail("city"));
		nhForm.postalCode.setText(data.getDetail("postal_code"));
		//Translate string value to integer index position for Combo Box
		int idx = 0;
		if(data.getDetail("country").equals("UK")){
			idx = 1;
		} else if(data.getDetail("country").equals("Scotland")) {
			idx = 2;
		} else if(data.getDetail("country").equals("Ireland")){
			idx = 3;
		}
		nhForm.country.setSelectedIndex(idx);
		nhForm.job.setText(data.getDetail("position_title"));
		nhForm.jobN.setText(data.getDetail("position_number"));
		nhForm.location.setText(data.getDetail("location"));
		nhForm.bu.setText(data.getDetail("business_area"));
		//Translate string value to integer index position for Combo Box
		
		if(data.getDetail("contract_type").equals("Permanent")){
			nhForm.conType.setSelectedIndex(0);
		} else {
			nhForm.conType.setSelectedIndex(1);
		}
		//Translate string value to integer index position for Combo Box
		if(data.getDetail("work_contract").equals("Full Time")){
			nhForm.workContract.setSelectedIndex(0);
		} else {
			nhForm.workContract.setSelectedIndex(1);
		}
		nhForm.manager.setText(data.getDetail("lm_name"));
		nhForm.manPhone.setText(data.getDetail("lm_phone_no"));
		if(Boolean.parseBoolean(data.getDetail("signatory_name_req"))){
			nhForm.signatoryReq.setSelected(true);
		} else {
			nhForm.signatoryReq.setSelected(false);
		}
		if(!data.getDetail("signatory_name").equals("")){
			nhForm.signatoryName.setText(data.getDetail("signatory_name"));
		}
		try {
			Date sDate = new SimpleDateFormat("dd-MM-yyyy").parse(data.getDetail("start_date"));
			nhForm.effDate.setDate(sDate);
			nhForm.dateTBC.setSelected(false);
		} catch (NoSuchElementException | PropertyVetoException e) {
			System.out.println("Date conversion failed");
			if(data.getDetail("start_date").equals("")){
				nhForm.dateTBC.setSelected(true);
			}
		} catch (ParseException e1) {
			System.out.println("date parse error");		
		}
		
		nhForm.annPay.setText(data.getDetail("salary"));
		nhForm.ggs.setText(data.getDetail("job_grade"));
		//Relocation fields group
		if(Boolean.parseBoolean(data.getDetail("relocation"))){
			nhForm.relocation.setSelected(true);
			nhForm.reloAmount.setEnabled(true);
			nhForm.reloAmount.setText(data.getDetail("relocation_amount"));
			nhForm.reloLocation.setEnabled(true);
			nhForm.reloLocation.setText(data.getDetail("relocation_area"));
		} else {
			nhForm.relocation.setSelected(false);
			nhForm.reloAmount.setEnabled(false);
			nhForm.reloAmount.setText("");
			nhForm.reloLocation.setEnabled(false);
			nhForm.reloLocation.setText("");
		}
		
		//Probation period fields group
		if(Boolean.parseBoolean(data.getDetail("probation_period"))){
			nhForm.trial.setSelected(true);
			nhForm.trialDuration.setEnabled(true);
			nhForm.trialDuration.setText(data.getDetail("duration_of_probation"));
		} else {
			nhForm.trial.setSelected(false);
			nhForm.trialDuration.setEnabled(false);
			nhForm.trialDuration.setText("");
		}
		
		//Sign On Bonus fields group
		if(Boolean.parseBoolean(data.getDetail("sign_on_bonus"))){
			nhForm.signOn.setSelected(true);
			nhForm.signOnAmount.setEnabled(true);
			nhForm.signOnAmount.setText(data.getDetail("sign_on_bonus_amount"));
		} else {
			nhForm.signOn.setSelected(false);
			nhForm.signOnAmount.setEnabled(false);
			nhForm.signOnAmount.setText("");
		}
		
		//Competition Compliance
		if(Boolean.parseBoolean(data.getDetail("competition_compliance"))){
			nhForm.compComp.setSelected(true);
		} else {
			nhForm.compComp.setSelected(false);
		}
		
		//Travel Supplement field group
		if(Boolean.parseBoolean(data.getDetail("travel_supplement"))){
			nhForm.travelSupp.setSelected(true);
			nhForm.travelSuppAmount.setEnabled(true);
			nhForm.travelSuppAmount.setText(data.getDetail("travel_supplement_amount"));
			nhForm.travelSuppDate.setEnabled(true);
			nhForm.travelSuppDur.setEnabled(true);
			nhForm.pencePerMile.setEnabled(true);
		} else {
			nhForm.travelSupp.setSelected(false);
			nhForm.travelSuppAmount.setEnabled(false);
			nhForm.travelSuppAmount.setText("");
			nhForm.travelSuppDate.setEnabled(false);
			try {
				nhForm.travelSuppDate.setDate(null);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			nhForm.travelSuppDur.setEnabled(false);
			nhForm.travelSuppDur.setText("");
			nhForm.pencePerMile.setEnabled(false);
			nhForm.pencePerMile.setText("");
		}
		
		//Company Mobile Phone
		if(Boolean.parseBoolean(data.getDetail("mobile_phone"))){
			nhForm.compMobile.setSelected(true);
		} else {
			nhForm.compMobile.setSelected(false);
		}
		
		//Company Credit Card
		if(Boolean.parseBoolean(data.getDetail("company_credit_card"))){
			nhForm.compCreditCard.setSelected(true);
		} else {
			nhForm.compCreditCard.setSelected(false);
		}
		
		//Company Car
		String cCar = data.getDetail("company_car");
		if(cCar.equals("None")){
			nhForm.compCar.setSelectedIndex(0);
		} else if (cCar.equals("Company Car")){
			nhForm.compCar.setSelectedIndex(1);
		} else if (cCar.equals("Company Van")){
			nhForm.compCar.setSelectedIndex(2);
		} else if (cCar.equals("Company Car Cash Allowance")){
			nhForm.compCar.setSelectedIndex(3);
		}
		
		if(data.getDetail("addWageType1").toLowerCase().contains("shift") || 
				data.getDetail("addWageType1").toLowerCase().contains("inconv")){
			nhForm.shiftPay.setSelected(true);
			nhForm.shiftPayVal.setEnabled(true);
			nhForm.shiftPayVal.setText(data.getDetail("addWageTypeAmount1"));
		} else if (data.getDetail("addWageType2").toLowerCase().contains("shift") || 
				data.getDetail("addWageType2").toLowerCase().contains("inconv")){
			nhForm.shiftPay.setSelected(true);
			nhForm.shiftPayVal.setEnabled(true);
			nhForm.shiftPayVal.setText(data.getDetail("addWageTypeAmount2"));
		} else {
			nhForm.shiftPay.setSelected(false);
			nhForm.shiftPayVal.setEnabled(false);
			nhForm.shiftPayVal.setText("");
		}
	}
	
	/**
	 * Method closing app.
	 */
	public void exit() {
		System.exit(0);
	}
}

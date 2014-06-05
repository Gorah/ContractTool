package contract.gui.view.swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.ezware.dialog.task.TaskDialogs;
import com.michaelbaranov.microba.calendar.DatePicker;

import contract.logging.ContractLogger;
import contract.model.ComboItem;
import contract.model.HireModel;
import contract.repository.ComboOptions;
import contract.repository.ItemNotFoundException;



public class NewHire extends SwingView {
	
	private Logger logger = new ContractLogger(NewHire.class.getName()).getLogger();
	
	private ComboOptions opts;
	
	private JLabel refL = new JLabel("Contract Reference");
	private JTextField ref = new JTextField(14);
	private JLabel refCredsL = new JLabel("HRBP credentials");
	private JTextField refCreds = new JTextField("JB", 5);
	private JPanel nhPanel = new JPanel();
	private JPanel contractDetails = new JPanel();
	private JTabbedPane tabs = new JTabbedPane();
	private JPanel genInfo = new JPanel();
	private JPanel eeDetails = new JPanel();
	private JPanel posDetails = new JPanel();
	private JPanel addDetails = new JPanel();
	private JPanel addContractElems = new JPanel();
	private JPanel benefitsPanel = new JPanel();
	private JPanel additionalPay = new JPanel();
	private JTextField conID = new JTextField();
	private JLabel eeidL = new JLabel("Employee ID");
	private JTextField eeid = new JTextField(9);
	private JTextField name = new JTextField(15);
	private JTextField lName = new JTextField(30);
	private JLabel nameL = new JLabel("First Name");
	private JLabel lNameL = new JLabel("Last Name");
	private JLabel addressLine1Label = new JLabel("Address Line 1:");
	private JLabel addressLine2Label = new JLabel("Address Line 2:");
	private JLabel cityLabel = new JLabel("City:");
	private JLabel postalCodeLabel = new JLabel("Postal Code:");
	private JLabel countryLabel = new JLabel("Country");
	private JTextField addressLine1 = new JTextField(30);
	private JTextField addressLine2 = new JTextField(30);
	private JTextField city = new JTextField(15);
	private JTextField postalCode = new JTextField(10);
	private JComboBox<String> country = new JComboBox<String>();
	private JLabel jobL = new JLabel("Position Title");
	private JTextField job = new JTextField(15);
	private JLabel jobNL = new JLabel("Position Number");
	private JTextField jobN = new JTextField(15);
	private JLabel ggsL = new JLabel("GGS:");
	private JTextField ggs = new JTextField(4);
	private JLabel locationL = new JLabel("Location");
	private JTextField location = new JTextField(15);
	private JLabel buL = new JLabel("Business Area");
	private JTextField bu = new JTextField(30);
	private JLabel conTypeL = new JLabel("Contract Type");
	private JComboBox<String> conType = new JComboBox<String>();
	private JLabel workContractL = new JLabel("Work Contract");
	private JComboBox<String> workContract = new JComboBox<String>();
	private JLabel managerL = new JLabel("Line Manager");
	private JTextField manager = new JTextField(15);
	private JLabel managerPosL = new JLabel("Manager's Position Title");
	private JTextField managerPos = new JTextField(30);
	private JLabel manPhoneL = new JLabel("Manager's phone number");
	private JTextField manPhone = new JTextField(15);
	private JLabel effDateL = new JLabel("Start date:");
	private DatePicker effDate = new DatePicker();
	private JCheckBox dateTBC = new JCheckBox("Date to be confirmed");
	private JCheckBox isCTS = new JCheckBox("CTS");
	private JTextField annPay = new JTextField(15);
	private JLabel annPayL = new JLabel("Annual Pay:");
	private JTextField shiftVal = new JTextField(15);
	private JLabel reloAmountL = new JLabel("Relocation Amount");
	private JTextField reloAmount = new JTextField(15);
	private JLabel hoursWorkL = new JLabel("Hours of work");
	private JTextField hoursWork = new JTextField(5);
	private JLabel relocationL = new JLabel("Relocation");
	private JCheckBox relocation = new JCheckBox();
	private JLabel reloLocationL = new JLabel("Relocation location");
	private JTextField reloLocation = new JTextField(15);
	private JLabel signatoryReqL = new JLabel("Signatory Name requied");
	private JCheckBox signatoryReq = new JCheckBox();
	private JLabel singatoryNameL = new JLabel("Signatory Name");
	private JTextField signatoryName = new JTextField(15);
	private JLabel trialL = new JLabel("Trial Period:");
	private JCheckBox trial = new JCheckBox();
	private JLabel trialDurationL = new JLabel("Trial Duration");
	private JTextField trialDuration = new JTextField(5);
	private JLabel signOnL = new JLabel("Sign-On bonus");
	private JCheckBox signOn = new JCheckBox();
	private JLabel signOnAmountL = new JLabel("Sign On bonus Amount");
	private JTextField signOnAmount = new JTextField(10);
	private JLabel travelSuppL = new JLabel("Travel Supplement");
	private JCheckBox travelSupp = new JCheckBox();
	private JLabel travelSuppAmountL = new JLabel("Travel Supplement Amount");
	private JTextField travelSuppAmount = new JTextField(10);
	private JLabel travelSuppDateL = new JLabel("Travel Supplement Start Date");
	private DatePicker travelSuppDate = new DatePicker();
	private JLabel travelSuppDurL = new JLabel("Travel Supplement Duration");
	private JTextField travelSuppDur = new JTextField(5);
	private JLabel pencePerMileL = new JLabel("Pence per mile");
	private JTextField pencePerMile = new JTextField(5);
	private JLabel persQualFeeL = new JLabel("Personal Qualification Fees");
	private JCheckBox persQualFee = new JCheckBox();
	private JLabel compMobileL = new JLabel("Company Mobile Phone");
	private JCheckBox compMobile = new JCheckBox();
	private JLabel compCreditCardL = new JLabel("Company Credit Card");
	private JCheckBox compCreditCard = new JCheckBox();
	private JLabel compCarL = new JLabel("Company Car:");
	private JComboBox<String> compCar = new JComboBox<String>();
	private JLabel shiftPayL = new JLabel("Shift/Inconvenience Pay");
	private JCheckBox shiftPay = new JCheckBox();
	private JLabel shiftPayValL = new JLabel("Shift Pay Value");
	private JTextField shiftPayVal = new JTextField(15);
	private JLabel compCompL = new JLabel("Competition Compliance");
	private JCheckBox compComp = new JCheckBox();
	private JButton submitBut = new JButton("Generate Contract");
	private JLabel empGroupL = new JLabel("Employee Group");
	private JComboBox<String> empGroup = new JComboBox<String>();
	private JLabel profSubsL = new JLabel("Professional Subscriptions");
	private JCheckBox profSubs = new JCheckBox();
	private JLabel workVisaL = new JLabel("Working Visa Required");
	private JCheckBox workVisa = new JCheckBox();
	private JLabel pyAreaL = new JLabel("Payroll Area");
	private String[] areas = {"4W", "MO", "DE"};
	private JComboBox<String> pyArea = new JComboBox<String>(areas);
	private JCheckBox mop = new JCheckBox("MOP FSE");
	private InputVerifier pureTextVerifier = new NameVerifier();
	private AmountVerifier amountVerifier = new AmountVerifier();
	private NumericValueVerifier numVerifier = new NumericValueVerifier();
	private EmptyVerifier emptyVerifier = new EmptyVerifier();
	private SubmitEventListener subListener = new SubmitEventListener();

	
	public NewHire(ComboOptions opts) {
		super(Name.NEW_HIRE);
		this.opts = opts;
		//Fill combo box with options
		//---------------------------
		
		//Country
		for(ComboItem item : opts.getCountries()){
			country.addItem(item.toString());
		}
		
		//Contract Type
		for(ComboItem item : opts.getContract_types()){
			conType.addItem(item.toString());
		}
		
		//Work Contract
		for(ComboItem item : opts.getWork_contracts()){
			workContract.addItem(item.toString());
		}
		
		//Company Car
		for(ComboItem item : opts.getCar_options()){
			compCar.addItem(item.toString());
		}
		
		//Employee Group
		for(ComboItem item : opts.getEe_groups()){
			empGroup.addItem(item.toString());
		}
		
		//add validation to fields
		
		//Plain text, only letter chars
		name.setInputVerifier(pureTextVerifier);
		lName.setInputVerifier(pureTextVerifier);
		city.setInputVerifier(pureTextVerifier);
		manager.setInputVerifier(pureTextVerifier);
		signatoryName.setInputVerifier(pureTextVerifier);
		reloLocation.setInputVerifier(pureTextVerifier);
		location.setInputVerifier(pureTextVerifier);
		bu.setInputVerifier(pureTextVerifier);
		refCreds.setInputVerifier(pureTextVerifier);
		
		
		//amounts matching specified amount format
		annPay.setInputVerifier(amountVerifier);
		reloAmount.setInputVerifier(amountVerifier);
		signOnAmount.setInputVerifier(amountVerifier);
		travelSuppAmount.setInputVerifier(amountVerifier);
		pencePerMile.setInputVerifier(amountVerifier);
		shiftPayVal.setInputVerifier(amountVerifier);
		
		//fields with numeric values only
		hoursWork.setInputVerifier(numVerifier);
		trialDuration.setInputVerifier(numVerifier);
		travelSuppDur.setInputVerifier(numVerifier);
		jobN.setInputVerifier(numVerifier);
		
		//setup of date picker fields
		//----------------------------
		//Contract Start Date picker
		effDate.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
		effDate.setEnabled(true);
		effDate.setKeepTime(false);
		effDate.setStripTime(false);
		effDate.setShowNumberOfWeek(false);
		
		//Zero the datepicker fields
		try {
			effDate.setDate(null);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		try {
			travelSuppDate.setDate(null);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		nhPanel.setLayout(new GridBagLayout());
		GridBagConstraints cn = new GridBagConstraints();
		cn.anchor = GridBagConstraints.WEST;
		cn.insets = new Insets(2,2,2,2);
		
		//Contract General Info Panel
		contractDetails.setBorder(BorderFactory.createTitledBorder("Contract General Info"));
		contractDetails.setLayout(new GridBagLayout());
		//Contract ID
		conID.setVisible(false);
		contractDetails.add(conID);
		//Contract Reference
		cn.gridx = 0;
		cn.gridy = 0;
		contractDetails.add(refL, cn);
		cn.gridx = 1;
		contractDetails.add(ref, cn);
		//HRBP credentials for reference
		refCreds.getDocument().addDocumentListener(new RefCredsListener());
		cn.gridy = 1;
		cn.gridx = 2;
		contractDetails.add(refCredsL, cn);
		cn.gridx = 3;
		contractDetails.add(refCreds, cn);
		
		cn.gridx = 0;
		cn.gridy = 0;
		nhPanel.add(contractDetails, cn);
		
		cn.gridy = 0;
		//First tab
		genInfo.setLayout(new GridBagLayout());
			
		//EE Details panel setup
		eeDetails.setBorder(BorderFactory.createTitledBorder("Employee Details"));
		eeDetails.setLayout(new GridBagLayout());
		//Employee ID
		cn.gridx = 0;
		cn.gridy = 0;
		cn.insets = new Insets(15, 2, 2, 2);
		eeDetails.add(eeidL, cn);
		cn.gridx = 1;
		eeDetails.add(eeid, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		//First Name
		cn.gridy = 1;
		cn.gridx = 0;
		eeDetails.add(nameL, cn);
		cn.gridx = 1;
		eeDetails.add(name, cn);
		//Last Name
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		eeDetails.add(lNameL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		eeDetails.add(lName, cn);
		//Address Line 1
		cn.gridy = 2;
		cn.gridx = 0;
		eeDetails.add(addressLine1Label, cn);
		cn.gridx = 1;
		cn.gridwidth = 3;
		eeDetails.add(addressLine1, cn);
		//Address Line 2
		cn.gridy = 3;
		cn.gridwidth = 1;
		cn.gridx = 0;
		eeDetails.add(addressLine2Label, cn);
		cn.gridx = 1;
		cn.gridwidth = 3;
		eeDetails.add(addressLine2, cn);
		//City
		cn.gridy = 4;
		cn.gridwidth = 1;
		cn.gridx = 0;
		eeDetails.add(cityLabel, cn);
		cn.gridx = 1;
		eeDetails.add(city, cn);
		//Postal Code
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		eeDetails.add(postalCodeLabel, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		eeDetails.add(postalCode, cn);
		//Country
		cn.gridy = 5;
		cn.gridx = 0;
		eeDetails.add(countryLabel, cn);
		cn.gridx = 1;
		cn.gridwidth = 2;
		eeDetails.add(country, cn);
		
		
		//Add eeDtails panel to first tab
		cn.gridx = 0;
		cn.gridy = 1;
		cn.insets = new Insets(15, 8, 2, 8);
		cn.ipadx = 15;
		genInfo.add(eeDetails, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.ipadx = 0;
		
		//Position details panel setup
		posDetails.setBorder(BorderFactory.createTitledBorder("Position Details"));
		posDetails.setLayout(new GridBagLayout());
		
		//Position Title
		cn.gridx = 0;
		cn.gridy = 0;
		cn.insets = new Insets(15, 2, 2, 2);
		posDetails.add(jobL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 1;
		posDetails.add(job, cn);
		//Position Number
		cn.gridwidth = 1;
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(jobNL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		posDetails.add(jobN, cn);
		//Location
		cn.gridy = 1;
		cn.gridx = 0;
		posDetails.add(locationL, cn);
		cn.gridx = 1;
		posDetails.add(location, cn);
		//Business Area
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(buL, cn);
		cn.gridx = 3;
		cn.insets = new Insets(2, 2, 2, 2);
		posDetails.add(bu, cn);
		//Contract Type
		cn.gridy = 2;
		cn.gridx = 0;
		posDetails.add(conTypeL, cn);
		cn.gridx = 1;
		posDetails.add(conType, cn);
		//Work Contract
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(workContractL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		posDetails.add(workContract, cn);
		//Employee Group
		cn.gridx = 0;
		cn.gridy = 3;
		posDetails.add(empGroupL, cn);
		cn.gridx = 1;
		posDetails.add(empGroup, cn);
		//Payroll Area
		cn.gridx = 2;
		posDetails.add(pyAreaL, cn);
		cn.gridx = 3;
		posDetails.add(pyArea, cn);
		//Line Manager
		cn.gridx = 0;
		cn.gridy = 4;
		posDetails.add(managerL, cn);
		cn.gridx = 1;
		posDetails.add(manager, cn);
		//Manager's Phone number
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(manPhoneL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		posDetails.add(manPhone, cn);
		//Manager's position name 
		cn.gridy = 5;
		cn.gridx = 0;
		posDetails.add(managerPosL, cn);
		cn.gridx = 1;
		cn.gridwidth = 2;
		posDetails.add(managerPos, cn);
		cn.gridwidth = 1;
		//Signatory name required
		cn.gridy = 6;
		cn.gridx = 0;
		posDetails.add(signatoryReqL, cn);
		cn.gridx = 1;
		//Add Action listener
		signatoryReq.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(signatoryReq.isSelected()){
					signatoryName.setEnabled(true);
				} else {
					signatoryName.setText("  ");
					pureTextVerifier.verify(signatoryName);
					signatoryName.setText(null);
					signatoryName.setEnabled(false);
				}
			}
			
		});
		posDetails.add(signatoryReq, cn);
		//Signatory Name
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(singatoryNameL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		signatoryName.setEnabled(false);
		posDetails.add(signatoryName, cn);
		//Start Date
		cn.gridy = 7;
		cn.gridx = 0;
		posDetails.add(effDateL, cn);
		cn.gridx = 1;
		posDetails.add(effDate, cn);
		//Date To Be Confirmed
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		//Add action listener
		dateTBC.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dateTBC.isSelected()){
					effDate.setEnabled(false);
					try {
						effDate.setDate(null);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				} else {
					effDate.setEnabled(true);
				}	
			}});
		posDetails.add(dateTBC, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		//Hours Of Work
		cn.gridy = 8;
		cn.gridx = 0;
		posDetails.add(hoursWorkL, cn);
		cn.gridx = 1;
		posDetails.add(hoursWork, cn);
		//CTS
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(isCTS, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		posDetails.add(mop, cn);
		//Annual Pay
		cn.gridy = 9;
		cn.gridx = 0;
		posDetails.add(annPayL, cn);
		cn.gridx = 1;
		posDetails.add(annPay, cn);
		//GGS
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(ggsL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		shiftVal.setEnabled(false);
		posDetails.add(ggs, cn);

	
		
		//Add position details to first tab
		cn.gridx = 0;
		cn.gridy = 2;
		cn.insets = new Insets(15, 8, 2, 8);
		cn.ipadx = 15;
		genInfo.add(posDetails, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.ipadx = 0;
		
		//Add General Info tab to the form
		tabs.add("General Info", genInfo);
		
		//Prepare Additional Details Tab
		addDetails.setLayout(new GridBagLayout());
		//Setup Additional Contract Elements panel
		addContractElems.setBorder(BorderFactory.createTitledBorder("Additional Contract Elements"));
		addContractElems.setLayout(new GridBagLayout());
		//Relocation 
		cn.gridy = 0;
		cn.gridx = 0;
		addContractElems.add(relocationL, cn);
		cn.gridx = 1;
		//Add Action listener
		relocation.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(relocation.isSelected()){
					reloLocation.setText(null);
					reloLocation.setEnabled(true);
					reloAmount.setText(null);
					reloAmount.setEnabled(true);
				} else {
					reloLocation.setText("   ");
					pureTextVerifier.verify(reloLocation);
					reloLocation.setEnabled(false);
					reloAmount.setText("   ");
					amountVerifier.verify(reloAmount);
					reloAmount.setEnabled(false);
				}
			}
			
		});
		addContractElems.add(relocation, cn);
		//Relocation Location
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		addContractElems.add(reloLocationL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		reloLocation.setEnabled(false);
		addContractElems.add(reloLocation, cn);
		//Relocation Amount
		cn.gridy = 1;
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		addContractElems.add(reloAmountL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		reloAmount.setEnabled(false);
		addContractElems.add(reloAmount, cn);
		//Trial Period
		cn.gridy = 2;
		cn.gridx = 0;
		addContractElems.add(trialL, cn);
		cn.gridx = 1;
		trial.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(trial.isSelected()){
					trialDuration.setText(null);
					trialDuration.setEnabled(true);
				} else {
					trialDuration.setText("   ");
					numVerifier.verify(trialDuration);
					trialDuration.setEnabled(false);
				}
			}
			
		});
		addContractElems.add(trial, cn);
		//Trial Period Duration
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		addContractElems.add(trialDurationL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		trialDuration.setEnabled(false);
		addContractElems.add(trialDuration, cn);
		//Sign On Bonus
		cn.gridy = 3;
		cn.gridx = 0;
		addContractElems.add(signOnL, cn);
		cn.gridx = 1;
		signOn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(signOn.isSelected()){
					signOnAmount.setText(null);
					signOnAmount.setEnabled(true);
				} else {
					signOnAmount.setText("   ");
					amountVerifier.verify(signOnAmount);
					signOnAmount.setEnabled(false);
				}
			}
			
		});
		addContractElems.add(signOn, cn);
		//Sign On Bonus Amount
		cn.gridx = 2;
		signOnAmount.setEnabled(false);
		cn.insets = new Insets(2, 8, 2, 2);
		addContractElems.add(signOnAmountL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		addContractElems.add(signOnAmount, cn);
		//Competition Compliance
		cn.gridy = 4;
		cn.gridx = 0;
		addContractElems.add(compCompL, cn);
		cn.gridx = 1;
		addContractElems.add(compComp, cn);
		//Working Visa
		cn.gridy = 5;
		cn.gridx = 0;
		addContractElems.add(workVisaL, cn);
		cn.gridx = 1;
		addContractElems.add(workVisa, cn);
		
		//Add Additional Contract Elements Panel to tab
		cn.gridx = 0;
		cn.gridy = 0;
		cn.insets = new Insets(15, 8, 2, 8);
		cn.ipadx = 15;
		cn.anchor = GridBagConstraints.NORTHWEST;
		addDetails.add(addContractElems, cn);
		cn.anchor = GridBagConstraints.WEST;
		cn.insets = new Insets(2, 2, 2, 2);
		cn.ipadx = 0;
		
		
		//Setup Benefits panel
		benefitsPanel.setBorder(BorderFactory.createTitledBorder("Benefits"));
		benefitsPanel.setLayout(new GridBagLayout());
		
		//Travel Supplement
		cn.gridx = 0;
		cn.gridy = 0;
		benefitsPanel.add(travelSuppL, cn);
		cn.gridx = 1;
		cn.anchor = GridBagConstraints.CENTER;
		//Setup Action Listener for this event
		travelSupp.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == travelSupp){
						if(travelSupp.isSelected()){
							travelSuppAmount.setEnabled(true);
							travelSuppAmount.setText(null);
							travelSuppDate.setEnabled(true);
							travelSuppDur.setEnabled(true);
							travelSuppDur.setText(null);
							pencePerMile.setEnabled(true);
							pencePerMile.setText(null);
						} else {
							travelSuppAmount.setText("   ");
							travelSuppAmount.setEnabled(false);
							try {
								travelSuppDate.setDate(null);
							} catch (PropertyVetoException e1) {
								e1.printStackTrace();
							}
							travelSuppDate.setEnabled(false);
							travelSuppDur.setText("   ");
							travelSuppDur.setEnabled(false);
							pencePerMile.setText("   ");
							pencePerMile.setEnabled(false);
						}
					}
				}
		});
		benefitsPanel.add(travelSupp, cn);
		cn.anchor = GridBagConstraints.WEST;
		//Travel Supplement Amount
		cn.gridx = 2;
		benefitsPanel.add(travelSuppAmountL, cn);
		cn.gridx = 3;
		travelSuppAmount.setEnabled(false);
		benefitsPanel.add(travelSuppAmount, cn);
		//Travel Supplement Start Date
		cn.gridy = 3;
		cn.gridx = 0;
		benefitsPanel.add(travelSuppDateL, cn);
		cn.gridx = 1;
		travelSuppDate.setEnabled(false);
		cn.anchor = GridBagConstraints.CENTER;
		benefitsPanel.add(travelSuppDate, cn);
		cn.anchor = GridBagConstraints.WEST;
		//Travel Supplement Duration
		cn.gridx = 2;
		benefitsPanel.add(travelSuppDurL, cn);
		cn.gridx = 3;
		travelSuppDur.setEnabled(false);
		benefitsPanel.add(travelSuppDur, cn);
		//Pence per Mile
		cn.gridy = 4;
		cn.gridx = 0;
		benefitsPanel.add(pencePerMileL, cn);
		cn.gridx = 1;
		cn.anchor = GridBagConstraints.CENTER;
		pencePerMile.setEnabled(false);
		benefitsPanel.add(pencePerMile, cn);
		cn.anchor = GridBagConstraints.WEST;
		
		//Personal Qualification Fees
		cn.gridy = 5;
		cn.gridx = 0;
		benefitsPanel.add(persQualFeeL, cn);
		cn.gridx = 1;
		cn.anchor = GridBagConstraints.CENTER;
		benefitsPanel.add(persQualFee, cn);
		cn.anchor = GridBagConstraints.WEST;
		//Professional Subscriptions
		cn.gridy = 6;
		cn.gridx = 0;
		benefitsPanel.add(profSubsL, cn);
		cn.gridx = 1;
		cn.anchor = GridBagConstraints.CENTER;
		benefitsPanel.add(profSubs, cn);
		cn.anchor = GridBagConstraints.WEST;
		//Company Mobile
		cn.gridy = 7;
		cn.gridx = 0;
		benefitsPanel.add(compMobileL, cn);
		cn.gridx = 1;
		cn.anchor = GridBagConstraints.CENTER;
		benefitsPanel.add(compMobile, cn);
		cn.anchor = GridBagConstraints.WEST;
		//Company Credit Card
		cn.gridy = 8;
		cn.gridx = 0;
		benefitsPanel.add(compCreditCardL, cn);
		cn.gridx = 1;
		cn.anchor = GridBagConstraints.CENTER;
		benefitsPanel.add(compCreditCard, cn);
		cn.anchor = GridBagConstraints.WEST;
		//Company Car
		cn.gridy = 9;
		cn.gridx = 0;
		benefitsPanel.add(compCarL, cn);
		cn.gridx = 1;
		benefitsPanel.add(compCar, cn);
		
		//Add Benefits Panel to tab
		cn.gridx = 0;
		cn.gridy = 1;
		cn.insets = new Insets(15, 8, 2, 8);
		cn.ipadx = 15;
		cn.anchor = GridBagConstraints.NORTHWEST;
		addDetails.add(benefitsPanel, cn);
		cn.anchor = GridBagConstraints.WEST;
		cn.insets = new Insets(2, 2, 2, 2);
		cn.ipadx = 0;
		
		additionalPay.setLayout(new GridBagLayout());
		additionalPay.setBorder(BorderFactory.createTitledBorder("Additional Payment Details"));
		//Shift Pay
		cn.gridy = 0;
		cn.gridx = 0;
		additionalPay.add(shiftPayL, cn);
		cn.gridx = 1;
		additionalPay.add(shiftPay, cn);
		//Add Action Listener
		shiftPay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(shiftPay.isSelected()){
					shiftPayVal.setText(null);
					shiftPayVal.setEnabled(true);
				} else {
					shiftPayVal.setText("   ");
					amountVerifier.verify(shiftPayVal);
					shiftPayVal.setEnabled(false);
				}
				
			}
			
		});
		//Shift Pay Value
		cn.gridx = 2;
		cn.insets = new Insets(2,8,2,2);
		additionalPay.add(shiftPayValL, cn);
		cn.insets = new Insets(2,2,2,2);
		cn.gridx = 3;
		shiftPayVal.setEnabled(false);
		additionalPay.add(shiftPayVal, cn);
		
		//Add Benefits Panel to tab
		cn.gridx = 0;
		cn.gridy = 2;
		cn.insets = new Insets(15, 8, 2, 8);
		cn.ipadx = 15;
		cn.anchor = GridBagConstraints.NORTHWEST;
		addDetails.add(additionalPay, cn);
		cn.anchor = GridBagConstraints.WEST;
		cn.insets = new Insets(2, 2, 2, 2);
		cn.ipadx = 0;
		
		tabs.add("Additional Details", addDetails);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 0;
		cn.gridy = 1;
		cn.fill = GridBagConstraints.BOTH;
		nhPanel.add(tabs, cn);
		cn.gridy = 2;
		cn.fill = GridBagConstraints.NONE;
		cn.anchor = GridBagConstraints.CENTER;
		nhPanel.add(submitBut, cn);
		submitBut.addActionListener(subListener);
		
		
	}
	
	
	/**
	 * SubmitEventListener getter.
	 * 
	 * @return SubmitEventListener
	 */
	public SubmitEventListener getSubListener() {
		return subListener;
	}


	/**
	 * This method set all the fields of the form to it's default state. 
	 * Used to flush the data from the form.
	 * 
	 * @author	Bartosz Kratochwil (bartosz.krtochwil@hp.com)
	 * @version 1.0
	 */
	
	private void clear(){
		conID.setText("");
		ref.setText("");
		eeid.setText("");
		name.setText("");
		lName.setText("");
		addressLine1.setText("");
		addressLine2.setText("");
		city.setText("");
		postalCode.setText("");
		country.setSelectedIndex(0);
		job.setText("");
		jobN.setText("");
		ggs.setText("");
		location.setText("");
		bu.setText("");
		conType.setSelectedIndex(0);
		workContract.setSelectedIndex(0);
		manager.setText("");
		manPhone.setText("");
		managerPos.setText("");
		try {
			effDate.setDate(null);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dateTBC.setSelected(false);
		isCTS.setSelected(false);
		annPay.setText("");
		shiftVal.setText("");
		reloAmount.setText("");
		reloAmount.setEnabled(false);
		hoursWork.setText("");
		relocation.setSelected(false);
		reloLocation.setText("");
		reloLocation.setEnabled(false);
		signatoryReq.setSelected(false);
		signatoryName.setText("");
		signatoryName.setEnabled(false);
		trial.setSelected(false);
		trialDuration.setEnabled(false);
		trialDuration.setText("");
		signOn.setSelected(false);
		signOnAmount.setText("");
		signOnAmount.setEnabled(false);
		travelSupp.setSelected(false);
		travelSuppAmount.setText("");
		travelSuppAmount.setEnabled(false);
		try {
			travelSuppDate.setDate(null);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		travelSuppDur.setText("");
		travelSuppDur.setEnabled(false);
		pencePerMile.setText("");
		pencePerMile.setEnabled(false);
		persQualFee.setSelected(false);
		profSubs.setSelected(false);
		compMobile.setSelected(false);
		compCreditCard.setSelected(false);
		compCar.setSelectedIndex(0);
		shiftPay.setSelected(false);
		shiftPayVal.setText("");
		shiftPayVal.setEnabled(false);
		compComp.setSelected(false);
	}
	
	private Logger getLogger(){
		return logger;
	}

	/**
	 * This method extracts data from the map provided by the model and puts 
	 * it into appropriate fields on the form.
	 */
	@Override
	public void extractDataFromModel() {
		conID.setText(model.get("ID"));
		ref.setText(model.get("contract_ref"));
		eeid.setText(model.get("eeid"));
		name.setText(model.get("forenames"));
		lName.setText(model.get("surname"));
		addressLine1.setText(model.get("address_line1"));
		addressLine2.setText(model.get("address_line2"));
		city.setText(model.get("city"));
		postalCode.setText(model.get("postal_code"));
		//Translate string value to integer index position for Combo Box
		int idx = 0;
		if(model.get("country").equals("UK")){
			idx = 1;
		} else if(model.get("country").equals("Scotland")) {
			idx = 2;
		} else if(model.get("country").equals("Ireland")){
			idx = 3;
		}
		country.setSelectedIndex(idx);
		job.setText(model.get("position_title"));
		jobN.setText(model.get("position_number"));
		location.setText(model.get("location"));
		bu.setText(model.get("business_area"));
		hoursWork.setText(model.get("hours_of_work"));
		if(model.get("payroll_area").equals("4W")){
			pyArea.setSelectedIndex(0);
		} else if(model.get("payroll_area").equals("MO")) {
			pyArea.setSelectedIndex(1);
		} else {
			pyArea.setSelectedIndex(2);
		}
		
		//translate value of MOP check box and set it to proper state
		if(Boolean.parseBoolean(model.get("mop_fse"))){
			mop.setSelected(true);
		} else {
			mop.setSelected(false);
		}
		
		//Translate string value to integer index position for Combo Box
		if(model.get("contract_type").equals("Permanent")){
			conType.setSelectedIndex(0);
		} else {
			conType.setSelectedIndex(1);
		}
		//Translate string value to integer index position for Combo Box
		if(model.get("work_contract").equals("Full Time")){
			workContract.setSelectedIndex(0);
		} else {
			workContract.setSelectedIndex(1);
		}
		
		//Translate employee group into dropdown selection
		if(model.get("non_negotiated").toLowerCase().equals("non-negotiated")){
			empGroup.setSelectedIndex(0);
		} else {
			empGroup.setSelectedIndex(1);
		}
		manager.setText(model.get("lm_name"));
		manPhone.setText(model.get("lm_phone_no"));
		managerPos.setText(model.get("lm_pos_title"));
		if(Boolean.parseBoolean(model.get("signatory_name_req"))){
			signatoryReq.setSelected(true);
		} else {
			signatoryReq.setSelected(false);
		}
		if(model.get("signatory_name") != null && !model.get("signatory_name").equals("")){
			signatoryName.setText(model.get("signatory_name"));
		}
		try {
			Date sDate = new SimpleDateFormat("dd-MM-yyyy").parse(model.get("start_date"));
			effDate.setDate(sDate);
			dateTBC.setSelected(false);
		} catch (NoSuchElementException | PropertyVetoException e) {
			System.out.println("Date conversion failed");
			if(model.get("start_date").equals("")){
				dateTBC.setSelected(true);
			}
		} catch (ParseException e1) {
			System.out.println("date parse error");		
		}
		
		annPay.setText(model.get("salary"));
		ggs.setText(model.get("job_grade"));
		//Relocation fields group
		if(Boolean.parseBoolean(model.get("relocation"))){
			relocation.setSelected(true);
			reloAmount.setEnabled(true);
			reloAmount.setText(model.get("relocation_amount"));
			reloLocation.setEnabled(true);
			reloLocation.setText(model.get("relocation_area"));
		} else {
			relocation.setSelected(false);
			reloAmount.setEnabled(false);
			reloAmount.setText("");
			reloLocation.setEnabled(false);
			reloLocation.setText("");
		}
		
		//Probation period fields group
		if(Boolean.parseBoolean(model.get("probation_period"))){
			trial.setSelected(true);
			trialDuration.setEnabled(true);
			trialDuration.setText(model.get("duration_of_probation"));
		} else {
			trial.setSelected(false);
			trialDuration.setEnabled(false);
			trialDuration.setText("");
		}
		
		//Sign On Bonus fields group
		if(Boolean.parseBoolean(model.get("sign_on_bonus"))){
			signOn.setSelected(true);
			signOnAmount.setEnabled(true);
			signOnAmount.setText(model.get("sign_on_bonus_amount"));
		} else {
			signOn.setSelected(false);
			signOnAmount.setEnabled(false);
			signOnAmount.setText("");
		}
		
		//Competition Compliance
		if(Boolean.parseBoolean(model.get("competition_compliance"))){
			compComp.setSelected(true);
		} else {
			compComp.setSelected(false);
		}
		
		//Working Visa
		if(Boolean.parseBoolean(model.get("working_visa_paragraph"))){
			workVisa.setSelected(true);
		} else {
			workVisa.setSelected(false);
		}
		
		//Travel Supplement field group
		if(Boolean.parseBoolean(model.get("travel_supplement"))){
			travelSupp.setSelected(true);
			travelSuppAmount.setEnabled(true);
			travelSuppAmount.setText(model.get("travel_supplement_amount"));
			travelSuppDate.setEnabled(true);
			travelSuppDur.setEnabled(true);
			pencePerMile.setEnabled(true);
		} else {
			travelSupp.setSelected(false);
			travelSuppAmount.setEnabled(false);
			travelSuppAmount.setText("");
			travelSuppDate.setEnabled(false);
			try {
				travelSuppDate.setDate(null);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			travelSuppDur.setEnabled(false);
			travelSuppDur.setText("");
			pencePerMile.setEnabled(false);
			pencePerMile.setText("");
		}
		
		//Company Mobile Phone
		if(Boolean.parseBoolean(model.get("mobile_phone"))){
			compMobile.setSelected(true);
		} else {
			compMobile.setSelected(false);
		}
		
		//Company Credit Card
		if(Boolean.parseBoolean(model.get("company_credit_card"))){
			compCreditCard.setSelected(true);
		} else {
			compCreditCard.setSelected(false);
		}
		
		//Professional Subscriptions
		if(Boolean.parseBoolean(model.get("professional_subs"))){
			profSubs.setSelected(true);
		} else {
			profSubs.setSelected(false);
		}
		
		//Company Car
		String cCar = model.get("company_car");
		if(cCar.equals("None")){
			compCar.setSelectedIndex(0);
		} else if (cCar.equals("Company Car")){
			compCar.setSelectedIndex(1);
		} else if (cCar.equals("Company Van")){
			compCar.setSelectedIndex(2);
		} else if (cCar.equals("Company Car Cash Allowance")){
			compCar.setSelectedIndex(3);
		}
		
		if(model.get("addWageType1").toLowerCase().contains("shift") || 
				model.get("addWageType1").toLowerCase().contains("inconv")){
			shiftPay.setSelected(true);
			shiftPayVal.setEnabled(true);
			shiftPayVal.setText(model.get("addWageTypeAmount1"));
		} else if (model.get("addWageType2").toLowerCase().contains("shift") || 
				model.get("addWageType2").toLowerCase().contains("inconv")){
			shiftPay.setSelected(true);
			shiftPayVal.setEnabled(true);
			shiftPayVal.setText(model.get("addWageTypeAmount2"));
		} else {
			shiftPay.setSelected(false);
			shiftPayVal.setEnabled(false);
			shiftPayVal.setText("");
		}		
	}
	
	@Override
	public void render() {
		mainContainer.getContentPane().removeAll();
		clear();
		mainContainer.add(nhPanel);
		mainContainer.setSize(800, 700);
		mainContainer.setVisible(true);


	}
	
	/**
	 * Listener class for Reference Credentials text field. It handles updating contract reference
	 * when Initials are changes.
	 * 
	 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
	 *
	 * @version 0.1
	 *
	 */
	class RefCredsListener implements DocumentListener{

		/**
		 * This method updates contract reference field with new value of initials.
		 */
		@Override
		public void changedUpdate(DocumentEvent arg0) {
			ref.setText(appController.getContractTool().getUsr().getInitials() + "\\" + refCreds.getText() + "\\" + new SimpleDateFormat("ddMMyyH").format(new Date()));
			
		}

		/**
		 * This method updates contract reference field with new value of initials.
		 */
		@Override
		public void insertUpdate(DocumentEvent arg0) {
			ref.setText(appController.getContractTool().getUsr().getInitials() + "\\" + refCreds.getText() + "\\" + new SimpleDateFormat("ddMMyyH").format(new Date()));
			
		}

		/**
		 * This method updates contract reference field with new value of initials.
		 * If field is empty after deletion, a default value of "JB" is used instead.
		 */
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			if(!refCreds.getText().isEmpty()){
				ref.setText(appController.getContractTool().getUsr().getInitials() + "\\" + refCreds.getText() + "\\" + new SimpleDateFormat("ddMMyyH").format(new Date()));
			} else {
				ref.setText(appController.getContractTool().getUsr().getInitials() + "\\JB\\" + new SimpleDateFormat("ddMMyyH").format(new Date()));
			}
		}
		
	}
	
	
	/**
	 * Event listener class for submit button
	 * Before any action is taken, it's purpose is to fire validation 
	 * on key fields.
	 * 
	 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
	 *
	 * @version 0.1
	 *
	 */
	class SubmitEventListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent ev) {
			if(ev.getSource() == submitBut){
				if(!verifyForm()){
					TaskDialogs.inform(mainContainer, "Warrning!", "Not all fields validated. Make sure that all highlighted fields are corrected.");
				} else {
					appController.run_newHire_contract_gen(Integer.parseInt(conID.getText()));
				}
			}
		}
		
		/**
		 * This method checks all the fields on the form using their verifiers to see
		 * if there aren't any rule violations. 
		 * 
		 * @return boolean
		 */
		private boolean verifyForm(){
			if(!pureTextVerifier.verify(name)){
				return false;
			} 
			if(!pureTextVerifier.verify(lName)){
				return false;
			} 
			if(!pureTextVerifier.verify(city)){
				return false;
			} 
			if(!pureTextVerifier.verify(manager)){
				return false;
			}
			if(!pureTextVerifier.verify(refCreds)){
				return false;
			}
			
			if(!amountVerifier.verify(annPay)){
				return false;
			}
			
			if(!numVerifier.verify(hoursWork)){
				return false;
			}
			
			//Conditional checks - verification performed only if certain fields are checked
			if(relocation.isSelected()){
				if(!amountVerifier.verify(reloAmount)){
					return false;
				}
				if(!pureTextVerifier.verify(reloLocation)){
					return false;
				}
				if(!pureTextVerifier.verify(reloLocation)){
					return false;
				}
			}
			if(signOn.isSelected()){
				if(!amountVerifier.verify(signOnAmount)){
					return false;
				}
			}
			if(travelSupp.isSelected()){
				if(!amountVerifier.verify(travelSuppAmount)){
					return false;
				}
				if(!amountVerifier.verify(pencePerMile)){
					return false;
				}
			}
			if(shiftPay.isSelected()){			
				if(!amountVerifier.verify(shiftPayVal)){
					return false;
				}
			}
			if(trial.isSelected()){
				if(!numVerifier.verify(trialDuration)){
					return false;
				}
			}
			if(signatoryReq.isSelected()){
				if(!pureTextVerifier.verify(signatoryName)){
					return false;
				}
			}
			
			//Empty fields checks
			if(!emptyVerifier.verify(job)){
				return false;
			}
			if(!emptyVerifier.verify(manPhone)){
				return false;
			}
			if(!emptyVerifier.verify(managerPos)){
				return false;
			}
			if(!emptyVerifier.verify(addressLine1)){
				return false;
			}
			if(!emptyVerifier.verify(postalCode)){
				return false;
			}
			if(!emptyVerifier.verify(ggs)){
				return false;
			}
			return true;
		}
		
	}
	
	/**
	 * This function generates reference ID for a new contract
	 */
	public void genRefID(){
		ref.setText(appController.getContractTool().getUsr().getInitials() + "\\" + refCreds.getText() + "\\" + new SimpleDateFormat("ddMMyyH").format(new Date()));
	}
	
	/**
	 * Saves form data to a new model
	 */
	@Override
	public void saveDataToModel() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		HireModel hireModel = new HireModel();
		hireModel.addField("ID", conID.getText());
		hireModel.addField("contract_ref", ref.getText());
		hireModel.addField("eeid", eeid.getText());
		hireModel.addField("forenames", name.getText());
		hireModel.addField("surname", lName.getText());
		hireModel.addField("address_line1", addressLine1.getText());
		hireModel.addField("address_line2", addressLine2.getText());
		hireModel.addField("city", city.getText());
		hireModel.addField("postal_code", postalCode.getText());
		hireModel.addField("country", country.getSelectedItem().toString());
		hireModel.addField("position_title", job.getText());
		hireModel.addField("position_number", jobN.getText());
		hireModel.addField("job_grade", ggs.getText());
		hireModel.addField("location", location.getText());
		hireModel.addField("business_area", bu.getText());
		hireModel.addField("contract_type", conType.getSelectedItem().toString());
		hireModel.addField("work_contract", workContract.getSelectedItem().toString());
		hireModel.addField("payroll_area", pyArea.getItemAt(pyArea.getSelectedIndex()));
		hireModel.addField("non_negotiated", empGroup.getSelectedItem().toString());
		hireModel.addField("lm_name", manager.getText());
		hireModel.addField("lm_phone_no", manPhone.getText());
		hireModel.addField("lm_pos_title", managerPos.getText());
		hireModel.addField("start_date", dateFormat.format(effDate.getDate()));
		hireModel.addField("contract_end_date", "");
		hireModel.addField("hours_of_work", hoursWork.getText());
		//translate state of date tbc checkbox
		if(dateTBC.isSelected()){
			hireModel.addField("date_tbc", "true");
		} else {
			hireModel.addField("date_tbc", "false");
		}
		//translate state of CTS checkbox
		if(isCTS.isSelected()){
			hireModel.addField("cts", "true");
		} else {
			hireModel.addField("cts", "false");
		}
		//translate state of MOP FSE checkbox
		if(mop.isSelected()){
			hireModel.addField("mop_fse", "true");
		} else {
			hireModel.addField("mop_fse", "false");
		}
		hireModel.addField("salary", annPay.getText());
		if(shiftPay.isSelected()){
			hireModel.addField("addWageType1", "shift pay");
			hireModel.addField("addWageTypeAmount1", shiftPayVal.getText());
			hireModel.addField("addWageType2", "");
			hireModel.addField("addWageTypeAmount2", "");
		} else {
			hireModel.addField("addWageType1", "");
			hireModel.addField("addWageTypeAmount1", "");
			hireModel.addField("addWageType2", "");
			hireModel.addField("addWageTypeAmount2", "");
		}
		if(relocation.isSelected()){
			hireModel.addField("relocation", "true");
			hireModel.addField("relocation_amount", reloAmount.getText());
			hireModel.addField("relocation_area", reloLocation.getText());
		} else {
			hireModel.addField("relocation", "false");
			hireModel.addField("relocation_amount", "");
			hireModel.addField("relocation_area", "");
		}
		hireModel.addField("hours_of_work", hoursWork.getText());
		if(signatoryReq.isSelected()){
			hireModel.addField("signatory_name_req", "true");
			hireModel.addField("signatory_name", signatoryName.getText());
		} else {
			hireModel.addField("signatory_name_req", "false");
			hireModel.addField("signatory_name", "");
		}
		if(trial.isSelected()){
			hireModel.addField("probation_period", "true");
			hireModel.addField("duration_of_probation", trialDuration.getText());
		} else {
			hireModel.addField("probation_period", "false");
			hireModel.addField("duration_of_probation", "");
		}
		if(signOn.isSelected()){
			hireModel.addField("sign_on_bonus", "true");
			hireModel.addField("sign_on_bonus_amount", signOnAmount.getText());
		} else {
			hireModel.addField("sign_on_bonus", "false");
			hireModel.addField("sign_on_bonus_amount", "");
		}
		if(travelSupp.isSelected()){
			hireModel.addField("travel_supplement", "true");
			hireModel.addField("travel_supplement_amount", travelSuppAmount.getText());
			hireModel.addField("travel_supplement_start", dateFormat.format(travelSuppDate.getDate()));
			hireModel.addField("travel_supplement_duration", travelSuppDur.getText());
			hireModel.addField("pence_per_mile", pencePerMile.getText());
		} else {
			hireModel.addField("travel_supplement", "false");
			hireModel.addField("travel_supplement_amount", "");
			hireModel.addField("travel_supplement_start", "");
			hireModel.addField("travel_supplement_duration", "");
			hireModel.addField("pence_per_mile", "");
		}
		if(persQualFee.isSelected()){
			hireModel.addField("personal_qualification", "true");
		} else {
			hireModel.addField("personal_qualification", "false");
		}
		if(profSubs.isSelected()){
			hireModel.addField("professional_subs", "true");
		} else {
			hireModel.addField("professional_subs", "false");
		}
		
		if(compMobile.isSelected()){
			hireModel.addField("mobile_phone", "true");
		} else {
			hireModel.addField("mobile_phone", "false");
		}
		if(compCreditCard.isSelected()){
			hireModel.addField("company_credit_card", "true");
		} else {
			hireModel.addField("company_credit_card", "false");
		}
		try {
			hireModel.addField("company_car", Integer.toString(opts.findID(opts.getCar_options(), compCar.getSelectedItem().toString())));
		} catch (ItemNotFoundException e) {
			hireModel.addField("company_car", "1");
			logger.log(Level.WARNING, "Couldn't convert combo value for company car. Used 'no car' as default.");
		}
		if(compComp.isSelected()){
			hireModel.addField("competition_compliance", "true");
		} else {
			hireModel.addField("competition_compliance", "false");
		}
		
		if(workVisa.isSelected()){
			hireModel.addField("working_visa_paragraph", "true");
		} else {
			hireModel.addField("working_visa_paragraph", "false");
		}
		
		hireModel.addField("mcbc_sharps", "");
		Calendar cal = Calendar.getInstance();
		cal.setTime(effDate.getDate());
		cal.add(Calendar.YEAR, 1);
		hireModel.addField("next_salary_review", ""+cal.get(Calendar.YEAR) + "-04-01");
		hireModel.addField("reason_for_contract", "");
		//Add newly populated model map to form.
		this.model = hireModel.getHireDetails();
	}
	
	/**
	 * This method sets id field
	 * @param int id
	 */
	@Override
	public void setID(long id){
		conID.setText(new Long(id).toString());
	}
	
	/**
	 * This methods returns complete hire model.
	 * 
	 * @return HireModel
	 */
	public HireModel getModelObject(){
		return new HireModel(this.model);
	}
	
	/**
	 * This method returns reference ID of contract
	 * @return String 
	 */
	public String getRefID(){
		return ref.getText();
	}
	
	/**
	 * Verifier class for pure text fields like name, surname, city etc.
	 * 
	 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
	 *
	 * @version 0.9
	 *
	 */
	class NameVerifier extends InputVerifier {

		@Override
		public boolean verify(JComponent input) {
			//Try casting to JTextField. If cast fails element is not compatibile with verifier
			//and verifier returns false.
			try{
				JTextField tField = (JTextField)input;
				if(!hasNonLetters(tField.getText())){
					input.setBackground(Color.RED);
					return false;
				} else {
					input.setBackground(UIManager.getColor("TextField.background"));
					return true;
				}
			} catch (ClassCastException e){
				getLogger().log(Level.SEVERE, "NameVerifier attempted to check non-text field element (" + input.getName() +")! Cannot verify!");
				return false;
			}
		}
		
		/**
		 * finds if tested string contains only allowed chars
		 * @param text String
		 * @return boolean
		 */
		private Boolean hasNonLetters(String text){
			if(text.equals(null) || text.isEmpty()){
				return false;
			}
			Pattern pattern = Pattern.compile("^[a-zA-Z\\s\\D'-]+$");
			Matcher match = pattern.matcher(text);

			return match.find();
		}
		
	}
	
	
	/**
	 * Class for amount verifier - it makes sure that all fields with 
	 * currency amounts follow same rules (contain only digits, 0-1 "." and 0+ ","
	 * 
	 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
	 * @version 1.0
	 */
	class AmountVerifier extends InputVerifier{

		/**
		 * Verification method for component - it attempts to cast it to JTextField.
		 * 
		 * @param input JComponent input. 
		 */
		@Override
		public boolean verify(JComponent input) {
			//Try casting to JTextField. If cast fails element is not compatibile with verifier
			//and verifier returns false.
			try{
				JTextField tField = (JTextField)input;
				if(isValidAmount(tField.getText())){
					input.setBackground(UIManager.getColor("TextField.background"));
					return true;
				} else {
					input.setBackground(Color.RED);
					return false;
				}
			} catch (ClassCastException e){
				getLogger().log(Level.SEVERE, "AmountVerifier attempted to check non-text field element (" + input.getName() +")! Cannot verify!");
				return false;
			}
			
			
		}
		
		/**
		 * This method checks regex to match if string follow currency amount rules (up to 999,999,999.99
		 * is supported)
		 * 
		 * @param amount String amount
		 * @return boolean 
		 */
		private boolean isValidAmount(String amount){
			if (amount.equals("   ")){
				return true;
			}
			Pattern pattern = Pattern.compile("[\\d{0,3},{0,1}\\d{0,3},{0,1}\\d{0,3}.{0,1}\\d{0,2}]+$");
			Matcher match = pattern.matcher(amount);
			
			return match.find();
		}
		
	}
	
	/**
	 * NumericValueVerifier is a class implementing verifier for fields with numeric value. 
	 * 
	 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
	 * @version 1.0
	 *
	 */
	class NumericValueVerifier extends InputVerifier{

		/**
		 * Verification method for component - it attempts to cast it to JTextField.
		 * 
		 * @param input JComponent input. 
		 */
		@Override
		public boolean verify(JComponent input) {
			try{
				JTextField tField = (JTextField)input;
				if(checkIfNumeric(tField.getText())){
					input.setBackground(UIManager.getColor("TextField.background"));
					return true;
				} else {
					input.setBackground(Color.RED);
					return false;
				}
			} catch (ClassCastException e){
				getLogger().log(Level.SEVERE, "NumericValueVerifier attempted to check non-text field element (" + input.getName() +")! Cannot verify!"); 
				return false;
			}
		}
		
		/**
		 * This method checks regex to match if string contains only digits.
		 * 
		 * @param amount String amount
		 * @return boolean 
		 */
		private boolean checkIfNumeric(String val){
			if(val.equals("   ")){
				return true;
			}
			Pattern pattern = Pattern.compile("^[\\d{0}.{0,1}\\d{0}]+$");
			Matcher match = pattern.matcher(val);
			
			return match.find();
		}
		
	}
	
	
	/**
	 * This is class for verifier checking if fields are empty. It's used
	 * for special case fields where no other verifier rules were applied.
	 * 
	 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
	 * @version 1.0
	 *
	 */
	class EmptyVerifier extends InputVerifier{

		/**
		 * This method checks if field is empty. 
		 * 
		 * @param input JComponent
		 * @return boolean
		 */
		@Override
		public boolean verify(JComponent input) {
			try{
				JTextField tField = (JTextField)input;
				if(tField.getText().isEmpty() || tField.getText() == null){
					input.setBackground(Color.RED);
					return false;
				} else {
					input.setBackground(UIManager.getColor("TextField.background"));
					return true;
				}
			} catch (ClassCastException e){
				getLogger().log(Level.SEVERE, "EmptyVerifier attempted to check non-text field element (" + input.getName() +")! Cannot verify!");
				return false;
			}
		}
		
	}

	
	@Override
	public boolean verifyForm() {
		return subListener.verifyForm();
	}

}

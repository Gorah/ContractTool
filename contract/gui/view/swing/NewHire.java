package contract.gui.view.swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.michaelbaranov.microba.calendar.DatePicker;



public class NewHire extends SwingView {
	
	public JLabel refL = new JLabel("Contract Reference");
	public JTextField ref = new JTextField(10);
	public JLabel refCredsL = new JLabel("HRBP credentials");
	public JTextField refCreds = new JTextField("JB", 5);
	public JPanel nhPanel = new JPanel();
	public JPanel contractDetails = new JPanel();
	public JTabbedPane tabs = new JTabbedPane();
	public JPanel genInfo = new JPanel();
	public JPanel eeDetails = new JPanel();
	public JPanel posDetails = new JPanel();
	public JPanel addDetails = new JPanel();
	public JPanel addContractElems = new JPanel();
	public JPanel benefitsPanel = new JPanel();
	public JPanel additionalPay = new JPanel();
	public JTextField conID = new JTextField();
	public JLabel eeidL = new JLabel("Employee ID");
	public JTextField eeid = new JTextField(9);
	public JTextField name = new JTextField(15);
	public JTextField lName = new JTextField(30);
	public JLabel nameL = new JLabel("First Name");
	public JLabel lNameL = new JLabel("Last Name");
	public JLabel addressLine1Label = new JLabel("Address Line 1:");
	public JLabel addressLine2Label = new JLabel("Address Line 2:");
	public JLabel cityLabel = new JLabel("City:");
	public JLabel postalCodeLabel = new JLabel("Postal Code:");
	public JLabel countryLabel = new JLabel("Country");
	public JTextField addressLine1 = new JTextField(30);
	public JTextField addressLine2 = new JTextField(30);
	public JTextField city = new JTextField(15);
	public JTextField postalCode = new JTextField(10);
	public String[] countries = {"Choose country...","UK", "Scotland", "Ireland"};
	public JComboBox<String> country = new JComboBox<String>(countries);
	public JLabel jobL = new JLabel("Position Title");
	public JTextField job = new JTextField(15);
	public JLabel jobNL = new JLabel("Position Number");
	public JTextField jobN = new JTextField(15);
	public JLabel ggsL = new JLabel("GGS:");
	public JTextField ggs = new JTextField(4);
	public JLabel locationL = new JLabel("Location");
	public JTextField location = new JTextField(15);
	public JLabel buL = new JLabel("Business Area");
	public JTextField bu = new JTextField(30);
	public JLabel conTypeL = new JLabel("Contract Type");
	public String[] conTypes = {"Permanent", "Temporary"};
	public JComboBox<String> conType = new JComboBox<String>(conTypes);
	public JLabel workContractL = new JLabel("Work Contract");
	public String[] workContractTypes = {"Full Time", "Part Time"};
	public JComboBox<String> workContract = new JComboBox<String>(workContractTypes);
	public JLabel managerL = new JLabel("Line Manager");
	public JTextField manager = new JTextField(15);
	public JLabel manPhoneL = new JLabel("Manager's phone number");
	public JTextField manPhone = new JTextField(15);
	public JLabel effDateL = new JLabel("Start date:");
	public DatePicker effDate = new DatePicker();
	public JCheckBox dateTBC = new JCheckBox("Date to be confirmed");
	public JCheckBox isCTS = new JCheckBox("CTS");
	public JTextField annPay = new JTextField(15);
	public JLabel annPayL = new JLabel("Annual Pay:");
	public JTextField shiftVal = new JTextField(15);
	public JLabel reloAmountL = new JLabel("Relocation Amount");
	public JTextField reloAmount = new JTextField(15);
	public JLabel hoursWorkL = new JLabel("Hours of work");
	public JTextField hoursWork = new JTextField(5);
	public JLabel relocationL = new JLabel("Relocation");
	public JCheckBox relocation = new JCheckBox();
	public JLabel reloLocationL = new JLabel("Relocation location");
	public JTextField reloLocation = new JTextField(15);
	public JLabel signatoryReqL = new JLabel("Signatory Name requied");
	public JCheckBox signatoryReq = new JCheckBox();
	public JLabel singatoryNameL = new JLabel("Signatory Name");
	public JTextField signatoryName = new JTextField(15);
	public JLabel trialL = new JLabel("Trial Period:");
	public JCheckBox trial = new JCheckBox();
	public JLabel trialDurationL = new JLabel("Trial Duration");
	public JTextField trialDuration = new JTextField(5);
	public JLabel signOnL = new JLabel("Sign-On bonus");
	public JCheckBox signOn = new JCheckBox();
	public JLabel signOnAmountL = new JLabel("Sign On bonus Amount");
	public JTextField signOnAmount = new JTextField(10);
	public JLabel travelSuppL = new JLabel("Travel Supplement");
	public JCheckBox travelSupp = new JCheckBox();
	public JLabel travelSuppAmountL = new JLabel("Travel Supplement Amount");
	public JTextField travelSuppAmount = new JTextField(10);
	public JLabel travelSuppDateL = new JLabel("Travel Supplement Start Date");
	public DatePicker travelSuppDate = new DatePicker();
	public JLabel travelSuppDurL = new JLabel("Travel Supplement Duration");
	public JTextField travelSuppDur = new JTextField(5);
	public JLabel pencePerMileL = new JLabel("Pence per mile");
	public JTextField pencePerMile = new JTextField(5);
	public JLabel persQualFeeL = new JLabel("Personal Qualification Fees");
	public JCheckBox persQualFee = new JCheckBox();
	public JLabel compMobileL = new JLabel("Company Mobile Phone");
	public JCheckBox compMobile = new JCheckBox();
	public JLabel compCreditCardL = new JLabel("Company Credit Card");
	public JCheckBox compCreditCard = new JCheckBox();
	public JLabel compCarL = new JLabel("Company Car:");
	public String[] carOpts = {"None", "Company Car", "Company Van", "Company Car Cash Allowance"};
	public JComboBox<String> compCar = new JComboBox<String>(carOpts);
	public JLabel shiftPayL = new JLabel("Shift/Inconvenience Pay");
	public JCheckBox shiftPay = new JCheckBox();
	public JLabel shiftPayValL = new JLabel("Shift Pay Value");
	public JTextField shiftPayVal = new JTextField(15);
	public JLabel compCompL = new JLabel("Competition Compliance");
	public JCheckBox compComp = new JCheckBox();
	public JButton submitBut = new JButton("Generate Contract");
	private InputVerifier pureTextVerifier = new NameVerifier();

	
	public NewHire() {
		super(Name.NEW_HIRE);
		
		name.setInputVerifier(pureTextVerifier);
		lName.setInputVerifier(pureTextVerifier);
		city.setInputVerifier(pureTextVerifier);
		
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
		ref.setEnabled(false);
		//HRBP credentials for reference
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
		//Line Manager
		cn.gridx = 0;
		cn.gridy = 3;
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
		//Signatory name required
		cn.gridy = 4;
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
		cn.gridy = 5;
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
		cn.gridy = 6;
		cn.gridx = 0;
		posDetails.add(hoursWorkL, cn);
		cn.gridx = 1;
		posDetails.add(hoursWork, cn);
		//CTS
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(isCTS, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		//Annual Pay
		cn.gridy = 7;
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
					reloLocation.setEnabled(true);
					reloAmount.setEnabled(true);
				} else {
					reloLocation.setEnabled(false);
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
					trialDuration.setEnabled(true);
				} else {
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
					signOnAmount.setEnabled(true);
				} else {
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
		cn.gridy = 4;
		cn.gridx = 0;
		addContractElems.add(compCompL, cn);
		cn.gridx = 1;
		addContractElems.add(compComp, cn);
		
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
							travelSuppDate.setEnabled(true);
							travelSuppDur.setEnabled(true);
							pencePerMile.setEnabled(true);
						} else {
							travelSuppAmount.setEnabled(false);
							try {
								travelSuppDate.setDate(null);
							} catch (PropertyVetoException e1) {
								e1.printStackTrace();
							}
							travelSuppDate.setEnabled(false);
							travelSuppDur.setEnabled(false);
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
		//Company Mobile
		cn.gridy = 6;
		cn.gridx = 0;
		benefitsPanel.add(compMobileL, cn);
		cn.gridx = 1;
		cn.anchor = GridBagConstraints.CENTER;
		benefitsPanel.add(compMobile, cn);
		cn.anchor = GridBagConstraints.WEST;
		//Company Credit Card
		cn.gridy = 7;
		cn.gridx = 0;
		benefitsPanel.add(compCreditCardL, cn);
		cn.gridx = 1;
		cn.anchor = GridBagConstraints.CENTER;
		benefitsPanel.add(compCreditCard, cn);
		cn.anchor = GridBagConstraints.WEST;
		//Company Car
		cn.gridy = 8;
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
					shiftPayVal.setEnabled(true);
				} else {
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
		submitBut.addActionListener(new SubmitEventListener());
		
		
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
		compMobile.setSelected(false);
		compCreditCard.setSelected(false);
		compCar.setSelectedIndex(0);
		shiftPay.setSelected(false);
		shiftPayVal.setText("");
		shiftPayVal.setEnabled(false);
		compComp.setSelected(false);
	}

	@Override
	public void render() {
		mainContainer.getContentPane().removeAll();
		clear();
		mainContainer.add(nhPanel);
		mainContainer.setVisible(true);


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
				if(!pureTextVerifier.verify(name)){
					JDialog errDial = new JDialog(mainContainer, "Fields verification failed!", false);
					JPanel contents = new JPanel();
					JLabel errDetails = new JLabel("Test error message");
					contents.add(errDetails);
					errDial.setContentPane(contents);
					errDial.setSize(300, 200);
					errDial.setLocationRelativeTo(mainContainer);
					errDial.setVisible(true);
					
					System.out.println("failed");
				} 
				if(!pureTextVerifier.verify(lName)){
					System.out.println("failed");
				} 
				if(!pureTextVerifier.verify(city)){
					System.out.println("failed");
				} 
			}
		}
		
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
				//error message - to be implemented: error log where it will print
				System.out.println("NameVerifier attempted to check non-text field element! Cannot verify!");
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
				//error message - to be implemented: error log where it will print
				System.out.println("AmountVerifier attempted to check non-text field element! Cannot verify!");
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
			Pattern pattern = Pattern.compile("(\\d{0,3},{0,1}\\d{0,3},{0,1}\\d{3}.{0,1}\\d{0,2})");
			Matcher match = pattern.matcher(amount);
			
			return match.find();
		}
		
	}

}

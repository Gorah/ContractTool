package contract.gui.view.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.michaelbaranov.microba.calendar.DatePicker;



public class NewHire extends SwingView {
	
	private JLabel refL = new JLabel("Contract Reference");
	private JTextField ref = new JTextField(10);
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
	private String[] countries = {"Choose country...","England", "Wales", "Scotland"};
	private JComboBox<String> country = new JComboBox<String>(countries);
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
	private String[] conTypes = {"Permanent", "Temporary"};
	private JComboBox<String> conType = new JComboBox<String>(conTypes);
	private JLabel workContractL = new JLabel("Work Contract");
	private String[] workContractTypes = {"Full Time", "Part Time"};
	private JComboBox<String> workContract = new JComboBox<String>(workContractTypes);
	private JLabel managerL = new JLabel("Line Manager");
	private JTextField manager = new JTextField(15);
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
	private String[] carOpts = {"None", "Company Car", "Company Van", "Company Car Cash Allowance"};
	private JComboBox<String> compCar = new JComboBox<String>(carOpts);
	private JLabel shiftPayL = new JLabel("Shift/Inconvenience Pay");
	private JCheckBox shiftPay = new JCheckBox();
	private JLabel shiftPayValL = new JLabel("Shift Pay Value");
	private JTextField shiftPayVal = new JTextField(15);
	private JLabel compCompL = new JLabel("Competition Compliance");
	private JCheckBox compComp = new JCheckBox();
	

	
	public NewHire() {
		super(Name.NEW_HIRE);
		
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
	}
	

	@Override
	public void render() {
		mainContainer.getContentPane().removeAll();
		mainContainer.add(nhPanel);
		mainContainer.setVisible(true);


	}

}

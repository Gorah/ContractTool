package contract.gui.view.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewHire extends SwingView {

	private JPanel nhPanel = new JPanel();
	private JTabbedPane tabs = new JTabbedPane();
	private JPanel page1 = new JPanel();
	private JPanel eeDetails = new JPanel();
	private JPanel posDetails = new JPanel();
	private JLabel conID = new JLabel("Contract ID: ");
	private JLabel eeData = new JLabel("Basic Employee Data");
	private JLabel eeidL = new JLabel("Employee ID:");
	private JTextField eeid = new JTextField(9);
	private JTextField name = new JTextField(15);
	private JTextField lName = new JTextField(30);
	private JLabel nameL = new JLabel("First Name:");
	private JLabel lNameL = new JLabel("Last Name:");
	private JLabel addressLine1Label = new JLabel("Address Line 1:");
	private JLabel addressLine2Label = new JLabel("Address Line 2:");
	private JLabel cityLabel = new JLabel("City:");
	private JLabel postalCodeLabel = new JLabel("Postal Code:");
	private JLabel countryLabel = new JLabel("Country:");
	private JTextField addressLine1 = new JTextField(30);
	private JTextField addressLine2 = new JTextField(30);
	private JTextField city = new JTextField(15);
	private JTextField postalCode = new JTextField(10);
	private JTextField country = new JTextField(15);
	private JLabel jobL = new JLabel("Job Title:");
	private JTextField job = new JTextField(15);
	private JLabel ggsL = new JLabel("GGS:");
	private JTextField ggs = new JTextField(4);
	private JLabel locationL = new JLabel("Location:");
	private JTextField location = new JTextField(15);
	private JLabel buL = new JLabel("Business Area:");
	private JTextField bu = new JTextField(30);
	private JLabel conTypeL = new JLabel("Contract Type:");
	private String[] conTypes = {"Permanent", "Fix Term Contract"};
	private JComboBox<String> conType = new JComboBox<String>(conTypes);
	private JLabel workContractL = new JLabel("Employment Type");
	private String[] workContractTypes = {"Full Time", "Part Time"};
	private JComboBox<String> workContract = new JComboBox<String>(workContractTypes);
	private JLabel managerL = new JLabel("Line Manager:");
	private JTextField manager = new JTextField(15);
	private JLabel manPhoneL = new JLabel("Manager's phone number:");
	private JTextField manPhone = new JTextField(15);
	private JLabel effDateL = new JLabel("Effective date:");
	private JTextField effDate = new JTextField(15);
	private JCheckBox dateTBC = new JCheckBox("Date to be confirmed");
	private JCheckBox isCTS = new JCheckBox("CTS");
	private JTextField annPay = new JTextField(15);
	private JLabel annPayL = new JLabel("Annual Pay:");
	private JCheckBox isShift = new JCheckBox("Shift Pay");
	private JLabel shiftValL = new JLabel("Shift pay value:");
	private JTextField shiftVal = new JTextField(15);
	private JLabel reloAmountL = new JLabel("Relocation Amount:");
	private JTextField reloAmount = new JTextField(15);
	private JLabel reloLocationL = new JLabel("Relocation location:");
	private JTextField reloLocation = new JTextField(15);
	private JLabel hoursWorkL = new JLabel("Hours of work:");
	private JTextField hoursWork = new JTextField(5);
	
	
	
	

	
	public NewHire() {
		super(Name.NEW_HIRE);
		
		NewHireListener nhListener = new NewHireListener();
		
		nhPanel.setLayout(new GridBagLayout());
		GridBagConstraints cn = new GridBagConstraints();
		cn.anchor = GridBagConstraints.WEST;
		cn.insets = new Insets(2,2,2,2);
		
		cn.gridx = 0;
		cn.gridy = 0;
		nhPanel.add(conID, cn);
		
		cn.gridy = 0;
		//First tab
		page1.setLayout(new GridBagLayout());
		page1.add(eeData, cn);
		
		//EE Details panel setup
		eeDetails.setBorder(BorderFactory.createTitledBorder("Employee Details"));
		eeDetails.setLayout(new GridBagLayout());
		cn.gridx = 0;
		cn.gridy = 0;
		cn.insets = new Insets(15, 2, 2, 2);
		eeDetails.add(eeidL, cn);
		cn.gridx = 1;
		eeDetails.add(eeid, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridy = 1;
		cn.gridx = 0;
		eeDetails.add(nameL, cn);
		cn.gridx = 1;
		eeDetails.add(name, cn);
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		eeDetails.add(lNameL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		eeDetails.add(lName, cn);
		cn.gridy = 2;
		cn.gridx = 0;
		eeDetails.add(addressLine1Label, cn);
		cn.gridx = 1;
		cn.gridwidth = 3;
		eeDetails.add(addressLine1, cn);
		cn.gridy = 3;
		cn.gridwidth = 1;
		cn.gridx = 0;
		eeDetails.add(addressLine2Label, cn);
		cn.gridx = 1;
		cn.gridwidth = 3;
		eeDetails.add(addressLine2, cn);
		cn.gridy = 4;
		cn.gridwidth = 1;
		cn.gridx = 0;
		eeDetails.add(cityLabel, cn);
		cn.gridx = 1;
		eeDetails.add(city, cn);
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		eeDetails.add(postalCodeLabel, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		eeDetails.add(postalCode, cn);
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
		page1.add(eeDetails, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.ipadx = 0;
		
		//Position details panel setup
		posDetails.setBorder(BorderFactory.createTitledBorder("Position Details"));
		posDetails.setLayout(new GridBagLayout());
		cn.gridx = 0;
		cn.gridy = 0;
		cn.insets = new Insets(15, 2, 2, 2);
		posDetails.add(jobL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 1;
		posDetails.add(job, cn);
		cn.gridwidth = 1;
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(ggsL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		posDetails.add(ggs, cn);
		cn.gridy = 1;
		cn.gridx = 0;
		posDetails.add(locationL, cn);
		cn.gridx = 1;
		posDetails.add(location, cn);
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(buL, cn);
		cn.gridx = 3;
		cn.insets = new Insets(2, 2, 2, 2);
		posDetails.add(bu, cn);
		cn.gridy = 2;
		cn.gridx = 0;
		posDetails.add(conTypeL, cn);
		cn.gridx = 1;
		posDetails.add(conType, cn);
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(workContractL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		posDetails.add(workContract, cn);
		cn.gridx = 0;
		cn.gridy = 3;
		posDetails.add(managerL, cn);
		cn.gridx = 1;
		posDetails.add(manager, cn);
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(manPhoneL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		posDetails.add(manPhone, cn);
		cn.gridy = 4;
		cn.gridx = 0;
		posDetails.add(effDateL, cn);
		cn.gridx = 1;
		posDetails.add(effDate, cn);
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		dateTBC.addActionListener(nhListener);
		posDetails.add(dateTBC, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridy = 5;
		cn.gridx = 0;
		posDetails.add(hoursWorkL, cn);
		cn.gridx = 1;
		posDetails.add(hoursWork, cn);
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(isCTS, cn);
		cn.gridx = 3;
		isShift.addActionListener(nhListener);
		posDetails.add(isShift, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridy = 6;
		cn.gridx = 0;
		posDetails.add(annPayL, cn);
		cn.gridx = 1;
		posDetails.add(annPay, cn);
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		posDetails.add(shiftValL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		shiftVal.setEnabled(false);
		posDetails.add(shiftVal, cn);
		cn.gridy = 7;
		cn.gridx = 0;
		posDetails.add(reloAmountL, cn);
		cn.gridx = 1;
		reloAmount.getDocument().addDocumentListener(new RelocationListener());
		posDetails.add(reloAmount, cn);
		cn.gridx = 2;
		cn.insets = new Insets(2, 8, 2, 2);
		reloLocationL.setVisible(false);
		posDetails.add(reloLocationL, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 3;
		reloLocation.setVisible(false);
		posDetails.add(reloLocation, cn);
		
		//Add position details to first tab
		cn.gridx = 0;
		cn.gridy = 2;
		cn.insets = new Insets(15, 8, 2, 8);
		cn.ipadx = 15;
		page1.add(posDetails, cn);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.ipadx = 0;
		
		tabs.add("General Info", page1);
		cn.insets = new Insets(2, 2, 2, 2);
		cn.gridx = 0;
		cn.gridy = 1;
		cn.fill = GridBagConstraints.BOTH;
		nhPanel.add(tabs, cn);
	}
	
	
	private class NewHireListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == dateTBC){
				if(dateTBC.isSelected()){
					effDate.setEnabled(false);
					
				} else {
					effDate.setEnabled(true);
				}
			} else if (e.getSource() == isShift){
				if(isShift.isSelected()){
					shiftVal.setEnabled(true);
				} else {
					shiftVal.setEnabled(false);
				}
			}
			
		}
		
	}
	
	private class RelocationListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			applyVisibility();
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			applyVisibility();
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			applyVisibility();
			
		}
		
	}
	
	private void applyVisibility(){
		if(reloAmount.getText().equals("")){
			reloLocationL.setVisible(false);
			reloLocation.setVisible(false);
		} else {
			reloLocationL.setVisible(true);
			reloLocation.setVisible(true);
		}
		
	}
	

	@Override
	public void render() {
		mainContainer.getContentPane().removeAll();
		mainContainer.add(nhPanel);
		mainContainer.setVisible(true);


	}

}

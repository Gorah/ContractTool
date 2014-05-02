package contract.gui.view.swing;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import contract.gui.view.swing.table.DateRenderer;
import contract.gui.view.swing.table.NewHireTableModel;

/**
 * OpenContract class is used to create Open Contract form view.
 * It takes care of querying for list of contracts that are 
 * satisfying provided criteria and enables user to filter results.
 * 
 * @author Bartosz Kratochwil (bartosz.kratochwil@hp.com)
 * @version 1.0
 */
public class OpenContract extends SwingView {
	
	private JPanel contents = new JPanel();
	private GridLayout gLayout = new GridLayout(0, 1);
	private JLabel eeidLabel = new JLabel("Employee ID");
	private JTextField eeid = new JTextField(6);
	private JLabel firstNameLabel = new JLabel("First Name");
	private JTextField name = new JTextField(15);
	private JLabel lastNameLabel = new JLabel("Last Name");
	private JTextField surname = new JTextField(30);
	private JButton searchButton = new JButton();
	private JPanel searchConditions = new JPanel();
	private JPanel buttonPanel = new JPanel(); 
	private JPanel interLine = new JPanel();
	private JLabel filterLabel = new JLabel("Filter Text:");
	private JTextField filterText = new JTextField();
	private NewHireTableModel entryData;
	private JTable tab; 
	private TableRowSorter<NewHireTableModel> sorter;
	private JScrollPane scrollPane;
	private Comparator<Integer> intCompare = new Comparator<Integer>() {
	    @Override
	    public int compare(Integer o1, Integer o2) {
	        return o1 - o2;
	    }
	};
	
	/**
	 * Only constructor for the class.
	 */
	public OpenContract(NewHireTableModel data) {
		//run parent constructor and register view under correct enum value
		super(Name.OPEN_CONTRACT);
		this.entryData = data;
		
		tab = new JTable(entryData);
		tab.setDefaultRenderer(Date.class, new DateRenderer());
		sorter = new TableRowSorter<NewHireTableModel>(entryData);
		sorter.setComparator(0, intCompare);
		sorter.setComparator(2, intCompare);
		tab.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tab.setFillsViewportHeight(true);
		tab.setRowSorter(sorter);
		tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//hide filter box and label
		filterLabel.setVisible(false);
		filterText.setVisible(false);
		scrollPane  = new JScrollPane(tab);
		
		//set grid layout for container panel
		contents.setLayout(gLayout);
		
		//set Search button text and action listener
		searchButton.setText("Search");
		searchButton.addActionListener(new SearchButtonListener());
		
		//set GridBagLayout for searchConditions sub-panel
		searchConditions.setLayout(new GridBagLayout());
		
		//create new instance of constraints and pre configure them
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.insets = new Insets(10, 20, 0, 0);
		
		//add employee ID label at x=0. y=0 coords
		cons.gridx = 0;
		cons.gridy = 0;
		searchConditions.add(eeidLabel, cons);
		
		//add employee id text box at x=1, y=0
		cons.gridx = 1;
		cons.gridy = 0;
		searchConditions.add(eeid, cons);
		
		//add interline panel 
		cons.insets = new Insets(10, 40, 0, 40);
		cons.gridx = 2;
		cons.gridy = 0;
		searchConditions.add(interLine, cons);
		
		//add first name label
		cons.insets = new Insets(10, 20, 0, 0);
		cons.gridx = 3;
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.LINE_END;
		searchConditions.add(firstNameLabel, cons);
		
		//add first name text box 
		cons.gridx = 4;
		cons.gridy = 0;
		cons.anchor = GridBagConstraints.CENTER;
		searchConditions.add(name, cons);
		
		//add last name label
		cons.gridx = 3;
		cons.gridy = 1;
		cons.anchor = GridBagConstraints.LINE_END;
		searchConditions.add(lastNameLabel, cons);
		
		//add last name text box
		cons.gridx = 4;
		cons.gridy = 1;
		cons.anchor = GridBagConstraints.CENTER;
		searchConditions.add(surname, cons);
		
		//set border layout for button panel and add centred button to that panel
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(searchButton, BorderLayout.CENTER);
		
		//add button panel to search conditions container
		cons.gridx = 2;
		cons.gridwidth = 2;
		cons.gridy = 2;
		cons.insets = new Insets(30, 20, 30, 0);
		searchConditions.add(buttonPanel, cons);
		
		//add filter label
		cons.gridx = 0;
		cons.gridy = 3;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = new Insets(10, 20, 0, 0);
		searchConditions.add(filterLabel, cons);
		
		//set listener for filter text box and add text box to container
		filterText.getDocument().addDocumentListener(new DataTableListener());
		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridwidth = 3;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = new Insets(10, 20, 0, 0);
		searchConditions.add(filterText, cons);
		
		//add search conditions panel to main container
		contents.add(searchConditions);
		
		//create table with result set and add it to main container
		
		
		//res = new DataTable();
		contents.add(scrollPane);
	}

	/**
	 * DataTableListener is an implementation for Document Listener.
	 * It's purpose is to apply appropriate filter on all table rows.
	 * 
	 * @author Bartosz Kratochwil (bartosz.kratochwil@hp.com)
	 * @version 1.0
	 * @see javax.swing.event.DocumentListener;
	 */
	private class DataTableListener implements DocumentListener{

		/**
		 * Applies filter on changed event.
		 * @param e DocumentEvent 
		 */
		@Override
    	public void changedUpdate(DocumentEvent e) {
			resultFilter();
    	}

		/**
		 * Applies filter on changed event.
		 * @param e DocumentEvent 
		 */
    	@Override
    	public void insertUpdate(DocumentEvent e) {
    		resultFilter();
    	}

    	/**
		 * Applies filter on changed event.
		 * @param e DocumentEvent 
		 */
    	@Override
    	public void removeUpdate(DocumentEvent e) {
    		resultFilter();
    	}
		
	}
	
	/**
	 * Implementation of regex based filter. Filter text box is 
	 * pattern source.
	 */
	public void resultFilter(){
		RowFilter<NewHireTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        //sets filter to the table
        sorter.setRowFilter(rf);
    }
	
	/**
	 * SearchButtonListener class implements action listener for
	 * search button click. Filter text box and label are shown
	 * and data querying event from AppController is fired. 
	 * 
	 * @author Bartosz Kratochwil (bartosz.kratochwil@hp.com)
	 * @version 0.6
	 * @see contract.controller.AppController
	 *
	 */
	private class SearchButtonListener implements ActionListener{

		/**
		 * When button is clicked this event it fired.
		 */
		@Override
		public void actionPerformed(ActionEvent ev) {
			//change visibility of filter label and text box
			filterLabel.setVisible(true);
            filterText.setVisible(true);
			//add searching and populating stuff through appController
            //TO IMPLEMENT
		}
		
	}
	
	/**
	 * This method cleans content pane of main window and adds content panel 
	 * from this class to it.
	 */
	@Override
	public void render() {
		mainContainer.getContentPane().removeAll();
		mainContainer.add(contents);
		mainContainer.setVisible(true);

	}

	@Override
	public void extractDataFromModel() {
		
	}

	@Override
	public void saveDataToModel() {}

	@Override
	public boolean verifyForm() {
		return true;
	}

	@Override
	public void setID(long id) {}

}

package contract.gui.view.swing;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

import com.ezware.oxbow.swingbits.table.filter.TableRowFilterSupport;

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
	private BorderLayout gLayout = new BorderLayout();
	private JLabel searchConsL = new JLabel("Find:");
	private JPanel searchConditions = new JPanel();
	private JPanel submit = new JPanel();
	private JPanel navPanel = new JPanel();
	private JTextField filterText = new JTextField();
	private NewHireTableModel entryData;
	private JTable tab; 
	private TableRowSorter<NewHireTableModel> sorter;
	private JScrollPane scrollPane;
	private JButton open = new JButton("Open");
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
		
		//Change main container of this form to BORDER LAYOUT!!!!
		
		tab = new JTable(entryData);
		TableRowFilterSupport.forTable(tab).apply();
		tab.setDefaultRenderer(Date.class, new DateRenderer());
		sorter = new TableRowSorter<NewHireTableModel>(entryData);
		sorter.setComparator(0, intCompare);
		sorter.setComparator(2, intCompare);
		tab.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tab.setFillsViewportHeight(true);
		tab.setRowSorter(sorter);
		tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane  = new JScrollPane(tab);
		
		//set grid layout for container panel
		contents.setLayout(gLayout);
		
		//set BoxLayout for searchConditions sub-panel
		searchConditions.setLayout(new BoxLayout(searchConditions, BoxLayout.LINE_AXIS));

		searchConditions.setBorder(BorderFactory.createEmptyBorder(10,150,10,150));
		searchConditions.add(Box.createRigidArea(new Dimension(0,25)));
		searchConsL.setAlignmentX(Component.LEFT_ALIGNMENT);
		searchConditions.add(searchConsL);
		searchConditions.add(Box.createRigidArea(new Dimension(10,0)));
		searchConditions.add(filterText);
		searchConditions.add(Box.createRigidArea(new Dimension(25,25)));
		
		navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.PAGE_AXIS));
		navPanel.add(searchConditions);
		
		//submit.setLayout(new BoxLayout(submit, BoxLayout.LINE_AXIS));
		submit.setLayout(new BoxLayout(submit, BoxLayout.LINE_AXIS));
		open.setAlignmentX(Component.CENTER_ALIGNMENT);
		open.setMinimumSize(new Dimension(100, 100));
		submit.add(Box.createHorizontalGlue());
		submit.add(open);
		submit.add(Box.createHorizontalGlue());
		navPanel.add(submit);
		
		open.addActionListener(new OpenButtonListener());
				
		//set listener for filter text box and add text box to container
		filterText.getDocument().addDocumentListener(new DataTableListener());
		
		//create table with result set and add it to main container
		contents.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(500, 300));
		searchConditions.setPreferredSize(new Dimension(500, 40));
		contents.add(navPanel, BorderLayout.PAGE_END);
		
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
	
	private class OpenButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ev) {
			try{
				int viewRow = tab.getSelectedRow();
				System.out.println(tab.getValueAt(viewRow, 0));
				appController.open_newHire_from_list((Integer) tab.getValueAt(viewRow, 0));
			} catch (IndexOutOfBoundsException e){
				System.out.println("Nothing selected!");
			}
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
		mainContainer.setSize(940, 550);
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

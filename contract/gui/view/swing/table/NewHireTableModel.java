package contract.gui.view.swing.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class NewHireTableModel extends AbstractTableModel{

	private String[] columns = {
				"Contract ID", 
				"Contract Reference", 
				"EEID",
				"Surname",
				"Forename",
				"Position Title",
				"Contract Start Date"};
	private List<HireEntry> hires;
	
	private static final long serialVersionUID = 1938866900520420245L;

	public NewHireTableModel(List<HireEntry> hires) {
		this.hires = hires;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return hires.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		HireEntry hire = hires.get(row);
		Object result = null;
		
		switch(col){
		case 0:
			result = hire.getId();
			break;
		case 1:
			result = hire.getContract_ref();
			break;
		case 2:
			result = hire.getEeid();
			break;
		case 3:
			result = hire.getSurname();
			break;
		case 4:
			result = hire.getForename();
			break;
		case 5:
			result = hire.getPosition_title();
			break;
		case 6:
			result = hire.getStart_date();
			break;
		}
		
		return result;
	}

}

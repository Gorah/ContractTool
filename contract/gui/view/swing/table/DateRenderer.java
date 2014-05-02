package contract.gui.view.swing.table;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableCellRenderer;

public class DateRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	protected void setValue(Object value) {
		setText(formatter.format((Date) value));
	}
	
}

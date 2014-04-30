package contract.gui.view.swing.table;

import java.util.Date;

public class HireEntry {
	private int id;
	private String contract_ref;
	private int eeid;
	private String surname;
	private String forename;
	private String position_title;
	private Date start_date;
	
	public HireEntry(int id, String contract_ref, int eeid, String surname, String forename, String position_title, Date start_date) {
		this.id = id;
		this.contract_ref = contract_ref;
		this.eeid = eeid;
		this.surname = surname;
		this.forename = forename;
		this.position_title = position_title;
		this.start_date = start_date;
	}

	public int getId() {
		return id;
	}

	public String getContract_ref() {
		return contract_ref;
	}

	public int getEeid() {
		return eeid;
	}

	public String getSurname() {
		return surname;
	}

	public String getForename() {
		return forename;
	}

	public String getPosition_title() {
		return position_title;
	}

	public Date getStart_date() {
		return start_date;
	}
	
	

}

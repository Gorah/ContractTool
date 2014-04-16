package contract.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import contract.model.ComboItem;

/**
 * JDBCOptionsRepository is a model class containing all the combo box item arrays.
 * These arrays are built from data fetched from database - fetch is done only once,
 * when program is initiated and data can be reused from memory afterwards.
 * 
 * @author Bartosz Kratochwil (bartosz.krtochwil@hp.com)
 * @version 1.0
 *
 */

public class JDBCOptionsRepository {
	private Connection conn;
	private PreparedStatement WORK_CONTRACT_OPTIONS;
	private PreparedStatement CONTRACT_TYPE_OPTIONS;
	private PreparedStatement COUNTRY_OPTIONS;
	private PreparedStatement COMPANY_CAR_OPTIONS;
	private ComboItem[] work_contracts;
	private ComboItem[] contract_types;
	private ComboItem[] countries;
	private ComboItem[] car_options;
	private static final Logger logger = LogManager.getLogger(JdbcNewHireRepository.class.getName());

	public JDBCOptionsRepository(DataSource dataSource) throws SQLException {
		conn = dataSource.getConnection();
		WORK_CONTRACT_OPTIONS = conn.prepareStatement("SELECT * FROM work_contract ORDER BY ID ASC");
		CONTRACT_TYPE_OPTIONS = conn.prepareStatement("SELECT * FROM contract_type ORDER BY ID ASC");
		COUNTRY_OPTIONS = conn.prepareStatement("SELECT * FROM countries ORDER BY ID ASC");
		COMPANY_CAR_OPTIONS = conn.prepareStatement("SELECT * FROM company_car ORDER BY ID ASC");
		setWorkContractItems();
		setContractTypeItems();
		setCountryItems();
	}
	
	
	/**
	 * This method assigns to a field an array of ComboItem objects built from DB data, representing 
	 * work contract options.
	 * 
	 */
	public void setWorkContractItems(){
		ComboItem[] comboItems;
		//array list is used as interim solution due to the fact that regular arrays size is immutable
		ArrayList<ComboItem> itemList = new ArrayList<ComboItem>();
		
		try {
			//Query database and populate array list with values. 
			ResultSet result = WORK_CONTRACT_OPTIONS.executeQuery();
			while(result.next()){
				itemList.add(new ComboItem(result.getInt(1), result.getString(2)));
			}
			
			//Initialise new array with needed size
			comboItems = new ComboItem[itemList.size()];
			//convert arraylist object into array
			comboItems = itemList.toArray(comboItems);
		} catch (SQLException e) {
			//initialise empty array to be returned
			comboItems = new ComboItem[0];
			logger.error(e.getMessage());
		} 
		
		this.work_contracts = comboItems;
	}
	
	/**
	 * This method assigns to a field an array of ComboItem objects built from DB data, representing 
	 * contract type options.
	 * 
	 */
	public void setContractTypeItems(){
		ComboItem[] comboItems;
		//array list is used as interim solution due to the fact that regular arrays size is immutable
		ArrayList<ComboItem> itemList = new ArrayList<ComboItem>();
		
		try {
			//Query database and populate array list with values. 
			ResultSet result = CONTRACT_TYPE_OPTIONS.executeQuery();
			while(result.next()){
				itemList.add(new ComboItem(result.getInt(1), result.getString(2)));
			}
			
			//Initialise new array with needed size
			comboItems = new ComboItem[itemList.size()];
			//convert arraylist object into array
			comboItems = itemList.toArray(comboItems);
		} catch (SQLException e) {
			//initialise empty array to be returned
			comboItems = new ComboItem[0];
			logger.error(e.getMessage());
		} 
		
		this.contract_types = comboItems;
	}
	
	/**
	 * This method assigns to a field an array of ComboItem objects built from DB data, representing 
	 * countries type options.
	 * 
	 */
	public void setCountryItems(){
		ComboItem[] comboItems;
		//array list is used as interim solution due to the fact that regular arrays size is immutable
		ArrayList<ComboItem> itemList = new ArrayList<ComboItem>();
		
		try {
			//Query database and populate array list with values. 
			ResultSet result = COUNTRY_OPTIONS.executeQuery();
			while(result.next()){
				itemList.add(new ComboItem(result.getInt(1), result.getString(2)));
			}
			
			//Initialise new array with needed size
			comboItems = new ComboItem[itemList.size()];
			//convert arraylist object into array
			comboItems = itemList.toArray(comboItems);
		} catch (SQLException e) {
			//initialise empty array to be returned
			comboItems = new ComboItem[0];
			logger.error(e.getMessage());
		} 
		
		this.countries = comboItems;
	}
	
	/**
	 * This method assigns to a field an array of ComboItem objects built from DB data, representing 
	 * countries type options.
	 * 
	 */
	public void setCarItems(){
		ComboItem[] comboItems;
		//array list is used as interim solution due to the fact that regular arrays size is immutable
		ArrayList<ComboItem> itemList = new ArrayList<ComboItem>();
		
		try {
			//Query database and populate array list with values. 
			ResultSet result = COMPANY_CAR_OPTIONS.executeQuery();
			while(result.next()){
				itemList.add(new ComboItem(result.getInt(1), result.getString(2)));
			}
			
			//Initialise new array with needed size
			comboItems = new ComboItem[itemList.size()];
			//convert arraylist object into array
			comboItems = itemList.toArray(comboItems);
		} catch (SQLException e) {
			//initialise empty array to be returned
			comboItems = new ComboItem[0];
			logger.error(e.getMessage());
		} 
		
		this.car_options = comboItems;
	}


	/**
	 * Getter for work contracts
	 * 
	 * @return ComboItem[]
	 */
	public ComboItem[] getWork_contracts() {
		return work_contracts;
	}

	
	/**
	 * Getter for contract types
	 * 
	 * @return ComboItem[]
	 */
	public ComboItem[] getContract_types() {
		return contract_types;
	}


	/**
	 * Getter for work countries
	 * 
	 * @return ComboItem[]
	 */
	public ComboItem[] getCountries() {
		return countries;
	}


	/**
	 * Getter for car options
	 * 
	 * @return ComboItem[]
	 */
	public ComboItem[] getCar_options() {
		return car_options;
	}

	
	
}

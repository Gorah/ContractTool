package contract.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import contract.model.HireAbstractModel;
import contract.model.HireModel;

public class JdbcNewHireRepository implements NewHire {

	private Connection conn;
	private PreparedStatement INSERT_EMPLOYEE;
	private PreparedStatement SELECT_EMPLOYEE;
	private PreparedStatement UPDATE_EMPLOYEE;
	private PreparedStatement FIND_NEWEST_EMPLOYEE;
	private static final Logger logger = LogManager.getLogger(JdbcNewHireRepository.class.getName());
	
	public JdbcNewHireRepository(DataSource dataSource) throws SQLException{
		conn = dataSource.getConnection();
		INSERT_EMPLOYEE = conn.prepareStatement("INSERT INTO Hires VALUES ()");
		SELECT_EMPLOYEE = conn.prepareStatement("SELECT * FROM Hires WHERE ContractID = ?");
		UPDATE_EMPLOYEE = conn.prepareStatement("UPDATE Hires SET WHERE ID = ?");
		FIND_NEWEST_EMPLOYEE = conn.prepareStatement("SELECT TOP 1 ID FROM Hires WHERE Name = ?, LastName = ? ORDER BY ID Desc");
	}

	/**
	 * This method is responsible for adding new entry to database.
	 * 
	 * @param hire HireAbstractModel
	 * @return long Database ID of newly added entry
	 */
	@Override
	public long add(HireAbstractModel hire) throws EntityAlreadyExistsException {
		
		//check if entry is already in DB. If yes - throw exception
		try {
			HireAbstractModel entryExists = get(Long.parseLong(hire.getDetail("ID"), 10));
			if(!entryExists.equals(null)){
				throw new EntityAlreadyExistsException();
			}
			
		} catch (NumberFormatException | NoSuchElementException | EntityNotFoundException e) {
			logger.error(e.getMessage());
			return 0;
		}
		
		//execute insertion of new record to DB
		try {
			
			//ADD QUERY FILLUP WITH DATA
			
			INSERT_EMPLOYEE.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return 0;
		}
		
		
		//Find ID of newly added entry
		try {
			return getNewestEntryID(hire);
		} catch (EntityNotFoundException e) {
			logger.error("Could not find newly added entry in database.");
			return 0;
		}
	}

	@Override
	public HireAbstractModel get(long id) throws EntityNotFoundException {
		try {
			SELECT_EMPLOYEE.setLong(1, id);
			ResultSet result = SELECT_EMPLOYEE.executeQuery();
			if(result.next()){
				
				HireAbstractModel hireModel = new HireModel();
				//ADD NewHire Object fill
				
				return hireModel;
			} else {
				throw new EntityNotFoundException();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean update(HireAbstractModel hire) throws EntityNotFoundException  {
		//check if entry is already in DB. If not - throw exception
		try {
			HireAbstractModel entryExists = get(Long.parseLong(hire.getDetail("ID"), 10));
			if(entryExists.equals(null)){
				throw new EntityNotFoundException();
			}
		} catch (NumberFormatException | NoSuchElementException | EntityNotFoundException e) {
			logger.error(e.getMessage());
			return false;
		}
		
		try {
			if(UPDATE_EMPLOYEE.executeUpdate() == 0){
				return false;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	private long getNewestEntryID(HireAbstractModel hire) throws EntityNotFoundException{
		try {
			FIND_NEWEST_EMPLOYEE.setString(1, hire.getDetail("forenames"));
			FIND_NEWEST_EMPLOYEE.setString(2, hire.getDetail("surname"));
			
			ResultSet result = FIND_NEWEST_EMPLOYEE.executeQuery();
			
			if(result.next()){
				return result.getLong("ID");
			} else {
				throw new EntityNotFoundException();
			}
		} catch (NoSuchElementException | SQLException e) {
			logger.error(e.getMessage());
			return 0;
		}
	}

}

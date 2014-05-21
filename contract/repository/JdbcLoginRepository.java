package contract.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import contract.logging.ContractLogger;
import contract.security.BCrypt;

public class JdbcLoginRepository implements Login {
	private Connection conn;
	private PreparedStatement QUERY_USER;
	private static final Logger logger = new ContractLogger(JdbcNewHireRepository.class.getName()).getLogger();
	
	public JdbcLoginRepository(DataSource dataSource) throws SQLException {
		this.conn = dataSource.getConnection();
		QUERY_USER = conn.prepareStatement("SELECT usr_name, password, active FROM users WHERE usr_name = ?");
	}

	public String validate_user(String user, String password){
		try {
			QUERY_USER.setString(1, user);
			ResultSet result = QUERY_USER.executeQuery();
			
			if(result.next() && BCrypt.checkpw(password, result.getString("password"))){
				if(result.getBoolean("active")){
					return "success";
				} else {
					return "User account disabled. Please contact administrator!";
				}
			} else {
				return "Wrong user name or password!";
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
		
		return "";
	}
}

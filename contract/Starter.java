package contract;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.microsoft.sqlserver.jdbc.SQLServerConnectionPoolDataSource;

import contract.controller.AppController;
import contract.gui.view.swing.Main;
import contract.gui.view.swing.NewHire;
import contract.gui.view.swing.OpenContract;
import contract.logging.ContractLogger;
import contract.model.Settings;
import contract.repository.JDBCOptionsRepository;

public class Starter {

	public static void main(String[] args) {
		Logger logger = new ContractLogger(Starter.class.getName()).getLogger();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			Logger.getLogger(Starter.class.getName())
				.log(Level.WARNING, "Invalid look and feel class");
		}
		
		SQLServerConnectionPoolDataSource dataSource = new SQLServerConnectionPoolDataSource();
		dataSource.setURL("jdbc:" + Settings.DBTYPE + "://BPOPLMCBC16;databaseName=" + Settings.DB_NAME);
		dataSource.setUser(Settings.DB_USER);
		dataSource.setPassword(Settings.DB_PASS);
		
		ContractTool tool;
		try {
			tool = new ContractTool(new JDBCOptionsRepository(dataSource));
			AppController app = new AppController(tool);
			app.addViews(new Main(),
						new OpenContract(),
						new NewHire());
			app.init();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Cannot initialise program. " + e.getMessage());
			System.exit(0);
		}
	}

}

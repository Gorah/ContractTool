package contract.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ContractLogger {
	
	private Logger logger;

	public ContractLogger(String className) {
		logger = Logger.getLogger(className);
		
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream(System.getProperty("user.dir") + "\\src\\logger.properties"));
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		logger.setLevel(Level.FINE);
		
		try {
			File f = new File(System.getProperty("user.dir") +"\\logs\\Error.log");
//			if(!f.isFile()){
//				f.createNewFile();
//			}
			Handler fileHandler = new FileHandler(f.getPath());
			fileHandler.setFormatter(new ContractLogFormatter());
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	public Logger getLogger() {
		return logger;
	}

	
}

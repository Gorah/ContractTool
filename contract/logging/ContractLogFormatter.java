package contract.logging;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ContractLogFormatter extends Formatter {

	@Override
	public String format(LogRecord log) {

		return new Date(log.getMillis()) + " " + log.getSourceClassName() +"."
				+ log.getSourceMethodName() + ": " + log.getMessage() + System.getProperty("line.separator");
	}

}

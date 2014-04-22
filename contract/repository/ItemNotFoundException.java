package contract.repository;

public class ItemNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException() {
		super();
	}

	public ItemNotFoundException(String message) {
		super(message);
	}

	public ItemNotFoundException(Throwable cause) {
		super(cause);
	}

	public ItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}

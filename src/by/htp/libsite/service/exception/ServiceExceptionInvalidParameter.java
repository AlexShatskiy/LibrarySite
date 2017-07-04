package by.htp.libsite.service.exception;

public class ServiceExceptionInvalidParameter extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceExceptionInvalidParameter() {
		super();
	}

	public ServiceExceptionInvalidParameter(String message) {
		super(message);
	}

	public ServiceExceptionInvalidParameter(Exception e) {
		super(e);
	}

	public ServiceExceptionInvalidParameter(String message, Exception e) {
		super(message, e);
	}
}

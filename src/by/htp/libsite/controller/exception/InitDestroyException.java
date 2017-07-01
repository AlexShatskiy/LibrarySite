package by.htp.libsite.controller.exception;
//utf-8
public class InitDestroyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InitDestroyException() {
		super();
	}

	public InitDestroyException(String message) {
		super(message);
	}

	public InitDestroyException(Exception e) {
		super(e);
	}

	public InitDestroyException(String message, Exception e) {
		super(message, e);
	}
}

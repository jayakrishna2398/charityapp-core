package exception;

import java.sql.SQLException;

public class DBException extends SQLException {
	private static final long serialVersionUID = 1L;

	public DBException(String message) {
		super(message);
	}

	public DBException(String message, Throwable t) {
		super(message, t);
	}
}

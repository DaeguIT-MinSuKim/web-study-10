package web_study_10.dao.impl;

@SuppressWarnings("serial")
public class CustomSQLException extends RuntimeException {

    public CustomSQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomSQLException(Throwable cause) {
        super(cause);
    }

}

package bcsoft.it.glam.exception;

public class EmailException extends RuntimeException {
    public EmailException(String message, Exception e) {
        super(message, e);
    }

}

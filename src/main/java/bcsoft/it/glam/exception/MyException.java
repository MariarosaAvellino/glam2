package bcsoft.it.glam.exception;

public class MyException extends RuntimeException {
    public MyException(String message, Exception e) {
        super(message, e);

    }

    public MyException(String message) {
        super(message);
    }
}

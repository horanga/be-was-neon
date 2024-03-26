package exception;

public class InvalidHttpMethodException extends RuntimeException{

    public InvalidHttpMethodException() {
    }

    public InvalidHttpMethodException(String message) {
        super(message);
    }
}

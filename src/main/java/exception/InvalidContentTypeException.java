package exception;

public class InvalidContentTypeException extends RuntimeException{

    public InvalidContentTypeException() {
    }

    public InvalidContentTypeException(String message) {
        super(message);
    }
}

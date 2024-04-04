package exception;

public class InvalidPostRequestException extends RuntimeException{

    public InvalidPostRequestException() {
    }

    public InvalidPostRequestException(String message) {
        super(message);
    }
}

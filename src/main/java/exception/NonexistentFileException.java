package exception;

public class NonexistentFileException extends RuntimeException {

    public NonexistentFileException() {
    }

    public NonexistentFileException(String message) {
        super(message);
    }
}

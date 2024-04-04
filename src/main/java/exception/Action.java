package exception;

import java.io.IOException;

@FunctionalInterface
public interface Action {

    public void execute() throws IOException;
}

package exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
public class ReadFileException extends IOException {
    private String message;

    @Override
    public String toString() {
        String msg = "ReadFileException - can not read data from file";
        if (message != null) {
            msg += ": " + message;
        }
        return msg;
    }
}

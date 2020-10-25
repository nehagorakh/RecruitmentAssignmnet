package recruitment.assignment.recruitment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class IncorrectDataException extends RuntimeException{
    public IncorrectDataException(String message) {
        super(message);
    }
}

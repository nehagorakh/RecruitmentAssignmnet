package recruitment.assignment.recruitment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason="Object Order Found")
public class NotFoundException extends RuntimeException {
    String message;
    public NotFoundException(String message) {
        this.message = message;
    }
    public NotFoundException(){

    }
}
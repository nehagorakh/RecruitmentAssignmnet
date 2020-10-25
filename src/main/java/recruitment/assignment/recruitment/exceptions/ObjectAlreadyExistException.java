package recruitment.assignment.recruitment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT,reason="Resource already available")
public class ObjectAlreadyExistException extends RuntimeException {

}
package recruitment.assignment.recruitment.exceptions;

import org.springframework.stereotype.Component;

/**
 * Created by Nagoor on 05-May-2020.
 */
@Component
public class CustomException extends Exception{

    private int code;
    private String message;

    public CustomException(String message) {
        super(message);
    }
    public CustomException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}



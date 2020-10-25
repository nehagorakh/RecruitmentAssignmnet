package recruitment.assignment.recruitment.request;

import lombok.Data;
import lombok.NonNull;
import org.aspectj.bridge.IMessage;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.security.PublicKey;

@Data
public class ApplicationRequest {
    @Min(value = 1, message = "please provide valid offerId")
    private long offerId;

    @NonNull
    @NotBlank(message = "Email can not be blank")
    @Email(message = "please provide valid Email")
    private String candidateEmail;

    private String resumeText;

    public ApplicationRequest(){}
}

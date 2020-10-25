package recruitment.assignment.recruitment.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OfferRequest {
    @NotBlank
    @NotNull
    private String jobTitle;
}

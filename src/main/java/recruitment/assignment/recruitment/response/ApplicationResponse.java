package recruitment.assignment.recruitment.response;

import lombok.Data;

@Data
public class ApplicationResponse {
    private long offerId;
    private String candidateEmail;
    private String resumeText;
    private long applicationId;
    private String applicationStatus;
}

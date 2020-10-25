package recruitment.assignment.recruitment.response;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OfferResponse {

    private String jobTitle;

    private Date startDate;

    private long offerId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private long numberOfApplications;

}

package recruitment.assignment.recruitment.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "application_model", uniqueConstraints = {@UniqueConstraint(columnNames = {"offer_id", "candidate_email"})})
@DynamicUpdate
@Data
public class ApplicationModel {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "application_id")
    private long applicationId;

    @Column(name = "offer_id")
    private long offerId;

    @Lob
    @Column(name = "resume_text")
    private String resumeText;

    @NotBlank
    @Column(name = "application_status")
    private String applicationStatus;

    @Email
    @NotNull
    @Column(name = "candidate_email")
    private String candidateEmail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private boolean deleted;

    public interface APPLICATION_STATUS{
        String APPLIED = "applied";
        String INVITED = "invited";
        String REJECTED = "rejected";
        String HIRED = "hired";

    }
}

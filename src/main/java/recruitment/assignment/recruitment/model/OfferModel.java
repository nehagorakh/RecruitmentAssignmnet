package recruitment.assignment.recruitment.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "offer_model")
@DynamicUpdate
@Data
public class OfferModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @NotBlank
    @NotNull
    @Column(name = "job_title", unique = true)
    private String jobTitle;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "offer_id")
    private long offerId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private boolean deleted;
}

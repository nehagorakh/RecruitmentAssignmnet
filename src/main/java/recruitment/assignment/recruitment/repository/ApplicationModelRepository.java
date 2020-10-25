package recruitment.assignment.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recruitment.assignment.recruitment.model.ApplicationModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationModelRepository extends JpaRepository<ApplicationModel, Long> {
    long countByOfferIdAndDeleted(long offerId, boolean b);

    List<ApplicationModel> findByDeleted(boolean b);

    List<ApplicationModel> findByOfferIdAndDeleted(long offerId, boolean b);

    Optional<ApplicationModel> findByOfferIdAndApplicationIdAndDeleted(long offerId, long applicationId, boolean b);
}

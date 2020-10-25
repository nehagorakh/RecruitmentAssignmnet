package recruitment.assignment.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recruitment.assignment.recruitment.model.OfferModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferModelRepository extends JpaRepository<OfferModel, Long> {

    Optional<OfferModel> findByOfferIdAndDeleted(long offerId, boolean b);

    List<OfferModel> findByDeleted(boolean b);
}

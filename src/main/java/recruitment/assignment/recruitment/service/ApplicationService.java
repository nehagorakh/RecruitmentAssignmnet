package recruitment.assignment.recruitment.service;

import recruitment.assignment.recruitment.model.ApplicationModel;
import recruitment.assignment.recruitment.request.ApplicationRequest;
import recruitment.assignment.recruitment.response.UniversalResponse;

import java.util.List;

public interface ApplicationService {
    UniversalResponse applyForOffer(ApplicationRequest applicationRequest);

    long getApplicationCountPerOffer(long offerId);

    List<ApplicationModel> getAllApplication();

    UniversalResponse getAllApplicationPerOffer(long offerId);

    UniversalResponse getApplicationPerOfferForGivenApplicationId(long offerId, long applicationId);
}

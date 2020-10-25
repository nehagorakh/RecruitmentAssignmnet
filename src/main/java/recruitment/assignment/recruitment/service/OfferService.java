package recruitment.assignment.recruitment.service;

import recruitment.assignment.recruitment.request.OfferRequest;
import recruitment.assignment.recruitment.response.UniversalResponse;

public interface OfferService {
    UniversalResponse createOffer(OfferRequest offerRequest);

    UniversalResponse getSingleOffer(long offerId);

    UniversalResponse getAll();
}

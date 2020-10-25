package recruitment.assignment.recruitment.service.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import recruitment.assignment.recruitment.model.ApplicationModel;
import recruitment.assignment.recruitment.model.OfferModel;
import recruitment.assignment.recruitment.repository.OfferModelRepository;
import recruitment.assignment.recruitment.request.OfferRequest;
import recruitment.assignment.recruitment.response.OfferResponse;
import recruitment.assignment.recruitment.response.ResponseCodeJson;
import recruitment.assignment.recruitment.response.UniversalResponse;
import recruitment.assignment.recruitment.service.ApplicationService;
import recruitment.assignment.recruitment.service.OfferService;
import recruitment.assignment.recruitment.util.AtomicIdCounter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferModelRepository offerModelRepository;
    @Autowired
    ApplicationService applicationService;

    @Override
    public UniversalResponse createOffer(OfferRequest offerRequest) {
        UniversalResponse ur = new UniversalResponse();
        OfferModel offerModel = new OfferModel();
        BeanUtils.copyProperties(offerRequest, offerModel);
        offerModel.setCreatedAt(LocalDateTime.now());
        offerModel.setUpdatedAt(LocalDateTime.now());
        offerModel.setDeleted(false);
        offerModel.setOfferId(AtomicIdCounter.getRandomUID());
        offerModel.setStartDate(new Date());
        try {
            offerModel = offerModelRepository.save(offerModel);
        }catch (DataIntegrityViolationException ex){
             ur.setResponseCodeJson(new ResponseCodeJson("Offer With job title "+offerRequest.getJobTitle()+" already existed", HttpStatus.CONFLICT.value()));
             return ur;
        }
        OfferResponse offerResponse = new OfferResponse();
        BeanUtils.copyProperties(offerModel, offerResponse);
        ur.setObject(offerResponse);
        ur.setResponseCodeJson(new ResponseCodeJson("offer created successfully",
                HttpStatus.CREATED.value()));
        return ur;
    }

    @Override
    public UniversalResponse getSingleOffer(long offerId) {
        UniversalResponse ur = new UniversalResponse();
        if(offerId <= 0){
            ur.setResponseCodeJson(new ResponseCodeJson("please provide valid offerId",
                    HttpStatus.BAD_REQUEST.value()));
            return ur;
        }
        Optional<OfferModel> optionalOfferModel = offerModelRepository.findByOfferIdAndDeleted(offerId, false);
        if(!optionalOfferModel.isPresent()){
            ur.setResponseCodeJson(new ResponseCodeJson("no offer present with given offer id",
                    HttpStatus.NO_CONTENT.value()));
            return ur;
        }
        OfferResponse offerResponse = new OfferResponse();
        BeanUtils.copyProperties(optionalOfferModel.get(), offerResponse);
        //get number of application for this offer
        long numberOfApplications = applicationService.getApplicationCountPerOffer(offerId);
        offerResponse.setNumberOfApplications(numberOfApplications);
        ur.setObject(offerResponse);
        ur.setResponseCodeJson(new ResponseCodeJson("", HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponse getAll() {
        UniversalResponse ur = new UniversalResponse();
        List<OfferModel> offerModelList = offerModelRepository.findByDeleted(false);
        List<OfferResponse> offerResponses = new ArrayList<>(offerModelList.size());
        List<ApplicationModel> applicationModels = applicationService.getAllApplication();
        //getting counts with offer Id
        Map<Long, Long> applicationCoutMap = applicationModels.stream().collect(Collectors.groupingBy(application -> application.getOfferId(),
                Collectors.counting()));
        offerModelList.stream().forEach(offerModel -> {
          OfferResponse offerResponse = new OfferResponse();
          BeanUtils.copyProperties(offerModel, offerResponse);
          offerResponse.setNumberOfApplications(applicationCoutMap.get(offerModel.getOfferId()));
          offerResponses.add(offerResponse);
        });
        ur.setResponseCodeJson(new ResponseCodeJson("", HttpStatus.OK.value()));
        ur.setList(offerResponses);
        return ur;
    }
}

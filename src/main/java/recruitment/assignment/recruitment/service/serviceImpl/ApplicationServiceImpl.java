package recruitment.assignment.recruitment.service.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import recruitment.assignment.recruitment.model.ApplicationModel;
import recruitment.assignment.recruitment.repository.ApplicationModelRepository;
import recruitment.assignment.recruitment.request.ApplicationRequest;
import recruitment.assignment.recruitment.response.ApplicationResponse;
import recruitment.assignment.recruitment.response.ResponseCodeJson;
import recruitment.assignment.recruitment.response.UniversalResponse;
import recruitment.assignment.recruitment.service.ApplicationService;
import recruitment.assignment.recruitment.util.AtomicIdCounter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationModelRepository applicationModelRepository;

    @Override
    public UniversalResponse applyForOffer(ApplicationRequest applicationRequest) {
        UniversalResponse ur = new UniversalResponse();
        ApplicationModel applicationModel = new ApplicationModel();
        BeanUtils.copyProperties(applicationRequest, applicationModel);
        applicationModel.setApplicationId(AtomicIdCounter.getRandomUID());
        applicationModel.setApplicationStatus(ApplicationModel.APPLICATION_STATUS.APPLIED);
        applicationModel.setDeleted(false);
        applicationModel.setCreatedAt(LocalDateTime.now());
        applicationModel.setUpdatedAt(LocalDateTime.now());
        try {
            applicationModel = applicationModelRepository.save(applicationModel);
        }catch (DataIntegrityViolationException ex){
            ur.setResponseCodeJson(new ResponseCodeJson(applicationRequest.getCandidateEmail()+" Already applied for this job", HttpStatus.CONFLICT.value()));
            return ur;
        }
        ApplicationResponse applicationResponse = new ApplicationResponse();
        BeanUtils.copyProperties(applicationModel, applicationResponse);
        ur.setObject(applicationResponse);
        ur.setResponseCodeJson(new ResponseCodeJson("applied successfully", HttpStatus.CREATED.value()));
        return ur;
    }

    @Override
    public long getApplicationCountPerOffer(long offerId) {
        return applicationModelRepository.countByOfferIdAndDeleted(offerId, false);
    }

    @Override
    public List<ApplicationModel> getAllApplication() {
        return applicationModelRepository.findByDeleted(false);
    }

    @Override
    public UniversalResponse getAllApplicationPerOffer(long offerId) {
        UniversalResponse ur = new UniversalResponse();
        if(offerId <= 0){
            ur.setResponseCodeJson(new ResponseCodeJson("please provide valid offerId",
                    HttpStatus.BAD_REQUEST.value()));
            return ur;
        }
        List<ApplicationModel> applicationModelList = applicationModelRepository.findByOfferIdAndDeleted(offerId, false);
        List<ApplicationResponse> applicationResponseList = new ArrayList<>(applicationModelList.size());
        applicationModelList.stream().forEach(applicationModel -> {
            ApplicationResponse applicationResponse = new ApplicationResponse();
            BeanUtils.copyProperties(applicationModel, applicationResponse);
            applicationResponseList.add(applicationResponse);
        });
        ur.setList(applicationResponseList);
        ur.setResponseCodeJson(new ResponseCodeJson("", HttpStatus.OK.value()));
        return ur;
    }

    @Override
    public UniversalResponse getApplicationPerOfferForGivenApplicationId(long offerId, long applicationId) {
        UniversalResponse ur = new UniversalResponse();
        if(offerId <= 0 || applicationId <= 0){
            ur.setResponseCodeJson(new ResponseCodeJson("incorrect offerId or applicationId",
                    HttpStatus.BAD_REQUEST.value()));
            return ur;
        }
        Optional<ApplicationModel> optionalApplicationModel = applicationModelRepository.findByOfferIdAndApplicationIdAndDeleted(offerId,
                applicationId, false);
        if(!optionalApplicationModel.isPresent()){
            ur.setResponseCodeJson(new ResponseCodeJson("application not found with given application and offerId",
                    HttpStatus.NO_CONTENT.value()));
            return ur;
        }
        ApplicationModel applicationModel = optionalApplicationModel.get();
        ApplicationResponse applicationResponse = new ApplicationResponse();
        BeanUtils.copyProperties(applicationModel, applicationResponse);
        ur.setObject(applicationResponse);
        ur.setResponseCodeJson(new ResponseCodeJson("", HttpStatus.OK.value()));
        return ur;
    }
}

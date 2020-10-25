package recruitment.assignment.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recruitment.assignment.recruitment.exceptions.CustomException;
import recruitment.assignment.recruitment.exceptions.ExceptionThrower;
import recruitment.assignment.recruitment.request.ApplicationRequest;
import recruitment.assignment.recruitment.response.UniversalResponse;
import recruitment.assignment.recruitment.service.ApplicationService;

import javax.validation.Valid;

@RestController
@RequestMapping("application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @PostMapping(value = "")
    public ResponseEntity ApplyForOffer(@Valid @RequestBody ApplicationRequest applicationRequest) throws CustomException {
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        UniversalResponse ur  = applicationService.applyForOffer(applicationRequest);
        if (ur.getResponseCodeJson().getCode() != 201) {
            if(ur.getResponseCodeJson().getCode() == 204)
                return new ResponseEntity<>(ur, HttpStatus.NO_CONTENT);
            else
                exceptionThrower.throwCustomException(ur.getResponseCodeJson().getCode(), ur.getResponseCodeJson().getMessage());
        }
        return new ResponseEntity<>(ur, HttpStatus.CREATED);
    }
    @GetMapping(value = "/{offerId}")
    public ResponseEntity getAllApplicationPerOffer(@PathVariable long offerId) throws CustomException {
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        UniversalResponse ur  = applicationService.getAllApplicationPerOffer(offerId);
        if (ur.getResponseCodeJson().getCode() != 200) {
            exceptionThrower.throwCustomException(ur.getResponseCodeJson().getCode(), ur.getResponseCodeJson().getMessage());
        }
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
    @GetMapping(value = "/{offerId}/{applicationId}")
    public ResponseEntity getApplicationPerOfferForGivenApplicationId(@PathVariable long offerId, @PathVariable long applicationId) throws CustomException {
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        UniversalResponse ur  = applicationService.getApplicationPerOfferForGivenApplicationId(offerId, applicationId);
        if (ur.getResponseCodeJson().getCode() != 200) {
            if(ur.getResponseCodeJson().getCode() == 204)
                return new ResponseEntity<>(ur, HttpStatus.NO_CONTENT);
            else
            exceptionThrower.throwCustomException(ur.getResponseCodeJson().getCode(), ur.getResponseCodeJson().getMessage());
        }
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
}

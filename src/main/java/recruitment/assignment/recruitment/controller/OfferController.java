package recruitment.assignment.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recruitment.assignment.recruitment.exceptions.CustomException;
import recruitment.assignment.recruitment.exceptions.ExceptionThrower;
import recruitment.assignment.recruitment.request.OfferRequest;
import recruitment.assignment.recruitment.response.UniversalResponse;
import recruitment.assignment.recruitment.service.OfferService;

import javax.validation.Valid;

@RestController
@RequestMapping("offer")
public class OfferController {

    @Autowired
    OfferService offerService;

    @PostMapping(value = "")
    public ResponseEntity craeteOffer(@Valid @RequestBody OfferRequest offerRequest) throws CustomException {
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        UniversalResponse ur  = offerService.createOffer(offerRequest);
        if (ur.getResponseCodeJson().getCode() != 201) {
            if(ur.getResponseCodeJson().getCode() == 409)
                return new ResponseEntity<>(ur, HttpStatus.CONFLICT);
            else
            exceptionThrower.throwCustomException(ur.getResponseCodeJson().getCode(), ur.getResponseCodeJson().getMessage());
        }
        return new ResponseEntity<>(ur, HttpStatus.CREATED);
    }
    @GetMapping(value = "{offerId}")
    public ResponseEntity getSingleOffer(@PathVariable long offerId) throws CustomException {
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        UniversalResponse ur  = offerService.getSingleOffer(offerId);
        if (ur.getResponseCodeJson().getCode() != 200) {
            if(ur.getResponseCodeJson().getCode() == 204)
                return new ResponseEntity<>(ur, HttpStatus.NO_CONTENT);
            else
                exceptionThrower.throwCustomException(ur.getResponseCodeJson().getCode(), ur.getResponseCodeJson().getMessage());
        }
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity getAllOffers() throws CustomException {
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        UniversalResponse ur  = offerService.getAll();
        if (ur.getResponseCodeJson().getCode() != 200) {
                exceptionThrower.throwCustomException(ur.getResponseCodeJson().getCode(), ur.getResponseCodeJson().getMessage());
        }
        return new ResponseEntity<>(ur, HttpStatus.OK);
    }
}

package recruitment.assignment.recruitment.TestServices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import recruitment.assignment.recruitment.controller.OfferController;
import recruitment.assignment.recruitment.repository.OfferModelRepository;
import recruitment.assignment.recruitment.request.OfferRequest;
import recruitment.assignment.recruitment.response.UniversalResponse;
import recruitment.assignment.recruitment.service.OfferService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OfferService.class)
public class OfferTestService {
//    @Autowired
//    MockMvc mockMvc;
//
//    @InjectMocks
//    private OfferService offerService;
//
//    @Mock
//    OfferModelRepository offerModelRepository;
//
//    @Test
//    public void testcreateOffer() throws Exception{
//        OfferRequest offer  = new OfferRequest();
//        offer.setJobTitle("Software developer");
//        UniversalResponse ur = offerService.createOffer(offer);
//        assertEquals(201, ur.getResponseCodeJson().getCode());
//    }
}


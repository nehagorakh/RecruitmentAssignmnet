package recruitment.assignment.recruitment.testControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import recruitment.assignment.recruitment.request.OfferRequest;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OfferController.class)
public class OfferTestService {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OfferController offerController;

    @Test
    public void testcreateOffer() throws Exception{
        OfferRequest offer  = new OfferRequest();
        offer.setJobTitle("Software developer");

        String inputInJson = this.mapToJson(offer);
        String uri = "/offer";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                post(uri)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String outputInJson = mockHttpServletResponse.getContentAsString();
        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(200);
    }
    @Test
    public void getOfferByOfferId() throws Exception{
        String offerId = "1605514660783";
        String uri = "/offer/"+offerId;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get(uri)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String outputInJson = mockHttpServletResponse.getContentAsString();
        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(200);

    }
    @Test
    public void getAllOffers() throws Exception{
        String uri = "/offer";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get(uri)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String outputInJson = mockHttpServletResponse.getContentAsString();
        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(200);
    }
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

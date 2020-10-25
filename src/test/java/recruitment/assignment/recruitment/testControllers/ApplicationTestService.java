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
import recruitment.assignment.recruitment.controller.ApplicationController;
import recruitment.assignment.recruitment.controller.OfferController;
import recruitment.assignment.recruitment.request.ApplicationRequest;
import recruitment.assignment.recruitment.request.OfferRequest;
import recruitment.assignment.recruitment.service.ApplicationService;
import recruitment.assignment.recruitment.service.OfferService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ApplicationController.class)
public class ApplicationTestService {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ApplicationController applicationController;

    @Test
    public void applyOfferTest() throws Exception{
        ApplicationRequest applicationRequest  = new ApplicationRequest();
        applicationRequest.setCandidateEmail("neha@gmail.com");
        applicationRequest.setOfferId(1605514660783L);
        applicationRequest.setResumeText("sdkalgjaksl");

        String inputInJson = this.mapToJson(applicationRequest);
        String uri = "/application";

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
    public void getApplicationPerOffer() throws Exception{
        String offerId = "1605514660783";
        String uri = "/application/"+offerId;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                get(uri)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String outputInJson = mockHttpServletResponse.getContentAsString();
        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(200);
    }
    @Test
    public void getApplicationByApplicationId() throws Exception{
        String applicationId = "1605643786737";
        String offerId = "1605514660783";
        String uri = "/application/"+offerId+"/"+applicationId;

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

    //negative test cases
    @Test
    public void applyOfferTestForInvalidAndidateEmail() throws Exception{
        ApplicationRequest applicationRequest  = new ApplicationRequest();
        applicationRequest.setCandidateEmail("neha");
        applicationRequest.setOfferId(1605514660783L);
        applicationRequest.setResumeText("sdkalgjaksl");

        String inputInJson = this.mapToJson(applicationRequest);
        String uri = "/application";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.
                post(uri)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String outputInJson = mockHttpServletResponse.getContentAsString();
        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(400);
    }
}

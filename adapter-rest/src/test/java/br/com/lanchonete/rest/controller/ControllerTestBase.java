package br.com.lanchonete.rest.controller;

import br.com.lanchonete.port.repository.LogRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerTestBase {

    /**
     * URL part for the ping operation.
     */
    protected static final String PING_PART = "/ping";

    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    @Qualifier("mapperApi")
    protected ModelMapper modelMapper;
    @MockBean
    private LogRepository logRepository;

    protected static EasyRandom easyRandom;
    protected static ObjectMapper objectMapper;

    protected static ModelMapper modelMapperAPI;

    @BeforeAll
    public static void beforeTests() {
        easyRandom = new EasyRandom();
        objectMapper = new ObjectMapper();
        modelMapperAPI = new ModelMapper();
    }

    /**
     * Executes the specified request, tests the response status without parsing the response.
     *
     * @param requestBuilder the request builder
     * @param expectedStatus the expected response status
     * @return the response action
     * @throws Exception
     */
    protected ResultActions doRequest(RequestBuilder requestBuilder, HttpStatus expectedStatus)
            throws Exception {
        return mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(expectedStatus.value()));
    }

    /**
     * Executes the specified request, tests the response status and returns the parsed response.
     *
     * @param requestBuilder the request builder
     * @param expectedStatus the expected response status
     * @param responseType the expected returned type reference
     * @param <T> the response type
     * @return the actual response
     * @throws Exception
     */
    protected <T> T doRequest(RequestBuilder requestBuilder, HttpStatus expectedStatus, TypeReference<T> responseType)
            throws Exception {
        byte[] bytes = doRequest(requestBuilder, expectedStatus)
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        return objectMapper.readValue(bytes, responseType);
    }

    /**
     * Performs a GET and returns the ResultActions.
     *
     * @param url the endpoint url
     * @param expectedStatus the expected HttpStatus
     * @param uriVars the uri path variables
     * @return the ResultActions
     * @throws Exception if something goes wrong
     */
    protected ResultActions doGet(String url, HttpStatus expectedStatus, Object... uriVars) throws Exception {
        return doRequest(get(url, uriVars)
                        .contentType(MediaType.APPLICATION_JSON),
                expectedStatus);
    }

    /**
     * Performs a GET and returns the parsed response object.
     *
     * @param url the endpoint url
     * @param expectedStatus the expected status
     * @param responseType the expected response type
     * @param <T> the class of the expected response
     * @param uriVars the uri path variables
     * @return the parsed response
     * @throws Exception if something goes wrong
     */
    protected <T> T doGet(String url, HttpStatus expectedStatus, TypeReference<T> responseType, Object... uriVars) throws Exception {
        byte[] bytes = doGet(url, expectedStatus, uriVars)
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        return objectMapper.readValue(bytes, responseType);
    }

    /**
     * Performs a POST and returns ResultActions.
     *
     * @param url the endpoint url
     * @param body the request body content
     * @param expectedStatus the expected HttpStatus
     * @param uriVars the uri path variables
     * @return the ResultActions
     * @throws Exception if something goes wrong
     */
    protected ResultActions doPost(String url, Object body, HttpStatus expectedStatus, Object... uriVars) throws Exception {
        return doRequest(
                post(url, uriVars)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(body)),
                expectedStatus);
    }


    /**
     * Performs a POST and returns ResultActions.
     *
     * @param url the endpoint url
     * @param body the request body content
     * @param expectedStatus the expected HttpStatus
     * @param uriVars the uri path variables
     * @return the ResultActions
     * @throws Exception if something goes wrong
     */
    protected <T> T doPost(String url, Object body, HttpStatus expectedStatus, TypeReference<T> responseType,
                           Object... uriVars) throws Exception {
        byte[] bytes = doPost(url, body, expectedStatus, uriVars)
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        return objectMapper.readValue(bytes, responseType);
    }

    /**
     * Performs a POST and returns the parsed response.
     *
     * @param url the endpoint url
     * @param body the request body content
     * @param expectedStatus the expected HttpStatus
     * @param uriVars the uri path variables
     * @return the parsed response
     * @throws Exception if something goes wrong
     */
    protected ResultActions doPut(String url, Object body, HttpStatus expectedStatus, Object... uriVars) throws Exception {
        return doRequest(put(url, uriVars)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(body)),
                expectedStatus);
    }

    /**
     * Performs a POST and returns the parsed response.
     *
     * @param url the endpoint url
     * @param body the request body content
     * @param expectedStatus the expected HttpStatus
     * @param responseType the expected response type
     * @param <T> the class of the expected response
     * @param uriVars the uri path variables
     * @return the parsed response
     * @throws Exception if something goes wrong
     */
    protected <T> T doPut(String url, Object body, HttpStatus expectedStatus, TypeReference<T> responseType,
                          Object... uriVars) throws Exception {
        byte[] bytes = doPut(url, body, expectedStatus, uriVars)
                .andReturn()
                .getResponse()
                .getContentAsByteArray();
        return objectMapper.readValue(bytes, responseType);
    }

}

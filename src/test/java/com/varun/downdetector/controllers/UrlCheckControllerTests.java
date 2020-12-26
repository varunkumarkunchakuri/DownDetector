package com.varun.downdetector.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.varun.downdetector.AbstractTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(JUnitPlatform.class)
public class UrlCheckControllerTests extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @ParameterizedTest
    @CsvSource(value = 
    { 
    "https://www.google.com,Site is up!", 
    "https://www.varun.com,Site is down!",
    "randomstring,Not a valid URL",
    ",Not a valid URL"
    })
    public void getUrlStatusMessage(String url, String result) throws Exception {
        String uri = "/check?url=" + url;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(result, content);
    }

}

package com.zsj.controller;

import com.zsj.test.controller.GreetingController;
import com.zsj.test.service.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangshangji
 * @since 2021/2/1 11:39
 */
@WebMvcTest(GreetingController.class)
public class ControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private GreetingService service;

    @Test
    public void getListTest() throws Exception {
        mvc.perform(get("/greeting/greeting").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    ;
}

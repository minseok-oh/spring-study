package com.java.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        HomeController.class
})
class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;


}
package com.java.study.context;

import com.java.study.FileUploadController;
import com.java.study.global.handler.GlobalExceptionHandlerTest.ExceptionController;
import com.java.study.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        ExceptionController.class,
        FileUploadController.class
})
public class ApiTest {

    @MockitoBean
    public StorageService storageService;

    @Autowired
    public MockMvc mockMvc;

}

package com.java.study.global.handler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.java.study.context.ApiTest;
import com.java.study.storage.exception.StorageFileNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class GlobalExceptionHandlerTest extends ApiTest {

    @RestController
    public class ExceptionController {

        @GetMapping("/file-not-found")
        public void fileNotFound() {
            throw new StorageFileNotFoundException("존재하지 않는 파일입니다.");
        }
    }


    @Test
    void 예외가_발생하면_404를_반환한다() throws Exception {
        mockMvc.perform(get("/file-not-found"))
                .andExpect(status().isNotFound());
    }
}
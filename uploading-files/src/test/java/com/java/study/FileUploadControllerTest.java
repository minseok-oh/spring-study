package com.java.study;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.java.study.context.ApiTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;

class FileUploadControllerTest extends ApiTest {

    @Nested
    class 기본_페이지 {

        @Test
        void uploadForm_뷰가_반환된다() throws Exception {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("uploadForm"))
                    .andExpect(model().attributeExists("files"));
        }
    }

    @Nested
    class 파일_업로드 {
        @Test
        void 파일을_업로드하면_리다이렉트된다() throws Exception {
            MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "File content".getBytes());
            Mockito.doNothing().when(storageService).store(file);

            mockMvc.perform(multipart("/")
                            .file(file)
                            .flashAttr("message", "You successfully uploaded test.txt!"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/"))
                    .andExpect(flash().attribute("message", "You successfully uploaded test.txt!"));
        }

        @Test
        void 업로드된_파일이_없으면_에러가_발생한다() throws Exception {
            MockMultipartFile emptyFile = new MockMultipartFile("file", "", "text/plain", new byte[0]);
            Mockito.doNothing().when(storageService).store(emptyFile);

            mockMvc.perform(multipart("/")
                            .file(emptyFile))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/"))
                    .andExpect(flash().attributeExists("message"));
        }
    }

    @Nested
    class 파일_다운로드 {

        @Test
        void 파일을_정상적으로_다운로드한다() throws Exception {
            String filename = "test.txt";
            Resource resource = new ByteArrayResource("File content".getBytes());
            Mockito.when(storageService.loadAsResource(filename)).thenReturn(resource);

            mockMvc.perform(get("/files/{filename}", filename))
                    .andExpect(status().isOk())
                    .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\""))
                    .andExpect(content().string("File content"));
        }

        @Test
        void 존재하지_않는_파일을_요청하면_404를_반환한다() throws Exception {
            String filename = "not_exist.txt";
            Mockito.when(storageService.loadAsResource(filename)).thenReturn(null);

            mockMvc.perform(get("/files/{filename}", filename))
                    .andExpect(status().isNotFound());
        }
    }
}
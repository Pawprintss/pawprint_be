package com.recorders.pet_diary;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class HelloControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello1() throws Exception {

        mockMvc.perform(RestDocumentationRequestBuilders.get("/hello")
                        .accept("application/json")
                        .param("name", "User"))
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentationWrapper.document("hello/get-hello",
                       resource(
                               ResourceSnippetParameters.builder()
                                       .queryParameters(parameterWithName("name").description("query parameter name").optional())
                                       .responseFields(fieldWithPath("content").type(JsonFieldType.STRING).description("Greeting message"))
                                       .build()
                       )));
    }

    @Test
    public void hello2() throws Exception {
        Map<String,String> request=new HashMap<>();
        request.put("name","User");
        ObjectMapper objectMapper = new ObjectMapper();
        String data=objectMapper.writeValueAsString(request);
        mockMvc.perform(RestDocumentationRequestBuilders.post("/hello")
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data))
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentationWrapper.document("hello/post-hello",
                        resource(
                                ResourceSnippetParameters.builder()
                                        .requestFields(fieldWithPath("name").type(JsonFieldType.STRING).description("post data field"))
                                        .responseFields(fieldWithPath("content").type(JsonFieldType.STRING).description("Greeting message"))
                                        .build()
                        )));
    }
}

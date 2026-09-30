package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
public class PetControllerTest {
    
    @Autowired
    private WebApplicationContext context; 

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void 名前が空っぽの時に400エラーで弾かれることをテストする() throws Exception {
        String badRequestJson = "{\"name\":\"\", \"breed\":\"チワワ\"}";
        
        mockMvc.perform(post("/") 
                .contentType(MediaType.APPLICATION_JSON) 
                .content(badRequestJson)) 
                .andDo(print()) 
                .andExpect(status().isBadRequest()); 
    }
}
package com.fmolnar.demo.features.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebLayerFeatureTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnDefaultOkMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/free/test")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().string("Hello World"));
    }
}

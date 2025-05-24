package com.fmolnar.demo.features.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RestControllerFeatureService.class)
class WebMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // MockBean and SpyBean are deprecated to MockitoBean
    private FeatureService featureService;

    @Test
    void shouldHaveReturnOk() throws Exception {
        when(featureService.getFeatureName()).thenReturn("Rest Feature Service");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rs/test")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().string("Rest Feature Service"));
    }
}

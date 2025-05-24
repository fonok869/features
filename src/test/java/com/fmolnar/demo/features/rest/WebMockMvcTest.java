package com.fmolnar.demo.features.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebMockMvcTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @MockitoBean // MockBean and SpyBean are deprecated to MockitoBean
    private FeatureService featureService;

    @Test
    void shouldHaveReturnUnauthorized() throws Exception {
        when(featureService.getFeatureName()).thenReturn("Rest Feature Service");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rs/test")).andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"WS"})
    void shouldHaveReturnOk() throws Exception {
        when(featureService.getFeatureName()).thenReturn("Rest Feature Service");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rs/test")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().string("Rest Feature Service"));
    }
}

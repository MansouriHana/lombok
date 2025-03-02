package hm.springframework.spring6restmvc.controllers;

import hm.springframework.spring6restmvc.model.Beverage;
import hm.springframework.spring6restmvc.services.BeverageService;
import hm.springframework.spring6restmvc.services.BeverageServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeverageController.class)
class BeverageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BeverageService beverageService;

    BeverageServiceImpl beverageServiceImpl = new BeverageServiceImpl();

    @Test
    void getBeverageById() throws Exception {
        Beverage testBeverage = beverageServiceImpl.listBeverages().get(0);
        given(beverageService.getBeverageById(any(UUID.class))).willReturn(testBeverage);

        mockMvc.perform(get("/api/v1/beverages/" + testBeverage.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeverage.getId().toString())))
                .andExpect(jsonPath("$.beverageName", is(testBeverage.getBeverageName())));

    }
}
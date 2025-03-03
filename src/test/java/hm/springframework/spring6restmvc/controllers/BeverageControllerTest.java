package hm.springframework.spring6restmvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hm.springframework.spring6restmvc.model.BeverageDTO;
import hm.springframework.spring6restmvc.services.BeverageService;
import hm.springframework.spring6restmvc.services.BeverageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeverageController.class)
class BeverageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    BeverageService beverageService;

    BeverageServiceImpl beverageServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<BeverageDTO> beverageArgumentCaptor;
    @BeforeEach
    void setUp() {
        beverageServiceImpl = new BeverageServiceImpl();
    }
    @Test
    void testPatchBeer() throws Exception {
        BeverageDTO bev = beverageServiceImpl.listBeverages().get(0);

        Map<String, Object> bevMap = new HashMap<>();
        bevMap.put("beverageName", "New Name");

        mockMvc.perform(patch(BeverageController.BEVERAGE_PATH_ID , bev.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bevMap)))
                .andExpect(status().isNoContent());

        verify(beverageService).patchBeverageById(uuidArgumentCaptor.capture(), beverageArgumentCaptor.capture());

        assertThat(bev.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(bevMap.get("beverageName")).isEqualTo(beverageArgumentCaptor.getValue().getBeverageName());
    }
    @Test
    void testDeleteBeverage() throws Exception {
        BeverageDTO testBev = beverageServiceImpl.listBeverages().get(0);

        mockMvc.perform(delete(BeverageController.BEVERAGE_PATH_ID, testBev.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(beverageService).deleteBeverageById(uuidArgumentCaptor.capture());
        assertThat(testBev.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }
    @Test
    void testPutBeverage() throws Exception {
        BeverageDTO testBev = beverageServiceImpl.listBeverages().get(0);

        mockMvc.perform(put(BeverageController.BEVERAGE_PATH_ID,  testBev.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBev)))
                .andExpect(status().isNoContent());
        verify(beverageService).updateBeverage(any(UUID.class), any(BeverageDTO.class));
    }
    @Test
    void createBeverage() throws Exception {
        BeverageDTO testBev = beverageServiceImpl.listBeverages().get(0);
        testBev.setVersion(null);
        testBev.setId(null);

        given(beverageService.saveNewBeverage(any(BeverageDTO.class))).willReturn(beverageServiceImpl.listBeverages().get(1));
        mockMvc.perform(post(BeverageController.BEVERAGE_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBev)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
    @Test
    void getBeveragesList() throws Exception {

        given(beverageService.listBeverages()).willReturn(beverageServiceImpl.listBeverages());

        mockMvc.perform(get(BeverageController.BEVERAGE_PATH)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.length()", is(3)));
    }
    @Test
    void getBeverageById() throws Exception {
        BeverageDTO testBeverage = beverageServiceImpl.listBeverages().get(0);
        given(beverageService.getBeverageById(any(UUID.class))).willReturn(Optional.of(testBeverage));

        mockMvc.perform(get(BeverageController.BEVERAGE_PATH_ID, testBeverage.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeverage.getId().toString())))
                .andExpect(jsonPath("$.beverageName", is(testBeverage.getBeverageName())));

    }
    @Test
    void getBeerByIdNotFound() throws Exception {

        given(beverageService.getBeverageById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(BeverageController.BEVERAGE_PATH_ID, UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

}
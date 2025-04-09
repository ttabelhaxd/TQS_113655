package com.tqs.backend.controller;

import com.tqs.backend.entity.Restaurant;
import com.tqs.backend.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void testGetAllRestaurants() throws Exception {
        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetRestaurants_ReturnsListOfRestaurants() throws Exception {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1L);
        restaurant1.setName("Cantina dos Barcos");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Cantina da Barra");

        restaurantRepository.saveAll(List.of(restaurant1, restaurant2));

        mockMvc.perform(get("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Cantina dos Barcos"))
                .andExpect(jsonPath("$[1].name").value("Cantina da Barra"));
    }
}
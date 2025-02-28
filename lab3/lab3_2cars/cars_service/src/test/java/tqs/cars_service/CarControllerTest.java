package tqs.cars_service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tqs.cars_service.controller.CarController;
import tqs.cars_service.model.Car;
import tqs.cars_service.service.CarManagerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService carService;

    @Test
    public void testGetCarById() throws Exception {
        Car car = new Car(1L, "Toyota", "Corolla");
        when(carService.getCarById(1L)).thenReturn(car);

        mockMvc.perform(get("/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carId").value(1L))
                .andExpect(jsonPath("$.maker").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Corolla"));
    }
}

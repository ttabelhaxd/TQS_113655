package tqs.cars_service;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.cars_service.controller.CarController;
import tqs.cars_service.entity.Car;
import tqs.cars_service.service.CarManagerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService carService;

    private Car car1, car2;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        car1 = new Car(1L, "Toyota", "Corolla");
        car2 = new Car(2L, "Honda", "Civic");

        List<Car> cars = Arrays.asList(car1, car2);
        when(carService.getAllCars()).thenReturn(cars);
        when(carService.getCarById(1L)).thenReturn(car1);
        when(carService.saveCar(any(Car.class))).thenReturn(car1);
    }

    @Test
    void testGetAllCars() {
        RestAssuredMockMvc.given()
                .when().get("/cars")
                .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    void testGetCarById() {
        RestAssuredMockMvc.given()
                .when().get("/cars/1")
                .then()
                .statusCode(200)
                .body("maker", equalTo("Toyota"))
                .body("model", equalTo("Corolla"));
    }

    @Test
    void testCreateCar() {
        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("{\"maker\": \"Ford\", \"model\": \"Focus\"}")
                .when().post("/cars")
                .then()
                .statusCode(201);
    }
}

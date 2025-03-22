package tqs.lab6_5;


import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tqs.lab6_5.entity.Car;
import tqs.lab6_5.repository.CarRepository;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class CarAPITest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.flyway.enabled", () -> true);
        registry.add("spring.flyway.cleanDisabled", () -> false);
    }

    @LocalServerPort
    private int port;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        flyway.clean();
        flyway.migrate();

        carRepository.deleteAll();
        carRepository.save(new Car("Fiat", "Linea"));
        carRepository.save(new Car("Toyota", "Corolla"));
    }

    @Test
    void getAllCarsTest() {
        when()
                .get("/cars")
                .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    void getCarByIdTest() {
        Car car = carRepository.findAll().get(0);

        when()
                .get("/cars/" + car.getCarId())
                .then()
                .statusCode(200)
                .body("maker", equalTo(car.getMaker()))
                .body("model", equalTo(car.getModel()));
    }

}

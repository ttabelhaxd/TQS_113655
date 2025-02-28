package tqs.cars_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tqs.cars_service.model.Car;
import tqs.cars_service.repository.CarRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Test
    public void testFindById() {
        Car car = new Car(1L, "Toyota", "Corolla");
        carRepository.save(car);

        Car found = carRepository.findById(1L).orElse(null);

        assertNotNull(found);
        assertEquals(1L, found.getCarId());
        assertEquals("Toyota", found.getMaker());
        assertEquals("Corolla", found.getModel());
    }
}

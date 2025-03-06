package tqs.cars_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tqs.cars_service.model.Car;
import tqs.cars_service.repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Test
    void findByIdTest() {
        Car car = new Car("BMW", "M3");
        Car savedCar = carRepository.save(car);

        Car foundCar = carRepository.findById(savedCar.getCarId()).orElse(null);
        assertThat(foundCar).isNotNull();
        assertThat(foundCar.getMaker()).isEqualTo("BMW");
    }

    @Test
    void returnAllCarsTest() {
        Car car1 = new Car("Audi", "A4");
        Car car2 = new Car("Mercedes", "C-Class");

        carRepository.save(car1);
        carRepository.save(car2);

        List<Car> allCars = carRepository.findAll();
        assertThat(allCars).hasSize(2);
    }
}

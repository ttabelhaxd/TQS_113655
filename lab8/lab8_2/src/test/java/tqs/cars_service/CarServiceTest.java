package tqs.cars_service;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import tqs.cars_service.model.Car;
import tqs.cars_service.repository.CarRepository;
import tqs.cars_service.service.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @Test
    void returnAllCarsListTest() {
        Car car1 = new Car("Toyota", "Corolla");
        Car car2 = new Car("Honda", "Civic");

        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2));

        List<Car> result = carService.getAllCars();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getMaker()).isEqualTo("Toyota");
    }

    @Test
    void returnCarDetailsTest() {
        Car car = new Car("Ford", "Focus");
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        Optional<Car> found = carService.getCarDetails(1L);

        assertThat(found).isPresent();
        assertThat(found.get().getModel()).isEqualTo("Focus");
    }
}

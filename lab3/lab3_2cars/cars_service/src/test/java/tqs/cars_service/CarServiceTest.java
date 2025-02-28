package tqs.cars_service;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import tqs.cars_service.model.Car;
import tqs.cars_service.repository.CarRepository;
import tqs.cars_service.service.CarManagerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @Test
    public void testGetCarById() {
        Car car = new Car(1L, "Toyota", "Corolla");
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));

        Car result = carService.getCarById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getCarId());
        assertEquals("Toyota", result.getMaker());
        assertEquals("Corolla", result.getModel());
    }
}

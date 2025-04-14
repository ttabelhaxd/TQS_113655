package tqs.cars_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.cars_service.model.Car;
import tqs.cars_service.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Optional<Car> getCarDetails(Long carId) {
        return Optional.ofNullable(carRepository.findById(carId).orElse(null));
    }

}

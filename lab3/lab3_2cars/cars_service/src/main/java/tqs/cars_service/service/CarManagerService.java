package tqs.cars_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.cars_service.model.Car;
import tqs.cars_service.repository.CarRepository;

import java.util.List;

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
}

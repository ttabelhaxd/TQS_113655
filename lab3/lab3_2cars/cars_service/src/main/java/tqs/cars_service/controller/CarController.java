package tqs.cars_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.cars_service.model.Car;
import tqs.cars_service.service.CarManagerService;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarManagerService carService;

    @PostMapping
    public ResponseEntity<Car> createCar() {
        return ResponseEntity.ok(new Car());
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
    }
}

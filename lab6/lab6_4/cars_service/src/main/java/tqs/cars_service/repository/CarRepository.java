package tqs.cars_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.cars_service.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}

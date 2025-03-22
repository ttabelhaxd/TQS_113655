package tqs.lab6_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.lab6_5.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}

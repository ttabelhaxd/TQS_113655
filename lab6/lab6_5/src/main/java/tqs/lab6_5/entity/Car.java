package tqs.lab6_5.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String maker;
    private String model;

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

}

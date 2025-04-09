package com.tqs.backend.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReservationRequestDTOTest {

    @Test
    void testSettersAndGetters() {
        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setRestaurantId(1L);
        request.setDate(LocalDate.now());
        request.setType("ALMOCO");

        assertThat(request.getRestaurantId()).isEqualTo(1L);
        assertThat(request.getDate()).isEqualTo(LocalDate.now());
        assertThat(request.getType()).isEqualTo("ALMOCO");
    }
}
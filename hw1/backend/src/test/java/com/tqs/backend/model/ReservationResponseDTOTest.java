package com.tqs.backend.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReservationResponseDTOTest {

    @Test
    void testConstructorAndGetters() {
        ReservationResponseDTO response = new ReservationResponseDTO(
                "token123",
                "Cantina dos Barcos",
                LocalDate.now(),
                true,
                "ALMOCO"
        );

        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRestaurantName()).isEqualTo("Cantina dos Barcos");
        assertThat(response.getDate()).isEqualTo(LocalDate.now());
        assertThat(response.isCheckedIn()).isTrue();
        assertThat(response.getType()).isEqualTo("ALMOCO");
    }

    @Test
    void testSetters() {
        ReservationResponseDTO response = new ReservationResponseDTO(
                "token123",
                "Cantina dos Barcos",
                LocalDate.now(),
                true,
                "ALMOCO"
        );

        response.setToken("newToken");
        response.setRestaurantName("Cantina da Barra");
        response.setDate(LocalDate.now().plusDays(1));
        response.setCheckedIn(false);
        response.setType("JANTAR");

        assertThat(response.getToken()).isEqualTo("newToken");
        assertThat(response.getRestaurantName()).isEqualTo("Cantina da Barra");
        assertThat(response.getDate()).isEqualTo(LocalDate.now().plusDays(1));
        assertThat(response.isCheckedIn()).isFalse();
        assertThat(response.getType()).isEqualTo("JANTAR");
    }
}
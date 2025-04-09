package com.tqs.backend.service;

import com.tqs.backend.entity.Reservation;
import com.tqs.backend.entity.Restaurant;
import com.tqs.backend.enums.MealType;
import com.tqs.backend.model.ReservationRequestDTO;
import com.tqs.backend.model.ReservationResponseDTO;
import com.tqs.backend.repository.ReservationRepository;
import com.tqs.backend.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Restaurant mockRestaurant;

    @BeforeEach
    void setUp() {
        mockRestaurant = new Restaurant();
        mockRestaurant.setId(1L);
        mockRestaurant.setName("Cantina do Barcos");
    }

    @Test
    void testCreateReservation_Success() {
        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setRestaurantId(1L);
        request.setDate(LocalDate.now());
        request.setType("ALMOCO");

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(mockRestaurant));

        ReservationResponseDTO response = reservationService.createReservation(request);

        assertNotNull(response.getToken());
        assertEquals("Cantina do Barcos", response.getRestaurantName());
        assertEquals("ALMOCO", response.getType());
        assertFalse(response.isCheckedIn());

        verify(restaurantRepository).findById(1L);
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void testCreateReservation_RestaurantNotFound() {
        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setRestaurantId(99L);
        request.setDate(LocalDate.now());
        request.setType("JANTAR");

        when(restaurantRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                reservationService.createReservation(request)
        );

        verify(restaurantRepository).findById(99L);
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void testCreateReservation_DuplicateMealTypeSameDay() {
        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setRestaurantId(1L);
        request.setDate(LocalDate.now());
        request.setType("ALMOCO");

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(mockRestaurant));

        Reservation existingReservation = new Reservation();
        existingReservation.setRestaurant(mockRestaurant);
        existingReservation.setDate(request.getDate());
        existingReservation.setType(MealType.ALMOCO);
        existingReservation.setCancelled(false);

        when(reservationRepository.findByRestaurantIdAndDateAndTypeAndCancelledFalse(
                1L, request.getDate(), MealType.ALMOCO
        )).thenReturn(Optional.of(existingReservation));

        assertThrows(ResponseStatusException.class, () -> {
            reservationService.createReservation(request);
        });

        verify(restaurantRepository).findById(1L);
        verify(reservationRepository).findByRestaurantIdAndDateAndTypeAndCancelledFalse(
                1L, request.getDate(), MealType.ALMOCO);
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void testGetReservationByToken_Success() {
        Reservation reservation = new Reservation();
        reservation.setToken("token");
        reservation.setRestaurant(mockRestaurant);
        reservation.setDate(LocalDate.now());
        reservation.setCheckedIn(false);
        reservation.setType(MealType.ALMOCO);

        when(reservationRepository.findByToken("token")).thenReturn(Optional.of(reservation));

        ReservationResponseDTO response = reservationService.getReservationByToken("token");

        assertEquals("token", response.getToken());
        assertEquals("ALMOCO", response.getType());

        verify(reservationRepository).findByToken("token");
    }

    @Test
    void testGetReservationByToken_NotFound() {
        when(reservationRepository.findByToken("not-found")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                reservationService.getReservationByToken("not-found")
        );
    }

    @Test
    void testCancelReservation_TokenNotFound() {
        when(reservationRepository.findByToken("inexistente")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                reservationService.cancelReservation("inexistente")
        );
    }

    @Test
    void testCancelReservation_Success() {
        Reservation reservation = new Reservation();
        reservation.setToken("token");
        reservation.setCheckedIn(false);

        when(reservationRepository.findByToken("token")).thenReturn(Optional.of(reservation));

        reservationService.cancelReservation("token");

        assertTrue(reservation.isCancelled());
        verify(reservationRepository).findByToken("token");
        verify(reservationRepository).save(reservation);
    }

    @Test
    void testCheckInReservation_Success() {
        Reservation reservation = new Reservation();
        reservation.setToken("token");
        reservation.setCheckedIn(false);
        reservation.setCancelled(false);

        when(reservationRepository.findByToken("token")).thenReturn(Optional.of(reservation));

        reservationService.checkInReservation("token");

        assertTrue(reservation.isCheckedIn());
        verify(reservationRepository).findByToken("token");
        verify(reservationRepository).save(reservation);
    }

    @Test
    void testCheckInReservation_Cancelled() {
        Reservation reservation = new Reservation();
        reservation.setToken("token");
        reservation.setCheckedIn(false);
        reservation.setCancelled(true);

        when(reservationRepository.findByToken("token")).thenReturn(Optional.of(reservation));

        assertThrows(IllegalStateException.class, () ->
                reservationService.checkInReservation("token")
        );

        verify(reservationRepository).findByToken("token");
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void testCheckInReservation_AlreadyUsed() {
        Reservation reservation = new Reservation();
        reservation.setToken("token");
        reservation.setCheckedIn(true);
        reservation.setCancelled(false);

        when(reservationRepository.findByToken("token")).thenReturn(Optional.of(reservation));

        assertThrows(IllegalStateException.class, () ->
                reservationService.checkInReservation("token")
        );

        verify(reservationRepository).findByToken("token");
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void testCheckInReservation_TokenNotFound() {
        when(reservationRepository.findByToken("invalid")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                reservationService.checkInReservation("invalid")
        );

        verify(reservationRepository).findByToken("invalid");
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void testCreateReservation_InvalidMealType() {
        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setRestaurantId(1L);
        request.setDate(LocalDate.now());
        request.setType("INVALIDO");

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(mockRestaurant));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            reservationService.createReservation(request);
        });

        assertEquals("Tipo de refeição inválido", ex.getReason());
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }
}

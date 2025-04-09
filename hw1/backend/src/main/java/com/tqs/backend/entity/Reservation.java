package com.tqs.backend.entity;

import com.tqs.backend.enums.MealType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    private MealType type;

    private String token;
    private boolean checkedIn;
    private boolean cancelled = false;
    private LocalDate date;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public MealType getType() {
        return type;
    }
    public void setType(MealType type) {
        this.type = type;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }
    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public boolean isCancelled() {
        return cancelled;
    }
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

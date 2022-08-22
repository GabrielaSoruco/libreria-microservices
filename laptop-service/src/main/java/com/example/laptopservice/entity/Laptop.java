package com.example.laptopservice.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String model;
    private Double price;
    private Integer clientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop)) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(getId(), laptop.getId()) && Objects.equals(getBrand(), laptop.getBrand()) && Objects.equals(getModel(), laptop.getModel()) && Objects.equals(getPrice(), laptop.getPrice()) && Objects.equals(getClientId(), laptop.getClientId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBrand(), getModel(), getPrice(), getClientId());
    }
}

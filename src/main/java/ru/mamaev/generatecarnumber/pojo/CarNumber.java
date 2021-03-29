package ru.mamaev.generatecarnumber.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class CarNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "number", length = 15, nullable = false)
    private String carNumber;

    public CarNumber() {
    }

    public CarNumber(Long id, String carNumber) {
        this.id = id;
        this.carNumber = carNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarNumber carNumber = (CarNumber) o;
        return Objects.equals(id, carNumber.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

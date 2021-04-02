package ru.mamaev.generatecarnumber.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mamaev.generatecarnumber.pojo.CarNumber;

public interface CarNumberRepo extends JpaRepository<CarNumber, Long> {
}

package ru.mamaev.generatecarnumber.service;

import ru.mamaev.generatecarnumber.pojo.CarNumber;

import java.util.List;

public interface NumberService {
    String next() throws Exception;

    String random();

    CarNumber getById(Long id);

    void save(CarNumber number);

    void delete(Long id);

    List<CarNumber> getAll();
}

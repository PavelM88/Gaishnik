package ru.mamaev.generatecarnumber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mamaev.generatecarnumber.pojo.CarNumber;
import ru.mamaev.generatecarnumber.repo.CarNumberRepo;
import ru.mamaev.generatecarnumber.servise.GenerateCarNumber;

@RestController
@RequestMapping("/number")
public class GiveCarNumber {
    private GenerateCarNumber carNumber;
    private CarNumberRepo numberRepo;


    @Autowired
    public GiveCarNumber(GenerateCarNumber carNumber, CarNumberRepo numberRepo) {
        this.carNumber = carNumber;
        this.numberRepo = numberRepo;
    }

    @GetMapping("/random")
    public String getRandom() {
        return carNumber.random();
    }

    @GetMapping("/next")
    public String getNext(CarNumber number) throws Exception {
        number.setCarNumber(carNumber.next());
        numberRepo.save(number);
        return number.getCarNumber();
    }
}

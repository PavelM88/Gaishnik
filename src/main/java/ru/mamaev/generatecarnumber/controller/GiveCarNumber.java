package ru.mamaev.generatecarnumber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mamaev.generatecarnumber.servise.GenerateCarNumber;

@RestController
@RequestMapping("/number")
public class GiveCarNumber {
    private GenerateCarNumber carNumber;


    @Autowired
    public GiveCarNumber(GenerateCarNumber carNumber) {
        this.carNumber = carNumber;
    }

    @GetMapping("/random")
    public String getRandom() {
        return carNumber.random();
    }

    @GetMapping("/next")
    public String getNext() throws Exception {
        return carNumber.next();
    }
}

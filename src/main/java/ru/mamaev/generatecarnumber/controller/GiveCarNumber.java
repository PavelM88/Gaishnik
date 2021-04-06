package ru.mamaev.generatecarnumber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mamaev.generatecarnumber.pojo.CarNumber;
import ru.mamaev.generatecarnumber.service.NumberService;

@RestController
@RequestMapping("/number")
public class GiveCarNumber {
    private NumberService numberService;

    @Autowired
    public GiveCarNumber(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/random")
    public String getRandom() {
        return numberService.random();
    }

    @GetMapping("/next")
    public String getNext(CarNumber number) throws Exception {
        number.setCarNumber(numberService.next());
        numberService.save(number);
        return number.getCarNumber();
    }
}

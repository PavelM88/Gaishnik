package ru.mamaev.generatecarnumber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mamaev.generatecarnumber.pojo.CarNumber;
import ru.mamaev.generatecarnumber.repo.CarNumberRepo;

import java.util.List;
import java.util.Random;

@Service
public class GenerateCarNumber implements NumberService {

    @Autowired
    CarNumberRepo numberRepo;

    Random randomNumber = new Random();
    private final static char[] symbol = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
    private final static String region = " 116 RUS ";
    private int serialNumber;

    // позиции букв из массива symbol
    private static int firstPositionChar;
    private static int secondPositionChar;
    private static int thirdPositionChar;


    /**
     * Метод для рандомного создания номера машины
     *
     * @return номер машины
     */
    public String random() {
        StringBuilder number = new StringBuilder();
        char firstChar = symbol[randomNumber.nextInt(symbol.length)];
        char secondChar = symbol[randomNumber.nextInt(symbol.length)];
        char thirdChar = symbol[randomNumber.nextInt(symbol.length)];
        number.append(firstChar);
        number.append(getRandomNumber());
        number.append(secondChar);
        number.append(thirdChar);
        number.append(region);
        return number.toString();
    }

    /**
     * Метод получает рандомное 3-х значное число
     *
     * @return num
     */
    private StringBuilder getRandomNumber() {
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            num.append(randomNumber.nextInt(10));
        }
        return num;
    }

    /**
     * Метод получает номер последовательно
     *
     * @return carNumber
     * @throws Exception исключение если больше нет комбинаций для номеров
     */
    public String next() throws Exception {
        StringBuilder nextCarNumber = new StringBuilder();
        if (serialNumber > 999) {
            thirdPositionChar++;
        }
        if (thirdPositionChar > symbol.length - 1) {
            secondPositionChar++;
            thirdPositionChar = 0;
        }
        if (secondPositionChar > symbol.length - 1) {
            firstPositionChar++;
            secondPositionChar = 0;
        }
        if (firstPositionChar > symbol.length - 1) {
            throw new Exception("Комбинации номеров закончились!");
        }
        nextCarNumber.append(symbol[firstPositionChar]);
        nextCarNumber.append(ordinalNumber());
        nextCarNumber.append(symbol[secondPositionChar]);
        nextCarNumber.append(symbol[thirdPositionChar]);
        nextCarNumber.append(region);

        return nextCarNumber.toString();
    }

    /**
     * Метод для добавления префикса "0" или "00"
     *
     * @return prefix
     */
    private String ordinalNumber() {
        String prefix;
        if (serialNumber > 999) {
            serialNumber = 1;
        } else if (serialNumber == 0) {
            serialNumber = 1;
        }
        prefix = serialNumber < 10 ? "00" : "0";
        prefix = serialNumber < 100 ? prefix + serialNumber : String.valueOf(serialNumber);
        serialNumber++;
        return prefix;
    }

    @Override
    public CarNumber getById(Long id) {
        return numberRepo.getOne(id);
    }

    @Override
    public void save(CarNumber number) {
        numberRepo.save(number);
    }

    @Override
    public void delete(Long id) {
        numberRepo.deleteById(id);
    }

    @Override
    public List<CarNumber> getAll() {
        return numberRepo.findAll();
    }
}

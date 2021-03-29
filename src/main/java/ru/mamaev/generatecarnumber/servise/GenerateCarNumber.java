package ru.mamaev.generatecarnumber.servise;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateCarNumber {
    Random randomNumber = new Random();
    // для хранения номера
    private final static StringBuilder carNumber = new StringBuilder();
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
        char firstChar = symbol[randomNumber.nextInt(symbol.length)];
        char secondChar = symbol[randomNumber.nextInt(symbol.length)];
        char thirdChar = symbol[randomNumber.nextInt(symbol.length)];
        carNumber.append(firstChar);
        carNumber.append(getRandomNumber());
        carNumber.append(secondChar);
        carNumber.append(thirdChar);
        carNumber.append(region);
        return carNumber.toString();
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
     * @return carNumber
     * @throws Exception исключение если больше нет комбинаций для номеров
     */
    public String next() throws Exception {
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
        carNumber.append(symbol[firstPositionChar]);
        carNumber.append(ordinalNumber());
        carNumber.append(symbol[secondPositionChar]);
        carNumber.append(symbol[thirdPositionChar]);
        carNumber.append(region);

        return carNumber.toString();
    }

    /**
     * Метод для добавления префикса "0" или "00"
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
}

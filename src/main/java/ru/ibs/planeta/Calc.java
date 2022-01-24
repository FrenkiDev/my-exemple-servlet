package ru.ibs.planeta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calc {
    private double result;

    public void calculate(double a, double b, String math) {
        switch (math) {
            case "+": {
                result = a + b;
                break;
            }
            case "*": {
                result = a * b;
                break;
            }
            case "-": {
                result = a - b;
                break;
            }
            case "/": {
                assert b != 0;
                result = a / b;
                break;
            }
        }
    }
}

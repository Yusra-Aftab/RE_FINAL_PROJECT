package simplejavacalculator;

import static java.lang.Double.NaN;
import static java.lang.Math.*;

public class Calculator {

    public enum BiOperatorModes {
        NORMAL, ADD, MINUS, MULTIPLY, DIVIDE, X_POWER_OF_Y
    }

    public enum MonoOperatorModes {
        SQUARE, SQUARE_ROOT, ONE_DIVIDED_BY, COS, SIN, TAN, LOG, RATE, ABS, LN
    }

    private Double num1, num2;
    private BiOperatorModes mode = BiOperatorModes.NORMAL;

    private Double calculateBiImpl() {
        switch (mode) {
            case NORMAL:
                return num2;
            case ADD:
                return num1 + num2;
            case MINUS:
                return num1 - num2;
            case MULTIPLY:
                return num1 * num2;
            case DIVIDE:
                return num1 / num2;
            case X_POWER_OF_Y:
                return pow(num1, num2);
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
    }

    public Double calculateBi(BiOperatorModes newMode, Double num) {
        if (mode == BiOperatorModes.NORMAL) {
            num2 = 0.0;
            num1 = num;
            mode = newMode;
            return NaN;
        } else {
            num2 = num;
            num1 = calculateBiImpl();
            mode = newMode;
            return num1;
        }
    }

    public Double calculateEqual(Double num) {
        return calculateBi(BiOperatorModes.NORMAL, num);
    }

    public Double reset() {
        num2 = 0.0;
        num1 = 0.0;
        mode = BiOperatorModes.NORMAL;
        return NaN;
    }

    public Double calculateMono(MonoOperatorModes newMode, Double num) {
        switch (newMode) {
            case SQUARE:
                return num * num;
            case SQUARE_ROOT:
                return sqrt(num);
            case ONE_DIVIDED_BY:
                return 1 / num;
            case COS:
                return cos(toRadians(num));
            case SIN:
                return sin(toRadians(num));
            case TAN:
                if (num == 0 || num % 180 == 0) {
                    return 0.0;
                }
                if (num % 90 == 0 && num % 180 != 0) {
                    return NaN;
                }
                return tan(toRadians(num));
            case LOG:
                return log10(num);
            case LN:
                return log(num);
            case RATE:
                return num / 100;
            case ABS:
                return abs(num);
            default:
                throw new IllegalStateException("Unexpected value: " + newMode);
        }
    }

    public Double calculateFactorial(Double num) {
        if (num < 0 || num % 1 != 0) {
            return NaN;
        }

        double result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }

        return result;
    }

    public Double calculateSquareOfSum(Double num1, Double num2) {
        double sum = num1 + num2;
        return sum * sum;
    }

    public Double calculateCombination(Double n, Double r) {
        if (n < 0 || r < 0 || n < r) {
            return NaN;
        }
        return calculateFactorial(n) / (calculateFactorial(r) * calculateFactorial(n - r));
    }

    public Double calculatePermutation(Double n, Double r) {
        if (n < 0 || r < 0 || n < r) {
            return NaN;
        }
        return calculateFactorial(n) / calculateFactorial(n - r);
    }
}

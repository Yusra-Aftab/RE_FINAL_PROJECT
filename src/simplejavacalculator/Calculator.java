package simplejavacalculator;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Double.NaN;
import static java.lang.Math.log;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class Calculator {

    public enum BiOperatorModes {
        NORMAL, ADD, MINUS, MULTIPLY, DIVIDE, X_POWER_OF_Y
    }

    public enum MonoOperatorModes {
        SQUARE, SQUARE_ROOT, ONE_DIVIDED_BY, COS, SIN, TAN, LOG, RATE, ABS, LN,
    }

    private Double num1, num2;
    private BiOperatorModes mode = BiOperatorModes.NORMAL;

    private Double calculateBiImpl() {
        if (mode == BiOperatorModes.NORMAL) {
            return num2;
        }
        if (mode == BiOperatorModes.ADD) {
            if (num2 != 0) {
                return num1 + num2;
            }
            return num1;
        }
        if (mode == BiOperatorModes.MINUS) {
            return num1 - num2;
        }
        if (mode == BiOperatorModes.MULTIPLY) {
            return num1 * num2;
        }
        if (mode == BiOperatorModes.DIVIDE) {
            return num1 / num2;
        }
        if (mode == BiOperatorModes.X_POWER_OF_Y) {
            return pow(num1, num2);
        }

        throw new IllegalStateException("Unexpected value: " + mode);
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
                return Math.sqrt(num);
            case ONE_DIVIDED_BY:
                return 1 / num;
            case COS:
                return Math.cos(Math.toRadians(num));
            case SIN:
                return Math.sin(Math.toRadians(num));
            case TAN:
                if (num == 0 || num % 180 == 0) {
                    return 0.0;
                }
                if (num % 90 == 0 && num % 180 != 0) {
                    return NaN;
                }
                return Math.tan(Math.toRadians(num));
            case LOG:
                return log10(num);
            case LN:
                return log(num);
            case RATE:
                return num / 100;
            case ABS:
                return Math.abs(num);
            default:
                throw new IllegalStateException("Unexpected value: " + newMode);
        }
    }
}

class BufferedImageCustom {
    public Image imageReturn() throws IOException {
        InputStream bis = getClass().getResourceAsStream("/resources/icon/icon.png");
        return ImageIO.read(bis);
    }
}

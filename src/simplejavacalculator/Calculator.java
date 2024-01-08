/**
 * @name        Simple Java Calculator
 * @package     ph.calculator
 * @file        Main.java
 * @author      SORIA Pierre-Henry
 * @email       pierrehs@hotmail.com
 * @link        http://github.com/pH-7
 * @copyright   Copyright Pierre-Henry SORIA, All Rights Reserved.
 * @license     Apache (http://www.apache.org/licenses/LICENSE-2.0)
 */

package simplejavacalculator;

import static java.lang.Double.NaN;
import static java.lang.Math.log;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class Calculator {

    public enum BiOperatorModes {
        normal, add, minus, multiply, divide , xpowerofy 
    }

    public enum MonoOperatorModes {
        square, squareRoot, oneDividedBy, cos, sin, tan, log, rate, abs, ln,
    }

    private Double num1, num2;
    private BiOperatorModes mode = BiOperatorModes.normal;

    private Double calculateBiImpl() {
        if (mode == BiOperatorModes.normal) {
            return num2;
        }
        if (mode == BiOperatorModes.add) {
            if (num2 != 0) {
                return num1 + num2;
            }

            return num1;
        }
        if (mode == BiOperatorModes.minus) {
            return num1 - num2;
        }
        if (mode == BiOperatorModes.multiply) {
            return num1 * num2;
        }
        if (mode == BiOperatorModes.divide) {
            return num1 / num2;
        }
        if (mode == BiOperatorModes.xpowerofy) {
            return pow(num1,num2);
        }

        // never reach
        throw new Error();
    }

    public Double calculateBi(BiOperatorModes newMode, Double num) {
        if (mode == BiOperatorModes.normal) {
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
        return calculateBi(BiOperatorModes.normal, num);
    }

    public Double reset() {
        num2 = 0.0;
        num1 = 0.0;
        mode = BiOperatorModes.normal;

        return NaN;
    }

    
    public Double calculateMono(MonoOperatorModes newMode, Double num) {
        if (newMode == MonoOperatorModes.square) {
            return num * num;
        }
        if (newMode == MonoOperatorModes.squareRoot) {
            return Math.sqrt(num);
        }
        if (newMode == MonoOperatorModes.oneDividedBy) {
            return 1 / num;
        }
        if (newMode == MonoOperatorModes.cos) {
            return Math.cos(Math.toRadians(num));
        }
        if (newMode == MonoOperatorModes.sin) {
            return Math.sin(Math.toRadians(num));
        }
        if (newMode == MonoOperatorModes.tan) {
            if (num == 0 || num % 180 == 0) {
                return 0.0;
            }
            if (num % 90 == 0 && num % 180 != 0) {
                return NaN;
            }

            return Math.tan(Math.toRadians(num));
        }
        if (newMode == MonoOperatorModes.log) {
            return log10(num);
        }
        if (newMode == MonoOperatorModes.ln) {
            return log(num);
        }
        if (newMode == MonoOperatorModes.rate) {
           return num / 100;
        }
        if (newMode == MonoOperatorModes.abs){
            return Math.abs(num);
        }

        // never reach
        throw new Error();
    }

    public Double calculateFactorial(Double num) {
        // Check if the number is a non-negative integer
        if (num < 0 || num % 1 != 0) {
            return NaN; // Factorial is undefined for negative numbers or non-integers
        }

        // Calculate the factorial using a loop
        double result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }

        return result;
    }

 
    //New Functions

    
    public Double calculateSquareOfSum(Double num1, Double num2) {
        double sum = num1 + num2;
        return sum * sum;
    }


    public Double calculateCombination(Double n, Double r) {
        if (n < 0 || r < 0 || n < r) {
            return NaN; // Combination is undefined for these cases
        }
        
        return calculateFactorial(n) / (calculateFactorial(r) * calculateFactorial(n - r));
    }

    public Double calculatePermutation(Double n, Double r) {
        if (n < 0 || r < 0 || n < r) {
            return NaN; // Permutation is undefined for these cases
        }

        return calculateFactorial(n) / calculateFactorial(n - r);
    }

}

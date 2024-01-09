package simplejavacalculatorTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import simplejavacalculator.Calculator;

public class AfterRefactoringTests {
    
    @Test
    void testBasicOperations() {
        Calculator calculator = new Calculator();

        // Addition
        assertEquals(5.0, calculator.calculateBi(Calculator.BiOperatorModes.add, 5.0));
        assertEquals(8.0, calculator.calculateEqual(3.0));

        // Subtraction
        assertEquals(4.0, calculator.calculateBi(Calculator.BiOperatorModes.minus, 4.0));
        assertEquals(2.0, calculator.calculateEqual(2.0));

        // Multiplication
        assertEquals(10.0, calculator.calculateBi(Calculator.BiOperatorModes.multiply, 5.0));
        assertEquals(50.0, calculator.calculateEqual(5.0));

        // Division
        assertEquals(2.0, calculator.calculateBi(Calculator.BiOperatorModes.divide, 25.0));
        assertEquals(5.0, calculator.calculateEqual(10.0));

        // Power of
        assertEquals(100.0, calculator.calculateBi(Calculator.BiOperatorModes.xpowerofy, 2.0));
        assertEquals(1000.0, calculator.calculateEqual(3.0));
    }

    @Test
    void testMonoOperations() {
        Calculator calculator = new Calculator();

        // Square
        assertEquals(25.0, calculator.calculateMono(Calculator.MonoOperatorModes.square, 5.0));

        // Square Root
        assertEquals(3.0, calculator.calculateMono(Calculator.MonoOperatorModes.squareRoot, 9.0));

        // One Divided By
        assertEquals(0.2, calculator.calculateMono(Calculator.MonoOperatorModes.oneDividedBy, 5.0));

        // Cosine
        assertEquals(0.5, calculator.calculateMono(Calculator.MonoOperatorModes.cos, 60.0));

        // Sine
        assertEquals(1.0, calculator.calculateMono(Calculator.MonoOperatorModes.sin, 90.0));

        // Tangent
        assertEquals(1.0, calculator.calculateMono(Calculator.MonoOperatorModes.tan, 45.0));

        // Log base 10
        assertEquals(2.0, calculator.calculateMono(Calculator.MonoOperatorModes.log, 100.0));

        // Natural Logarithm
        assertEquals(1.0, calculator.calculateMono(Calculator.MonoOperatorModes.ln, Math.E));

        // Percentage
        assertEquals(0.5, calculator.calculateMono(Calculator.MonoOperatorModes.rate, 50.0));

        // Absolute Value
        assertEquals(5.0, calculator.calculateMono(Calculator.MonoOperatorModes.abs, -5.0));
    }

    @Test
    void testFactorial() {
        Calculator calculator = new Calculator();

        // Factorial
        assertEquals(120.0, calculator.calculateFactorial(5.0));
        assertEquals(1.0, calculator.calculateFactorial(0.0));
        assertEquals(Double.NaN, calculator.calculateFactorial(-5.0));
        assertEquals(Double.NaN, calculator.calculateFactorial(5.5));
    }

    @Test
    void testAdditionalFunctions() {
        Calculator calculator = new Calculator();

        // Square of Sum
        assertEquals(81.0, calculator.calculateSquareOfSum(4.0, 5.0));

        // Combination
        assertEquals(10.0, calculator.calculateCombination(5.0, 2.0));
        assertEquals(Double.NaN, calculator.calculateCombination(-5.0, 2.0));
        assertEquals(Double.NaN, calculator.calculateCombination(5.0, 6.0));

        // Permutation
        assertEquals(20.0, calculator.calculatePermutation(5.0, 2.0));
        assertEquals(Double.NaN, calculator.calculatePermutation(-5.0, 2.0));
        assertEquals(Double.NaN, calculator.calculatePermutation(5.0, 6.0));
    }
}

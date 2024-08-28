package br.com.cristianoaf81.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.cristianoaf81.exceptions.UnsupportedMathOperationException;


@Service
public class MathService {

    /**
     * Check if a string can be converted to boolean.
     *
     * @param strNumber
     *            - string containing a number.
     *
     * @return true or false
     */
    private Boolean isNumeric(String strNumber) {
        if (strNumber == null)
            return false;
        String numberStr = strNumber.replaceAll(",", ".");
        return numberStr.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    /**
     * Convert a string number to Double.
     *
     * @param strNumber
     *            - string containing a number.
     *
     * @return a converted string to Double.
     */
    private Double convertToDouble(String strNumber) {
        if (strNumber == null)
            return 0D;
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) {
            return Double.parseDouble(number);
        }
        return 0D;
    }

    /**
     * Check if given strings are able to be parseable to numbers.
     *
     * @param numberOne
     *            - expected numeric string to be checked.
     * @param numberTwo
     *            - another expected numeric string to be checked.
     * @param isDivision
     *            - an optional parameter boolean type to mark operation as division.
     *
     * @throws UnsupportedMathOperationException
     *             - if cant convert strings to doubles.
     */
    private void trhowUnsupportedMathOperationIfNecessary(String numberOne, String numberTwo,
            Optional<Boolean> isDivision) throws UnsupportedMathOperationException {
        boolean divOperation = isDivision.isPresent() ? isDivision.get() : false;
        boolean n1 = isNumeric(numberOne);
        boolean n2 = isNumeric(numberTwo);
        if (!n1 || !n2) {
            throw new UnsupportedMathOperationException("Non numeric values(s) detected!");
        }
        this.throwExceptionWhenIsImpossibleToDivideNumbers(divOperation, numberOne, numberTwo);
    }

    /**
     * Method responsible to throw an exception in specific division math operation when isn't possible to divide 2
     * numbers.
     *
     * @param isDivision
     *            - boolean.
     * @param numberOne
     *            - string representing a number.
     * @param numberTwo
     *            - string representing a number.
     *
     * @throws UnsupportedMathOperationException
     */
    private void throwExceptionWhenIsImpossibleToDivideNumbers(boolean isDivision, String numberOne, String numberTwo)
            throws UnsupportedMathOperationException {
        if (isDivision == true) {
            Double num1 = convertToDouble(numberOne);
            Double num2 = convertToDouble(numberTwo);
            if (num1 == 0 && num2 == 0) {
                throw new UnsupportedMathOperationException("Can\'t divide 0/0 undefined result");
            }
            if (num1 > 0 && num2 == 0) {
                throw new UnsupportedMathOperationException("Can\' divide n/0 indeterminated result");
            }
        }
    }

    /**
     * sum two numbers.
     *
     * @param numberOne
     *            - string representing a number.
     * @param numberTwo
     *            - another number in string.
     *
     * @throws UnsupportedMathOperationException
     *
     * @returns sum result.
     */
    public Double sum(String numberOne, String numberTwo) {
        this.trhowUnsupportedMathOperationIfNecessary(numberOne, numberTwo, Optional.empty());
        return this.convertToDouble(numberOne) + this.convertToDouble(numberTwo);
    }

    /**
     * subtract two numbers.
     *
     * @param numberOne
     *            - string representing a number.
     * @param numberTwo
     *            - another number in string.
     *
     * @throws UnsupportedMathOperationException
     *
     * @returns subtract result.
     */
    public Double sub(String numberOne, String numberTwo) {
        this.trhowUnsupportedMathOperationIfNecessary(numberOne, numberTwo, Optional.empty());
        return this.convertToDouble(numberOne) - this.convertToDouble(numberTwo);
    }

    /**
     * multiply two numbers.
     *
     * @param numberOne
     *            - string representing a number.
     * @param numberTwo
     *            - another number in string.
     *
     * @throws UnsupportedMathOperationException
     *
     * @returns multiply result.
     */
    public Double multiply(String numberOne, String numberTwo) {
        this.trhowUnsupportedMathOperationIfNecessary(numberOne, numberTwo, Optional.empty());
        return this.convertToDouble(numberOne) * this.convertToDouble(numberTwo);
    }

    /**
     * divide two numbers.
     *
     * @param numberOne
     *            - string representing a number.
     * @param numberTwo
     *            - another number in string.
     *
     * @throws UnsupportedMathOperationException
     *
     * @returns devide result.
     */
    public Double divide(String numberOne, String numberTwo) {
        this.trhowUnsupportedMathOperationIfNecessary(numberOne, numberTwo, Optional.of(true));
        return this.convertToDouble(numberOne) / this.convertToDouble(numberTwo);
    }

    /**
     * calculate squareroot for a given number.
     *
     * @param number
     *            - string representing a number.
     *
     * @throws UnsupportedMathOperationException
     *
     * @returns sum result.
     */
    public Double squareRoot(String number) {
        this.trhowUnsupportedMathOperationIfNecessary(number, "0", Optional.empty());
        return Math.sqrt(this.convertToDouble(number));
    }

    /**
     * calculates a mean of two numbers.
     *
     * @param numberOne
     *            - string representing a number.
     * @param numberTwo
     *            - another number in string.
     *
     * @throws UnsupportedMathOperationException
     *
     * @returns mean result.
     */
    public Double mean(String numberOne, String numberTwo) {
        this.trhowUnsupportedMathOperationIfNecessary(numberOne, numberTwo, Optional.empty());
        Double num1 = this.convertToDouble(numberOne);
        Double num2 = this.convertToDouble(numberTwo);
        Double sum = num1 + num2;
        return sum / 2;
    }

}

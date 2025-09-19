package validators;

import exceptions.InputFault;

import java.math.BigDecimal;

public class ParametersChecker {

    private static final String PARAMETER_NOT_FOUND = " is not found!";
    private static final String WRONG_FORMAT= " is in wrong format!";
    private static final String NOT_IN_RANGE = " is not in range!";
    private static final String NOT_IN_LIST = " is not in its list";


    public static void checkParams(String x, String y, String R) throws InputFault {
        if(x == null || x.isEmpty()) throw new InputFault("x" + PARAMETER_NOT_FOUND);
        if(y == null || y.isEmpty()) throw new InputFault("y" + PARAMETER_NOT_FOUND);
        if(R == null || R.isEmpty()) throw new InputFault("R" + PARAMETER_NOT_FOUND);
        checkX(x);
        checkY(y);
        checkR(R);
    }

    private static boolean isIntegerOrHalf(BigDecimal num) {
        BigDecimal twoY = num.multiply(new BigDecimal(2));
        return twoY.stripTrailingZeros().scale() <= 0;
    }
    private static void checkX(String x) throws InputFault {
        BigDecimal numX;
        try {
            numX = new BigDecimal(x);
        } catch(NumberFormatException e) {
            throw new InputFault("x" + WRONG_FORMAT);
        }
        if(numX.compareTo(new BigDecimal(-5)) < 0 || numX.compareTo(new BigDecimal(5)) > 0) throw new InputFault("x" + NOT_IN_RANGE);
    }

    private static void checkY(String y) throws InputFault {
        BigDecimal numY;
        try {
            numY = new BigDecimal(y);
        } catch(NumberFormatException e) {
            throw new InputFault("y" + WRONG_FORMAT);
        }
        if(numY.compareTo(new BigDecimal(-2)) < 0 || numY.compareTo(new BigDecimal(2)) > 0) throw new InputFault("y" + NOT_IN_RANGE);
        if(!isIntegerOrHalf(numY)) throw new InputFault("y" + NOT_IN_LIST);
    }

    private static void checkR(String R) throws InputFault {
        BigDecimal numR;
        try {
            numR = new BigDecimal(R);
        } catch(NumberFormatException e) {
            throw new InputFault("R" + WRONG_FORMAT);
        }
        if(numR.compareTo(new BigDecimal(1)) < 0 || numR.compareTo(new BigDecimal(3)) > 0) throw new InputFault("R" + NOT_IN_RANGE);
        if(!isIntegerOrHalf(numR)) throw new InputFault("R" + NOT_IN_LIST);
    }


}
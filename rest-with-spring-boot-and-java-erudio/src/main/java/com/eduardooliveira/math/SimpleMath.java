package com.eduardooliveira.math;

import com.eduardooliveira.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class SimpleMath {

    public Double sum( Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double subtraction( Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public Double multiply( Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    public Double divide( Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }

    public Double mean( Double numberOne, Double numberTwo) {
        return (numberOne + numberTwo) / 2;
    }

    public Double squareRoot( Double number) {
        return Math.sqrt(number);
    }
}

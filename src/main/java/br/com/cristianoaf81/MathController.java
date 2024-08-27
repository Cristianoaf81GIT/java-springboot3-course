package br.com.cristianoaf81;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.cristianoaf81.exceptions.UnsupportedMathOperationException;
import br.com.cristianoaf81.services.MathService;

@RestController
class MathController {

    @Autowired
    private MathService mathService;

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws UnsupportedMathOperationException {
        return this.mathService.sum(numberOne, numberTwo);
    }

    @GetMapping("/sub/{numberOne}/{numberTwo}")
    public Double sub(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {
        return this.mathService.sub(numberOne, numberTwo);
    }

    @GetMapping("/mult/{numberOne}/{numberTwo}")
    public double multiply(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {
        return this.mathService.multiply(numberOne, numberTwo);
    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public Double divide(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {
        return this.mathService.divide(numberOne, numberTwo);
    }

    @GetMapping("/squareroot/{number}")
    public Double squareRoot(@PathVariable(value = "number") String number) {
        return this.mathService.squareRoot(number);
    }

    @GetMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) {
        return this.mathService.mean(numberOne, numberTwo);
    }
}

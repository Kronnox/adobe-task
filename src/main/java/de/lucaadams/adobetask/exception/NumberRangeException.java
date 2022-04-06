package de.lucaadams.adobetask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class NumberRangeException extends RuntimeException {

    /**
     * Thrown as soon as an entered number is outside the allowed range.
     * @param min the minimum value
     * @param max the maximum value
     */
    public NumberRangeException(int min, int max) {
        super(String.format("The value provided needs to be between %s and %s", min, max));
    }
}

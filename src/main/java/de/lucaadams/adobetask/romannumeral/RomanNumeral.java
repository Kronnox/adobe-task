package de.lucaadams.adobetask.romannumeral;

import de.lucaadams.adobetask.exception.NumberRangeException;
import de.lucaadams.adobetask.model.ConversionResult;
import lombok.Getter;

import java.util.TreeMap;

public class RomanNumeral {

    // Lookup translation map for easy conversion
    private static final TreeMap<Integer, String> ROMAN_NUMERAL_MAP = new TreeMap<>() {{
        put(100, "C");
        put(90, "XC");
        put(50, "L");
        put(40, "XL");
        put(10, "X");
        put(9, "IX");
        put(5, "V");
        put(4, "IV");
        put(1, "I");
    }};

    @Getter
    private final short value;

    public RomanNumeral(short value) {
        // Check for input values out of the specified and supported range
        if (value < 1 || value > 255) {
            throw new NumberRangeException(1, 255);
        }

        this.value = value;
    }

    public ConversionResult getConvertedResult() {
        // Create a returnable ConversionResult from input and converted translation
        // In combination with Jackson this guarantees the desired JSON schema
        return new ConversionResult(String.valueOf(value), getValueAsRomanNumeral(value));
    }

    private String getValueAsRomanNumeral(int value) {
        // Get entry from translation map that is equal or smaller than the given number
        int closestNumber = ROMAN_NUMERAL_MAP.floorKey(value);

        // In case it is already the given value, return early
        if (closestNumber == value) {
            return ROMAN_NUMERAL_MAP.get(value);
        }

        // Otherwise, continue recursively and add up translations
        return ROMAN_NUMERAL_MAP.get(closestNumber) + getValueAsRomanNumeral(value - closestNumber);
    }
}

package de.lucaadams.adobetask.romannumeral;

import de.lucaadams.adobetask.model.ConversionResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/romannumeral")
public class RomanNumeralController {

    /**
     * Endpoint dor converting numbers into roman numerals
     * @param inputValue the actual number value (between 1-255)
     * @return the corresponding {@link ConversionResult} object
     */
    @GetMapping()
    public ConversionResult convert(@RequestParam(name = "query") short inputValue) {
        RomanNumeral romanNumeral = new RomanNumeral(inputValue);
        return romanNumeral.getConvertedResult();
    }
}

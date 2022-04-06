package de.lucaadams.adobetask.romannumeral;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/romannumeral")
public class RomanNumeralController {

    @GetMapping()
    public String convert(@RequestParam(name = "query") short inputValue) {
        return "Test";
    }
}

package de.lucaadams.adobetask.romannumeral;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RomanNumeralController.class)
public class RomanNumeralControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNoParameter() throws Exception {
        this.mockMvc.perform(get("/romannumeral"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("Required request parameter 'query' for method parameter type short is not present")));
    }

    @Test
    public void testValidNumber() throws Exception {
        this.mockMvc.perform(get("/romannumeral?query=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"input\":\"1\",\"output\":\"I\"}")));

        this.mockMvc.perform(get("/romannumeral?query=42"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"input\":\"42\",\"output\":\"XLII\"}")));

        this.mockMvc.perform(get("/romannumeral?query=255"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"input\":\"255\",\"output\":\"CCLV\"}")));
    }

    @Test
    public void testInvalidNumber() throws Exception {
        this.mockMvc.perform(get("/romannumeral?query=abc"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLargeNumber() throws Exception {
        this.mockMvc.perform(get("/romannumeral?query=256"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(get("/romannumeral?query=2147483648"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNegativeNumber() throws Exception {
        this.mockMvc.perform(get("/romannumeral?query=-1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}

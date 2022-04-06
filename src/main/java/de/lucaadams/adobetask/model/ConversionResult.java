package de.lucaadams.adobetask.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class ConversionResult {

    @NonNull
    private final String input;
    @NonNull
    private final String output;
}

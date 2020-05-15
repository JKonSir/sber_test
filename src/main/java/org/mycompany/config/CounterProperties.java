package org.mycompany.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Validated
@ConfigurationProperties("counter")
public class CounterProperties {

    /**
     * Template of counter name (default - counter_%s)
     * %s - will be replaced with generated sequence (is mandatory)
     */
    @NotEmpty
    @Pattern(regexp = ".*%s.*", message = "must contain %s")
    private String nameTemplate = "counter_%s";

    /**
     * length of generated sequence (min 4)
     */
    @Min(value = 4, message = "value must be more or equal 4")
    private int genSeqLength;

    /**
     * Alphabet as a source of name generation (min 10 characters)
     */
    @NotEmpty
    @Pattern(regexp = "\\b\\w{10,}\\b", message = "length must be more or equal 10")
    private String alphabet;
}

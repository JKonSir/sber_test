package org.mycompany.util;

import lombok.RequiredArgsConstructor;
import org.mycompany.config.CounterProperties;
import org.springframework.stereotype.Component;

import java.util.SplittableRandom;

import static java.util.stream.IntStream.range;

@Component
@RequiredArgsConstructor
public class CounterNameGenerator {

    private static final SplittableRandom RANDOM = new SplittableRandom();

    private final CounterProperties properties;

    public String generate() {
        String suffix = range(0, properties.getGenSeqLength())
                .map(i -> properties.getAlphabet().charAt(RANDOM.nextInt(0, properties.getAlphabet().length())))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return String.format(properties.getNameTemplate(), suffix);
    }
}

package org.mycompany.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycompany.exception.NotFoundException;
import org.mycompany.service.CounterService;
import org.mycompany.util.CounterNameGenerator;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultCounterService implements CounterService {

    private static final String NOT_FOUND_MESSAGE_TEMPLATE = "There is no counter with name %s";

    private final Map<String, AtomicLong> counterStorage = new ConcurrentHashMap<>();

    private final CounterNameGenerator nameGenerator;

    @Override
    public String create() {
        AtomicLong counter = new AtomicLong();

        String counterName;
        do {
            counterName = nameGenerator.generate();
            log.debug("Generated new counter name {}", counterName);
        } while (counterStorage.put(counterName, counter) != null);

        return counterName;
    }

    @Override
    public long get(String name) throws NotFoundException {
        return ofNullable(counterStorage.get(name))
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE_TEMPLATE, name)))
                .longValue();
    }

    @Override
    public long increment(String name) throws NotFoundException {
        return ofNullable(counterStorage.get(name))
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE_TEMPLATE, name)))
                .incrementAndGet();
    }

    @Override
    public void remove(String name) throws NotFoundException {
        ofNullable(counterStorage.remove(name))
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE_TEMPLATE, name)));
    }

    @Override
    public long getSum() {
        return counterStorage.values().stream()
                .mapToLong(AtomicLong::longValue)
                .sum();
    }

    @Override
    public Set<String> getNames() {
        return counterStorage.keySet();
    }
}

package org.mycompany.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycompany.service.CounterService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.mycompany.controller.CounterController.ROOT_PATH;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
@RestController
@RequestMapping(ROOT_PATH)
@RequiredArgsConstructor
public class CounterController {

    static final String ROOT_PATH = "/counter";

    private final CounterService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public String create() {
        log.info("Create new counter");

        return service.create();
    }

    @GetMapping("/{counter_name}")
    public long get(@PathVariable("counter_name") String name) {
        log.info("Get counter with name {}", name);

        return service.get(name);
    }

    @DeleteMapping("/{counter_name}")
    @ResponseStatus(NO_CONTENT)
    public void remove(@PathVariable("counter_name") String name) {
        log.info("Remove counter with name {}", name);

        service.remove(name);
    }

    @PutMapping("/{counter_name}")
    public long increment(@PathVariable("counter_name") String name) {
        log.info("Increment counter with name {}", name);

        return service.increment(name);
    }

    @GetMapping
    public long getSum() {
        log.info("Get sum of all counters");

        return service.getSum();
    }

    @GetMapping("/names")
    public Set<String> getNames() {
        log.info("Get all counters names");

        return service.getNames();
    }
}

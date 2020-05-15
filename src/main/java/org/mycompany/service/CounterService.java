package org.mycompany.service;

import org.mycompany.exception.NotFoundException;

import java.util.Set;

public interface CounterService {

    /**
     * Create counter with new unique name
     *
     * @return name of new counter
     */
    String create();

    /**
     * Get counter with given name
     *
     * @param name counter name
     * @return counter value
     * @throws NotFoundException if counter with name doesn't exist
     */
    long get(String name) throws NotFoundException;

    /**
     * Increment counter with given name
     *
     * @param name counter name
     * @return new counter value
     * @throws NotFoundException if counter with name doesn't exist
     */
    long increment(String name) throws NotFoundException;

    /**
     * Remove counter with given name
     *
     * @param name counter name
     * @throws NotFoundException if counter with name doesn't exist
     */
    void remove(String name) throws NotFoundException;

    /**
     * Get sum of all counters
     *
     * @return sum of all counters
     */
    long getSum();

    /**
     * Get set of unique counter names
     *
     * @return set of unique counter names
     */
    Set<String> getNames();
}

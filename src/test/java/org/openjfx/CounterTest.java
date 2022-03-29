package org.openjfx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

    Counter counter;

    @BeforeEach
    void setUp() {
        counter = new Counter();
    }

    @Test
    public void default_counter_should_have_a_max_of_200_000()
    {
        assertEquals(200_000, counter.getMax());
    }


    @Test
    public void counter_with_a_max_value_of_500_000()
    {
        Counter counter = new Counter(500_000);
        assertEquals(500_000, counter.getMax());
    }


    @Test
    public void value_should_be_200_000_by_default()
    {
        counter.startIncrementing();
        assertEquals(200_000, counter.getValue());
    }
}
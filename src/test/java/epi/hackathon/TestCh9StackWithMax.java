package epi.hackathon;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCh9StackWithMax {

    @Test public void stack_gives_max_when_1_element() {
        s.push(1);
        assertEquals(1, s.max());
    }
    @Test public void stack_gives_max_when_1_element_() {
        s.push(2);
        assertEquals(2, s.max());
    }
    @Test public void stack_gives_max_when_inserted_in_ascending_order() {
        s.push(1);
        s.push(2);
        assertEquals(2, s.max());
    }
    @Test public void stack_gives_max_when_inserted_in_descending_order() {
        s.push(2);
        s.push(1);
        assertEquals(2, s.max());
    }
    @Test public void stack_works() {
        s.push(1);
        assertEquals(1, s.pop());
    }
    @Test public void stack_works2() {
        s.push(1);
        s.push(2);
        assertEquals(2, s.pop());
        assertEquals(1, s.pop());
    }
    @Test(expected = IllegalStateException.class) public void stack_works3() {
        s.pop();
    }
    Ch9StackWithMax s = new Ch9StackWithMax();

}

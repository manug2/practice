package extras.atm;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by maverick on 5/17/2017.
 */
public class TestATM {

    @Test(expected = RuntimeException.class)
    public void should_fail_to_yield_500_when_165_loaded() {
        ATM atm = new ATM()
                .load(5, 1)
                .load(10, 1)
                .load(50, 1)
                .load(100, 1)
                ;
        Change change = atm.extract(500);
        change.getQuantity(5);
    }

    @Test public void should_yield_575_when_only_4_of_100_but_plenty_others() {
        ATM atm = new ATM()
                .load(5, 2)
                .load(10, 2)
                .load(50, 3)
                .load(100, 4)
                ;
        Change change = atm.extract(575);
        assertEquals(4, change.getQuantity(100));
        assertEquals(3, change.getQuantity(50));
        assertEquals(2, change.getQuantity(10));
        assertEquals(1, change.getQuantity(5));
    }

    @Test public void should_yield_575() {
        ATM atm = new ATM()
                .load(5, 2)
                .load(10, 2)
                .load(50, 2)
                .load(100, 5)
                ;
        Change change = atm.extract(575);
        assertEquals(5, change.getQuantity(100));
        assertEquals(1, change.getQuantity(50));
        assertEquals(2, change.getQuantity(10));
        assertEquals(1, change.getQuantity(5));
    }

    @Test public void should_yield_2_of_5_when_2_of_5_loaded() {
        ATM atm = new ATM().load(5, 2);
        Change change = atm.extract(10);
        assertEquals(2, change.getQuantity(5));
    }

    @Test public void should_yield_1_of_5_when_5_loaded() {
        ATM atm = new ATM().load(5, 1);
        Change change = atm.extract(5);
        assertEquals(1, change.getQuantity(5));
    }

    @Test public void should_yield_1_of_10_when_10_loaded() {
        ATM atm = new ATM().load(10, 1);
        Change change = atm.extract(10);
        assertEquals(1, change.getQuantity(10));
    }

}

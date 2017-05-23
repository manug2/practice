package extras;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLeisureTraveller {

    @Test public void should_answer_2_when_only_1_route_present() {
        LeisureTraveller traveller = new LeisureTraveller();
        traveller.add("ABC", "DEF");
        assertEquals(2, traveller.max());
    }
    @Test public void should_answer_3_when_only_1_1_route_present() {
        LeisureTraveller traveller = new LeisureTraveller();
        traveller.add("ABC", "DEF");
        traveller.add("DEF", "HIG");
        assertEquals(3, traveller.max());
    }
    @Test public void should_answer_4_when_only_1_1_1_route_present() {
        LeisureTraveller traveller = new LeisureTraveller();
        traveller.add("ABC", "DEF");
        traveller.add("HIG", "JKM");
        traveller.add("DEF", "HIG");
        assertEquals(4, traveller.max());
    }
    @Test public void should_answer_4_when_only_1_1_1_route_present2() {
        LeisureTraveller traveller = new LeisureTraveller();
        traveller.add("ABC", "DEF");
        traveller.add("DEF", "HIG");
        traveller.add("HIG", "JKM");
        assertEquals(4, traveller.max());
    }

}

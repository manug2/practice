package extras;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLeisureTraveller {

    @Test public void should_answer_6_when_only_5_routes() {
        LeisureTraveller traveller = new LeisureTraveller();
        traveller.add("3", "4");
        traveller.add("1", "2");
        traveller.add("5", "6");
        traveller.add("4", "5");
        traveller.add("2", "3");
        assertEquals(6, traveller.max());
    }
    @Test public void should_answer_6_when_only_5_routes_added_in_order() {
        LeisureTraveller traveller = new LeisureTraveller();
        traveller.add("1", "2");
        traveller.add("2", "3");
        traveller.add("3", "4");
        traveller.add("4", "5");
        traveller.add("5", "6");
        assertEquals(6, traveller.max());
    }

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

    @Test public void should_answer_2_when_only_2_components() {
        LeisureTraveller traveller = new LeisureTraveller();
        traveller.add("ABC", "DEF");
        traveller.add("JSD", "ADF");
        assertEquals(2, traveller.max());
    }

}

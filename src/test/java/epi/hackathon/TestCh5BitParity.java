package epi.hackathon;


import org.junit.Test;

import static epi.hackathon.Ch5BitParity.powerOf2;
import static epi.hackathon.Ch5BitParity.powerOf2Long;
import static org.junit.Assert.assertEquals;

public class TestCh5BitParity {

    @Test public void parity_for_2_32_minus_1(){
        assertEquals(0, new Ch5BitParity().computeLong(powerOf2Long(32)-1));
    }
    @Test public void parity_for_2_32_plus_1(){
        assertEquals(0, new Ch5BitParity().computeLong(powerOf2Long(32)+1));
    }
    @Test public void parity_for_2_32(){
        assertEquals(1, new Ch5BitParity().computeLong(powerOf2Long(32)));
    }
    @Test public void parity_for_2_31_minus_1(){
        assertEquals(1, new Ch5BitParity().computeLong(powerOf2Long(31)-1));
    }
    @Test public void parity_for_2_31_plus_1(){
        assertEquals(0, new Ch5BitParity().computeLong(powerOf2Long(31)+1));
    }
    @Test public void parity_for_2_31(){
        assertEquals(1, new Ch5BitParity().computeLong(powerOf2Long(31)));
    }
    @Test public void parity_for_2_30_long(){
        assertEquals(1, new Ch5BitParity().computeLong(powerOf2Long(30)));
    }
    @Test public void parity_for_2_30(){
        assertEquals(1, new Ch5BitParity().compute(powerOf2(30)));
    }

    @Test public void parity_for_5_long(){
        assertEquals(0, new Ch5BitParity().computeLong(5L));
    }

    @Test public void parity_for_2_8(){
        assertEquals(1, new Ch5BitParity().compute(powerOf2(8)));
    }
    @Test public void parity_for_2_10(){
        assertEquals(1, new Ch5BitParity().compute(powerOf2(10)));
    }
    @Test public void parity_for_2_16(){
        assertEquals(1, new Ch5BitParity().compute(powerOf2(16)));
    }
    @Test public void parity_for_31(){
        assertEquals(1, new Ch5BitParity().compute(31));
    }
    @Test public void parity_for_15(){
        assertEquals(0, new Ch5BitParity().compute(15));
    }

    @Test public void parity_for_5(){
        assertEquals(0, new Ch5BitParity().compute(5));
    }
    @Test public void parity_for_4(){
        assertEquals(1, new Ch5BitParity().compute(4));
    }
    @Test public void parity_for_3(){
        assertEquals(0, new Ch5BitParity().compute(3));
    }
    @Test public void parity_for_2(){
        assertEquals(1, new Ch5BitParity().compute(2));
    }
    @Test public void parity_for_1(){
        assertEquals(1, new Ch5BitParity().compute(1));
    }
    @Test public void parity_for_0(){
        assertEquals(0, new Ch5BitParity().compute(0));
    }
}

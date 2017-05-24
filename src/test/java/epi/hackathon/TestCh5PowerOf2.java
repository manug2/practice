package epi.hackathon;


import org.junit.Test;

import static epi.hackathon.Ch5BitParity.powerOf2;
import static epi.hackathon.Ch5BitParity.powerOf2Long;
import static org.junit.Assert.assertEquals;

public class TestCh5PowerOf2 {
    @Test public void power_2_8(){
        assertEquals(256, powerOf2(8));
    }
    @Test public void power_2_4(){
        assertEquals(16, powerOf2(4));
    }
    @Test public void power_2_10(){
        assertEquals(1024, powerOf2(10));
    }
    @Test public void power_2_16(){
        assertEquals(65536, powerOf2(16));
    }
    @Test public void power_2_30(){
        assertEquals(1073741824, powerOf2(30));
    }
    @Test public void power_2_31(){
        assertEquals(Integer.MIN_VALUE, powerOf2(31));
    }
    @Test public void power_2_31_long(){
        assertEquals(2147483648L, powerOf2Long(31));
    }
    @Test public void power_2_32_long(){
        assertEquals(4294967296L, powerOf2Long(32));
    }
    @Test public void power_2_31_and(){
        assertEquals(2147483648L, powerOf2Long(31) & 2147483648L);
    }
    @Test public void power_2_31_minus_1_and(){
        assertEquals(0L, (powerOf2Long(31)-1) & 2147483648L);
    }
    @Test public void power_2_32_minus_1_and(){
        assertEquals(0L, (powerOf2Long(32)-1) & 4294967296L);
    }

}

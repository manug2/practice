import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestBurnOut {

    @Test
    public void should_detect() {
        int[][]  e = new int[][] {
                new int[] {0, 0}
                , new int[] {1, 0}
                , new int[] {2, 0}
                , new int[] {2, 0}
        };
        assertEquals(1, new BurnOut(e).min());
    }
/*

    @Test
    public void should_make() {
        int[][]  e = new int[][] {
                new int[] {0, 0}
                , new int[] {1, 0}
                , new int[] {2, 0}
                , new int[] {2, 0}
        };
        assertArrayEquals(
                new int[] {0, 1, 1, 1, 1},
                new BurnOut(e).make().burned);
    }

    @Test
    public void should_detect2() {
        int[][]  e = new int[][] {
                new int[] {0, 1}
                , new int[] {1, 0}
                , new int[] {2, 1}
                , new int[] {1, 1}
                , new int[] {0, 1}
                , new int[] {2, 0}
                , new int[] {5, 0}
        };
        assertEquals(2, new BurnOut(e).min());
    }

    @Test
    public void should_make2() {
        int[][]  e = new int[][] {
                new int[] {0, 1}
                , new int[] {1, 0}
                , new int[] {2, 1}
                , new int[] {1, 1}
                , new int[] {0, 1}
                , new int[] {2, 0}
                , new int[] {5, 0}
        };
        assertArrayEquals(
                new int[] {0, 0, 1, 0, 0, 0, 1, 1},
                new BurnOut(e).make().burned);
    }

    @Test
    public void should_detect_no_BO_when_1_BO_0_NBO() {
        int[][]  e = new int[1][];
        e[0] = new int[] {0, 1};
        assertEquals(0, new BurnOut(e).min());
    }

    @Test
    public void should_make_when_1_BO_0_NBO() {
        int[][]  e = new int[1][];
        e[0] = new int[] {0, 0};
        assertArrayEquals(
                new int[] {0, 1},
                new BurnOut(e).make().burned);
    }

    @Test
    public void should_detect_no_BO_when_0_BO_1_NBO() {
        int[][]  e = new int[1][];
        e[0] = new int[] {0, 0};
        assertEquals(1, new BurnOut(e).min());
    }

    @Test
    public void should_make_when_0_BO_1_NBO() {
        int[][]  e = new int[1][];
        e[0] = new int[] {0, 1};
        assertArrayEquals(
                new int[] {0, 0},
                new BurnOut(e).make().burned);
    }

    @Test
    public void should_detect_no_emp() {
        int[][] e = new int[0][];
        assertEquals(0, new BurnOut(e).min());
    }

    @Test
    public void should_make_when_no_emp() {
        int[][] e = new int[0][];
        assertArrayEquals(
                new int[] {0},
                new BurnOut(e).make().burned);
    }
*/
}

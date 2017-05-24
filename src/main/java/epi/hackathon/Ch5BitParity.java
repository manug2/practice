package epi.hackathon;


public class Ch5BitParity {
    public int compute(final int input) {
        int ones = 0;
        int running = input;
        while (running>0) {
            ones ^= (running & 1);
            running >>= 1;
        }

        return ones;
    }

    public int computeLong(final long input) {
        long running = input;
        running ^= running >> 32;
        running ^= running >> 16;
        running ^= running >>  8;
        running ^= running >>  4;
        running &= 15;
        return (0x6996 >> running) & 1;
    }

    public static int powerOf2(int power) {
        int input = 1;
        for (int i=0; i<power; i++)
            input <<= 1;
        return input;
    }

    public static long powerOf2Long(int power) {
        long input = 1;
        for (int i=0; i<power; i++)
            input <<= 1;
        return input;
    }
}

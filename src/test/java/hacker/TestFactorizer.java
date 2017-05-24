package hacker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class TestFactorizer {

    //@Pa

    @Test
    public void should_check_number_of_factors() {
        assertThat(new Factorizer(1, 1).factors().size(), is(equalTo(1)));
    }

    @Test
    public void should_give_1_set_of_factors_for_1_1() {
        assertThat(new Factorizer(N, B).factors().size(), is(equalTo(numOfSolutions)));
    }

    @Test
    public void should_solve_for_factors() {
        assertThat(new Factorizer(N, B).factors().get(0), is(equalTo(factors)));
    }


    final long N, K;
    final int B, numOfSolutions;
    final long[] factors;

    public TestFactorizer(long n, long k, int b, int numOfSolutions, long[] factors) {
        N = n;
        K = k;
        B = b;
        this.numOfSolutions = numOfSolutions;
        this.factors = factors;
    }

}

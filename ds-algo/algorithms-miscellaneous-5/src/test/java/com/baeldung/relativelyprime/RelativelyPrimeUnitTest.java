package com.baeldung.relativelyprime;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RelativelyPrimeUnitTest {

    @Test
    public void givenNonRelativelyPrimeNumbers_whenCheckingIteratively_shouldReturnFalse() {

        boolean result = RelativelyPrime.iterativeRelativelyPrime(45, 35);
        assertThat(result).isFalse();
    }

    @Test
    public void givenRelativelyPrimeNumbers_whenCheckingIteratively_shouldReturnTrue() {

        boolean result = RelativelyPrime.iterativeRelativelyPrime(500, 501);
        assertThat(result).isTrue();
    }

    @Test
    public void givenNonRelativelyPrimeNumbers_whenCheckingRecursively_shouldReturnFalse() {

        boolean result = RelativelyPrime.recursiveRelativelyPrime(45, 35);
        assertThat(result).isFalse();
    }

    @Test
    public void givenRelativelyPrimeNumbers_whenCheckingRecursively_shouldReturnTrue() {

        boolean result = RelativelyPrime.recursiveRelativelyPrime(500, 501);
        assertThat(result).isTrue();
    }

    @Test
    public void givenNonRelativelyPrimeNumbers_whenCheckingUsingBigIntegers_shouldReturnFalse() {

        boolean result = RelativelyPrime.bigIntegerRelativelyPrime(45, 35);
        assertThat(result).isFalse();
    }

    @Test
    public void givenRelativelyPrimeNumbers_whenCheckingBigIntegers_shouldReturnTrue() {

        boolean result = RelativelyPrime.bigIntegerRelativelyPrime(500, 501);
        assertThat(result).isTrue();
    }
}

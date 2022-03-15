package ua.leirimnad;

import java.util.Objects;

public class RationalNumber implements Comparable<RationalNumber> {
    protected int numerator, denominator;

    public RationalNumber(int numerator, int denominator) {
        if (denominator <= 0)
            throw new IllegalArgumentException("Denominator must be greater that zero");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public int compareTo(RationalNumber o) {
        return this.numerator*o.denominator - o.numerator*this.denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RationalNumber that = (RationalNumber) o;
        return numerator == that.numerator && denominator == that.denominator;
    }
}

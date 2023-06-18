package ru.stqa.pft.mfp;

public class Equation {

    private final int n;

    public Equation(double a, double b, double c) {

        double d = Math.pow(b, 2) - 4 * a * c;

        if (d > 0) {
            n = 2;
        } else {
            if (d == 0) {
                n = 1;
            } else {
                n = 0;
            }
        }

    }

    public int rootNumber() {
        return n;
    }
}

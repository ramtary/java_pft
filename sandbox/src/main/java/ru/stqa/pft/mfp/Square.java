package ru.stqa.pft.mfp;

public class Square {
    public double a;

    public Square(double a) {
        this.a = a;
    }

    public double area() {
        return Math.pow(this.a, 2);
    }
}

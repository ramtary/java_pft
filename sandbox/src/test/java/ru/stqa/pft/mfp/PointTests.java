package ru.stqa.pft.mfp;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {

    // задаю точки
    public static final Point p1 = new Point(1, 2);
    public static final Point p2 = new Point(2, 1);

    // тест для метода класса MyFirstProgram
    @Test
    public void testDistanceOne() {
        Assert.assertEquals(MyFirstProgram.distance(p1, p2), 1.4142135623730951);
    }

    // тест для static метода класса Point
    @Test
    public void testDistanceTwo() {
        Assert.assertEquals(Point.distance(p1, p2), 1.4142135623730951);
    }

    // тест для non-static метода класса Point
    @Test
    public void testDistanceThree() {
        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    }
}

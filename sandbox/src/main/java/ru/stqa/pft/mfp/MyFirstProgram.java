package ru.stqa.pft.mfp;

public class MyFirstProgram {

	public static void main (String[] args) {
		helloSomebody("World");
		helloSomebody("User");
		helloSomebody("Aleksei");

		double a = 5;
		System.out.println("Площадь квадрата со стороной " + a + " = " + area(a));

		double b = 6;
		System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));
	}

	public static void helloSomebody (String somebody) {
		System.out.println("Hello, " + somebody + "!!!11");
	}

	public static double area (double a) {
		return a * a;
	}

	public static double area (double a, double b) {
		return a * b;
	}

}
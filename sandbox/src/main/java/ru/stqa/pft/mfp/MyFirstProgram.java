package ru.stqa.pft.mfp;

public class MyFirstProgram {

	public static void main (String[] args) {
		helloSomebody("World");
		helloSomebody("User");
		helloSomebody("Aleksei");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.a + " = " + s.area());

		Rectangle r = new Rectangle(5, 6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

		// Задание №2
		Point p1 = new Point(1, 2);
		Point p2 = new Point(2, 1);
		System.out.println("Расстояние между точками " + p1 + " и " + p2 + " = " + distance(p1, p2));

		// через метод класса (вариант 1)
		System.out.println("Расстояние между точками " + p1 + " и " + p2 + " = " + new Point().distance(p1, p2));

		// через метод класса (вариант 2)
		System.out.println("Расстояние между точками " + p1 + " и " + p2 + " = " + p1.distance(p2));
	}

	public static void helloSomebody (String somebody) {
		System.out.println("Hello, " + somebody + "!!!11");
	}

	// Задание №2
	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
	}
}
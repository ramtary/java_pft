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
	}

	public static void helloSomebody (String somebody) {
		System.out.println("Hello, " + somebody + "!!!11");
	}

}
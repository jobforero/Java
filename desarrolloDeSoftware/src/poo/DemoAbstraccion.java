package poo;

import java.util.ArrayList;
import java.util.List;

public class DemoAbstraccion {
	public static void main(String[] args) {
		List<Figura> figuras = new ArrayList<>();
		figuras.add(new Circulo(2.0));
		figuras.add(new Rectangulo(3.0, 4.0));

		for (Figura f : figuras) {
			System.out.println(f.describir() + " | área = " + f.area());
		}
	}
}
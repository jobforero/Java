package poo;

public class DemoHerencia {
	public static void main(String[] args) {
		
		Vehiculo v1 = new Coche("Toyota", 2022, 4);
		Vehiculo v2 = new Moto("Honda", 2020, false);

		v1.encender(); // Llamará a Coche.encender()
		v2.encender(); // Llamará a Moto.encender()

		((Coche) v1).acelerar(80); // Downcasting seguro
		System.out.println(v1);
		System.out.println(v2);
	}
}
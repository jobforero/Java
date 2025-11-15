package poo;

public class Coche extends Vehiculo {
	private int puertas;

	public Coche(String marca, int anio, int puertas) {
		super(marca, anio);
		this.puertas = puertas;
	}

	@Override
	public void encender() {
		super.encender();
		System.out.println("Coche listo para conducir");
	}

	public void acelerar(int kmh) {
		System.out.println("Coche acelera a " + kmh + " km/h");
	}
}
package poo;

public class Vehiculo {
	protected String marca;
	protected int anio;

	public Vehiculo(String marca, int anio) {
		this.marca = marca;
		this.anio = anio;
	}

	public void encender() {
		System.out.println("Vehículo encendido");
	}

	public void apagar() {
		System.out.println("Vehículo apagado");
	}

	@Override
	public String toString() {
		return "Vehiculo{marca='" + marca + "', anio=" + anio + "}";
	}
}

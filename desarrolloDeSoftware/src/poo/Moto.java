package poo;

public class Moto extends Vehiculo {
	private boolean tieneSidecar;

	public Moto(String marca, int anio, boolean tieneSidecar) {
		super(marca, anio);
		this.tieneSidecar = tieneSidecar;
	}

	@Override
	public void encender() {
		System.out.println("Moto encendida");
	}
}
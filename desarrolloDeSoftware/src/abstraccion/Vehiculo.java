package abstraccion;

abstract class Vehiculo {
	// Método abstracto (sin implementación)
	public abstract void mover();

	// Método concreto (con implementación)
	public void detener() {
		System.out.println("El vehículo se detiene");
	}
}

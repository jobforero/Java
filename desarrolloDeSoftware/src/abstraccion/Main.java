package abstraccion;

public class Main {
	public static void main(String[] args) {
		Vehiculo miAuto = new Auto();
		Vehiculo miBicicleta = new Bicicleta();

		miAuto.mover(); // Salida: El auto está andando en la carretera
		miBicicleta.mover(); // Salida: La bicicleta está siendo pedalada

		miAuto.detener(); // Salida: El vehículo se detiene
	}
}

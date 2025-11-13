package polimorfismo;

public class TestPolimorfismo {
	
	public static void main(String[] args) {
		
		Animal miAnimal;

		miAnimal = new Perro();
		miAnimal.hacerSonido(); // Salida: El perro dice: Guau

		miAnimal = new Gato();
		miAnimal.hacerSonido(); // Salida: El gato dice: Miau
	}
}

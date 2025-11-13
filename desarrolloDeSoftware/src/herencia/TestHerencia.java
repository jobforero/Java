package herencia;

public class TestHerencia {
	public static void main(String[] args) {
		Estudiante estudiante = new Estudiante();
		estudiante.nombre = "Carlos";
		estudiante.carrera = "Ingeniería";

		Profesor profesor = new Profesor();
		profesor.nombre = "María";
		profesor.materia = "Matemáticas";

		// Uso de herencia
		estudiante.saludar(); // "Hola, soy una persona"
		estudiante.estudiar(); // "Carlos está estudiando Ingeniería"

		profesor.saludar(); // "Hola, soy una persona"
		profesor.enseñar(); // "María enseña Matemáticas"
	}
}

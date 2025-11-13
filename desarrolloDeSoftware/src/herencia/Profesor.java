package herencia;

class Profesor extends Persona {
	String materia;

	void enseñar() {
		System.out.println(nombre + " enseña " + materia);
	}
}

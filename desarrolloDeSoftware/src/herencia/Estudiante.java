package herencia;

class Estudiante extends Persona {
	String carrera;

	void estudiar() {
		System.out.println(nombre + " está estudiando " + carrera);
	}
}

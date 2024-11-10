package clases;

public class ClasePrincipal {
    
	public static void main(String[] args) {
		
		
		/* Instancia que llama la clase que no tiene constructor 
		 *           pero si tiene dos metoso
		 * SinConstructor  mensajero = new SinConstructor();
		 mensajero.PedirNombre();
		 mensajero.Imprimir();*/
		
		/* cuando la clase tiene constructor, el constructor 
		 * se encarga de ejecutar toda la clase.
		 */
		ConConstructor mensajero = new ConConstructor();
		 
	}
	
}

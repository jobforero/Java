package utilList;

import java.util.ArrayList;
import java.util.List;

public class principal {
	
	public static void main(String args[]) {
		
		List<String> lis_nombres = new ArrayList<> ();
		
		lis_nombres.add("walter");
		lis_nombres.add("Paul");
		lis_nombres.add("Manuel");
		lis_nombres.add("Sonia");
		lis_nombres.add("Job");
		lis_nombres.add("Manuel");
		
		System.out.println("Tamaño: " +  lis_nombres.size());
		
		System.out.println("Dato de la posición 0 " + lis_nombres.get(0));
		
		lis_nombres.remove(1);
		
		System.out.println("\nImprimir la lista : ");	
		System.out.println("Tamaño : " + lis_nombres.size());
		
		for(String n : lis_nombres) {
	        System.out.println("Nombre: " + n);		
		}
		
	}
}
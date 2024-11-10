package utilList;

import java.util.ArrayList;
import java.util.List;

public class listaPersona {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// se le asigna la clase Persona a List
		List<Persona> lis_persona = new ArrayList<>();
		
		for(int i = 0; i < 10 ; i++) {
			
			Persona per = new Persona();
			per.setCodigo(i);
			per.setNombre("Walter" + i);
			per.setApellido("Forero" + i);
			per.setEdad(25 + i);
			
			lis_persona.add(per); //se agrega un registro a la lista
		}
		
		System.out.println("Tamaño de Lista: " + lis_persona.size());
		
		for(Persona p: lis_persona) {
			System.out.println("Codigo:   " + p.getCodigo());
			System.out.println("Nombre:   " + p.getNombre());
			System.out.println("Apellido: " + p.getApellido());
			System.out.println("Edad:     " + p.getEdad());
			System.out.println("------------------------------");
		}
	}
}

//lis_persona.add(per);

/*System.out.println("Dato Lista :" + lis_persona.get(0).getNombre() + " " 
+ lis_persona.get(0).getApellido());*/
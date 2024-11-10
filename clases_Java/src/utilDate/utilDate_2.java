package utilDate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class utilDate_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
       Calendar calendario = Calendar.getInstance(); // la clase calendar es una clase abstracta
         // no se puede instanciar, se puede accesde con el getInstance
       int anio = calendario.get(Calendar.YEAR);
       
       System.out.println(anio);
       
       System.out.println("----------------");
       
       Calendar calendariogr = new GregorianCalendar(); // se esta instanciando la
                         // la clase calendario, tambien se puede pasar parametros 
       int aniogr = calendariogr.get(Calendar.YEAR);
       
       System.out.println(aniogr);
       
       System.out.println("----------------");
       // se envia parametros a ka clase GregorianCalendar//
       
       Calendar calendarioo = new GregorianCalendar(2015, Calendar.DECEMBER, 25);
       int MES = calendarioo.get(Calendar.MONTH);
       
       System.out.println(MES);
	}

}

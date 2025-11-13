package arregloEnUnaDimension;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class arreglosUna {

	public static void main(String[] args) throws IOException {

		int A[] = new int[5], x;

		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

		for (x = 0; x <= 4; x++) {
			System.out.println("Ingrese un numero");
			A[x] = Integer.parseInt(b.readLine());
		}

		System.out.println("Números del Arreglo \n");

		for (x = 0; x <= 4; x++) {
			System.out.println(A[x] + " \n ");
		}
	}
}

package arregloEnDosDimensiones;

import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class arreglos {

	public static void main(String args[]) {
		int M[][] = new int[3][3], x, y;

		JTextArea t = new JTextArea();

		// Leer los números
		for (x = 0; x <= 2; x++) {
			for (y = 0; y <= 2; y++) {
				M[x][y] = Integer.parseInt(JOptionPane.showInputDialog("Número"));
			}
		}
		// Mostrar la matriz
		for (x = 0; x <= 2; x++) {
			for (y = 0; y <= 2; y++) {
				t.append(M[x][y] + "\t");
			}
			t.append("\n");
		}

		JOptionPane.showMessageDialog(null, t);
	}

}

package poo;

public class DemoEncapsulacion {
	public static void main(String[] args) {
		CuentaBancaria c = new CuentaBancaria("Ana", 1000.0, "PAB");
		c.depositar(250.0);
		c.retirar(300.0);
		System.out.println(c);
	}
}
package encapsulamenito;

public class CuentaBancaria {

	// Atributo privado (no accesible directamente desde fuera)
	private double saldo;

	// Constructor
	public CuentaBancaria(double saldoInicial) {
		saldo = saldoInicial;
	}

	// Método para acceder al saldo (getter)
	public double getSaldo() {
		return saldo;
	}

	// Método para modificar el saldo (setter)
	public void setSaldo(double nuevoSaldo) {
		if (nuevoSaldo >= 0) {
			saldo = nuevoSaldo;
		} else {
			System.out.println("El saldo no puede ser negativo");
		}
	}

	// Método para depositar dinero
	public void depositar(double cantidad) {
		if (cantidad > 0) {
			saldo += cantidad;
		}
	}

	// Método para retirar dinero
	public void retirar(double cantidad) {
		if (cantidad > 0 && saldo >= cantidad) {
			saldo -= cantidad;
		} else {
			System.out.println("Fondos insuficientes o cantidad inválida");
		}
	}
}
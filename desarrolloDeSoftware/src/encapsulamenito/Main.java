package encapsulamenito;

public class Main {
	public static void main(String[] args) {
		CuentaBancaria miCuenta = new CuentaBancaria(1000);

		// Accediendo al saldo vía getter
		System.out.println("Saldo inicial: " + miCuenta.getSaldo());
		// Depositando
		miCuenta.depositar(500);
		System.out.println("Saldo después de depósito: " + miCuenta.getSaldo());
		// Retirando dinero
		miCuenta.retirar(300);
		System.out.println("Saldo después de retiro: " + miCuenta.getSaldo());
		// Intentar poner saldo negativo (no permitido)
		miCuenta.setSaldo(-50); // Muestra mensaje de error
	}
}

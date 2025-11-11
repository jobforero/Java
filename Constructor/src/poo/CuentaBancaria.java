package poo;

public class CuentaBancaria {
	
	      private String titular;
	      private double saldo;
	      private String moneda;
	  
	      public CuentaBancaria(String titular, double saldoInicial, String moneda) {
	          if (titular == null || titular.isBlank()) {
	              throw new IllegalArgumentException("El titular no puede ser vacío");
	          }
	          if (saldoInicial < 0) {
	              throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
	          }
	          if (!moneda.equals("USD") && !moneda.equals("EUR") && !moneda.equals("PAB")) {
	              throw new IllegalArgumentException("Moneda no soportada");
	          }
	          this.titular = titular;
	          this.saldo = saldoInicial;
	          this.moneda = moneda;
	      }
	  
	      public String getTitular() {
	          return titular;
	      }
	  
	      public String getMoneda() {
	          return moneda;
	      }
	  
	      public double getSaldo() {
	          return saldo;
	      }
	  
	      public void depositar(double cantidad) {
	          if (cantidad <= 0) {
	              throw new IllegalArgumentException("La cantidad a depositar debe ser positiva");
	          }
	          this.saldo += cantidad;
	      }
	  
	      public void retirar(double cantidad) {
	          if (cantidad <= 0) {
	              throw new IllegalArgumentException("La cantidad a retirar debe ser positiva");
	          }
	          if (cantidad > this.saldo) {
	              throw new IllegalStateException("Fondos insuficientes");
	          }
	          this.saldo -= cantidad;
	      }
	  
	      @Override
	      public String toString() {
	          return "CuentaBancaria{titular='" + titular + "', saldo=" + saldo + " " + moneda + "}";
	      }
	      
}
	  
	  
  


package poo;

public class Calculadora {
    public int sumar(int a, int b) {
        return a + b;
    }

    // Sobrecarga: distinta firma
    public double sumar(double a, double b) {
        return a + b;
    }
}
package poo;

public class CalculadoraCientifica extends Calculadora {
    @Override
    public int sumar(int a, int b) {
        System.out.println("Suma con registro de auditoría");
        return super.sumar(a, b);
    }
}
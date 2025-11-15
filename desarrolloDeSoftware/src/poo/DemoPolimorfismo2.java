package poo;

public class DemoPolimorfismo2 {
    public static void main(String[] args) {
        Calculadora calc = new CalculadoraCientifica(); // Upcasting
        System.out.println(calc.sumar(2, 3));   // usa la versión sobrescrita
        System.out.println(calc.sumar(2.5, 3.5)); // usa la sobrecargada (double)
    }
}

package poo;

public class DemoPolimorfismo1 {
    public static void main(String[] args) {
        Animal a1 = new Perro();
        Animal a2 = new Gato();

        a1.hacerSonido();  // Guau
        a2.hacerSonido();  // Miau
    }
}

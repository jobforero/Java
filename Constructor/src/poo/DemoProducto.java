package poo;

public class DemoProducto {
	
    public static void main(String[] args) {
        Producto p = new Producto("Laptop", 1200.0);
        p.aplicarDescuento(10);
        System.out.println(p); // Producto{nombre='Laptop', precio=1080.0}
    }

}

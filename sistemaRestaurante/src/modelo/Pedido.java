package modelo;

import java.util.ArrayList;

/**
 * Clase Pedido
 * Representa un pedido que contiene varios productos
 * 
 * COMPOSICIÓN: Un Pedido "tiene" (contiene) una lista de Productos
 */

public class Pedido {
    
    // ATRIBUTOS
    private static int contadorPedidos = 0; // Contador estático para generar IDs únicos
    private int numeroPedido;
    private ArrayList<Producto> productos;
    private String estado; // "En Proceso", "Completado", "Cancelado"
    
    /**
     * CONSTRUCTOR
     */
    public Pedido() {
        contadorPedidos++;
        this.numeroPedido = contadorPedidos;
        this.productos = new ArrayList<>();
        this.estado = "En Proceso";
    }
    
    /**
     * Agregar producto al pedido
     */
    public void agregarProducto(Producto producto) {
        // VALIDACIÓN: El producto no puede ser null
        if (producto == null) {
            System.out.println("Error: Producto inválido");
            return;
        }
        
        productos.add(producto);
        System.out.println(" " + producto.getNombre() + " agregado al pedido");
    }
    
    /**
     * Eliminar producto del pedido
     */
    public void eliminarProducto(int indice) {
        // VALIDACIÓN: Verificar que el índice sea válido
        if (indice >= 0 && indice < productos.size()) {
            Producto removido = productos.remove(indice);
            System.out.println("✓ " + removido.getNombre() + " eliminado del pedido");
        } else {
            System.out.println("Error: Índice inválido");
        }
    }
    
    /**
     * Calcular total del pedido
     * FOR: Recorre todos los productos
     * POLIMORFISMO: Cada producto calcula su precio según su tipo
     */
    public double calcularTotal() {
        double total = 0;
        
        // FOR mejorado (for-each)
        for (Producto producto : productos) {
            // Aquí se aplica POLIMORFISMO
            total += producto.calcularPrecio();
        }
        
        return total;
    }
    
    /**
     * Mostrar todos los productos del pedido
     * FOR: Para recorrer la lista
     */
    public void mostrarProductos() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║    PEDIDO #" + numeroPedido + " - " + estado + "           ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        // VALIDACIÓN: Si el pedido está vacío
        if (productos.isEmpty()) {
            System.out.println("⚠ El pedido está vacío");
            return;
        }
        
        // FOR tradicional con índice
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.printf("%d. %-30s $%.2f\n", 
                            (i + 1), 
                            p.getNombre(), 
                            p.calcularPrecio());
        }
        
        System.out.println("────────────────────────────────────────");
        System.out.printf("TOTAL: $%.2f\n", calcularTotal());
        System.out.println("════════════════════════════════════════\n");
    }
    
    /**
     * Verificar si el pedido tiene productos
     */
    public boolean tieneProductos() {
        return !productos.isEmpty();
    }
    
    /**
     * Cambiar estado del pedido
     */
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }
    
    // GETTERS Y SETTERS
    
    public int getNumeroPedido() {
        return numeroPedido;
    }
    
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public String getEstado() {
        return estado;
    }
    
    /**
     * toString() para representación rápida
     */
    @Override
    public String toString() {
        return "Pedido #" + numeroPedido + " - " + productos.size() + 
               " productos - Total: $" + calcularTotal();
    }
}
package servicio;

import modelo.*;
import java.util.ArrayList;

/**
 * GestorProductos
 * Maneja el catálogo de productos disponibles en el restaurante
 */

public class GestorProductos {
    
    private ArrayList<Producto> catalogo;
    
    /**
     * CONSTRUCTOR
     * Inicializa el catálogo con algunos productos de ejemplo
     */
    public GestorProductos() {
        this.catalogo = new ArrayList<>();
        cargarProductosIniciales();
    }
    
    /**
     * Carga productos de ejemplo al iniciar
     */
    private void cargarProductosIniciales() {
        // Comidas
        catalogo.add(new Comida("Arroz con Pollo", 8.50, "Plato Fuerte", false));
        catalogo.add(new Comida("Ceviche", 12.00, "Entrada", true));
        catalogo.add(new Comida("Sancocho", 9.00, "Plato Fuerte", false));
        catalogo.add(new Comida("Patacones", 5.50, "Entrada", false));
        catalogo.add(new Comida("Ropa Vieja", 10.00, "Plato Fuerte", true));
        
        // Bebidas
        catalogo.add(new Bebida("Coca Cola", 2.00, "Mediana", false));
        catalogo.add(new Bebida("Jugo Natural", 3.50, "Grande", false));
        catalogo.add(new Bebida("Cerveza Atlas", 3.00, "Mediana", true));
        catalogo.add(new Bebida("Agua", 1.50, "Pequeña", false));
        catalogo.add(new Bebida("Chicha", 2.50, "Mediana", false));
        
        System.out.println("✓ Catálogo cargado con " + catalogo.size() + " productos");
    }
    
    /**
     * Agregar un nuevo producto al catálogo
     */
    public void agregarProducto(Producto producto) {
        // VALIDACIÓN: Producto no puede ser null
        if (producto == null) {
            System.out.println("Error: Producto inválido");
            return;
        }
        
        catalogo.add(producto);
        System.out.println("✓ Producto agregado al catálogo: " + producto.getNombre());
    }
    
    /**
     * Mostrar todos los productos del catálogo
     * FOR: Recorre la lista de productos
     */
    public void mostrarCatalogo() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          📋 CATÁLOGO DE PRODUCTOS              ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        // VALIDACIÓN: Si el catálogo está vacío
        if (catalogo.isEmpty()) {
            System.out.println("⚠ No hay productos en el catálogo");
            return;
        }
        
        // Separar por tipo
        System.out.println("🍽️  COMIDAS:");
        System.out.println("────────────────────────────────────────────────");
        int index = 0;
        for (Producto p : catalogo) {
            if (p instanceof Comida) {
                System.out.printf("[%d] %-35s $%.2f\n", 
                                index, 
                                p.toString(), 
                                p.calcularPrecio());
            }
            index++;
        }
        
        System.out.println("\n🥤 BEBIDAS:");
        System.out.println("────────────────────────────────────────────────");
        index = 0;
        for (Producto p : catalogo) {
            if (p instanceof Bebida) {
                System.out.printf("[%d] %-35s $%.2f\n", 
                                index, 
                                p.toString(), 
                                p.calcularPrecio());
            }
            index++;
        }
        
        System.out.println("\n🎁 COMBOS:");
        System.out.println("────────────────────────────────────────────────");
        index = 0;
        for (Producto p : catalogo) {
            if (p instanceof Combo) {
                System.out.printf("[%d] %s\n", index, p.toString());
            }
            index++;
        }
        
        System.out.println("════════════════════════════════════════════════\n");
    }
    
    /**
     * Buscar producto por índice
     */
    public Producto obtenerProducto(int indice) {
        // VALIDACIÓN: Verificar que el índice sea válido
        if (indice >= 0 && indice < catalogo.size()) {
            return catalogo.get(indice);
        } else {
            System.out.println("Error: Índice inválido");
            return null;
        }
    }
    
    /**
     * Obtener el tamaño del catálogo
     */
    public int cantidadProductos() {
        return catalogo.size();
    }
    
    /**
     * Verificar si hay productos disponibles
     */
    public boolean tieneProductos() {
        return !catalogo.isEmpty();
    }
    
    /**
     * Crear un combo personalizado
     */
    public Combo crearCombo(String nombreCombo, double descuento) {
        // VALIDACIÓN: Nombre no vacío y descuento válido
        if (nombreCombo == null || nombreCombo.trim().isEmpty()) {
            System.out.println("Error: El nombre del combo no puede estar vacío");
            return null;
        }
        
        if (descuento < 0 || descuento > 1) {
            System.out.println("Error: El descuento debe estar entre 0 y 1");
            return null;
        }
        
        Combo nuevoCombo = new Combo(nombreCombo, descuento);
        agregarProducto(nuevoCombo);
        return nuevoCombo;
    }
    
    public ArrayList<Producto> getCatalogo() {
        return catalogo;
    }
}
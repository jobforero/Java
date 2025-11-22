package servicio;

import modelo.*;
import java.util.ArrayList;

/**
 * GestorPedidos
 * Maneja todos los pedidos del restaurante
 */

public class GestorPedidos {
    
    private ArrayList<Pedido> pedidos;
    private Pedido pedidoActual; // El pedido que se está construyendo
    
    /**
     * CONSTRUCTOR
     */
    public GestorPedidos() {
        this.pedidos = new ArrayList<>();
        this.pedidoActual = null;
    }
    
    /**
     * Crear un nuevo pedido
     */
    public Pedido crearNuevoPedido() {
        pedidoActual = new Pedido();
        pedidos.add(pedidoActual);
        System.out.println("\n✓ Nuevo pedido creado: #" + pedidoActual.getNumeroPedido());
        return pedidoActual;
    }
    
    /**
     * Agregar producto al pedido actual
     */
    public void agregarProductoAPedido(Producto producto) {
        // VALIDACIÓN: Debe haber un pedido activo
        if (pedidoActual == null) {
            System.out.println("Error: No hay un pedido activo. Cree un pedido primero.");
            return;
        }
        
        // VALIDACIÓN: El producto no puede ser null
        if (producto == null) {
            System.out.println("Error: Producto inválido");
            return;
        }
        
        pedidoActual.agregarProducto(producto);
    }
    
    /**
     * Mostrar el pedido actual
     */
    public void mostrarPedidoActual() {
        // VALIDACIÓN: Debe haber un pedido activo
        if (pedidoActual == null) {
            System.out.println("⚠ No hay un pedido activo");
            return;
        }
        
        pedidoActual.mostrarProductos();
    }
    
    /**
     * Mostrar todos los pedidos
     * FOR: Recorre la lista de pedidos
     */
    public void mostrarTodosPedidos() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║           📑 TODOS LOS PEDIDOS                 ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        // VALIDACIÓN: Si no hay pedidos
        if (pedidos.isEmpty()) {
            System.out.println("⚠ No hay pedidos registrados");
            return;
        }
        
        // FOR: Recorrer todos los pedidos
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            System.out.printf("[%d] Pedido #%d | %d productos | Total: $%.2f | Estado: %s\n",
                            i,
                            p.getNumeroPedido(),
                            p.getProductos().size(),
                            p.calcularTotal(),
                            p.getEstado());
        }
        System.out.println("════════════════════════════════════════════════\n");
    }
    
    /**
     * Obtener pedido por índice
     */
    public Pedido obtenerPedido(int indice) {
        // VALIDACIÓN: Verificar índice válido
        if (indice >= 0 && indice < pedidos.size()) {
            return pedidos.get(indice);
        } else {
            System.out.println("Error: Índice de pedido inválido");
            return null;
        }
    }
    
    /**
     * Finalizar el pedido actual
     */
    public void finalizarPedidoActual() {
        // VALIDACIÓN: Debe haber un pedido activo
        if (pedidoActual == null) {
            System.out.println("Error: No hay un pedido activo");
            return;
        }
        
        // VALIDACIÓN: El pedido debe tener productos
        if (!pedidoActual.tieneProductos()) {
            System.out.println("Error: El pedido está vacío. Agregue productos primero.");
            return;
        }
        
        pedidoActual.cambiarEstado("Completado");
        System.out.println("✓ Pedido #" + pedidoActual.getNumeroPedido() + " finalizado");
        pedidoActual = null; // Libera el pedido actual
    }
    
    /**
     * Cancelar el pedido actual
     */
    public void cancelarPedidoActual() {
        if (pedidoActual == null) {
            System.out.println("Error: No hay un pedido activo");
            return;
        }
        
        pedidoActual.cambiarEstado("Cancelado");
        System.out.println("✓ Pedido #" + pedidoActual.getNumeroPedido() + " cancelado");
        pedidoActual = null;
    }
    
    /**
     * Eliminar producto del pedido actual
     */
    public void eliminarProductoDePedido(int indice) {
        if (pedidoActual == null) {
            System.out.println("Error: No hay un pedido activo");
            return;
        }
        
        pedidoActual.eliminarProducto(indice);
    }
    
    /**
     * Verificar si hay un pedido activo
     */
    public boolean hayPedidoActivo() {
        return pedidoActual != null;
    }
    
    /**
     * Obtener el pedido actual
     */
    public Pedido getPedidoActual() {
        return pedidoActual;
    }
    
    /**
     * Obtener todos los pedidos
     */
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    
    /**
     * Cantidad de pedidos
     */
    public int cantidadPedidos() {
        return pedidos.size();
    }
}
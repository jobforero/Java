package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase Factura
 * Genera el documento final de un pedido con cliente, fecha y total
 * 
 * COMPOSICIÓN: Una Factura "tiene" un Pedido
 */
public class Factura {
    
    // ATRIBUTOS
    private static int contadorFacturas = 0; // Para generar números únicos
    private int numeroFactura;
    private String nombreCliente;
    private LocalDateTime fecha; // Fecha y hora de la factura
    private Pedido pedido; // COMPOSICIÓN: Factura contiene un Pedido
    private double total;
    
    /**
     * CONSTRUCTOR
     */
    public Factura(String nombreCliente, Pedido pedido) {
        contadorFacturas++;
        this.numeroFactura = contadorFacturas;
        this.nombreCliente = nombreCliente;
        this.fecha = LocalDateTime.now(); // Fecha actual
        this.pedido = pedido;
        this.total = pedido.calcularTotal();
    }
    
    /**
     * Generar la factura en formato visual
     * Este método muestra toda la información de la factura
     */
    public void generarFactura() {
        // Formato para la fecha
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║          🍽️  RESTAURANTE POO  🍽️             ║");
        System.out.println("║            FACTURA DE VENTA                    ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Factura No: " + numeroFactura);
        System.out.println("Fecha: " + fecha.format(formato));
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Pedido No: " + pedido.getNumeroPedido());
        System.out.println();
        System.out.println("────────────────────────────────────────────────");
        System.out.println("DETALLE DE PRODUCTOS:");
        System.out.println("────────────────────────────────────────────────");
        
        // FOR: Recorrer productos del pedido
        int contador = 1;
        for (Producto producto : pedido.getProductos()) {
            System.out.printf("%d. %-35s $%.2f\n", 
                            contador, 
                            producto.getNombre(),
                            producto.calcularPrecio());
            contador++;
        }
        
        System.out.println("────────────────────────────────────────────────");
        System.out.printf("SUBTOTAL:                              $%.2f\n", total);
        System.out.printf("ITBMS (7%%):                            $%.2f\n", calcularImpuesto());
        System.out.printf("TOTAL A PAGAR:                         $%.2f\n", calcularTotalConImpuesto());
        System.out.println("════════════════════════════════════════════════");
        System.out.println("        ¡Gracias por su preferencia!            ");
        System.out.println("════════════════════════════════════════════════\n");
    }
    
    /**
     * Calcula el impuesto (7% ITBMS en Panamá)
     */
    public double calcularImpuesto() {
        return total * 0.07;
    }
    
    /**
     * Calcula el total con impuesto incluido
     */
    public double calcularTotalConImpuesto() {
        return total + calcularImpuesto();
    }
    
    /**
     * Método para imprimir resumen corto
     */
    public void imprimirResumen() {
        System.out.println("Factura #" + numeroFactura + 
                         " | Cliente: " + nombreCliente + 
                         " | Total: $" + calcularTotalConImpuesto());
    }
    
    // GETTERS Y SETTERS
    
    public int getNumeroFactura() {
        return numeroFactura;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente) {
        // VALIDACIÓN: El nombre no puede estar vacío
        if (nombreCliente != null && !nombreCliente.trim().isEmpty()) {
            this.nombreCliente = nombreCliente;
        } else {
            System.out.println("Error: El nombre del cliente no puede estar vacío");
        }
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public Pedido getPedido() {
        return pedido;
    }
    
    public double getTotal() {
        return total;
    }
    
    /**
     * toString() para representación rápida
     */
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Factura #" + numeroFactura + 
               " | " + nombreCliente + 
               " | " + fecha.format(formato) + 
               " | $" + calcularTotalConImpuesto();
    }
}
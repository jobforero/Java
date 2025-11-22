package servicio;

import modelo.*;
import java.util.ArrayList;

/**
 * GestorFacturas
 * Maneja la generación y almacenamiento de facturas
 */
public class GestorFacturas {
    
    private ArrayList<Factura> facturas;
    
    /**
     * CONSTRUCTOR
     */
    public GestorFacturas() {
        this.facturas = new ArrayList<>();
    }
    
    /**
     * Generar factura a partir de un pedido
     */
    public Factura generarFactura(String nombreCliente, Pedido pedido) {
        // VALIDACIÓN: Nombre del cliente no puede estar vacío
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            System.out.println("Error: Debe ingresar el nombre del cliente");
            return null;
        }
        
        // VALIDACIÓN: El pedido no puede ser null
        if (pedido == null) {
            System.out.println("Error: Pedido inválido");
            return null;
        }
        
        // VALIDACIÓN: El pedido debe tener productos
        if (!pedido.tieneProductos()) {
            System.out.println("Error: No se puede facturar un pedido vacío");
            return null;
        }
        
        // Crear la factura
        Factura nuevaFactura = new Factura(nombreCliente, pedido);
        facturas.add(nuevaFactura);
        
        // Cambiar estado del pedido
        pedido.cambiarEstado("Facturado");
        
        System.out.println("\n✓ Factura generada exitosamente");
        nuevaFactura.generarFactura();
        
        return nuevaFactura;
    }
    
    /**
     * Mostrar todas las facturas generadas
     * FOR: Recorre la lista de facturas
     */
    public void mostrarTodasFacturas() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          📄 HISTORIAL DE FACTURAS              ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        // VALIDACIÓN: Si no hay facturas
        if (facturas.isEmpty()) {
            System.out.println("⚠ No hay facturas registradas");
            return;
        }
        
        // FOR: Recorrer todas las facturas
        for (int i = 0; i < facturas.size(); i++) {
            Factura f = facturas.get(i);
            System.out.printf("[%d] ", i);
            f.imprimirResumen();
        }
        
        System.out.println("\n════════════════════════════════════════════════\n");
    }
    
    /**
     * Mostrar factura específica por índice
     */
    public void mostrarFactura(int indice) {
        // VALIDACIÓN: Verificar índice válido
        if (indice >= 0 && indice < facturas.size()) {
            facturas.get(indice).generarFactura();
        } else {
            System.out.println("Error: Índice de factura inválido");
        }
    }
    
    /**
     * Obtener factura por índice
     */
    public Factura obtenerFactura(int indice) {
        if (indice >= 0 && indice < facturas.size()) {
            return facturas.get(indice);
        } else {
            System.out.println("Error: Índice inválido");
            return null;
        }
    }
    
    /**
     * Calcular total de ventas
     * FOR: Suma todas las facturas
     */
    public double calcularTotalVentas() {
        double total = 0;
        
        for (Factura f : facturas) {
            total += f.calcularTotalConImpuesto();
        }
        
        return total;
    }
    
    /**
     * Mostrar reporte de ventas
     */
    public void mostrarReporteVentas() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          📊 REPORTE DE VENTAS                  ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        if (facturas.isEmpty()) {
            System.out.println("⚠ No hay ventas registradas");
            return;
        }
        
        System.out.println("Total de facturas: " + facturas.size());
        System.out.printf("Total de ventas: $%.2f\n", calcularTotalVentas());
        System.out.printf("Promedio por factura: $%.2f\n", 
                        calcularTotalVentas() / facturas.size());
        
        System.out.println("\n════════════════════════════════════════════════\n");
    }
    
    /**
     * Cantidad de facturas
     */
    public int cantidadFacturas() {
        return facturas.size();
    }
    
    /**
     * Obtener todas las facturas
     */
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }
}
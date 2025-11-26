package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import servicio.*;
import modelo.*;
import java.time.format.DateTimeFormatter;

/**
 * VentanaFacturas - GUI para gestionar facturas
 */
public class VentanaFacturas extends JFrame {
    
    private GestorPedidos gestorPedidos;
    private GestorFacturas gestorFacturas;
    
    // Componentes
    private JTable tablaFacturas;
    private DefaultTableModel modeloTabla;
    private JButton btnGenerarFactura;
    private JButton btnVerFactura;
    private JButton btnReporte;
    private JButton btnVolver;
    
    public VentanaFacturas(GestorPedidos gestorPedidos, GestorFacturas gestorFacturas) {
        this.gestorPedidos = gestorPedidos;
        this.gestorFacturas = gestorFacturas;
        
        configurarVentana();
        inicializarComponentes();
        cargarFacturas();
        
        setVisible(true);
    }
    
    private void configurarVentana() {
        setTitle("💵 Gestión de Facturas");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(255, 193, 7));
        JLabel lblTitulo = new JLabel("💵 GESTIÓN DE FACTURAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(51, 51, 51));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Tabla de facturas
        String[] columnas = {"N° Factura", "Cliente", "Fecha", "Productos", "Subtotal", "ITBMS", "Total"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaFacturas = new JTable(modeloTabla);
        tablaFacturas.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaFacturas.setRowHeight(30);
        tablaFacturas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tablaFacturas.getTableHeader().setBackground(new Color(255, 193, 7));
        tablaFacturas.getTableHeader().setForeground(new Color(51, 51, 51));
        
        JScrollPane scrollPane = new JScrollPane(tablaFacturas);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(245, 245, 245));
        
        btnGenerarFactura = crearBoton("📝 Generar Factura", new Color(40, 167, 69));
        btnGenerarFactura.addActionListener(e -> generarFactura());
        panelBotones.add(btnGenerarFactura);
        
        btnVerFactura = crearBoton("👁️ Ver Factura", new Color(0, 123, 255));
        btnVerFactura.addActionListener(e -> verFacturaDetallada());
        panelBotones.add(btnVerFactura);
        
        btnReporte = crearBoton("📊 Reporte de Ventas", new Color(108, 117, 125));
        btnReporte.addActionListener(e -> mostrarReporteVentas());
        panelBotones.add(btnReporte);
        
        btnVolver = crearBoton("← Volver", new Color(220, 53, 69));
        btnVolver.addActionListener(e -> dispose());
        panelBotones.add(btnVolver);
        
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(180, 40));
        return boton;
    }
    
    private void cargarFacturas() {
        modeloTabla.setRowCount(0);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (Factura f : gestorFacturas.getFacturas()) {
            modeloTabla.addRow(new Object[]{
                f.getNumeroFactura(),
                f.getNombreCliente(),
                f.getFecha().format(formato),
                f.getPedido().getProductos().size(),
                String.format("$%.2f", f.getTotal()),
                String.format("$%.2f", f.calcularImpuesto()),
                String.format("$%.2f", f.calcularTotalConImpuesto())
            });
        }
    }
    
    private void generarFactura() {
        // Mostrar lista de pedidos disponibles
        if (gestorPedidos.cantidadPedidos() == 0) {
            JOptionPane.showMessageDialog(this, 
                "No hay pedidos disponibles para facturar", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Crear diálogo para seleccionar pedido
        JDialog dialogo = new JDialog(this, "Seleccionar Pedido", true);
        dialogo.setSize(500, 400);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < gestorPedidos.getPedidos().size(); i++) {
            Pedido p = gestorPedidos.getPedidos().get(i);
            if (p.tieneProductos()) {
                listModel.addElement(String.format("[%d] Pedido #%d | %d productos | $%.2f | %s",
                    i, p.getNumeroPedido(), p.getProductos().size(), p.calcularTotal(), p.getEstado()));
            }
        }
        
        JList<String> lista = new JList<>(listModel);
        lista.setFont(new Font("Arial", Font.PLAIN, 13));
        
        JPanel panelInferior = new JPanel(new GridLayout(2, 2, 10, 10));
        panelInferior.add(new JLabel("Nombre del cliente:"));
        JTextField txtCliente = new JTextField();
        panelInferior.add(txtCliente);
        
        JButton btnGenerar = new JButton("Generar Factura");
        btnGenerar.setBackground(new Color(40, 167, 69));
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.addActionListener(e -> {
            int indice = lista.getSelectedIndex();
            String cliente = txtCliente.getText().trim();
            
            if (indice < 0) {
                JOptionPane.showMessageDialog(dialogo, "Seleccione un pedido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (cliente.isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "Ingrese el nombre del cliente", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Obtener el índice real del pedido
            String seleccion = lista.getSelectedValue();
            int indiceReal = Integer.parseInt(seleccion.substring(1, seleccion.indexOf("]")));
            
            Pedido pedido = gestorPedidos.obtenerPedido(indiceReal);
            if (pedido != null) {
                gestorFacturas.generarFactura(cliente, pedido);
                cargarFacturas();
                dialogo.dispose();
                
                // Mostrar factura generada
                Factura facturaGenerada = gestorFacturas.getFacturas().get(gestorFacturas.cantidadFacturas() - 1);
                mostrarFactura(facturaGenerada);
            }
        });
        
        panelInferior.add(btnGenerar);
        
        dialogo.add(new JScrollPane(lista), BorderLayout.CENTER);
        dialogo.add(panelInferior, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    private void verFacturaDetallada() {
        int filaSeleccionada = tablaFacturas.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione una factura de la tabla", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Factura factura = gestorFacturas.getFacturas().get(filaSeleccionada);
        mostrarFactura(factura);
    }
    
    private void mostrarFactura(Factura factura) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        StringBuilder contenido = new StringBuilder();
        contenido.append("╔════════════════════════════════════════════════╗\n");
        contenido.append("║          🍽️  RESTAURANTE POO  🍽️              ║\n");
        contenido.append("║            FACTURA DE VENTA                    ║\n");
        contenido.append("╚════════════════════════════════════════════════╝\n\n");
        contenido.append("Factura No: ").append(factura.getNumeroFactura()).append("\n");
        contenido.append("Fecha: ").append(factura.getFecha().format(formato)).append("\n");
        contenido.append("Cliente: ").append(factura.getNombreCliente()).append("\n");
        contenido.append("Pedido No: ").append(factura.getPedido().getNumeroPedido()).append("\n\n");
        contenido.append("────────────────────────────────────────────────\n");
        contenido.append("DETALLE DE PRODUCTOS:\n");
        contenido.append("────────────────────────────────────────────────\n");
        
        int contador = 1;
        for (Producto p : factura.getPedido().getProductos()) {
            contenido.append(String.format("%d. %-35s $%.2f\n", 
                contador++, p.getNombre(), p.calcularPrecio()));
        }
        
        contenido.append("────────────────────────────────────────────────\n");
        contenido.append(String.format("SUBTOTAL:                              $%.2f\n", factura.getTotal()));
        contenido.append(String.format("ITBMS (7%%):                            $%.2f\n", factura.calcularImpuesto()));
        contenido.append(String.format("TOTAL A PAGAR:                         $%.2f\n", factura.calcularTotalConImpuesto()));
        contenido.append("════════════════════════════════════════════════\n");
        contenido.append("        ¡Gracias por su preferencia!            \n");
        contenido.append("════════════════════════════════════════════════\n");
        
        JTextArea textArea = new JTextArea(contenido.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(550, 500));
        
        JOptionPane.showMessageDialog(this, scroll, 
            "Factura #" + factura.getNumeroFactura(), 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarReporteVentas() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("╔════════════════════════════════════════════════╗\n");
        reporte.append("║          📊 REPORTE DE VENTAS                  ║\n");
        reporte.append("╚════════════════════════════════════════════════╝\n\n");
        
        if (gestorFacturas.cantidadFacturas() == 0) {
            reporte.append("⚠ No hay ventas registradas\n");
        } else {
            reporte.append("Total de facturas: ").append(gestorFacturas.cantidadFacturas()).append("\n");
            reporte.append(String.format("Total de ventas: $%.2f\n", gestorFacturas.calcularTotalVentas()));
            reporte.append(String.format("Promedio por factura: $%.2f\n", 
                gestorFacturas.calcularTotalVentas() / gestorFacturas.cantidadFacturas()));
        }
        
        reporte.append("\n════════════════════════════════════════════════\n");
        
        JTextArea textArea = new JTextArea(reporte.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);
        
        JOptionPane.showMessageDialog(this, textArea, 
            "Reporte de Ventas", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}
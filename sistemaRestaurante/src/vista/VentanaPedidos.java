package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import servicio.*;
import modelo.*;

/**
 * VentanaPedidos - GUI para gestionar pedidos
 */
public class VentanaPedidos extends JFrame {
    
    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;
    
    // Componentes
    private JTable tablaPedidoActual;
    private DefaultTableModel modeloTabla;
    private JLabel lblNumeroPedido;
    private JLabel lblTotal;
    private JButton btnNuevoPedido;
    private JButton btnAgregarProducto;
    private JButton btnEliminarProducto;
    private JButton btnFinalizarPedido;
    private JButton btnVerTodos;
    private JButton btnVolver;
    
    public VentanaPedidos(GestorProductos gestorProductos, GestorPedidos gestorPedidos) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;
        
        configurarVentana();
        inicializarComponentes();
        actualizarVista();
        
        setVisible(true);
    }
    
    private void configurarVentana() {
        setTitle("🛒 Gestión de Pedidos");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(40, 167, 69));
        
        JLabel lblTitulo = new JLabel("🛒 GESTIÓN DE PEDIDOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);
        
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfo.setBackground(new Color(40, 167, 69));
        lblNumeroPedido = new JLabel("Sin pedido activo");
        lblNumeroPedido.setFont(new Font("Arial", Font.BOLD, 16));
        lblNumeroPedido.setForeground(Color.WHITE);
        panelInfo.add(lblNumeroPedido);
        panelSuperior.add(panelInfo, BorderLayout.WEST);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        // Tabla del pedido actual
        String[] columnas = {"#", "Producto", "Precio", "Tipo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaPedidoActual = new JTable(modeloTabla);
        tablaPedidoActual.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaPedidoActual.setRowHeight(30);
        tablaPedidoActual.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaPedidoActual.getTableHeader().setBackground(new Color(52, 152, 219));
        tablaPedidoActual.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(tablaPedidoActual);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior con total y botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(245, 245, 245));
        
        // Panel del total
        JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTotal.setBackground(new Color(245, 245, 245));
        lblTotal = new JLabel("TOTAL: $0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 20));
        lblTotal.setForeground(new Color(40, 167, 69));
        panelTotal.add(lblTotal);
        panelInferior.add(panelTotal, BorderLayout.NORTH);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(245, 245, 245));
        
        btnNuevoPedido = crearBoton("🆕 Nuevo Pedido", new Color(40, 167, 69));
        btnNuevoPedido.addActionListener(e -> crearNuevoPedido());
        panelBotones.add(btnNuevoPedido);
        
        btnAgregarProducto = crearBoton("➕ Agregar Producto", new Color(0, 123, 255));
        btnAgregarProducto.addActionListener(e -> agregarProducto());
        panelBotones.add(btnAgregarProducto);
        
        btnEliminarProducto = crearBoton("🗑️ Eliminar Producto", new Color(255, 193, 7));
        btnEliminarProducto.addActionListener(e -> eliminarProducto());
        panelBotones.add(btnEliminarProducto);
        
        btnFinalizarPedido = crearBoton("✅ Finalizar Pedido", new Color(40, 167, 69));
        btnFinalizarPedido.addActionListener(e -> finalizarPedido());
        panelBotones.add(btnFinalizarPedido);
        
        btnVerTodos = crearBoton("📋 Ver Todos", new Color(108, 117, 125));
        btnVerTodos.addActionListener(e -> verTodosPedidos());
        panelBotones.add(btnVerTodos);
        
        btnVolver = crearBoton("← Volver", new Color(220, 53, 69));
        btnVolver.addActionListener(e -> dispose());
        panelBotones.add(btnVolver);
        
        panelInferior.add(panelBotones, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(160, 35));
        return boton;
    }
    
    private void crearNuevoPedido() {
        if (gestorPedidos.hayPedidoActivo()) {
            int opcion = JOptionPane.showConfirmDialog(this,
                "Ya hay un pedido activo. ¿Desea finalizarlo y crear uno nuevo?",
                "Advertencia",
                JOptionPane.YES_NO_OPTION);
            
            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }
        }
        
        gestorPedidos.crearNuevoPedido();
        actualizarVista();
        JOptionPane.showMessageDialog(this, "✓ Nuevo pedido creado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void agregarProducto() {
        if (!gestorPedidos.hayPedidoActivo()) {
            JOptionPane.showMessageDialog(this, "Debe crear un pedido primero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Crear ventana para seleccionar producto
        JDialog dialogo = new JDialog(this, "Seleccionar Producto", true);
        dialogo.setSize(600, 400);
        dialogo.setLocationRelativeTo(this);
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < gestorProductos.getCatalogo().size(); i++) {
            Producto p = gestorProductos.getCatalogo().get(i);
            listModel.addElement(i + ". " + p.getNombre() + " - $" + String.format("%.2f", p.calcularPrecio()));
        }
        
        JList<String> lista = new JList<>(listModel);
        lista.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton btnSeleccionar = new JButton("Agregar al Pedido");
        btnSeleccionar.addActionListener(e -> {
            int indice = lista.getSelectedIndex();
            if (indice >= 0) {
                Producto producto = gestorProductos.obtenerProducto(indice);
                gestorPedidos.agregarProductoAPedido(producto);
                actualizarVista();
                dialogo.dispose();
                JOptionPane.showMessageDialog(this, "✓ Producto agregado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        dialogo.setLayout(new BorderLayout());
        dialogo.add(new JScrollPane(lista), BorderLayout.CENTER);
        dialogo.add(btnSeleccionar, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    private void eliminarProducto() {
        if (!gestorPedidos.hayPedidoActivo()) {
            JOptionPane.showMessageDialog(this, "No hay un pedido activo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int filaSeleccionada = tablaPedidoActual.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int opcion = JOptionPane.showConfirmDialog(this,
                "¿Desea eliminar este producto del pedido?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);
            
            if (opcion == JOptionPane.YES_OPTION) {
                gestorPedidos.eliminarProductoDePedido(filaSeleccionada);
                actualizarVista();
                JOptionPane.showMessageDialog(this, "✓ Producto eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void finalizarPedido() {
        if (!gestorPedidos.hayPedidoActivo()) {
            JOptionPane.showMessageDialog(this, "No hay un pedido activo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!gestorPedidos.getPedidoActual().tieneProductos()) {
            JOptionPane.showMessageDialog(this, "El pedido está vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        gestorPedidos.finalizarPedidoActual();
        actualizarVista();
        JOptionPane.showMessageDialog(this, "✓ Pedido finalizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void verTodosPedidos() {
        StringBuilder lista = new StringBuilder();
        lista.append("═══════ TODOS LOS PEDIDOS ═══════\n\n");
        
        if (gestorPedidos.cantidadPedidos() == 0) {
            lista.append("No hay pedidos registrados");
        } else {
            for (int i = 0; i < gestorPedidos.getPedidos().size(); i++) {
                Pedido p = gestorPedidos.getPedidos().get(i);
                lista.append(String.format("[%d] Pedido #%d | %d productos | Total: $%.2f | Estado: %s\n",
                    i, p.getNumeroPedido(), p.getProductos().size(), p.calcularTotal(), p.getEstado()));
            }
        }
        
        JTextArea textArea = new JTextArea(lista.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(500, 300));
        
        JOptionPane.showMessageDialog(this, scroll, "Todos los Pedidos", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void actualizarVista() {
        modeloTabla.setRowCount(0);
        
        if (gestorPedidos.hayPedidoActivo()) {
            Pedido pedido = gestorPedidos.getPedidoActual();
            lblNumeroPedido.setText("Pedido #" + pedido.getNumeroPedido() + " - En Proceso");
            
            int contador = 1;
            for (Producto p : pedido.getProductos()) {
                String tipo = p instanceof Comida ? "Comida" : p instanceof Bebida ? "Bebida" : "Combo";
                modeloTabla.addRow(new Object[]{
                    contador++,
                    p.getNombre(),
                    String.format("$%.2f", p.calcularPrecio()),
                    tipo
                });
            }
            
            lblTotal.setText(String.format("TOTAL: $%.2f", pedido.calcularTotal()));
        } else {
            lblNumeroPedido.setText("Sin pedido activo");
            lblTotal.setText("TOTAL: $0.00");
        }
    }
}
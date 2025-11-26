package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import servicio.GestorProductos;
import modelo.*;

/**
 * VentanaProductos - GUI para gestionar productos
 */
public class VentanaProductos extends JFrame {
    
    private GestorProductos gestorProductos;
    
    // Componentes
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregarComida;
    private JButton btnAgregarBebida;
    private JButton btnCrearCombo;
    private JButton btnActualizar;
    private JButton btnVolver;
    
    public VentanaProductos(GestorProductos gestorProductos) {
        this.gestorProductos = gestorProductos;
        
        configurarVentana();
        inicializarComponentes();
        cargarProductos();
        
        setVisible(true);
    }
    
    private void configurarVentana() {
        setTitle("📋 Gestión de Productos");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 123, 255));
        JLabel lblTitulo = new JLabel("📋 CATÁLOGO DE PRODUCTOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Tabla de productos
        String[] columnas = {"Tipo", "Nombre", "Precio", "Detalles"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };
        
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaProductos.setRowHeight(30);
        tablaProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaProductos.getTableHeader().setBackground(new Color(52, 152, 219));
        tablaProductos.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(245, 245, 245));
        
        btnAgregarComida = crearBoton("🍽️ Agregar Comida", new Color(40, 167, 69));
        btnAgregarComida.addActionListener(e -> mostrarFormularioComida());
        panelBotones.add(btnAgregarComida);
        
        btnAgregarBebida = crearBoton("🥤 Agregar Bebida", new Color(0, 123, 255));
        btnAgregarBebida.addActionListener(e -> mostrarFormularioBebida());
        panelBotones.add(btnAgregarBebida);
        
        btnCrearCombo = crearBoton("🎁 Crear Combo", new Color(255, 193, 7));
        btnCrearCombo.addActionListener(e -> mostrarFormularioCombo());
        panelBotones.add(btnCrearCombo);
        
        btnActualizar = crearBoton("🔄 Actualizar", new Color(108, 117, 125));
        btnActualizar.addActionListener(e -> cargarProductos());
        panelBotones.add(btnActualizar);
        
        btnVolver = crearBoton("← Volver", new Color(220, 53, 69));
        btnVolver.addActionListener(e -> dispose());
        panelBotones.add(btnVolver);
        
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(160, 40));
        return boton;
    }
    
    private void cargarProductos() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        for (Producto p : gestorProductos.getCatalogo()) {
            String tipo = "";
            String detalles = "";
            
            if (p instanceof Comida) {
                tipo = "🍽️ Comida";
                Comida c = (Comida) p;
                detalles = c.getTipoComida() + (c.isEsPicante() ? " 🌶️" : "");
            } else if (p instanceof Bebida) {
                tipo = "🥤 Bebida";
                Bebida b = (Bebida) p;
                detalles = b.getTamaño() + (b.isEsAlcoholica() ? " 🍺" : "");
            } else if (p instanceof Combo) {
                tipo = "🎁 Combo";
                Combo combo = (Combo) p;
                detalles = combo.getProductosDelCombo().size() + " productos";
            }
            
            modeloTabla.addRow(new Object[]{
                tipo,
                p.getNombre(),
                String.format("$%.2f", p.calcularPrecio()),
                detalles
            });
        }
    }
    
    private void mostrarFormularioComida() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();
        JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Entrada", "Plato Fuerte", "Postre"});
        JCheckBox chkPicante = new JCheckBox("¿Es picante?");
        
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Precio: $"));
        panel.add(txtPrecio);
        panel.add(new JLabel("Tipo:"));
        panel.add(cmbTipo);
        panel.add(new JLabel(""));
        panel.add(chkPicante);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Comida", 
                                                   JOptionPane.OK_CANCEL_OPTION, 
                                                   JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                String tipo = (String) cmbTipo.getSelectedItem();
                boolean esPicante = chkPicante.isSelected();
                
                if (nombre.isEmpty() || precio <= 0) {
                    JOptionPane.showMessageDialog(this, "Datos inválidos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Comida nuevaComida = new Comida(nombre, precio, tipo, esPicante);
                gestorProductos.agregarProducto(nuevaComida);
                cargarProductos();
                
                JOptionPane.showMessageDialog(this, "✓ Comida agregada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Precio inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void mostrarFormularioBebida() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();
        JComboBox<String> cmbTamaño = new JComboBox<>(new String[]{"Pequeña", "Mediana", "Grande"});
        JCheckBox chkAlcoholica = new JCheckBox("¿Es alcohólica?");
        
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Precio: $"));
        panel.add(txtPrecio);
        panel.add(new JLabel("Tamaño:"));
        panel.add(cmbTamaño);
        panel.add(new JLabel(""));
        panel.add(chkAlcoholica);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Bebida", 
                                                   JOptionPane.OK_CANCEL_OPTION, 
                                                   JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                String tamaño = (String) cmbTamaño.getSelectedItem();
                boolean esAlcoholica = chkAlcoholica.isSelected();
                
                if (nombre.isEmpty() || precio <= 0) {
                    JOptionPane.showMessageDialog(this, "Datos inválidos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Bebida nuevaBebida = new Bebida(nombre, precio, tamaño, esAlcoholica);
                gestorProductos.agregarProducto(nuevaBebida);
                cargarProductos();
                
                JOptionPane.showMessageDialog(this, "✓ Bebida agregada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Precio inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void mostrarFormularioCombo() {
        JOptionPane.showMessageDialog(this, 
            "La creación de combos complejos se hará en una versión futura.\nPor ahora, use el menú de consola para combos avanzados.", 
            "Información", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}
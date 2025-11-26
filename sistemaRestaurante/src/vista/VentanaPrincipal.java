package vista;

import javax.swing.*;
import java.awt.*;
import servicio.*;

/**
 * VentanaPrincipal - Interfaz Gráfica Principal
 * GUI con Swing para el sistema de restaurante
 */
public class VentanaPrincipal extends JFrame {
    
    // Gestores del sistema
    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;
    private GestorFacturas gestorFacturas;
    
    // Componentes GUI
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JButton btnProductos;
    private JButton btnPedidos;
    private JButton btnFacturas;
    private JButton btnReportes;
    private JButton btnSalir;
    
    /**
     * CONSTRUCTOR
     */
    public VentanaPrincipal() {
        // Inicializar gestores
        gestorProductos = new GestorProductos();
        gestorPedidos = new GestorPedidos();
        gestorFacturas = new GestorFacturas();
        
        // Configurar ventana
        configurarVentana();
        inicializarComponentes();
        
        // Hacer visible
        setVisible(true);
    }
    
    /**
     * Configurar la ventana principal
     */
    private void configurarVentana() {
        setTitle("🍽️ Sistema de Pedidos - Restaurante POO");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        setResizable(false);
    }
    
    /**
     * Inicializar componentes GUI
     */
    private void inicializarComponentes() {
        // Panel principal con fondo
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null); // Layout absoluto para control total
        panelPrincipal.setBackground(new Color(245, 245, 245));
        
        // Título
        lblTitulo = new JLabel("🍽️ SISTEMA DE PEDIDOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(51, 51, 51));
        lblTitulo.setBounds(120, 30, 400, 40);
        panelPrincipal.add(lblTitulo);
        
        JLabel lblSubtitulo = new JLabel("Restaurante POO 2025");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(100, 100, 100));
        lblSubtitulo.setBounds(200, 70, 200, 25);
        panelPrincipal.add(lblSubtitulo);
        
        // Botón Gestión de Productos
        btnProductos = crearBoton("📋 Gestión de Productos", 150, 130, 300, 50);
        btnProductos.addActionListener(e -> abrirGestionProductos());
        panelPrincipal.add(btnProductos);
        
        // Botón Gestión de Pedidos
        btnPedidos = crearBoton("🛒 Gestión de Pedidos", 150, 190, 300, 50);
        btnPedidos.addActionListener(e -> abrirGestionPedidos());
        panelPrincipal.add(btnPedidos);
        
        // Botón Gestión de Facturas
        btnFacturas = crearBoton("💵 Gestión de Facturas", 150, 250, 300, 50);
        btnFacturas.addActionListener(e -> abrirGestionFacturas());
        panelPrincipal.add(btnFacturas);
        
        // Botón Reportes
        btnReportes = crearBoton("📊 Reportes", 150, 310, 300, 50);
        btnReportes.addActionListener(e -> mostrarReportes());
        panelPrincipal.add(btnReportes);
        
        // Botón Salir
        btnSalir = crearBoton("🚪 Salir", 150, 380, 300, 50);
        btnSalir.setBackground(new Color(220, 53, 69));
        btnSalir.addActionListener(e -> salir());
        panelPrincipal.add(btnSalir);
        
        // Agregar panel a la ventana
        add(panelPrincipal);
    }
    
    /**
     * Crear botón estilizado
     */
    private JButton crearBoton(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(0, 123, 255));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(0, 105, 217));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (boton != btnSalir) {
                    boton.setBackground(new Color(0, 123, 255));
                } else {
                    boton.setBackground(new Color(220, 53, 69));
                }
            }
        });
        
        return boton;
    }
    
    /**
     * Abrir ventana de gestión de productos
     */
    private void abrirGestionProductos() {
        new VentanaProductos(gestorProductos);
    }
    
    /**
     * Abrir ventana de gestión de pedidos
     */
    private void abrirGestionPedidos() {
        new VentanaPedidos(gestorProductos, gestorPedidos);
    }
    
    /**
     * Abrir ventana de gestión de facturas
     */
    private void abrirGestionFacturas() {
        new VentanaFacturas(gestorPedidos, gestorFacturas);
    }
    
    /**
     * Mostrar reportes
     */
    private void mostrarReportes() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("═══════ REPORTES DEL SISTEMA ═══════\n\n");
        reporte.append("📋 Total de productos: ").append(gestorProductos.cantidadProductos()).append("\n");
        reporte.append("🛒 Total de pedidos: ").append(gestorPedidos.cantidadPedidos()).append("\n");
        reporte.append("💵 Total de facturas: ").append(gestorFacturas.cantidadFacturas()).append("\n\n");
        
        if (gestorFacturas.cantidadFacturas() > 0) {
            reporte.append("Total de ventas: $").append(String.format("%.2f", gestorFacturas.calcularTotalVentas()));
        }
        
        JOptionPane.showMessageDialog(this, reporte.toString(), "Reportes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Salir del sistema
     */
    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro que desea salir del sistema?",
            "Confirmar salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (opcion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                this,
                "¡Gracias por usar el sistema!\nHasta pronto 👋",
                "Despedida",
                JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);
        }
    }
    
    /**
     * Main para ejecutar la aplicación GUI
     */
    public static void main(String[] args) {
        // SOLUCIÓN para Fedora/Linux con Wayland o X11
        System.setProperty("java.awt.headless", "false");
        
        // Configurar compatibilidad con Wayland (Fedora por defecto)
        System.setProperty("awt.toolkit.name", "sun.awt.X11.XToolkit");
        
        // Si DISPLAY no está configurado, intentar configurarlo
        String display = System.getenv("DISPLAY");
        if (display == null || display.isEmpty()) {
            System.setProperty("DISPLAY", ":0");
            System.out.println("DISPLAY no configurado, usando :0 por defecto");
        } else {
            System.out.println("DISPLAY detectado: " + display);
        }
        
        // Usar Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla, usar el Look and Feel por defecto
            System.out.println("No se pudo cargar el Look and Feel del sistema, usando el predeterminado");
        }
        
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            try {
                new VentanaPrincipal();
                System.out.println("✓ Aplicación GUI iniciada correctamente");
            } catch (Exception e) {
                System.err.println("Error al iniciar la aplicación GUI:");
                e.printStackTrace();
            }
        });
    }
}

/* java vista.VentanaPrincipal                                     */
/* comando para ejecutar desde consola, con comando                */
/* jobforero@fedora:~/Documentos/JAVA/Java/sistemaRestaurante/bin$ */
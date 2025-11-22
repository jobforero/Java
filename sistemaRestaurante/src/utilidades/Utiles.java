package utilidades;

/**
 * Utiles
 * Clase con funciones auxiliares y de utilidad general
 */
public class Utiles {
    
    /**
     * Pausa el programa (espera a que el usuario presione Enter)
     */
    public static void pausa() {
        System.out.println("\nPresione ENTER para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignorar excepción
        }
    }
    
    /**
     * Limpiar pantalla (simula limpiar la consola)
     */
    public static void limpiarPantalla() {
        // En consola real, esto no funciona en todas las plataformas
        // Es más visual que funcional
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    /**
     * Mostrar mensaje de bienvenida
     */
    public static void mostrarBienvenida() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║                                                ║");
        System.out.println("║        🍽️  SISTEMA DE PEDIDOS  🍽️            ║");
        System.out.println("║           RESTAURANTE POO 2024                 ║");
        System.out.println("║                                                ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Mostrar mensaje de despedida
     */
    public static void mostrarDespedida() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║                                                ║");
        System.out.println("║          ¡Gracias por usar el sistema!         ║");
        System.out.println("║              Hasta pronto 👋                   ║");
        System.out.println("║                                                ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Formatear precio con símbolo de dólar
     */
    public static String formatearPrecio(double precio) {
        return String.format("$%.2f", precio);
    }
    
    /**
     * Mostrar línea separadora
     */
    public static void mostrarSeparador() {
        System.out.println("════════════════════════════════════════════════");
    }
    
    /**
     * Centrar texto (para títulos)
     */
    public static String centrarTexto(String texto, int ancho) {
        if (texto.length() >= ancho) {
            return texto;
        }
        
        int espaciosIzq = (ancho - texto.length()) / 2;
        int espaciosDer = ancho - texto.length() - espaciosIzq;
        
        return " ".repeat(espaciosIzq) + texto + " ".repeat(espaciosDer);
    }
    
    /**
     * Validar si una cadena es numérica
     */
    public static boolean esNumerico(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
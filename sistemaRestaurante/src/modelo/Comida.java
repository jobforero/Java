package modelo;

/**
 * HERENCIA: Comida extiende (hereda de) Producto
 * 
 * ¿Qué hereda?
 * - Todos los atributos: nombre, precio
 * - Todos los métodos: getNombre(), getPrecio(), etc.
 * 
 * ¿Qué agrega?
 * - Atributos específicos de Comida
 * - Implementación del método abstracto calcularPrecio()
 */
public class Comida extends Producto {
    
    // ATRIBUTO ESPECÍFICO de Comida
    private String tipoComida; // Ej: "Entrada", "Plato Fuerte", "Postre"
    private boolean esPicante;
    
    /**
     * CONSTRUCTOR
     * Usa super() para llamar al constructor de la clase padre (Producto)
     */
    public Comida(String nombre, double precio, String tipoComida, boolean esPicante) {
        // super() llama al constructor de Producto
        super(nombre, precio);
        this.tipoComida = tipoComida;
        this.esPicante = esPicante;
    }
    
    // GETTERS Y SETTERS para atributos específicos
    
    public String getTipoComida() {
        return tipoComida;
    }
    
    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }
    
    public boolean isEsPicante() {
        return esPicante;
    }
    
    public void setEsPicante(boolean esPicante) {
        this.esPicante = esPicante;
    }
    
    /**
     * IMPLEMENTACIÓN del método abstracto
     * POLIMORFISMO: Este método se comporta específicamente para Comida
     * 
     * Por ahora, retorna el precio normal
     * Podrías agregar lógica como: si es picante, +$0.50
     */
    @Override
    public double calcularPrecio() {
        return getPrecio(); // Retorna el precio tal cual
    }
    
    /**
     * SOBREESCRITURA (Override) de toString()
     * Agrega información específica de Comida
     */
    @Override
    public String toString() {
        String picante = esPicante ? " 🌶️" : "";
        return super.toString() + " [" + tipoComida + "]" + picante;
    }
}
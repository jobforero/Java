package modelo;

/**
 * HERENCIA: Bebida extiende Producto
 * Similar a Comida, pero con atributos específicos de bebidas
 */
public class Bebida extends Producto {
    
    // ATRIBUTOS ESPECÍFICOS de Bebida
    private String tamaño; // "Pequeña", "Mediana", "Grande"
    private boolean esAlcoholica;
    
    /**
     * CONSTRUCTOR
     * Llama al constructor padre con super()
     */
    public Bebida(String nombre, double precio, String tamaño, boolean esAlcoholica) {
        super(nombre, precio);
        this.tamaño = tamaño;
        this.esAlcoholica = esAlcoholica;
    }
    
    // GETTERS Y SETTERS
    
    public String getTamaño() {
        return tamaño;
    }
    
    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }
    
    public boolean isEsAlcoholica() {
        return esAlcoholica;
    }
    
    public void setEsAlcoholica(boolean esAlcoholica) {
        this.esAlcoholica = esAlcoholica;
    }
    
    /**
     * IMPLEMENTACIÓN del método abstracto calcularPrecio()
     * POLIMORFISMO: Se comporta específico para Bebida
     * 
     * Podrías agregar lógica:
     * - Tamaño grande: +$1.00
     * - Es alcohólica: +$2.00
     */
    @Override
    public double calcularPrecio() {
        double precioFinal = getPrecio();
        
        // Ejemplo de lógica adicional (opcional)
        if (tamaño.equalsIgnoreCase("Grande")) {
            precioFinal += 1.0;
        }
        
        return precioFinal;
    }
    
    /**
     * SOBREESCRITURA de toString()
     */
    @Override
    public String toString() {
        String alcoholica = esAlcoholica ? " 🍺" : "";
        return super.toString() + " [" + tamaño + "]" + alcoholica;
    }
}
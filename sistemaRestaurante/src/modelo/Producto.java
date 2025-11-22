package modelo;

/**
 * CLASE ABSTRACTA Producto
 * 
 * ¿Qué es una clase abstracta?
 * - Es una clase que NO se puede instanciar directamente (no puedes hacer: new Producto())
 * - Sirve como "plantilla" o "molde" para otras clases
 * - Puede tener métodos abstractos (sin implementación) que las subclases DEBEN implementar
 * 
 * ¿Por qué abstracta?
 * - Porque "Producto" es muy genérico, siempre será una Comida, Bebida o Combo específico
 */

public abstract class Producto {
    
    // ATRIBUTOS - ENCAPSULAMIENTO (private)
    // El encapsulamiento oculta los datos y solo se accede mediante getters/setters
    private String nombre;
    private double precio;
    
    /**
     * CONSTRUCTOR
     * Se ejecuta al crear un objeto de las subclases
     */
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    // GETTERS Y SETTERS - Permiten acceso controlado a los atributos privados
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        // VALIDACIÓN: El precio debe ser positivo
        if (precio > 0) {
            this.precio = precio;
        } else {
            System.out.println("Error: El precio debe ser positivo");
        }
    }
    
    /**
     * MÉTODO ABSTRACTO - POLIMORFISMO
     * 
     * ¿Qué es un método abstracto?
     * - No tiene implementación (sin cuerpo {})
     * - Las subclases DEBEN implementarlo
     * 
     * ¿Por qué abstracto?
     * - Porque cada tipo de producto calcula su precio diferente:
     *   * Comida y Bebida: precio normal
     *   * Combo: precio con descuento
     * 
     * Esto es POLIMORFISMO: un mismo método se comporta diferente según la clase
     */
    public abstract double calcularPrecio();
    
    /**
     * toString() - Representación en texto del objeto
     * Útil para imprimir el objeto
     */
    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }
}
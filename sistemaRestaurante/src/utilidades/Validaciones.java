package utilidades;

import java.util.Scanner;

/**
 * Validaciones
 * Clase de utilidades para validar entradas del usuario
 * Métodos estáticos para ser usados en cualquier parte del programa
 */

public class Validaciones {
    
    /**
     * Validar que un número sea positivo
     */
    public static boolean esPositivo(double numero) {
        return numero > 0;
    }
    
    /**
     * Validar que un precio sea válido (positivo)
     */
    public static boolean validarPrecio(double precio) {
        if (precio <= 0) {
            System.out.println("Error: El precio debe ser mayor a 0");
            return false;
        }
        return true;
    }
    
    /**
     * Validar que un String no esté vacío
     */
    public static boolean validarTextoNoVacio(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println("Error: El texto no puede estar vacío");
            return false;
        }
        return true;
    }
    
    /**
     * Validar que un índice esté dentro del rango válido
     */
    public static boolean validarIndice(int indice, int tamaño) {
        if (indice < 0 || indice >= tamaño) {
            System.out.println("Error: Índice fuera de rango. Debe estar entre 0 y " + (tamaño - 1));
            return false;
        }
        return true;
    }
    
    /**
     * Validar entrada numérica del usuario
     * Evita que el programa crashee si el usuario ingresa texto en lugar de número
     */
    public static int validarEnteroPositivo(Scanner scanner) {
        int numero = -1;
        boolean valido = false;
        
        while (!valido) {
            try {
                numero = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                if (numero >= 0) {
                    valido = true;
                } else {
                    System.out.println("Error: Debe ingresar un número positivo");
                    System.out.print("Intente nuevamente: ");
                }
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número válido");
                System.out.print("Intente nuevamente: ");
                scanner.nextLine(); // Limpiar buffer
            }
        }
        
        return numero;
    }
    
    /**
     * Validar entrada decimal del usuario
     */
    public static double validarDecimalPositivo(Scanner scanner) {
        double numero = -1;
        boolean valido = false;
        
        while (!valido) {
            try {
                numero = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
                
                if (numero > 0) {
                    valido = true;
                } else {
                    System.out.println("Error: Debe ingresar un número positivo");
                    System.out.print("Intente nuevamente: ");
                }
            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número válido");
                System.out.print("Intente nuevamente: ");
                scanner.nextLine(); // Limpiar buffer
            }
        }
        
        return numero;
    }
    
    /**
     * Validar opción del menú
     */
    public static boolean validarOpcionMenu(int opcion, int min, int max) {
        if (opcion < min || opcion > max) {
            System.out.println("Error: Opción inválida. Debe estar entre " + min + " y " + max);
            return false;
        }
        return true;
    }
    
    /**
     * Validar descuento (debe estar entre 0 y 1)
     */
    public static boolean validarDescuento(double descuento) {
        if (descuento < 0 || descuento > 1) {
            System.out.println("Error: El descuento debe estar entre 0 y 1");
            return false;
        }
        return true;
    }
    
    /**
     * Confirmar acción (S/N)
     */
    public static boolean confirmarAccion(Scanner scanner, String mensaje) {
        System.out.print(mensaje + " (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();
        return respuesta.equals("S") || respuesta.equals("SI");
    }
}
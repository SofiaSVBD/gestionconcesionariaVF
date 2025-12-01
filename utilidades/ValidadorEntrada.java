package utilidades;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Clase de utilidad para validación de entrada de datos
 * Aplica el uso de Wrappers (Integer, Double, etc.)
 */
public class ValidadorEntrada {
    
    private Scanner scanner;
    
    public ValidadorEntrada(Scanner scanner) {
        this.scanner = scanner;
    }
    
    /**
     * Lee un Integer con validación (uso de Wrapper Integer)
     */
    public Integer leerEntero(String mensaje, Integer min, Integer max) {
        Integer valor = null;
        boolean valido = false;
        
        while (!valido) {
            try {
                System.out.print(mensaje);
                valor = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                if (min != null && valor < min) {
                    System.out.println(" El valor debe ser mayor o igual a " + min);
                    continue;
                }
                if (max != null && valor > max) {
                    System.out.println(" El valor debe ser menor o igual a " + max);
                    continue;
                }
                
                valido = true;
                
            } catch (InputMismatchException e) {
                System.out.println(" Error: Debe ingresar un número entero válido");
                scanner.nextLine(); // Limpiar buffer
            }
        }
        
        return valor;
    }
    
    /**
     * Lee un String no vacío
     */
    public String leerTexto(String mensaje, boolean permitirVacio) {
        String texto = null;
        boolean valido = false;
        
        while (!valido) {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            
            if (!permitirVacio && texto.isEmpty()) {
                System.out.println(" Error: Este campo no puede estar vacío");
            } else {
                valido = true;
            }
        }
        
        return texto;
    }

    /**
     * Lee un String que sólo puede contener letras y espacios (útil para colores, nombres, etc.).
     */
    public String leerTextoAlfabetico(String mensaje, boolean permitirVacio) {
        String texto = null;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();

            if (!permitirVacio && texto.isEmpty()) {
                System.out.println(" Error: Este campo no puede estar vacío");
                continue;
            }

            if (texto.isEmpty()) {
                return texto; // permitido vacío
            }

            // Aceptar letras Unicode y espacios y guiones (ej. "Rojo", "Azul claro", "Gris-oscuro")
            if (texto.matches("^[\\p{L} \\-]+$")) {
                valido = true;
            } else {
                System.out.println(" Error: El valor sólo puede contener letras y espacios");
            }
        }

        return texto;
    }

    /**
     * Lee y valida un ID/patente de vehículo con formatos aceptados.
     * Acepta formatos comunes como: "AAA000AA", "AAA 000", "AAA000" y "AAAA000" (insensible a mayúsculas).
     * Normaliza la salida a mayúsculas y sin espacios ni guiones.
     */
    public String leerPatente(String mensaje, boolean permitirVacio) {
        String texto = null;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim().toUpperCase();

            if (!permitirVacio && texto.isEmpty()) {
                System.out.println(" Error: Este campo no puede estar vacío");
                continue;
            }

            if (texto.isEmpty()) {
                return texto; // permitido vacío
            }

            // Normalizar quitando espacios y guiones
            String normalizada = texto.replace(" ", "").replace("-", "");

            // Patrones aceptados (mayúsculas):
            // 1) AAA000AA (3 letras, 3 dígitos, 2 letras)
            // 2) AAA000 or AAA 000 (3 letras + 3 dígitos)
            boolean coincide = normalizada.matches("^[A-Z]{3}\\d{3}[A-Z]{2}$")
                    || normalizada.matches("^[A-Z]{3}\\d{3}$");

            if (coincide) {
                valido = true;
                return normalizada; // devolver forma normalizada en mayúsculas sin separadores
            } else {
                System.out.println(" Error: Formato de ID inválido. Formatos válidos: AAA000AA o AAA 000");
            }
        }

        return texto;
    }
    
    /**
     * Lee una opción de menú
     */
    public Integer leerOpcionMenu(Integer min, Integer max) {
        return leerEntero("\n Seleccione una opción: ", min, max);
    }
    
    /**
     * Lee un valor booleano (S/N)
     */
    public Boolean leerBooleano(String mensaje) {
        String respuesta = null;
        boolean valido = false;
        
        while (!valido) {
            System.out.print(mensaje + " (S/N): ");
            respuesta = scanner.nextLine().trim().toUpperCase();
            
            if (respuesta.equals("S") || respuesta.equals("N")) {
                valido = true;
            } else {
                System.out.println(" Error: Debe ingresar S o N");
            }
        }
        
        return respuesta.equals("S");
    }
    
    /**
     * Pausa para que el usuario pueda leer mensajes
     */
    public void pausa() {
        System.out.println("\n Presione ENTER para continuar...");
        scanner.nextLine();
    }
}
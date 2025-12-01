import presentacion.MenuPrincipal;

/**
 * Clase principal del sistema
 * Sistema de Gestión de Concesionaria de Vehículos
 * 
 * ARQUITECTURA DEL PROYECTO:
 * ========================
 * 
 * 1. PAQUETE "excepciones" (Diseño)
 *    - VehiculoInvalidoException
 *    - InventarioException
 *    - TallerException
 * 
 * 2. PAQUETE "enumerativos" (Diseño)
 *    - TipoCarroceria
 *    - TipoMotocicleta
 *    - CondicionVehiculo
 *    - EstadoMantenimiento
 * 
 * 3. PAQUETE "interfaces" (Diseño)
 *    - IVehiculo
 *    - IMantenible
 *    - IGestionable
 * 
 * 4. PAQUETE "modelo" (Diseño - JavaBeans)
 *    - Vehiculo (clase abstracta)
 *    - AutomovilCamioneta
 *    - Motocicleta
 * 
 * 5. PAQUETE "negocio" (Negocio)
 *    - InventarioVehiculos (HashMap)
 *    - ColaRevisionMecanica (LinkedList/Queue)
 *    - GestorConcesionaria
 * 
 * 6. PAQUETE "utilidades" (Diseño)
 *    - ValidadorEntrada (Wrappers)
 *    - FormateadorSalida
 * 
 * 7. PAQUETE "presentacion" (Presentación/UIX)
 *    - MenuPrincipal
 * 
 * CONCEPTOS APLICADOS:
 * ===================
 * ✓ Patrón JavaBean (constructor vacío, getters/setters, Serializable)
 * ✓ Interfaces (IVehiculo, IMantenible, IGestionable)
 * ✓ Excepciones propias (VehiculoInvalidoException, InventarioException, TallerException)
 * ✓ Tipos Enumerativos (TipoCarroceria, TipoMotocicleta, etc.)
 * ✓ Wrappers (Integer, Boolean, String)
 * ✓ Colecciones:
 *   - HashMap: Para inventario (búsquedas eficientes O(1))
 *   - LinkedList/Queue: Para cola FIFO del taller
 *   - HashSet: Para evitar duplicados en cola
 *   - ArrayList: Para listados
 * ✓ UIX avanzada con menús, validaciones y formato
 * ✓ Separación Diseño vs. Negocio
 * 
 * @author Sistema de Gestión
 * @version 1.0
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                                                      ║");
        System.out.println("║     SISTEMA DE GESTIÓN DE CONCESIONARIA              ║");
        System.out.println("║                                                      ║");
        System.out.println("║           Bienvenido al Sistema                      ║");
        System.out.println("║                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        
        try {
            MenuPrincipal menu = new MenuPrincipal();
            menu.ejecutar();
        } catch (Exception e) {
            System.err.println("\n Error crítico en el sistema: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║          Sistema finalizado correctamente            ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
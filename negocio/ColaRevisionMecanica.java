package negocio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import excepciones.TallerException;
import interfaces.IMantenible;
import modelo.Vehiculo;

/**
 * Cola de espera para el Taller Mecánico
 * Utiliza LinkedList que implementa Queue (FIFO - First In First Out)
 */
public class ColaRevisionMecanica {

    // LinkedList implementa Queue - estructura ideal para colas
    private Queue<Vehiculo> colaEspera;
    private Set<String> vehiculosEnCola; // Para evitar duplicados

    public ColaRevisionMecanica() {
        this.colaEspera = new LinkedList<>();
        this.vehiculosEnCola = new HashSet<>();
    }

    /**
     * Agrega un vehículo a la cola de espera
     */
    public void agregarACola(modelo.Vehiculo v) throws excepciones.TallerException {
        if (v == null) {
            throw new TallerException("El vehículo no puede ser nulo");
        }

        if (!v.esUsado()) {
            throw new TallerException("Solo los vehículos usados necesitan revisión mecánica");
        }

        if (vehiculosEnCola.contains(v.getIdentificador())) {
            throw new TallerException("El vehículo ya está en la cola de espera");
        }

        // --- CORRECCIÓN INICIO ---
        // Al entrar a la cola, reseteamos el estado a PENDIENTE
        if (v instanceof modelo.AutomovilCamioneta) {
            ((modelo.AutomovilCamioneta) v).setEstadoMantenimiento(enumerativos.EstadoMantenimiento.PENDIENTE);
        } else if (v instanceof modelo.Motocicleta) {
            ((modelo.Motocicleta) v).setEstadoMantenimiento(enumerativos.EstadoMantenimiento.PENDIENTE);
        }

        v.resetearEstadoMantenimiento();
        
        // Si tienes una clase Motocicleta, agrega esto también:
        /*
         * s
         * 
         * else if (v instanceof modelo.Motocicleta) {
         * ((modelo.Motocicleta)
         * v).setEstadoMantenimiento(enumerativos.EstadoMantenimiento.PENDIENTE);
         * }
         */
        // --- CORRECCIÓN FIN ---

        boolean agregado = colaEspera.offer(v);
        if (agregado) {
            vehiculosEnCola.add(v.getIdentificador());
        }
    }

    /**
     * Atiende el siguiente vehículo (saca de la cola)
     */
    public Vehiculo atenderSiguiente() throws TallerException {
        if (colaEspera.isEmpty()) {
            throw new TallerException("No hay vehículos en la cola de espera");
        }

        Vehiculo vehiculo = colaEspera.poll();
        if (vehiculo != null) {
            vehiculosEnCola.remove(vehiculo.getIdentificador());

            // Realizar el mantenimiento
            if (vehiculo instanceof IMantenible) {
                IMantenible mantenible = (IMantenible) vehiculo;
                mantenible.realizarMantenimiento();
            }
        }
        return vehiculo;
    }

    /**
     * Ver el siguiente vehículo sin sacarlo de la cola
     */
    public Vehiculo verSiguiente() {
        return colaEspera.peek();
    }

    /**
     * Cantidad de vehículos en espera
     */
    public int cantidadEnEspera() {
        return colaEspera.size();
    }

    /**
     * Verifica si la cola está vacía
     */
    public boolean estaVacia() {
        return colaEspera.isEmpty();
    }

    /**
     * Obtiene todos los vehículos en la cola (sin modificar la cola)
     */
    public List<Vehiculo> obtenerVehiculosEnCola() {
        return new ArrayList<>(colaEspera);
    }

    /**
     * Limpia la cola
     */
    public void limpiarCola() {
        colaEspera.clear();
        vehiculosEnCola.clear();
    }

    public int getCantidadEnTaller() {
        return cantidadEnEspera();
    }
}
package utilidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase genérica Contenedor para almacenar y gestionar elementos de cualquier tipo
 * Demuestra el uso de Generics en Java
 * 
 * @param <T> El tipo de elementos que contendrá esta clase
 */
public class Contenedor<T> {
    
    private List<T> elementos;
    private String nombre;
    
    public Contenedor(String nombre) {
        this.nombre = nombre;
        this.elementos = new ArrayList<>();
    }
    
    /**
     * Agrega un elemento al contenedor
     */
    public void agregar(T elemento) {
        if (elemento != null) {
            elementos.add(elemento);
        }
    }
    
    /**
     * Obtiene un elemento por índice
     */
    public T obtener(int indice) {
        if (indice >= 0 && indice < elementos.size()) {
            return elementos.get(indice);
        }
        return null;
    }
    
    /**
     * Elimina un elemento
     */
    public boolean eliminar(T elemento) {
        return elementos.remove(elemento);
    }
    
    /**
     * Obtiene todos los elementos
     */
    public List<T> obtenerTodos() {
        return new ArrayList<>(elementos);
    }
    
    /**
     * Obtiene la cantidad de elementos
     */
    public int cantidad() {
        return elementos.size();
    }
    
    /**
     * Verifica si está vacío
     */
    public boolean estaVacio() {
        return elementos.isEmpty();
    }
    
    /**
     * Limpia todos los elementos
     */
    public void limpiar() {
        elementos.clear();
    }
    
    /**
     * Verifica si contiene un elemento
     */
    public boolean contiene(T elemento) {
        return elementos.contains(elemento);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString() {
        return String.format("Contenedor '%s' con %d elementos", nombre, elementos.size());
    }
}
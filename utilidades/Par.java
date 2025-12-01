package utilidades;

/**
 * Clase genérica Par para almacenar un par de valores (clave-valor)
 * Demuestra el uso de múltiples parámetros genéricos
 * 
 * @param <K> El tipo de la clave
 * @param <V> El tipo del valor
 */
public class Par<K, V> {
    
    private K clave;
    private V valor;
    
    public Par(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }
    
    public K getClave() {
        return clave;
    }
    
    public void setClave(K clave) {
        this.clave = clave;
    }
    
    public V getValor() {
        return valor;
    }
    
    public void setValor(V valor) {
        this.valor = valor;
    }
    
    /**
     * Verifica si alguno de los valores es nulo
     */
    public boolean tieneValoresNulos() {
        return clave == null || valor == null;
    }
    
    /**
     * Intercambia clave y valor (solo si son del mismo tipo)
     */
    public Par<V, K> intercambiar() {
        return new Par<>(valor, clave);
    }
    
    @Override
    public String toString() {
        return String.format("Par[%s = %s]", clave, valor);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Par<?, ?> par = (Par<?, ?>) obj;
        
        if (clave != null ? !clave.equals(par.clave) : par.clave != null) return false;
        return valor != null ? valor.equals(par.valor) : par.valor == null;
    }
    
    @Override
    public int hashCode() {
        int result = clave != null ? clave.hashCode() : 0;
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }
}
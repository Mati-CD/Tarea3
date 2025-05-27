package org.example.Codigo;

/**
 * Clase abstracta que representa un producto con un número de serie.
 */
public abstract class Producto {
    private int serie;

    /**
     * Constructor que recibe un número de serie único.
     * @param serie Número de serie del producto.
     */
    public Producto(int serie) {
        this.serie = serie;
    }

    /**
     * Devuelve el número de serie del producto.
     * @return número de serie.
     */
    public int getSerie() {
        return serie;
    }

    /**
     * Metodo abstracto que indica qué hace el producto al consumirlo.
     * @return String representando el resultado de consumirlo.
     */
    public abstract String consumir();
}
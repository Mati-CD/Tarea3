package org.example.Codigo;

/**
 * Clase abstracta que representa un dulce.
 */
public abstract class Dulce extends Producto {
    /**
     * Constructor del dulce.
     * @param serie NÃºmero de serie del dulce.
     */
    public Dulce(int serie) {
        super(serie);
    }

    @Override
    public abstract String consumir();
}
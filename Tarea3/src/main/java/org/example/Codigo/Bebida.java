package org.example.Codigo;

/**
 * Clase abstracta que representa una bebida.
 */
public abstract class Bebida extends Producto {
    /**
     * Constructor de la clase Bebida.
     * @param serie NÃºmero de serie de la bebida.
     */
    public Bebida(int serie) {
        super(serie);
    }

    @Override
    public abstract String consumir();
}
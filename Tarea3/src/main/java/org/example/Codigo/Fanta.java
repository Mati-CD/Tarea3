package org.example.Codigo;

/**
 * Clase que representa una Fanta.
 */
public class Fanta extends Bebida {
    /**
     * Constructor para Fanta.
     * @param serie Número de serie único.
     */
    public Fanta(int serie) {
        super(serie);
    }

    @Override
    public String consumir() {
        return "fanta";
    }
}
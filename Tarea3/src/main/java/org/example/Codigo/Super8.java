package org.example.Codigo;

/**
 * Clase que representa un Super8.
 */
public class Super8 extends Dulce {
    /**
     * Constructor para Super8.
     * @param serie Número de serie único.
     */
    public Super8(int serie) {
        super(serie);
    }

    @Override
    public String consumir() {
        return "super8";
    }
}
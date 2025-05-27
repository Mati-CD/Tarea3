package org.example.Codigo;

/**
 * Clase que representa una CocaCola.
 */
public class CocaCola extends Bebida {
    /**
     * Constructor para CocaCola.
     * @param serie Número de serie único.
     */
    public CocaCola(int serie) {
        super(serie);
    }

    @Override
    public String consumir() {
        return "cocacola";
    }
}
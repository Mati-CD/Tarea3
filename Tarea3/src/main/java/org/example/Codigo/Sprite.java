package org.example.Codigo;

/**
 * Clase que representa una Sprite.
 */
public class Sprite extends Bebida {
    /**
     * Constructor para Sprite.
     * @param serie Número de serie único.
     */
    public Sprite(int serie) {
        super(serie);
    }

    @Override
    public String consumir() {
        return "sprite";
    }
}

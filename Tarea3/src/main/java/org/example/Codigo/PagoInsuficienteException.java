package org.example.Codigo;

/**
 * Excepcion que se lanza cuando el valor de la moneda no alcanza para comprar el producto.
 */
public class PagoInsuficienteException extends Exception {
    public PagoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

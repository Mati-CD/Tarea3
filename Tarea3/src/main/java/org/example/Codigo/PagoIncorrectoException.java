package org.example.Codigo;

/**
 * Excepcion que se lanza cuando no se entrega una moneda (null).
 */
public class PagoIncorrectoException extends Exception {
    public PagoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}

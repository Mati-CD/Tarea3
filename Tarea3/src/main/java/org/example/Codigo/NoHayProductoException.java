package org.example.Codigo;

/**
 * Excepcion que se lanza cuando no hay productos disponibles del tipo solicitado.
 */
public class NoHayProductoException extends Exception {
    public NoHayProductoException(String mensaje) {
        super(mensaje);
    }
}
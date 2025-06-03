package org.example.Codigo;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase genérica que representa un depósito de objetos (productos o monedas).
 * @param <T> Tipo de objeto a almacenar.
 */
public class Deposito<T> {
    private ArrayList<T> elementos;

    /**
     * Constructor del depósito.
     */
    public Deposito() {
        elementos = new ArrayList<>();
    }

    /**
     * Agrega un elemento al depósito.
     * @param elem Elemento a agregar.
     */
    public void add(T elem) {
        elementos.add(elem);
    }

    /**
     * Extrae el primer elemento del depósito o null si está vacío.
     * @return Elemento extraído o null.
     */
    public T get() {
        if (elementos.isEmpty()) return null;
        return elementos.remove(0);
    }

    public int size() {
        return elementos.size();
    }

    public ArrayList<T> getDeposito() {
        return new ArrayList<>(elementos);
    }
}
package org.example.Codigo;

/**
 * Clase abstracta que representa una moneda.
 */
public abstract class Moneda implements Comparable<Moneda> {
    private static int contadorSerie = 0;
    private final int serie;

    /**
     * Constructor de la moneda, asigna un número de serie único.
     */
    public Moneda() {
        this.serie = contadorSerie++;
    }

    /**
     * Devuelve el número de serie de la moneda.
     * @return número de serie.
     */
    public int getSerie() {
        return serie;
    }

    /**
     * Devuelve el valor de la moneda.
     * @return valor en pesos.
     */
    public abstract int getValor();

    /**
     * Implementación de compareTo para ordenar por valor de menor a mayor.
     * @param otra Otra moneda con la que comparar.
     * @return -1 si es menor, 0 si igual, 1 si mayor.
     */
    @Override
    public int compareTo(Moneda otra) {
        return Integer.compare(this.getValor(), otra.getValor());
    }

    @Override
    public String toString() {
        return "$" + getValor() + " (serie " + serie + ")";
    }
}
package org.example.Codigo;

/**
 * EnumeraciÃ³n para los diferentes tipos de productos disponibles en el expendedor.
 */
public enum TipoProducto {
    COCA_COLA("CocaCola", 1000),
    SPRITE("Sprite", 500),
    FANTA("Fanta", 500),
    SNICKERS("Snickers", 100),
    SUPER8("Super8", 100);

    private final String nombre;
    private final int precio;

    TipoProducto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Retorna el nombre del producto.
     * @return nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el precio del producto.
     * @return precio en pesos.
     */
    public int getPrecio() {
        return precio;
    }
}

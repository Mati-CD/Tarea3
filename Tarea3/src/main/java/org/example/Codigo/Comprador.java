package org.example.Codigo;

/**
 * Clase que representa a un comprador que realiza una compra y obtiene el vuelto.
 */
public class Comprador {
    private String sabor;
    private int vuelto;

    /**
     * Constructor del comprador. Realiza una compra usando el expendedor.
     * @param m Moneda usada para pagar.
     * @param num Número que indica el producto deseado.
     * @param exp Expendedor donde se realiza la compra.
     */
    public Comprador(Moneda m, int num, Expendedor exp) {
        vuelto = 0;
        sabor = null;

        try {
            TipoProducto tipo = obtenerTipoPorNumero(num);
            Producto p = exp.comprarProducto(m, tipo);
            if (p != null) {
                sabor = p.consumir();
            }
        } catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        Moneda moneda;
        while ((moneda = exp.getVuelto()) != null) {
            vuelto += moneda.getValor();
        }
    }

    /**
     * Devuelve cuánto vuelto recibió el comprador.
     * @return Vuelto total.
     */
    public int cuantoVuelto() {
        return vuelto;
    }

    /**
     * Devuelve el sabor del producto consumido.
     * @return Sabor o null si no consumió nada.
     */
    public String queConsumiste() {
        return sabor;
    }

    /**
     * Metodo auxiliar para convertir un número a TipoProducto.
     * @param num Número entregado.
     * @return Tipo de producto correspondiente.
     */
    public TipoProducto obtenerTipoPorNumero(int num) {
        switch (num) {
            case 1:
                return TipoProducto.COCA_COLA;
            case 2:
                return TipoProducto.SPRITE;
            case 3:
                return TipoProducto.FANTA;
            case 4:
                return TipoProducto.SNICKERS;
            case 5:
                return TipoProducto.SUPER8;
            default:
                throw new IllegalArgumentException("Número de producto inválido.");
        }
    }
}
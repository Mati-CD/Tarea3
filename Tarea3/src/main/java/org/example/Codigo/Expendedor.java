package org.example.Codigo;

/**
 * Clase que representa un expendedor de productos (bebidas y dulces).
 */
public class Expendedor {
    private Deposito<Producto> coca;
    private Deposito<Producto> sprite;
    private Deposito<Producto> fanta;
    private Deposito<Producto> snickers;
    private Deposito<Producto> super8;
    private Deposito<Moneda> monVu;
    private int serieContador = 0;
    private Producto depositoEspecial;

    /**
     * Constructor del expendedor que llena todos los depósitos con productos.
     * @param cantidad Cantidad de productos por tipo.
     */
    public Expendedor(int cantidad) {
        monVu = new Deposito<>();
        coca = new Deposito<>();
        sprite = new Deposito<>();
        fanta = new Deposito<>();
        snickers = new Deposito<>();
        super8 = new Deposito<>();

        for (int i = 0; i < cantidad; i++) {
            coca.add(new CocaCola(serieContador++));
            sprite.add(new Sprite(serieContador++));
            fanta.add(new Fanta(serieContador++));
            snickers.add(new Snickers(serieContador++));
            super8.add(new Super8(serieContador++));
        }
    }

    /**
     * Metodo para comprar un producto del expendedor.
     * @param m Moneda entregada.
     * @param tipo Tipo de producto a comprar.
     * @return Producto adquirido.
     * @throws PagoIncorrectoException Si no se entrega moneda.
     * @throws PagoInsuficienteException Si el valor de la moneda no alcanza.
     * @throws NoHayProductoException Si no hay stock del producto.
     */
    public void comprarProducto(Moneda m, TipoProducto tipo)
            throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {

        if (m == null) {
            throw new PagoIncorrectoException("No se ha ingresado ninguna moneda.");
        }

        if (m.getValor() < tipo.getPrecio()) {
            monVu.add(m);
            throw new PagoInsuficienteException("Pago insuficiente para el producto '" + tipo.getNombre() + "'");
        }

        Deposito<Producto> deposito = seleccionarDeposito(tipo);
        Producto producto = deposito.get();

        if (producto == null) {
            monVu.add(m);
            throw new NoHayProductoException("No hay disponibilidad del producto '" + tipo.getNombre() + "'");
        }

        int vuelto = m.getValor() - tipo.getPrecio();
        while (vuelto >= 100) {
            monVu.add(new Moneda100());
            vuelto -= 100;
        }

        depositoEspecial = producto;
    }

    public Producto getProducto() {
        return depositoEspecial;
    }


    /**
     * Metodo que devuelve una moneda del depósito de vuelto.
     * @return Moneda o null si no hay más.
     */
    public Moneda getVuelto() {
        return monVu.get();
    }

    /**
     * Selecciona el depósito correspondiente según el tipo de producto.
     * @param tipo Tipo de producto.
     * @return Depósito correspondiente.
     */
    private Deposito<Producto> seleccionarDeposito(TipoProducto tipo) {
        switch (tipo) {
            case COCA_COLA:
                return coca;
            case SPRITE:
                return sprite;
            case FANTA:
                return fanta;
            case SNICKERS:
                return snickers;
            case SUPER8:
                return super8;
            default:
                throw new IllegalArgumentException("Tipo de producto no válido.");
        }
    }
}
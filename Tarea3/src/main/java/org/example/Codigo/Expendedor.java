package org.example.Codigo;

import java.util.ArrayList;
import java.util.Collections;

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
    private Deposito<Moneda> monComprasExitosas;

    /**
     * Constructor del expendedor que llena todos los depósitos con productos.
     * @param cantidad Cantidad de productos por tipo.
     */
    public Expendedor(int cantidad) {
        monVu = new Deposito<>();
        monComprasExitosas = new Deposito<>();
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
        monComprasExitosas.add(m);

        int cuantoVuelto = m.getValor() - tipo.getPrecio();
        for(Moneda moneda : vueltoMagico(cuantoVuelto)) {
            monVu.add(moneda);
        }

        depositoEspecial = producto;
    }

    public Producto getProducto() {
        return depositoEspecial;
    }

    public ArrayList<Moneda> vueltoMagico(int cuantoVuelto) {
        ArrayList<Moneda> vuelto = new ArrayList<>();

        while(cuantoVuelto >= 1000) {
            vuelto.add(new Moneda1000());
            cuantoVuelto -= 1000;
        }
        while(cuantoVuelto >= 500) {
            vuelto.add(new Moneda500());
            cuantoVuelto -= 500;
        }
        while(cuantoVuelto >= 100) {
            vuelto.add(new Moneda100());
            cuantoVuelto -= 100;
        }
        return vuelto;
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
    public Deposito<Producto> seleccionarDeposito(TipoProducto tipo) {
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

    public void restockear(TipoProducto tipo, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            switch (tipo) {
                case COCA_COLA:
                    coca.add(new CocaCola(serieContador++));
                    break;
                case SPRITE:
                    sprite.add(new Sprite(serieContador++));
                    break;
                case FANTA:
                    fanta.add(new Fanta(serieContador++));
                    break;
                case SNICKERS:
                    snickers.add(new Snickers(serieContador++));
                    break;
                case SUPER8:
                    super8.add(new Super8(serieContador++));
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de producto no válido.");
            }
        }
    }

}
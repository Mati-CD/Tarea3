package org.example.Codigo;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase principal de pruebas del expendedor.
 */
public class Main {
    public static void main(String[] args) {
        Expendedor exp = new Expendedor(1); // Un producto por tipo
        Moneda m = null;
        int numDeposito;
        Comprador c = null;
        TipoProducto producto = null;

        System.out.println("=== COMPRAS VÁLIDAS ===");

        // CocaCola
        m = new Moneda1000();
        numDeposito = 1;
        c = new Comprador(m, numDeposito, exp);
        producto = c.obtenerTipoPorNumero(numDeposito);

        System.out.println("\nHa introducido una moneda de: $" + m.getValor());
        System.out.println("Comprando " + producto.getNombre() + " ...");
        System.out.println("Has consumido '" + c.queConsumiste() + "'");
        System.out.println("Tu vuelto es: $" + c.cuantoVuelto());

        // Sprite
        m = new Moneda1000();
        numDeposito = 2;
        c = new Comprador(m, numDeposito, exp);
        producto = c.obtenerTipoPorNumero(numDeposito);

        System.out.println("\nHa introducido una moneda de: $" + m.getValor());
        System.out.println("Comprando " + producto.getNombre() + " ...");
        System.out.println("Has consumido '" + c.queConsumiste() + "'");
        System.out.println("Tu vuelto es: $" + c.cuantoVuelto());

        // Fanta
        m = new Moneda1000();
        numDeposito = 3;
        c = new Comprador(m, numDeposito, exp);
        producto = c.obtenerTipoPorNumero(numDeposito);

        System.out.println("\nHa introducido una moneda de: $" + m.getValor());
        System.out.println("Comprando " + producto.getNombre() + " ...");
        System.out.println("Has consumido '" + c.queConsumiste() + "'");
        System.out.println("Tu vuelto es: $" + c.cuantoVuelto());

        // Snickers
        m = new Moneda1000();
        numDeposito = 4;
        c = new Comprador(m, numDeposito, exp);
        producto = c.obtenerTipoPorNumero(numDeposito);

        System.out.println("\nHa introducido una moneda de: $" + m.getValor());
        System.out.println("Comprando " + producto.getNombre() + " ...");
        System.out.println("Has consumido '" + c.queConsumiste() + "'");
        System.out.println("Tu vuelto es: $" + c.cuantoVuelto());

        // Super 8
        m = new Moneda1000();
        numDeposito = 5;
        c = new Comprador(m, numDeposito, exp);
        producto = c.obtenerTipoPorNumero(numDeposito);

        System.out.println("\nHa introducido una moneda de: $" + m.getValor());
        System.out.println("Comprando " + producto.getNombre() + " ...");
        System.out.println("Has consumido '" + c.queConsumiste() + "'");
        System.out.println("Tu vuelto es: $" + c.cuantoVuelto());


        System.out.println("\n=== PRUEBAS DE EXCEPCIONES ===\n");

        // No hay más CocaCola
        m = new Moneda1000();
        numDeposito = 1;
        c = new Comprador(m, numDeposito, exp);

        // Pago insuficiente para Sprite
        m = new Moneda500();
        numDeposito = 2;
        c = new Comprador(m, numDeposito, exp);

        // Moneda nula
        m = null;
        numDeposito = 3;
        c = new Comprador(m, numDeposito, exp);

        // Producto inválido
        try {
            m = new Moneda1000();
            numDeposito = 99;
            c = new Comprador(m, numDeposito, exp);
        } catch (Exception e) {
            System.out.println("ERROR Capturado: " + e.getMessage());
        }

        System.out.println("\n=== ORDENANDO MONEDAS ===");

        ArrayList<Moneda> monedas = new ArrayList<>();
        monedas.add(new Moneda100());
        monedas.add(new Moneda1000());
        monedas.add(new Moneda500());

        System.out.println("Antes de ordenar:");
        for (Moneda moneda : monedas) {
            System.out.println(moneda);
        }

        Collections.sort(monedas);

        System.out.println("\nDespués de ordenar:");
        for (Moneda moneda : monedas) {
            System.out.println(moneda);
        }
    }
}
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

        // --- Compra CocaCola ---
        try {
            m = new Moneda1000();
            numDeposito = 1;
            c = new Comprador(m, numDeposito, exp);
            producto = c.obtenerTipoPorNumero(numDeposito);

            System.out.println("\nHa introducido una moneda de: $" + m.getValor());
            System.out.println("Comprando " + producto.getNombre() + " ...");
            System.out.println("Has consumido '" + c.queConsumiste() + "'");
            System.out.println("Tu vuelto es: $" + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // --- Compra Sprite ---
        try {
            m = new Moneda1000();
            numDeposito = 2;
            c = new Comprador(m, numDeposito, exp);
            producto = c.obtenerTipoPorNumero(numDeposito);

            System.out.println("\nHa introducido una moneda de: $" + m.getValor());
            System.out.println("Comprando " + producto.getNombre() + " ...");
            System.out.println("Has consumido '" + c.queConsumiste() + "'");
            System.out.println("Tu vuelto es: $" + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // --- Compra Fanta ---
        try {
            m = new Moneda1000();
            numDeposito = 3;
            c = new Comprador(m, numDeposito, exp);
            producto = c.obtenerTipoPorNumero(numDeposito);

            System.out.println("\nHa introducido una moneda de: $" + m.getValor());
            System.out.println("Comprando " + producto.getNombre() + " ...");
            System.out.println("Has consumido '" + c.queConsumiste() + "'");
            System.out.println("Tu vuelto es: $" + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // --- Compra Snickers ---
        try {
            m = new Moneda1000();
            numDeposito = 4;
            c = new Comprador(m, numDeposito, exp);
            producto = c.obtenerTipoPorNumero(numDeposito);

            System.out.println("\nHa introducido una moneda de: $" + m.getValor());
            System.out.println("Comprando " + producto.getNombre() + " ...");
            System.out.println("Has consumido '" + c.queConsumiste() + "'");
            System.out.println("Tu vuelto es: $" + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // --- Compra Super 8 ---
        try {
            m = new Moneda1000();
            numDeposito = 5;
            c = new Comprador(m, numDeposito, exp);
            producto = c.obtenerTipoPorNumero(numDeposito);

            System.out.println("\nHa introducido una moneda de: $" + m.getValor());
            System.out.println("Comprando " + producto.getNombre() + " ...");
            System.out.println("Has consumido '" + c.queConsumiste() + "'");
            System.out.println("Tu vuelto es: $" + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // ----------------------
        // PRUEBAS DE EXCEPCIONES
        // ----------------------
        System.out.println("\n=== PRUEBAS DE EXCEPCIONES ===\n");

        // --- No hay más CocaCola ---
        try {
            m = new Moneda1000();
            numDeposito = 1;
            c = new Comprador(m, numDeposito, exp);
            producto = c.obtenerTipoPorNumero(numDeposito);

            System.out.println("\n[EXCEPCIÓN] Intentando comprar: " + producto.getNombre());
            System.out.println("Has consumido '" + c.queConsumiste() + "'");
            System.out.println("Tu vuelto es: $" + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println("ERROR (CocaCola agotada): " + e.getMessage());
        }

        // --- Pago insuficiente para Sprite ---
        try {
            m = new Moneda500();
            numDeposito = 2;
            c = new Comprador(m, numDeposito, exp);
            producto = c.obtenerTipoPorNumero(numDeposito);

            System.out.println("\n[EXCEPCIÓN] Intentando comprar: " + producto.getNombre());
            System.out.println("Has consumido '" + c.queConsumiste() + "'");
            System.out.println("Tu vuelto es: $" + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println("ERROR (pago insuficiente): " + e.getMessage());
        }

        // --- Moneda nula ---
        try {
            m = null;
            numDeposito = 3;
            c = new Comprador(m, numDeposito, exp);
            producto = c.obtenerTipoPorNumero(numDeposito);

            System.out.println("\n[EXCEPCIÓN] Intentando comprar: " + producto.getNombre());
            System.out.println("Has consumido '" + c.queConsumiste() + "'");
            System.out.println("Tu vuelto es: $" + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println("ERROR (moneda nula): " + e.getMessage());
        }


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
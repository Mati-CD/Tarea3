package org.example.Codigo;
import java.util.Scanner; //Scanner para recibir entradas del usuario

/**
 * Clase con main interactivo para simular compras desde la consola.
 */
public class MainInteractivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Expendedor exp = new Expendedor(5); // Carga inicial con más productos

        System.out.println("=== EXPENDEDOR ===");

        boolean seguir = true;
        boolean deseaSeguir = false;

        while (seguir) {
            System.out.println("\n--- MENU DE PRODUCTOS ---");
            for (int i = 0; i < TipoProducto.values().length; i++) {
                TipoProducto tipo = TipoProducto.values()[i];
                System.out.println((i + 1) + ". " + tipo.getNombre() + " - $" + tipo.getPrecio());
            }
            System.out.println("0. Salir");

            System.out.print("Seleccione el número del producto: ");
            int opcionProducto = scanner.nextInt();

            if (opcionProducto == 0) {
                seguir = false;
                System.out.println("Saliendo del expendedor...");
                break;
            }

            System.out.println("\n--- MONEDAS DISPONIBLES ---");
            System.out.println("1. $100");
            System.out.println("2. $500");
            System.out.println("3. $1000");
            System.out.print("Seleccione el número de la moneda: ");
            int opcionMoneda = scanner.nextInt();

            Moneda moneda = null;
            switch (opcionMoneda) {
                case 1:
                    moneda = new Moneda100(); break;
                case 2:
                    moneda = new Moneda500(); break;
                case 3:
                    moneda = new Moneda1000(); break;
                default:
                    System.out.println("Opción de moneda no válida. Se intentará comprar sin moneda.");
                    break;
            }
            deseaSeguir = true;
            System.out.println();

            try {
                Comprador comprador = new Comprador(moneda, opcionProducto, exp);
                System.out.println("Has consumido '" + comprador.queConsumiste() + "'");
                System.out.println("Tu vuelto es: $" + comprador.cuantoVuelto());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }

            // Luego de haber comprado al menos una vez
            if (deseaSeguir) {
                System.out.println("\n¿Desea seguir comprando?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                System.out.print("Seleccione una opción: ");
                int opcionSeguir = scanner.nextInt();

                if (opcionSeguir != 1) {
                    seguir = false;
                    System.out.println("Saliendo del expendedor...");
                }
            }
        }

        scanner.close();
    }
}

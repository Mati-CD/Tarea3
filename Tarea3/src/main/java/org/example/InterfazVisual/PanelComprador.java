package org.example.InterfazVisual;

import org.example.Codigo.*;
import javax.swing.*;
import java.awt.*;


public class PanelComprador extends JPanel {
    private Deposito<Moneda> dineroInicial;
    private Expendedor expendedor;
    private PanelExpendedor panelExpendedor;
    private JComboBox<String> comboMonedas;
    private JButton btnComprar;
    private JButton btnMonedero;
    private JLabel lblEstado;

    public PanelComprador(Expendedor exp, PanelExpendedor panelExp, Deposito<Moneda> dineroInicial) {
        this.expendedor = exp;
        this.panelExpendedor = panelExp;
        this.dineroInicial = dineroInicial;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Realizar Compra"));

        // Panel Superior Derecho para monedero
        JPanel panelSuperiorDerecho = new JPanel();
        panelSuperiorDerecho.setLayout(new BoxLayout(panelSuperiorDerecho, BoxLayout.Y_AXIS));
        panelSuperiorDerecho.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel panelMonedero = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelMonedero.add(new JLabel("Monedero:"));

        JComboBox<String> comboMonedero = new JComboBox<>(new String[]{"$100 ", "$500 ", "$1000 "});
        comboMonedero.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMonedero.add(comboMonedero);

        // Botón de añadir moneda
        btnMonedero = new JButton("AÑADIR");
        btnMonedero.setFont(new Font("Arial", Font.BOLD, 16));
        btnMonedero.setBackground(new Color(60, 179, 113));
        btnMonedero.setForeground(Color.WHITE);

        // Panel para el botón
        JPanel panelBotonMonedero = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonMonedero.add(btnMonedero);

        // Juntar paneles
        panelSuperiorDerecho.add(panelMonedero);
        panelSuperiorDerecho.add(panelBotonMonedero);

        // Panel superior para selección de moneda
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelSuperior.add(new JLabel("Seleccione moneda:"));

        comboMonedas = new JComboBox<>();
        comboMonedas.setFont(new Font("Arial", Font.PLAIN, 14));
        actualizarComboMonedas();
        panelSuperior.add(comboMonedas);

        // Botón de compra
        btnComprar = new JButton("COMPRAR");
        btnComprar.setFont(new Font("Arial", Font.BOLD, 16));
        btnComprar.setBackground(new Color(70, 130, 180));
        btnComprar.setForeground(Color.WHITE);

        // Panel para el botón
        JPanel panelBotonComprar = new JPanel();
        panelBotonComprar.add(btnComprar);

        // Etiqueta de estado
        lblEstado = new JLabel(" ", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Arial", Font.ITALIC, 12));

        // Juntar paneles superiores
        JPanel panelSuperiorCompleto = new JPanel(new BorderLayout());
        panelSuperiorCompleto.add(panelSuperior, BorderLayout.CENTER);
        panelSuperiorCompleto.add(panelSuperiorDerecho, BorderLayout.EAST);

        add(panelSuperiorCompleto, BorderLayout.NORTH);
        add(panelBotonComprar, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.SOUTH);

        // Configurar eventos
        btnComprar.addActionListener(e -> realizarCompra());

        btnMonedero.addActionListener((e -> {
            Moneda moneda = crearMoneda(obtenerValorMoneda(comboMonedero));
            dineroInicial.add(moneda);
            actualizarComboMonedas();
        }));

    }

    private void realizarCompra() {
        try {
            int valorMoneda = obtenerValorMoneda(comboMonedas);
            TipoProducto tipoSeleccionado = panelExpendedor.getProductoSeleccionado();

            if (tipoSeleccionado == null) {
                lblEstado.setText("Seleccione un producto primero");
                lblEstado.setForeground(Color.RED);
                return;
            }

            Moneda moneda = retirarMoneda(valorMoneda);
            actualizarComboMonedas();
            Comprador comprador = new Comprador(moneda, tipoSeleccionado.ordinal() + 1, expendedor);

            lblEstado.setText("Compra exitosa! Producto: " + comprador.queConsumiste() +
                    " - Vuelto: $" + comprador.cuantoVuelto());
            lblEstado.setForeground(new Color(0, 100, 0));

        } catch (Exception ex) {
            lblEstado.setText("ERROR: " + ex.getMessage());
            lblEstado.setForeground(Color.RED);
        }
    }

    private void actualizarComboMonedas() {
        String seleccionAnterior = (String) comboMonedas.getSelectedItem();
        String cualValor = seleccionAnterior != null
                ? seleccionAnterior.substring(0, seleccionAnterior.indexOf('(')).trim()
                : null;

        comboMonedas.removeAllItems();  // Limpiar "visualmente" el combo

        int cantidad100 = 0;
        int cantidad500 = 0;
        int cantidad1000 = 0;

        for (Moneda m: dineroInicial.getDeposito()) {
            switch (m.getValor()) {
                case 100 -> cantidad100++;
                case 500 -> cantidad500++;
                case 1000 -> cantidad1000++;
                default -> {}
            }
        }

        String[] eleccionMoneda = {
                "$100 (" + cantidad100 + ")",
                "$500 (" + cantidad500 + ")",
                "$1000 (" + cantidad1000 + ")"
        };

        for (String eleccion : eleccionMoneda) {
            comboMonedas.addItem(eleccion);
        }

        // Mantener selección anterior "visual" de la moneda
        if (cualValor != null) {
            for (int i = 0; i < comboMonedas.getItemCount(); i++) {
                String item = comboMonedas.getItemAt(i);

                if (item.startsWith(cualValor)) {
                    comboMonedas.setSelectedIndex(i);
                    break;
                }
            }
        }

    }

    private int obtenerValorMoneda(JComboBox combo) {
        String seleccion = (String) combo.getSelectedItem();
        if(seleccion == null) return 0;

        return switch (seleccion.substring(0, seleccion.indexOf(" "))) {
            case "$100" -> 100;
            case "$500" -> 500;
            case "$1000" -> 1000;
            default -> 0;
        };
    }

    private Moneda retirarMoneda(int valor) {
        for(Moneda m : dineroInicial.getDeposito()) {
            if(m.getValor() == valor) {
                dineroInicial.getDeposito().remove(m);
                return m;
            }
        }
        // Si no quedan monedas de "valor"
        return null;
    }

    private Moneda crearMoneda(int valor) {
        return switch (valor) {
            case 100 -> new Moneda100();
            case 500 -> new Moneda500();
            case 1000 -> new Moneda1000();
            default -> null;
        };
    }
}
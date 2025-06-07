package org.example.InterfazVisual;

import org.example.Codigo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelComprador extends JPanel {
    private Expendedor expendedor;
    private PanelExpendedor panelExpendedor;
    private JComboBox<String> comboMonedas;
    private JButton btnComprar;
    private JLabel lblEstado;

    public PanelComprador(Expendedor exp, PanelExpendedor panelExp) {
        this.expendedor = exp;
        this.panelExpendedor = panelExp;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Realizar Compra"));

        // Panel superior para selección de moneda
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelSuperior.add(new JLabel("Seleccione moneda:"));

        comboMonedas = new JComboBox<>(new String[]{"$100", "$500", "$1000"});
        comboMonedas.setFont(new Font("Arial", Font.PLAIN, 14));
        panelSuperior.add(comboMonedas);

        // Botón de compra
        btnComprar = new JButton("COMPRAR");
        btnComprar.setFont(new Font("Arial", Font.BOLD, 16));
        btnComprar.setBackground(new Color(70, 130, 180));
        btnComprar.setForeground(Color.WHITE);

        // Etiqueta de estado
        lblEstado = new JLabel(" ", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Arial", Font.ITALIC, 12));

        // Panel para el botón
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnComprar);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelBoton, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.SOUTH);

        // Configurar evento
        btnComprar.addActionListener(e -> realizarCompra());
    }

    private void realizarCompra() {
        try {
            int valorMoneda = obtenerValorMoneda();
            TipoProducto tipoSeleccionado = panelExpendedor.getProductoSeleccionado();

            if (tipoSeleccionado == null) {
                lblEstado.setText("Seleccione un producto primero");
                lblEstado.setForeground(Color.RED);
                return;
            }

            Moneda moneda = crearMoneda(valorMoneda);
            Comprador comprador = new Comprador(moneda, tipoSeleccionado.ordinal() + 1, expendedor);

            lblEstado.setText("Compra exitosa! Producto: " + comprador.queConsumiste() +
                    " - Vuelto: $" + comprador.cuantoVuelto());
            lblEstado.setForeground(new Color(0, 100, 0));

        } catch (Exception ex) {
            lblEstado.setText("Error: " + ex.getMessage());
            lblEstado.setForeground(Color.RED);
        }
    }

    private int obtenerValorMoneda() {
        String seleccion = (String) comboMonedas.getSelectedItem();
        return switch (seleccion) {
            case "$100" -> 100;
            case "$500" -> 500;
            case "$1000" -> 1000;
            default -> 0;
        };
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
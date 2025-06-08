package org.example.InterfazVisual;

import org.example.Codigo.*;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private PanelExpendedor panelExpendedor;
    private PanelComprador panelComprador;

    public PanelPrincipal() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Crear expendedor con 5 productos de cada tipo
        Expendedor expendedor = new Expendedor(5);

        // Dinero inicial del comprador
        Deposito<Moneda> dineroInicial = new Deposito<>();
        for(int i=0; i < 6; i++) {
            dineroInicial.add(new Moneda100());
            dineroInicial.add(new Moneda500());
            dineroInicial.add(new Moneda1000());
        }

        // Configurar paneles (ahora el comprador necesita referencia al expendedor)
        panelExpendedor = new PanelExpendedor(expendedor);
        panelComprador = new PanelComprador(expendedor, panelExpendedor, dineroInicial);

        // Título
        JLabel titulo = new JLabel("EXPENDEDOR", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(70, 130, 180));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        add(titulo, BorderLayout.NORTH);
        add(panelExpendedor, BorderLayout.CENTER);
        add(panelComprador, BorderLayout.SOUTH);
    }
}
package org.example.InterfazVisual;
import org.example.Codigo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PanelDeposito<T> extends JPanel {
    private Deposito<T> deposito;
    private String cualDeposito;
    private boolean mostrar = false;
    private JButton btnAccion;

    public PanelDeposito(Deposito<T> deposito, String cualDeposito) {
        this.deposito = deposito;
        this.cualDeposito = cualDeposito;

        this.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(180, 220));
        this.setBackground(new Color(220, 220, 220));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 3),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        btnAccion = new JButton("Ver Depósito");

        Dimension buttonSize = new Dimension(180, 45);
        btnAccion.setPreferredSize(buttonSize);
        btnAccion.setMinimumSize(buttonSize);
        btnAccion.setMaximumSize(buttonSize);

        btnAccion.setFont(new Font("Arial", Font.BOLD, 14));
        btnAccion.setFocusPainted(false);
        btnAccion.setBackground(new Color(70, 130, 180));
        btnAccion.setForeground(Color.WHITE);

        btnAccion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnAccion.setBackground(new Color(100, 150, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAccion.setBackground(new Color(70, 130, 180));
            }
        });

        btnAccion.addActionListener(e -> {
            verPanel();
            if(mostrar) {
                btnAccion.setText("Volver");
            }
            else {
                btnAccion.setText("Ver Depósito");
            }
        });

        actualizarVentana();
    }

    private void verPanel() {
        mostrar = !mostrar;
        actualizarVentana();
    }

    private void actualizarVentana() {
        removeAll();
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0); // Márgenes superior e inferior
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHEAST; // Posicionamiento en la parte superior
        buttonPanel.add(btnAccion, gbc);

        this.add(buttonPanel, BorderLayout.NORTH);

        if(mostrar) {
            JPanel contenido = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
            contenido.setOpaque(false);

            ArrayList<T> elementos = deposito.getDeposito();

            for(T elemento: elementos) {
                if(elemento != null) {
                    if(elemento instanceof Moneda) {
                        contenido.add(new PanelMonedas((Moneda) elemento));
                    }
                    if(elemento instanceof Producto) {
                        contenido.add(new PanelProductos((Producto) elemento, cualDeposito));
                    }
                }
            }
            add(contenido, BorderLayout.CENTER);
        }

        revalidate();
        repaint();
    }
}

package org.example.InterfazVisual;

import javax.swing.*;

public class Ventana extends JFrame {
    /**
     * Constructor que inicializa la ventana principal del expendedor.
     * Configura el título, tamaño, y agrega el panel principal
     * que contiene el sistema Comprador-Expendedor.
     */
    public Ventana() {
        setTitle("Expendedor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        // Crear y agregar el panel principal
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        add(panelPrincipal);

        // Configuración adicional de la ventana
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ventana();
        });
    }
}

package org.example.InterfazVisual;
import org.example.Codigo.Moneda;
import javax.swing.*;
import java.awt.*;

public class PanelMonedas extends JPanel {
    private Moneda m;
    private Color colorMoneda;
    private int diametro;

    public PanelMonedas(Moneda m) {
        this.m = m;
        this.colorMoneda = Color.YELLOW;
        this.diametro = 100;
        this.setPreferredSize(new Dimension(diametro, diametro));
        this.setOpaque(false);  // Mantener fondo del panel padre
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(colorMoneda);
        g2d.fillOval(0,0, diametro, diametro);

        // Borde de la moneda
        int grosorBordeExt = 5;
        int grosorBordeInt = diametro - grosorBordeExt;

        g2d.setStroke(new BasicStroke(grosorBordeExt));
        g2d.setColor(Color.BLACK);
        g2d.drawOval(grosorBordeExt-3,grosorBordeExt-3, grosorBordeInt, grosorBordeInt);

        dibujarTexto(g2d);
    }

    protected void dibujarTexto(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String texto = " ";

        if(m != null) {
            int valor = m.getValor();
            switch(valor) {
                case 100:
                    texto = "$100";break;
                case 500:
                    texto = "$500";break;
                case 1000:
                    texto = "$1000";break;
                default:
                    texto = "?";
            }
        }

        FontMetrics c = g.getFontMetrics();
        // Centrar texto en x,y
        int x = (diametro - c.stringWidth(texto)) / 2;
        int y = (diametro - c.getHeight()) / 2 + c.getAscent();
        g.drawString(texto, x, y);
    }
}

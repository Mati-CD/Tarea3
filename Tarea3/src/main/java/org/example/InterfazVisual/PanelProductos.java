package org.example.InterfazVisual;
import org.example.Codigo.Producto;
import javax.swing.*;
import java.awt.*;

public class PanelProductos extends JPanel {
    private Producto p;
    private int diametro;
    private String cualProducto;
    private Color colorP;

    public PanelProductos(Producto p, String cualProducto) {
        this.p = p;
        this.diametro = 100;
        this.cualProducto = cualProducto;
        this.setPreferredSize(new Dimension(diametro, diametro));
        this.setOpaque(false);  // Mantener fondo del panel padre

        // Cambiar por imagenes/diseños
        switch (cualProducto) {
            case "CocaCola":
                colorP = Color.RED;
                break;
            case "Sprite":
                colorP = Color.GREEN;
                break;
            case "Fanta":
                colorP = Color.ORANGE;
                break;
            case "Snickers":
                colorP = Color.MAGENTA;
                break;
            case "Super8":
                colorP = Color.BLACK;
                break;
            default:
                colorP = Color.WHITE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(colorP);
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

        FontMetrics c = g.getFontMetrics();
        // Centrar texto en x,y
        int x = (diametro - c.stringWidth(cualProducto)) / 2;
        int y = (diametro - c.getHeight()) / 2 + c.getAscent();
        g.drawString(cualProducto, x, y);
    }
}

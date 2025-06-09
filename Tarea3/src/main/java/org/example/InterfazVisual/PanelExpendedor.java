package org.example.InterfazVisual;

import org.example.Codigo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;
    private Map<TipoProducto, JPanel> panelesProductos;
    private JPanel panelVistaAmpliada;
    private TipoProducto productoSeleccionado;
    private JLabel lblImagen;
    private JLabel lblPrecio;
    private Map<TipoProducto, JLabel> lblStock;
    private Map<TipoProducto, ImageIcon> imagenesProductos;
    private JButton btnRestockear;

    /**
     * Constructor del panel expendedor.
     * Inicializa componentes gráficos, carga imágenes y configura eventos de mouse.
     * @param exp Instancia del expendedor para interactuar con los productos.
     */
    public PanelExpendedor(Expendedor exp) {
        this.expendedor = exp;
        this.panelesProductos = new HashMap<>();
        this.lblStock = new HashMap<>();

        setLayout(new GridLayout(1, 2, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Cargar imágenes
        this.imagenesProductos = cargarImagenes();

        // Panel izquierdo - Lista de productos
        JPanel panelListaProductos = new JPanel();
        panelListaProductos.setLayout(new BoxLayout(panelListaProductos, BoxLayout.Y_AXIS));
        panelListaProductos.setBorder(BorderFactory.createTitledBorder("Productos Disponibles"));

        // Panel derecho - Vista ampliada
        panelVistaAmpliada = new JPanel(new BorderLayout(10, 10));
        panelVistaAmpliada.setBorder(BorderFactory.createTitledBorder("Vista Detallada"));

        // Configurar boton de reestock
        btnRestockear = new JButton("RE-STOCKEAR");
        btnRestockear.setFont(new Font("Arial", Font.BOLD, 16));
        btnRestockear.setBackground(new Color(255, 140, 0));
        btnRestockear.setForeground(Color.WHITE);
        btnRestockear.setVisible(false);

        JPanel panelBtnRestockear = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBtnRestockear.add(btnRestockear);
        panelVistaAmpliada.add(panelBtnRestockear, BorderLayout.NORTH);

        // Configurar vista de imagen
        lblImagen = new JLabel("", SwingConstants.CENTER);
        panelVistaAmpliada.add(lblImagen, BorderLayout.CENTER);

        // Configurar vista de precio
        lblPrecio = new JLabel("", SwingConstants.CENTER);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 18));
        panelVistaAmpliada.add(lblPrecio, BorderLayout.SOUTH);

        // Crear paneles para cada producto
        for (TipoProducto tipo : TipoProducto.values()) {
            JPanel panelProducto = crearPanelProducto(tipo);
            panelesProductos.put(tipo, panelProducto);
            panelListaProductos.add(panelProducto);
            panelListaProductos.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        add(panelListaProductos);
        add(panelVistaAmpliada);

        // Configurar eventos
        btnRestockear.addActionListener(e -> {
            if (productoSeleccionado != null && expendedor.seleccionarDeposito(productoSeleccionado).size() == 0) {
                expendedor.restockear(productoSeleccionado, 5);
                actualizarStock();
                actualizarVistaDetallada(productoSeleccionado);
            }
        });
    }

    /**
     * Carga y redimensiona las imágenes asociadas a cada producto.
     * @return Mapa con imágenes para cada tipo de producto.
     */
    private Map<TipoProducto, ImageIcon> cargarImagenes() {
        Map<TipoProducto, ImageIcon> imagenes = new HashMap<>();
        String basePath = "/productos/";  // ← AJUSTADO PARA RESOURCES

        try {
            imagenes.put(TipoProducto.COCA_COLA, redimensionarImagen(
                    cargarImagen(basePath + "CocaCola.jpg"), 300, 300));
            imagenes.put(TipoProducto.SPRITE, redimensionarImagen(
                    cargarImagen(basePath + "Sprite.jpg"), 300, 300));
            imagenes.put(TipoProducto.FANTA, redimensionarImagen(
                    cargarImagen(basePath + "Fanta.jpg"), 300, 300));
            imagenes.put(TipoProducto.SNICKERS, redimensionarImagen(
                    cargarImagen(basePath + "Snickers.jpg"), 300, 300));
            imagenes.put(TipoProducto.SUPER8, redimensionarImagen(
                    cargarImagen(basePath + "Super8.jpg"), 300, 300));
        } catch (Exception e) {
            System.err.println("Error cargando imágenes: " + e.getMessage());
            for (TipoProducto tipo : TipoProducto.values()) {
                imagenes.put(tipo, crearImagenReemplazo(tipo, 200, 200));
            }
        }

        return imagenes;
    }


    private ImageIcon cargarImagen(String path) {
        try {
            return new ImageIcon(getClass().getResource(path));
        } catch (Exception e) {
            System.err.println("No se pudo cargar: " + path);
            return null;
        }
    }


    private ImageIcon redimensionarImagen(ImageIcon icono, int ancho, int alto) {
        if (icono == null) return crearImagenReemplazo(null, ancho, alto);

        Image img = icono.getImage();
        Image nuevaImg = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(nuevaImg);
    }

    private ImageIcon crearImagenReemplazo(TipoProducto tipo, int ancho, int alto) {
        BufferedImage img = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();

        // Fondo
        g2d.setColor(tipo != null ? getColorProducto(tipo) : Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, ancho, alto);

        // Texto
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        if (tipo != null) {
            g2d.drawString(tipo.getNombre(), 10, 20);
        }
        g2d.drawString("Imagen no disponible", 10, 40);

        g2d.dispose();
        return new ImageIcon(img);
    }

    /**
     * Crea el panel para cada producto con su nombre y stock disponible,
     * y agrega eventos de mouse para seleccionar el producto.
     * @param tipo Tipo de producto.
     * @return JPanel representando el producto.
     */
    private JPanel crearPanelProducto(TipoProducto tipo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(getColorProducto(tipo));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.setPreferredSize(new Dimension(150, 60));

        JLabel label = new JLabel(tipo.getNombre(), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.CENTER);

        JLabel stock = new JLabel("Stock (" + expendedor.seleccionarDeposito(tipo).size() + ")", SwingConstants.CENTER);
        stock.setFont(new Font("Arial", Font.BOLD, 14));
        stock.setForeground(Color.WHITE);

        JPanel panelStock = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 24));
        panelStock.setOpaque(false);
        panelStock.add(stock);

        panel.add(panelStock, BorderLayout.EAST);

        // Etiqueta de stock
        lblStock.put(tipo, stock);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarProducto(tipo);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (productoSeleccionado != tipo) {
                    panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (productoSeleccionado != tipo) {
                    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                }
            }
        });

        return panel;
    }

    private void seleccionarProducto(TipoProducto tipo) {
        // Resetear selección anterior
        if (productoSeleccionado != null) {
            panelesProductos.get(productoSeleccionado).setBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 1));
        }

        // Marcar nuevo seleccionado
        productoSeleccionado = tipo;
        panelesProductos.get(tipo).setBorder(
                BorderFactory.createLineBorder(Color.YELLOW, 3));

        // Actualizar vista
        actualizarVistaDetallada(tipo);
    }

    /**
     * Actualiza la vista detallada del producto seleccionado,
     * mostrando imagen, precio y habilitando el botón de re-stock si corresponde.
     * @param tipo Producto seleccionado.
     */
    private void actualizarVistaDetallada(TipoProducto tipo) {
        lblImagen.setIcon(imagenesProductos.get(tipo));
        lblPrecio.setText("Precio: $" + tipo.getPrecio());
        lblPrecio.setForeground(getColorProducto(tipo));

        int stockActual = expendedor.seleccionarDeposito(tipo).size();
        btnRestockear.setVisible(stockActual == 0);     // Mostrar boton en el producto "tipo" solamente

        revalidate();
        repaint();
    }

    /**
     * Actualiza las etiquetas de stock en cada panel de cada producto
     * y controla visibilidad del botón de re-stock.
     */
    public void actualizarStock() {
        boolean agotado = false;

        for(TipoProducto tipo: TipoProducto.values()) {
            JLabel stock = lblStock.get(tipo);
            if(stock != null) {
                int stockActual = expendedor.seleccionarDeposito(tipo).size();
                stock.setText("Stock (" + stockActual + ")");
                if(stockActual == 0) {
                    agotado = true;     // Boton de reestock activado
                }
            }
        }
        btnRestockear.setVisible(agotado);      // Si hay "al menos un" producto agotado
    }

    /**
     * Obtiene el producto actualmente seleccionado.
     * @return TipoProducto seleccionado o null si no hay selección.
     */
    public TipoProducto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    /**
     * Obtiene un color representativo para cada tipo de producto.
     * @param tipo TipoProducto.
     * @return Color asociado.
     */
    private Color getColorProducto(TipoProducto tipo) {
        switch (tipo) {
            case COCA_COLA: return Color.RED;
            case SPRITE: return Color.GREEN;
            case FANTA: return Color.ORANGE;
            case SNICKERS: return new Color(139, 69, 19); // Café
            case SUPER8: return Color.BLACK;
            default: return Color.GRAY;
        }
    }
}
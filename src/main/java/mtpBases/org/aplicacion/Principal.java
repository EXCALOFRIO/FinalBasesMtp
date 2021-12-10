package mtpBases.org.aplicacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Principal extends JFrame {
    JMenuItem abrir;
    JMenuItem guardar;
    JMenuItem cargar;
    JMenuItem salir;
    JMenuItem nuevaCompeticion;
    JMenuItem helpItem;

    private class Funcionalidad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == salir)
                System.exit(0);
            if (e.getSource() == nuevaCompeticion) {
                System.out.println("NUEVA COMPETICION");
                // Competicion competicion = new Competicion();
                Competicion.iniciarCompeticion();
            }
        }

    }

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * 
     * @throws IOException
     */
    public Principal() throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        // contentPane.setBackground(Color.GRAY);
        BufferedImage myPicture = ImageIO.read(new File("./imagenes/fondo.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        contentPane.add(picLabel);
        setContentPane(contentPane);

        setFocusable(true);

        // Creamos la barra de Menu
        JMenuBar barraMenu = new JMenuBar();
        // Creamos los menus y modificamos los accesos rapidos
        JMenu archivo = new JMenu("Archivo");
        JMenu crear = new JMenu("Crear");
        JMenu help = new JMenu("Help");

        // Añadimos los menus a la barra de menu

        barraMenu.add(archivo);
        barraMenu.add(crear);
        barraMenu.add(help);

        // Creamos los submenus y modificamos los accesos rapidos

        abrir = new JMenuItem("Abrir");
        guardar = new JMenuItem("Guardar");
        cargar = new JMenuItem("Cargar");
        salir = new JMenuItem("Salir");
        nuevaCompeticion = new JMenuItem("Nueva Competicion");
        helpItem = new JMenuItem("Help");

        // Añadimos los submenus a los menus

        archivo.add(abrir);
        archivo.add(new JSeparator());
        archivo.add(guardar);
        archivo.add(cargar);
        archivo.add(new JSeparator());
        archivo.add(salir);

        crear.add(nuevaCompeticion);
        crear.add(new JSeparator());

        help.add(helpItem);

        // Indicamos que es el menu por defecto
        setJMenuBar(barraMenu);

        salir.addActionListener(new Funcionalidad());
        nuevaCompeticion.addActionListener(new Funcionalidad());

    }

}
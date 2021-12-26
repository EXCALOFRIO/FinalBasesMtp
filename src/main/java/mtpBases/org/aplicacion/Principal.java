package mtpBases.org.aplicacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.image.BufferedImage;
import java.beans.Statement;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Principal extends JFrame {
    JMenuItem abrir;
    JMenuItem guardar;
    JMenuItem cargar;
    JMenuItem salir;
    JMenuItem nuevaCompeticion;
    JMenuItem helpItem;
    Font producSans = new Font("title", 0, 0);
    Font producSansBold = new Font("title", 0, 0);

    private class Funcionalidad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == salir)
                dispose();
            if (e.getSource() == nuevaCompeticion) {

                // Competicion competicion = new Competicion();
                try {
                    DecoradorCompeticion competicion = DecoradorCompeticion.getInstance();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                //DecoradorCompeticion.iniciar();
                System.out.println("NUEVA COMPETICION");
            }
            if (e.getSource() == helpItem) {
                System.out.println("ABRIENDO HELP");
                // Competicion competicion = new Competicion();
                Help.help();
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
        // FUENTE
        try {
            // create the font to use. Specify the size!
            producSans = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\ProductSansRegular.ttf"))
                    .deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // register the font
            ge.registerFont(producSans);

            producSansBold = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\ProductSansBold.ttf"))
                    .deriveFont(19f);
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // register the font
            ge2.registerFont(producSansBold);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.black);
        BufferedImage myPicture = ImageIO.read(new File("./imagenes/fondo.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        contentPane.add(picLabel);
        setContentPane(contentPane);

        setFocusable(true);

        // Creamos la barra de Menu
        JMenuBar barraMenu = new JMenuBar();
        // Creamos los menus y modificamos los accesos rapidos
        JMenu archivo = new JMenu("Archivo");
        archivo.setFont(producSansBold);
        JMenu crear = new JMenu("Crear");
        crear.setFont(producSansBold);
        JMenu help = new JMenu("Help");
        help.setFont(producSansBold);

        // Añadimos los menus a la barra de menu

        barraMenu.add(archivo);
        barraMenu.add(crear);
        barraMenu.add(help);

        // Creamos los submenus y modificamos los accesos rapidos

        abrir = new JMenuItem("Abrir");
        abrir.setFont(producSans);
        guardar = new JMenuItem("Guardar");
        guardar.setFont(producSans);
        cargar = new JMenuItem("Cargar");
        cargar.setFont(producSans);
        salir = new JMenuItem("Salir");
        salir.setFont(producSans);
        nuevaCompeticion = new JMenuItem("Nueva Competicion");
        nuevaCompeticion.setFont(producSans);
        helpItem = new JMenuItem("Help");
        helpItem.setFont(producSans);

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
        helpItem.addActionListener(new Funcionalidad());
    }

}
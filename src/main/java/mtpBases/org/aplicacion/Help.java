package mtpBases.org.aplicacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import javax.imageio.ImageIO;

public class Help extends JFrame {
    JMenuItem guardar;
    JMenuItem salir;
    JMenuItem helpItem;
    Font producSans = new Font("title", 0, 0);
    Font producSansBold = new Font("title", 0, 0);

    private class Funcionalidad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == salir)
                dispose();

        }
    }

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void help() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Help frame = new Help();
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
    public Help() throws IOException {
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

        Connection connection = null;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.black);
        BufferedImage myPicture = ImageIO.read(new File("./imagenes/fondo2.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        contentPane.add(picLabel);
        setContentPane(contentPane);

        JTextArea textarea = new JTextArea(
                "ESTE ES EL TEXTO DE AYUDA QUE APARECE CUANDO HACES CLICK EN EL BOTON HELP \nPARA QUE FUNCIONE EL PROGRAMA HACER MAKE RUN POR CONSOLA.\n SI LO QUE QUEREMOS ES CREAR UNA COMPETICION DESDE EL MENU INICIAL HACER CLICK EN EL BOTON NUEVA COMPETICION,\n Y SI LO QUE QUEREMOS ES VER LOS RESULTADOS LE DAMOS AL BOTON DE COMENZAR EN LA PESTAÑA DE NUEVA COMPETICION. \n SI LO QUE QUEREMOS ES SALIR HACER CLIK EN EL BOTON DE SALIR. ");
        textarea.setBounds(200, 100, 850, 300);
        textarea.setEditable(false);
        textarea.setFont(producSans);

        picLabel.add(textarea);
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

        guardar = new JMenuItem("Guardar");
        guardar.setFont(producSans);
        salir = new JMenuItem("Salir");
        salir.setFont(producSans);
        helpItem = new JMenuItem("Help");
        helpItem.setFont(producSans);
        // Añadimos los submenus a los menus

        archivo.add(guardar);
        archivo.add(new JSeparator());
        archivo.add(salir);

        help.add(helpItem);

        // Indicamos que es el menu por defecto
        setJMenuBar(barraMenu);

        salir.addActionListener(new Funcionalidad());
        helpItem.addActionListener(new Funcionalidad());
    }
}

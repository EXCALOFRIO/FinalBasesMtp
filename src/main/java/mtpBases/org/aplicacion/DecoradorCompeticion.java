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

//CREAMOS ESTA CLASE PARA USAR EL PATRON DE FACHADA 
public class DecoradorCompeticion extends JFrame {
    JMenuItem guardar;
    JMenuItem salir;
    JMenuItem helpItem;
    Font producSans = new Font("title", 0, 0);
    Font producSansBold = new Font("title", 0, 0);
    JButton color;
    JButton blancoYNegro;

    // USAMOS EL PATRON STRATEGY PARA QUE SOLO SE ABRA UNA SOLA COMPETICION
    private static DecoradorCompeticion competicion;

    public static DecoradorCompeticion getInstance() throws IOException {
        if (competicion == null) {
            competicion = new DecoradorCompeticion();
            iniciar();
        }

        return competicion;
    }

    // Para permitir que se abra una sola competicion.
    public static void reset() {

        competicion = null;
    }

    private class Funcionalidad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == salir)
                reset();
            dispose();
            if (e.getSource() == color)
                Competicion.iniciarCompeticion();
            ;
            if (e.getSource() == blancoYNegro)
                CompeticionBlancoyNegro.iniciarCompeticion();

        }
    }

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void iniciar() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DecoradorCompeticion frame = new DecoradorCompeticion();
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
    private DecoradorCompeticion() throws IOException {
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

        color = new JButton("COLORIDO");
        color.setBounds(350, 240, 220, 70);
        color.setFont(producSansBold);
        color.setBackground(Color.CYAN);

        blancoYNegro = new JButton("BLANCO Y NEGRO");
        blancoYNegro.setBounds(650, 240, 220, 70);
        blancoYNegro.setFont(producSansBold);
        blancoYNegro.setBackground(Color.gray);

        picLabel.add(color);
        picLabel.add(blancoYNegro);
        // Creamos la barra de Menu
        JMenuBar barraMenu = new JMenuBar();

        // Creamos los menus y modificamos los accesos rapidos
        JMenu archivo = new JMenu("Archivo");
        archivo.setFont(producSansBold);
        JMenu crear = new JMenu("Crear");
        crear.setFont(producSansBold);
        JMenu help = new JMenu("Help");
        help.setFont(producSansBold);

        // A??adimos los menus a la barra de menu

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
        // A??adimos los submenus a los menus

        archivo.add(guardar);
        archivo.add(new JSeparator());
        archivo.add(salir);

        help.add(helpItem);

        // Indicamos que es el menu por defecto
        setJMenuBar(barraMenu);

        salir.addActionListener(new Funcionalidad());
        helpItem.addActionListener(new Funcionalidad());
        color.addActionListener(new Funcionalidad());
        blancoYNegro.addActionListener(new Funcionalidad());
    }

}

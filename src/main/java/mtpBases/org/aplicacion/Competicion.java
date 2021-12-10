package mtpBases.org.aplicacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Competicion extends JFrame {
    JMenuItem abrir;
    JMenuItem guardar;
    JMenuItem cargar;
    JMenuItem salir;
    JMenuItem helpItem;

    private class Funcionalidad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void iniciarCompeticion() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Competicion frame = new Competicion();
                    frame.setVisible(true);
                    System.out.println("La competicion se ha iniciado");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Competicion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.GRAY);
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

        helpItem = new JMenuItem("Help");

        // Añadimos los submenus a los menus

        archivo.add(abrir);
        archivo.add(new JSeparator());
        archivo.add(guardar);
        archivo.add(cargar);
        archivo.add(new JSeparator());
        archivo.add(salir);

        help.add(helpItem);

        // Indicamos que es el menu por defecto
        setJMenuBar(barraMenu);

        salir.addActionListener(new Funcionalidad());
    }
}

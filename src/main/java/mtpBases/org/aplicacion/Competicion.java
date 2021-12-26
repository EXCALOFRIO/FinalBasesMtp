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

import javax.swing.table.DefaultTableModel;

public class Competicion extends JFrame {
    JMenuItem abrir;
    JMenuItem guardar;
    JMenuItem cargar;
    JMenuItem salir;
    JMenuItem helpItem;
    JButton boton = new JButton("COMENZAR");
    Font producSans = new Font("title", 0, 0);
    Font producSansBold = new Font("title", 0, 0);

    private class Funcionalidad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == salir)
                DecoradorCompeticion.reset();
            dispose();
            if (e.getSource() == boton) {
                System.out.println("COMENZANDO");
                // Competicion competicion = new Competicion();
                Resultados.generarResultados();
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
    public static void iniciarCompeticion() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Competicion frame = new Competicion();
                    frame.setVisible(true);
                    System.out.println("La competicion se ha iniciado");
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
    public Competicion() throws IOException {
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

        // CONECTAR BASE DE DATOS
        Connection connection = null;
        ArrayList<Esgrimista> esgrimistaArray = new ArrayList<Esgrimista>();
        ArrayList<Esgrimista> esgrimistaArray2 = new ArrayList<Esgrimista>();
        ArrayList<Esgrimista> esgrimistaArray3 = new ArrayList<Esgrimista>();
        ArrayList<Esgrimista> esgrimistaArray4 = new ArrayList<Esgrimista>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.black);
        BufferedImage myPicture = ImageIO.read(new File("./imagenes/fondo2.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        contentPane.add(picLabel);
        setContentPane(contentPane);

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:esgrima.db");
            java.sql.Statement statement1 = connection.createStatement();
            java.sql.Statement statement2 = connection.createStatement();
            java.sql.Statement statement3 = connection.createStatement();
            java.sql.Statement statement4 = connection.createStatement();
            ResultSet poule1 = statement1.executeQuery("select * from esgrimista where id_poule ==1");
            ResultSet poule2 = statement2.executeQuery("select * from esgrimista where id_poule ==2 ");
            ResultSet poule3 = statement3.executeQuery("select * from esgrimista where id_poule ==3 ");
            ResultSet poule4 = statement4.executeQuery("select * from esgrimista where id_poule ==4 ");
            while (poule1.next()) {
                // ESCRIBIR LABASE DE DATOS
                Esgrimista esgrimista = new Esgrimista(poule1.getString("nombre"), poule1.getString("nacionalidad"),
                        poule1.getString("mano"), Integer.parseInt(poule1.getString("ranking_mundial")));
                esgrimistaArray.add(esgrimista);
            }

            while (poule2.next()) {
                // ESCRIBIR LABASE DE DATOS
                Esgrimista esgrimista = new Esgrimista(poule2.getString("nombre"), poule2.getString("nacionalidad"),
                        poule2.getString("mano"), Integer.parseInt(poule2.getString("ranking_mundial")));
                esgrimistaArray2.add(esgrimista);
            }
            while (poule3.next()) {
                // ESCRIBIR LABASE DE DATOS
                Esgrimista esgrimista = new Esgrimista(poule3.getString("nombre"), poule3.getString("nacionalidad"),
                        poule3.getString("mano"), Integer.parseInt(poule3.getString("ranking_mundial")));
                esgrimistaArray3.add(esgrimista);
            }
            while (poule4.next()) {
                // ESCRIBIR LABASE DE DATOS
                Esgrimista esgrimista = new Esgrimista(poule4.getString("nombre"), poule4.getString("nacionalidad"),
                        poule4.getString("mano"), Integer.parseInt(poule4.getString("ranking_mundial")));
                esgrimistaArray4.add(esgrimista);
            }

        } catch (

        SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setFocusable(true);
        // TABLE
        String[] columnNames = { "Nombre", "Nacionalidad", "ManoDominante", "RankingMundial" };
        Object[][] filasPoule1 = new Object[esgrimistaArray.size()][4];
        for (int i = 0; i < esgrimistaArray.size(); i++) {
            filasPoule1[i][0] = esgrimistaArray.get(i).getNombre();
            filasPoule1[i][1] = esgrimistaArray.get(i).getNacionalidad();
            filasPoule1[i][2] = esgrimistaArray.get(i).getManoDominante();
            filasPoule1[i][3] = esgrimistaArray.get(i).getRankingMundial();
        }
        DefaultTableModel dtmPoule1 = new DefaultTableModel(filasPoule1, columnNames);
        final JTable tablePoule1 = new JTable(dtmPoule1);

        // TABLE2
        Object[][] filasPoule2 = new Object[esgrimistaArray2.size()][4];
        for (int i = 0; i < esgrimistaArray2.size(); i++) {
            filasPoule2[i][0] = esgrimistaArray2.get(i).getNombre();
            filasPoule2[i][1] = esgrimistaArray2.get(i).getNacionalidad();
            filasPoule2[i][2] = esgrimistaArray2.get(i).getManoDominante();
            filasPoule2[i][3] = esgrimistaArray2.get(i).getRankingMundial();
        }
        DefaultTableModel dtmPoule2 = new DefaultTableModel(filasPoule2, columnNames);
        final JTable tablePoule2 = new JTable(dtmPoule2);

        // TABLE3
        Object[][] filasPoule3 = new Object[esgrimistaArray3.size()][4];
        for (int i = 0; i < esgrimistaArray3.size(); i++) {
            filasPoule3[i][0] = esgrimistaArray3.get(i).getNombre();
            filasPoule3[i][1] = esgrimistaArray3.get(i).getNacionalidad();
            filasPoule3[i][2] = esgrimistaArray3.get(i).getManoDominante();
            filasPoule3[i][3] = esgrimistaArray3.get(i).getRankingMundial();
        }
        DefaultTableModel dtmPoule3 = new DefaultTableModel(filasPoule3, columnNames);
        final JTable tablePoule3 = new JTable(dtmPoule3);

        // TABLE4
        Object[][] filasPoule4 = new Object[esgrimistaArray4.size()][4];
        for (int i = 0; i < esgrimistaArray4.size(); i++) {
            filasPoule4[i][0] = esgrimistaArray4.get(i).getNombre();
            filasPoule4[i][1] = esgrimistaArray4.get(i).getNacionalidad();
            filasPoule4[i][2] = esgrimistaArray4.get(i).getManoDominante();
            filasPoule4[i][3] = esgrimistaArray4.get(i).getRankingMundial();
        }
        DefaultTableModel dtmPoule4 = new DefaultTableModel(filasPoule4, columnNames);
        final JTable tablePoule4 = new JTable(dtmPoule4);

        // JText CON POULES
        tablePoule1.setBackground(Color.cyan);
        tablePoule1.setBounds(10, 10, 600, 170);
        tablePoule1.setEnabled(false);
        tablePoule1.setFont(producSans);

        tablePoule2.setBackground(Color.orange);
        tablePoule2.setBounds(650, 10, 600, 170);
        tablePoule2.setEnabled(false);
        tablePoule2.setFont(producSans);

        boton.setBackground(Color.orange);
        boton.setBounds(530, 240, 220, 70);
        boton.setFont(producSansBold);

        tablePoule3.setBackground(Color.gray);
        tablePoule3.setBounds(650, 350, 600, 170);
        tablePoule3.setEnabled(false);
        tablePoule3.setFont(producSans);

        tablePoule4.setBackground(Color.pink);
        tablePoule4.setBounds(10, 350, 600, 170);
        tablePoule4.setEnabled(false);
        tablePoule4.setFont(producSans);

        JScrollPane scrollPane1 = new JScrollPane();

        JScrollPane scrollPane2 = new JScrollPane();
        JScrollPane scrollPane3 = new JScrollPane();
        JScrollPane scrollPane4 = new JScrollPane();

        picLabel.add(scrollPane1);
        picLabel.add(scrollPane2);
        picLabel.add(scrollPane3);
        picLabel.add(scrollPane4);
        picLabel.add(boton);

        scrollPane1.setBounds(tablePoule1.getBounds());
        scrollPane2.setBounds(tablePoule2.getBounds());
        scrollPane3.setBounds(tablePoule3.getBounds());
        scrollPane4.setBounds(tablePoule4.getBounds());
        scrollPane1.setViewportView(tablePoule1);
        scrollPane2.setViewportView(tablePoule2);
        scrollPane3.setViewportView(tablePoule3);
        scrollPane4.setViewportView(tablePoule4);

        scrollPane1.setOpaque(false);
        scrollPane1.getViewport().setOpaque(false);
        scrollPane2.setOpaque(false);
        scrollPane2.getViewport().setOpaque(false);
        scrollPane3.setOpaque(false);
        scrollPane3.getViewport().setOpaque(false);
        scrollPane4.setOpaque(false);
        scrollPane4.getViewport().setOpaque(false);

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
        helpItem = new JMenuItem("Help");
        helpItem.setFont(producSans);
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
        boton.addActionListener(new Funcionalidad());
        helpItem.addActionListener(new Funcionalidad());
    }
}

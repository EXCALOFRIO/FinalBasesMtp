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
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;

public class Resultados extends JFrame {
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
    public static void generarResultados() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Resultados frame = new Resultados();
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
    public Resultados() throws IOException {
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

            ResultSet clasificados = statement1
                    .executeQuery("select * from clasificacion_esgrimista where eliminados = 'NO' ");
            ResultSet eliminados = statement2
                    .executeQuery("select * from clasificacion_esgrimista where eliminados = 'SI' ");

            while (clasificados.next()) {
                // ESCRIBIR LABASE DE DATOS
                Esgrimista esgrimista = new Esgrimista(
                        Integer.parseInt(clasificados.getString("posicion")), clasificados.getString("id_esgrimista"),
                        clasificados.getString("lugar"), clasificados.getString("fecha"),
                        Integer.parseInt(clasificados.getString("victorias")));
                esgrimistaArray.add(esgrimista);
            }
            while (eliminados.next()) {
                // ESCRIBIR LABASE DE DATOS
                Esgrimista esgrimista = new Esgrimista(
                        Integer.parseInt(eliminados.getString("posicion")), eliminados.getString("id_esgrimista"),
                        eliminados.getString("lugar"), eliminados.getString("fecha"),
                        Integer.parseInt(eliminados.getString("victorias")));
                esgrimistaArray2.add(esgrimista);
            }

        } catch (

        SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TABLE
        String[] columnNames = { "Posicion", "ID_Esgrimista", "Fecha", "Lugar", "Numero_Victorias" };
        Object[][] filasPoule1 = new Object[esgrimistaArray.size()][5];
        for (int i = 0; i < esgrimistaArray.size(); i++) {
            filasPoule1[i][0] = esgrimistaArray.get(i).getClasificacion();
            filasPoule1[i][1] = esgrimistaArray.get(i).getId();
            filasPoule1[i][2] = esgrimistaArray.get(i).getFechaCompeticion();
            filasPoule1[i][3] = esgrimistaArray.get(i).getLuarCompeticion();
            filasPoule1[i][4] = esgrimistaArray.get(i).getNumeroVictorias();
        }
        DefaultTableModel dtmPoule1 = new DefaultTableModel(filasPoule1, columnNames);
        final JTable tablePoule1 = new JTable(dtmPoule1);

        // TABLE2
        Object[][] filasPoule2 = new Object[esgrimistaArray2.size()][5];
        for (int i = 0; i < esgrimistaArray2.size(); i++) {
            filasPoule2[i][0] = esgrimistaArray2.get(i).getClasificacion();
            filasPoule2[i][1] = esgrimistaArray2.get(i).getId();
            filasPoule2[i][2] = esgrimistaArray2.get(i).getFechaCompeticion();
            filasPoule2[i][3] = esgrimistaArray2.get(i).getLuarCompeticion();
            filasPoule2[i][4] = esgrimistaArray2.get(i).getNumeroVictorias();
        }
        DefaultTableModel dtmPoule2 = new DefaultTableModel(filasPoule2, columnNames);
        final JTable tablePoule2 = new JTable(dtmPoule2);

        tablePoule1.setBackground(Color.green);
        tablePoule1.setBounds(10, 70, 600, 400);
        tablePoule1.setEnabled(false);
        tablePoule1.setFont(producSans);

        tablePoule2.setBackground(Color.red);
        tablePoule2.setBounds(650, 70, 600, 400);
        tablePoule2.setEnabled(false);
        tablePoule2.setFont(producSans);

        JScrollPane scrollPane1 = new JScrollPane();
        JScrollPane scrollPane2 = new JScrollPane();
        picLabel.add(scrollPane1);
        picLabel.add(scrollPane2);

        JButton label1 = new JButton("CLASIFICADOS");
        label1.setBounds(10, 10, 600, 60);
        label1.setBackground(Color.green);
        label1.setForeground(Color.white);
        label1.setEnabled(false);
        label1.setFont(producSansBold);

        JButton label2 = new JButton("ELIMINADOS");
        label2.setBounds(650, 10, 600, 60);
        label2.setBackground(Color.red);
        label2.setForeground(Color.WHITE);
        label2.setEnabled(false);
        label2.setFont(producSansBold);

        scrollPane1.setBounds(tablePoule1.getBounds());

        picLabel.add(label1);
        picLabel.add(label2);

        scrollPane2.setBounds(tablePoule2.getBounds());

        scrollPane1.setViewportView(tablePoule1);
        scrollPane2.setViewportView(tablePoule2);
        scrollPane1.setOpaque(false);

        scrollPane1.getViewport().setOpaque(false);
        scrollPane2.setOpaque(false);
        scrollPane2.getViewport().setOpaque(false);
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

    }
}

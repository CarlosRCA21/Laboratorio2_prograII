import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Interfaz extends JFrame {

    private static final int ANIO_MINIMO = 1500;

    private Biblioteca biblioteca = new Biblioteca();

    private DefaultTableModel modeloTabla = new DefaultTableModel();
    private JTable tablaMateriales = new JTable(modeloTabla);

    private JTabbedPane pestanas = new JTabbedPane();

    
    private JComboBox<String> cajaTipo = new JComboBox<>(new String[] {"Libro", "Revista", "Tesis"});
    private JTextField campoCodigoRegistro = new JTextField(15);
    private JTextField campoTitulo = new JTextField(15);
    private JTextField campoAnio = new JTextField(15);
    private JLabel etiquetaDato = new JLabel("Autor:");
    private JTextField campoDato = new JTextField(15);
    private JTextField campoImagen = new JTextField(15);

    
    private JTextField campoBusqueda = new JTextField(15);

    
    private JComboBox<String> cajaDisponibles = new JComboBox<>();
    private JComboBox<String> cajaPrestados = new JComboBox<>();

    public Interfaz() {
        super("Biblioteca universitaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modeloTabla.setColumnIdentifiers(new String[] {"Codigo", "Titulo", "Tipo", "Anio", "Imagen", "Disponible"});

        pestanas.addTab("Registro", crearPanelRegistro());
        pestanas.addTab("Busqueda", crearPanelBusqueda());
        pestanas.addTab("Prestamos", crearPanelPrestamos());
        pestanas.addTab("Devoluciones", crearPanelDevoluciones());

        add(pestanas, BorderLayout.NORTH);
        add(new JScrollPane(tablaMateriales), BorderLayout.CENTER);

        actualizarTodo();
    }
    
}

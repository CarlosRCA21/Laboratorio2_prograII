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
    
     private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));

        JButton botonRegistrar = new JButton("Registrar material");
        botonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarMaterial();
            }
        });

        cajaTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarEtiquetaDato();
            }
        });

        panel.add(new JLabel("Tipo de material:"));
        panel.add(cajaTipo);
        panel.add(new JLabel("Codigo:"));
        panel.add(campoCodigoRegistro);
        panel.add(new JLabel("Titulo:"));
        panel.add(campoTitulo);
        panel.add(new JLabel("Anio de publicacion:"));
        panel.add(campoAnio);
        panel.add(etiquetaDato);
        panel.add(campoDato);
        panel.add(new JLabel("Imagen (nombre o ruta):"));
        panel.add(campoImagen);
        panel.add(new JLabel(""));
        panel.add(botonRegistrar);
        return panel;
    }

    private JPanel crearPanelBusqueda() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarMaterial();
            }
        });

        panel.add(new JLabel("Codigo del material:"));
        panel.add(campoBusqueda);
        panel.add(new JLabel(""));
        panel.add(botonBuscar);
        return panel;
    }

    private JPanel crearPanelPrestamos() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

        JButton botonPrestar = new JButton("Prestar material");
        botonPrestar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prestarMaterial();
            }
        });

        panel.add(new JLabel("Material disponible:"));
        panel.add(cajaDisponibles);
        panel.add(new JLabel(""));
        panel.add(botonPrestar);
        return panel;
    }

    private JPanel crearPanelDevoluciones() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

        JButton botonDevolver = new JButton("Devolver material");
        botonDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                devolverMaterial();
            }
        });

        panel.add(new JLabel("Material prestado:"));
        panel.add(cajaPrestados);
        panel.add(new JLabel(""));
        panel.add(botonDevolver);
        return panel;
    }

    
    
    private void cambiarEtiquetaDato() {
        String tipo = (String) cajaTipo.getSelectedItem();

        if (tipo.equals("Libro")) {
            etiquetaDato.setText("Autor:");
        } else if (tipo.equals("Revista")) {
            etiquetaDato.setText("Numero de edicion:");
        } else {
            etiquetaDato.setText("Autor academico:");
        }
    }

    private void registrarMaterial() {
        String tipo = (String) cajaTipo.getSelectedItem();
        String codigo = campoCodigoRegistro.getText().trim();
        String titulo = campoTitulo.getText().trim();
        String textoAnio = campoAnio.getText().trim();
        String dato = campoDato.getText().trim();
        String imagen = campoImagen.getText().trim();

        if (codigo.isEmpty() || titulo.isEmpty() || textoAnio.isEmpty() || dato.isEmpty() || imagen.isEmpty()) {
            mostrarError("Llene todos los campos. No se aceptan campos vacios.");
            return;
        }

        int anio = 0;
        try {
            anio = Integer.parseInt(textoAnio);
        } catch (NumberFormatException e) {
            mostrarError("El anio de publicacion tiene que ser un numero.");
            return;
        }

        if (anio < ANIO_MINIMO || anio > anioActual()) {
            mostrarError("El anio tiene que estar entre " + ANIO_MINIMO + " y " + anioActual() + ".");
            return;
        }

        if (biblioteca.buscarPorCodigo(codigo) != null) {
            mostrarError("Ya existe un material con el codigo " + codigo + ".");
            return;
        }

        MaterialBibliografico nuevo = null;

        if (tipo.equals("Libro")) {
            nuevo = new Libro(codigo, titulo, anio, imagen, dato);
        } else if (tipo.equals("Revista")) {
            int edicion = 0;
            try {
                edicion = Integer.parseInt(dato);
            } catch (NumberFormatException e) {
                mostrarError("El numero de edicion tiene que ser un numero.");
                return;
            }
            if (edicion <= 0) {
                mostrarError("El numero de edicion tiene que ser mayor que cero.");
                return;
            }
            nuevo = new Revista(codigo, titulo, anio, imagen, edicion);
        } else {
            nuevo = new Tesis(codigo, titulo, anio, imagen, dato);
        }

        biblioteca.agregarMaterial(nuevo);
        mostrarAviso("El material " + codigo + " fue registrado correctamente.");

        campoCodigoRegistro.setText("");
        campoTitulo.setText("");
        campoAnio.setText("");
        campoDato.setText("");
        campoImagen.setText("");

        actualizarTodo();
    }

    private void buscarMaterial() {
        String codigo = campoBusqueda.getText().trim();

        if (codigo.isEmpty()) {
            mostrarError("Escriba un codigo para buscar.");
            return;
        }

        MaterialBibliografico encontrado = biblioteca.buscarPorCodigo(codigo);

        if (encontrado == null) {
            mostrarAviso("No existe ningun material registrado con el codigo " + codigo + ".");
        } else {
            String datos = "Codigo: " + encontrado.getCodigo()
                + "\nTitulo: " + encontrado.getTitulo()
                + "\nTipo: " + encontrado.getTipo()
                + "\nAnio: " + encontrado.getAnio()
                + "\nImagen: " + encontrado.getImagen()
                + "\nDisponible: " + textoDisponible(encontrado);
            mostrarAviso("Material encontrado:\n" + datos);
        }
    }

    
}

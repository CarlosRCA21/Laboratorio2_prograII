import java.util.Calendar;

public class Prestamo {
    private MaterialBibliografico material;
    private Calendar fechaInicio;
    private Calendar fechaPrevista;
    private boolean devuelto;

    public Prestamo(MaterialBibliografico material) {
        this.material = material;
        fechaInicio = Calendar.getInstance();
        fechaPrevista = (Calendar) fechaInicio.clone();
        fechaPrevista.add(Calendar.DAY_OF_MONTH, material.obtenerPlazoPrestamo());
        devuelto = false;
    }

    public MaterialBibliografico getMaterial() {
        return material;
    }

    public Calendar getFechaInicio() {
        return (Calendar) fechaInicio.clone();
    }

    public Calendar getFechaPrevista() {
        return (Calendar) fechaPrevista.clone();
    }

    public boolean isDevuelto() {
        return devuelto;
    }
}

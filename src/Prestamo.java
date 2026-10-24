import java.util.Calendar;

public class Prestamo {
    private MaterialBibliografico material;
    private Calendar fechaInicio;
    private Calendar fechaPrevista;
    private Calendar fechaDevolucion;
    private boolean devuelto;
    private int diasRetraso;
    private double multa;

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

    public Calendar getFechaDevolucion() {
        if (fechaDevolucion == null) {
            return null;
        }
        return (Calendar) fechaDevolucion.clone();
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public int getDiasRetraso() {
        return diasRetraso;
    }

    public double getMulta() {
        return multa;
    }

    private Calendar soloFecha(Calendar fecha) {
        Calendar aux = (Calendar) fecha.clone();
        aux.set(Calendar.HOUR_OF_DAY, 0);
        aux.set(Calendar.MINUTE, 0);
        aux.set(Calendar.SECOND, 0);
        aux.set(Calendar.MILLISECOND, 0);
        return aux;
    }

    /*
     aca Comparo copias de las fechas sin la hora para contar solo los dias.
     y voy sumando un dia con Calendar desde la fecha prevista hasta la devolucion.
     Si se entrega ese mismo dia o antes, el retraso queda en 0.
     entonces asi calendar maneja los cambios de mes y de anio.
     */
    public int calcularDiasRetraso(Calendar fecha) {
        Calendar prevista = soloFecha(fechaPrevista);
        Calendar actual = soloFecha(fecha);
        int dias=0;

        while (prevista.before(actual)) {
            prevista.add(Calendar.DAY_OF_MONTH, 1);
            dias++;
        }
        return dias;
    }

    public boolean devolver() {
        if (devuelto) {
            return false;
        }

        Calendar fecha = Calendar.getInstance();
        int dias = calcularDiasRetraso(fecha);
        double monto = material.calcularMulta(dias);

        fechaDevolucion = fecha;
        diasRetraso = dias;
        multa = monto;
        devuelto = true;
        return true;
    }
}

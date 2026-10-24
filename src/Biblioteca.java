import java.util.ArrayList;
import java.util.Calendar;

public class Biblioteca {
    private ArrayList<MaterialBibliografico> materiales;
    private ArrayList<Prestamo> prestamos;

    public Biblioteca() {
        materiales = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    public boolean registrarMaterial(MaterialBibliografico material) {
        if (material == null || material.getCodigo() == null || material.getTitulo() == null) {
            return false;
        }
        if (material.getCodigo().trim().isEmpty() || material.getTitulo().trim().isEmpty()) {
            return false;
        }

        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        if (material.getAnioPublicacion() < 1000 || material.getAnioPublicacion() > anioActual) {
            return false;
        }
        if (buscarPorCodigo(material.getCodigo(), 0) != null) {
            return false;
        }

        materiales.add(material);
        return true;
    }

    public MaterialBibliografico buscarPorCodigo(String codigo) {
        return buscarPorCodigo(codigo, 0);
    }

    public MaterialBibliografico buscarPorCodigo(String codigo, int indice) {
        if (codigo == null || codigo.trim().isEmpty() || indice < 0 || indice >= materiales.size()) {
            return null;
        }

        MaterialBibliografico aux = materiales.get(indice);
        if (aux.getCodigo().trim().equalsIgnoreCase(codigo.trim())) {
            return aux;
        }
        return buscarPorCodigo(codigo, indice + 1);
    }

    public ArrayList<MaterialBibliografico> getMateriales() {
        return new ArrayList<>(materiales);
    }

    public ArrayList<Prestamo> getPrestamos() {
        return new ArrayList<>(prestamos);
    }

    public Prestamo buscarPrestamoActivo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }

        for (int i=0; i < prestamos.size(); i++) {
            Prestamo aux = prestamos.get(i);
            if (!aux.isDevuelto() && aux.getMaterial().getCodigo().trim().equalsIgnoreCase(codigo.trim())) {
                return aux;
            }
        }
        return null;
    }

    public Prestamo prestarMaterial(String codigo) {
        MaterialBibliografico material = buscarPorCodigo(codigo, 0);
        if (material == null || !material.isDisponible()) {
            return null;
        }
        if (buscarPrestamoActivo(codigo) != null || material.obtenerPlazoPrestamo() <= 0) {
            return null;
        }

        Prestamo prestamo = new Prestamo(material);
        prestamos.add(prestamo);
        material.setDisponible(false);
        return prestamo;
    }

    public Prestamo devolverMaterial(String codigo) {
        Prestamo prestamo = buscarPrestamoActivo(codigo);
        if (prestamo == null) {
            return null;
        }

        if (prestamo.devolver()) {
            prestamo.getMaterial().setDisponible(true);
            return prestamo;
        }
        return null;
    }
}

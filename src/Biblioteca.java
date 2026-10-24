import java.util.ArrayList;
import java.util.Calendar;

public class Biblioteca {
    private ArrayList<MaterialBibliografico> materiales;

    public Biblioteca() {
        materiales = new ArrayList<>();
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

}

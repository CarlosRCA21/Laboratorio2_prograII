/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author XPS 13
 */
public class MaterialBibliografico {
    private String codigo;
    private String titulo;
    private int anioPublicacion;
    private boolean disponible;
    private String imagen;

    public MaterialBibliografico(String codigo, String titulo, int anioPublicacion, String imagen) {
        this.codigo=codigo;
        this.titulo=titulo;
        this.anioPublicacion=anioPublicacion;
        this.disponible=true;
        this.imagen=imagen;
    }
     public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo=codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo=titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion=anioPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible=disponible;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen=imagen;
    }

    public int obtenerPlazoPrestamo() {
        return 7;
    }

    public double calcularMulta(int diasRetraso) {
        return diasRetraso*1.0;
    }
}

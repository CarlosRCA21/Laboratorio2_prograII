/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author XPS 13
 */
public class Libro extends MaterialBibliografico {
    private String autor;

    public Libro(String codigo, String titulo, int anioPublicacion, String imagen, String autor) {
        super(codigo, titulo, anioPublicacion, imagen);
        this.autor=autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor=autor;
    }

    @Override
    public int obtenerPlazoPrestamo() {
        return 14;
    }

    @Override
    public double calcularMulta(int diasRetraso) {
        double multaTotal=0.0;
        if (diasRetraso>0) {
            multaTotal=diasRetraso*1.50;
        }
        return multaTotal;
    }
}

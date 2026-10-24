/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author XPS 13
 */
public class Revistas extends MaterialBibliografico {
    private int numeroEdicion;

    public Revistas(String codigo, String titulo, int anioPublicacion, String imagen, int numeroEdicion) {
        super(codigo, titulo, anioPublicacion, imagen);
        this.numeroEdicion = numeroEdicion;
    }
    
public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public int obtenerPlazoPrestamo() {
        return 7;
    }

    @Override
    public double calcularMulta(int diasRetraso) {
        double multaTotal = 0.0;
        if (diasRetraso > 0) {
            multaTotal = diasRetraso * 0.75;
        }
        return multaTotal;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author XPS 13
 */
public class Tesis extends MaterialBibliografico {
    private String autorAcademico;

    public Tesis(String codigo, String titulo, int anioPublicacion, String imagen, String autorAcademico) {
        super(codigo, titulo, anioPublicacion, imagen);
        this.autorAcademico=autorAcademico;
    }

    public String getAutorAcademico() {
        return autorAcademico;
    }

    public void setAutorAcademico(String autorAcademico) {
        this.autorAcademico=autorAcademico;
    }

    @Override
    public int obtenerPlazoPrestamo() {
        return 3;
    }

    @Override
    public double calcularMulta(int diasRetraso) {
        double multaTotal=0.0;
        if (diasRetraso>0) {
            multaTotal=diasRetraso*3.00;
        }
        return multaTotal;
    }
}
   

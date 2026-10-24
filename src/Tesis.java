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
        this.autorAcademico = autorAcademico;
    }
}    

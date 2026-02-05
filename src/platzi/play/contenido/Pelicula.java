package platzi.play.contenido;

import java.time.LocalDate;
import java.util.Locale;

//atributos (Por buena práctica los atributos deberian de ser private para cada clase)
public class Pelicula {

private String titulo;
private String descripcion;
private int duracion;
private String genero;
private double calificacion;
private boolean disponibilidad;
//LocalDate para manejar las fechas
private LocalDate fehaEstreno;

//Constructor
    public Pelicula (String titulo, int duracion, String genero, double calificacion, LocalDate fecha){
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.disponibilidad = true;
        this.calificar(calificacion);
        this.fehaEstreno = fecha;



    }

//metodos
    public void reproducir(){
        System.out.println("Reproduciendo: "+  titulo );
    }

    public String obtenerFichaTecnica(){
        return titulo+" "+"("+fehaEstreno.getYear()+")"+"\n" +
                "Género: "+ genero + "\n"+
                "Calificación: "+ calificacion + "/5";

    }

    public void calificar(double calificacion){
        if(calificacion >= 0 && calificacion <= 5){
            this.calificacion = calificacion;
        }
    }

    public boolean esPopular (){
        return calificacion >= 4;
    }

    //Getters

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public LocalDate getFehaEstreno() {
        return fehaEstreno;
    }

    public void setFehaEstreno(LocalDate fehaEstreno) {
        this.fehaEstreno = fehaEstreno;
    }

    // Estructura de una clase
    /*
        atributos
        constructores
        metodos
        getters y setters
    */
}




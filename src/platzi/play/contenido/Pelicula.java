package platzi.play.contenido;

import java.sql.SQLOutput;
import java.time.LocalDate;

public class Pelicula extends Contenido{


    public Pelicula(String titulo, int duracion, Genero genero, double calificacion, LocalDate fecha) {
        super(titulo, duracion, genero, calificacion, fecha);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproducionedo la peilcula " + getTitulo());
    }
}

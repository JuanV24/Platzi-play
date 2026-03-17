package platzi.play.contenido;

import java.time.LocalDate;

public class Documental extends Contenido implements Promocionable{

   private String narrador;

    public Documental(String titulo, int duracion, Genero genero, double calificacion, LocalDate fecha, String narrador) {
        super(titulo, duracion, genero, calificacion, fecha);
        this.narrador = narrador;

    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo " + getTitulo()+ " Narrado por: " + getNarrador() );
    }

    @Override
    public String promocionar() {
        return "Descubre el documental: " + getTitulo() + " Narrador por: "+ getNarrador() + ". Ahora en PlatziPlay";
    }

    public String getNarrador()
    {
        return narrador;
    }
}

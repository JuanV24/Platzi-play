package platzi.play.plataforma;

import platzi.play.contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private String nombrePlataforma;
    private List <Pelicula> contenido;

    public Plataforma(String nombrePlataforma) {
    this.nombrePlataforma = nombrePlataforma;
    //Inicializando nuestra lista (Muy importante para que funcione de manera correcta)
    this.contenido = new ArrayList<>();
    }

    public void Agregar(Pelicula pelicula ){
        this.contenido.add(pelicula);
    }

    public void MostrarTitulos(){

        System.out.println("Titulos en el sistema: ");

        for(int i = 0; i < contenido.size(); i++){
            //Mostrando los titulos en su posicion
            System.out.println(contenido.get(i).getTitulo());
        }
    }

    public void Eliminar(Pelicula pelicula){
        this.contenido.remove(pelicula);
    }

    //Segunda forma de mostrar los objetos de la lista
    public void MostrarTitulos2(){
        // for mejorado en donde solo se crea el objeto de la lista de contenido y se imprime el titulo
        for(Pelicula pelicula: contenido){
            System.out.println(pelicula.getTitulo());
        }
    }

    public Pelicula BuscarTitulo(String titulo) {
        //Recorriendo las peliculas en contenido
        for (Pelicula pelicula : contenido){

            //equalsIgnoreCase Busca el titulo sin importar si es mayusculas o minusculas
            if(pelicula.getTitulo().equalsIgnoreCase(titulo)){

                //retornando la pelicula si se cumple la condición
                return pelicula;
            }

        }
        //si no retornara null
        return  null;


    }

    public Pelicula EliminarPorTitulo(String titulo){

        for(Pelicula pelicula: contenido){

            if (pelicula.getTitulo().equalsIgnoreCase(titulo)){

                contenido.remove(pelicula);

                return pelicula;

            }
        }

        return null;
    }

    public String getNombrePlataforma() {
        return nombrePlataforma;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}

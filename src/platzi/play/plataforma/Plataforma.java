package platzi.play.plataforma;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Plataforma {
    private String nombrePlataforma;
    private List <Pelicula> contenido;
    private Map<Pelicula, Integer> visualizaciones;

    public Plataforma(String nombrePlataforma) {
    this.nombrePlataforma = nombrePlataforma;
    //Inicializando nuestra lista (Muy importante para que funcione de manera correcta)
    this.contenido = new ArrayList<>();
    this.visualizaciones = new HashMap<>();

    }

    public void Agregar(Pelicula pelicula ){
        Pelicula contenido = this.BuscarTituloStream(pelicula.getTitulo());

        if(contenido != null){
            throw new PeliculaExistenteException(pelicula.getTitulo());
        }

        FileUtils.escribirContenido(pelicula);
        this.contenido.add(pelicula);

    }

    public void reproducir(Pelicula elemento){
        int conteoActual = visualizaciones.getOrDefault(elemento,0);
        System.out.println( elemento.getTitulo() + "Ha sido reproducido: "+ conteoActual + " veces");


        this.contarVisualizacion(elemento);
        elemento.reproducir();
    }

    private void contarVisualizacion(Pelicula contenido){
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual+1);
    }

    public void MostrarTitulos(){

        System.out.println("Titulos en el sistema: ");

        for(int i = 0; i < contenido.size(); i++){
            //Mostrando los titulos en su posicion
            System.out.println(contenido.get(i).getTitulo());
        }
    }

    public void MostrarTitulosForeach(){

        contenido.forEach(pelicula -> {
            System.out.println("Nombre de la pelicula: "+ pelicula.getTitulo() +"\n" +
                    "Año de estreno: "+ "("+ pelicula.getFehaEstreno()+")" + "\n");
        });
    }

    public void Eliminar(Pelicula pelicula){
        this.contenido.remove(pelicula);
    }

    //Segunda forma de mostrar los objetos de la lista
    public void MostrarTitulos2(){
        // for mejorado en donde solo se crea el objeto de la lista de contenido y se imprime el título
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

    //Mostrando los titulos pero ahora por medio de un stram, map y añadiendo a la lista
    public List<String> MostrarTitulosStream(){
         return contenido.stream()
                .map(Pelicula::getTitulo)
                .toList();
    }

    public Pelicula BuscarTituloStream(String titulo){
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

        /*
        * stream() recorre la listas de peliculas
        * filter() filtra cada una de ellas por medio del titulo
        * findFirst() Encuentra la primera
        * orElse() segunda condición la cual si no la encuentra retornará nulll
        * */


    }

    public List<Pelicula> BuscarporGenero(Genero genero){
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();

        /*
        * lo recorremos con stream
        * hacemos el filtro con filter
        * lo añadimos a la lista con toList()
        * */

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

    public int getDuracionTotal(){
        return contenido.stream()
                .mapToInt(Pelicula::getDuracion)
                .sum();

    }

    public List<Pelicula> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    //Encontrando las peliculas con calificación mayor a 4
    public List<Pelicula> getCalicacionMayor(){
        return contenido.stream()
                .filter(contenido-> contenido.getCalificacion() >= 4)
                .sorted(Comparator.comparing(Pelicula::getCalificacion).reversed())
                .toList();
    }

    public Pelicula obtenerMayorDuracion(){
        return contenido.stream()
                .sorted( Comparator.comparing(Pelicula::getDuracion).reversed())
                .findFirst()
                .orElse(null);
    }

    public Pelicula obtenerMenorDuracion(){
        return contenido.stream()
                .sorted(Comparator.comparing(Pelicula::getDuracion))
                .findFirst()
                .orElse(null);
    }

    public String getNombrePlataforma() {
        return nombrePlataforma;
    }

    public List<ResumenContenido> getResumenContenido(){
        return contenido.stream()
                .map( c -> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero() ))
                .toList();
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}

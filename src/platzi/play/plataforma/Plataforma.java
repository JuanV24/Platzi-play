package platzi.play.plataforma;

import platzi.play.contenido.*;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Plataforma {
    private String nombrePlataforma;
    private List <Contenido> contenido;
    private Map<Contenido, Integer> visualizaciones;

    public Plataforma(String nombrePlataforma) {
    this.nombrePlataforma = nombrePlataforma;
    //Inicializando nuestra lista (Muy importante para que funcione de manera correcta)
    this.contenido = new ArrayList<>();
    this.visualizaciones = new HashMap<>();

    }

    public void Agregar(Contenido pelicula ){
        Contenido contenido = this.BuscarTituloStream(pelicula.getTitulo());

        if(contenido != null){
            throw new PeliculaExistenteException(pelicula.getTitulo());
        }

        FileUtils.escribirContenido(pelicula);
        this.contenido.add(pelicula);

    }

    public void reproducir(Contenido elemento){
        int conteoActual = visualizaciones.getOrDefault(elemento,0);
        System.out.println( elemento.getTitulo() + "Ha sido reproducido: "+ conteoActual + " veces");


        this.contarVisualizacion(elemento);
        elemento.reproducir();
    }

    private void contarVisualizacion(Contenido contenido){
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

    public void Eliminar(Contenido contenido){
        this.contenido.remove(contenido);
    }

    //Segunda forma de mostrar los objetos de la lista
    public void MostrarTitulos2(){
        // for mejorado en donde solo se crea el objeto de la lista de contenido y se imprime el título
        for(Contenido contenido : this.contenido){
            System.out.println(contenido.getTitulo());
        }
    }

    public Contenido BuscarTitulo(String titulo) {
        //Recorriendo las peliculas en contenido
        for (Contenido contenido : this.contenido){

            //equalsIgnoreCase Busca el titulo sin importar si es mayusculas o minusculas
            if(contenido.getTitulo().equalsIgnoreCase(titulo)){

                //retornando la contenido si se cumple la condición
                return contenido;
            }

        }
        //si no retornara null
        return  null;


    }

    //Mostrando los titulos pero ahora por medio de un stram, map y añadiendo a la lista
    public List<String> MostrarTitulosStream(){
         return contenido.stream()
                .map(Contenido::getTitulo)
                .toList();
    }

    public Contenido BuscarTituloStream(String titulo){
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

    public List<Contenido> BuscarporGenero(Genero genero){
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();

        /*
        * lo recorremos con stream
        * hacemos el filtro con filter
        * lo añadimos a la lista con toList()
        * */

    }

    public Contenido EliminarPorTitulo(String titulo){

        for(Contenido contenido : this.contenido){

            if (contenido.getTitulo().equalsIgnoreCase(titulo)){

                this.contenido.remove(contenido);

                return contenido;

            }
        }

        return null;
    }

    public int getDuracionTotal(){
        return contenido.stream()
                .mapToInt(Contenido::getDuracion)
                .sum();

    }

    public List<Contenido> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    public List<Pelicula> getPeliculas()
    {
       return contenido.stream()
                .filter(contenido -> contenido instanceof Pelicula)
                .map( contenidoFiltrado -> (Pelicula) contenidoFiltrado)
                .toList();

    }

    public List<Documental> getDocumentales()
    {
        return contenido.stream()
                .filter(contenido -> contenido instanceof Documental)
                .map( contenidoFiltrado -> (Documental) contenidoFiltrado)
                .toList();

    }

    //Encontrando las peliculas con calificación mayor a 4
    public List<Contenido> getCalicacionMayor(){
        return contenido.stream()
                .filter(contenido-> contenido.getCalificacion() >= 4)
                .sorted(Comparator.comparing(Contenido::getCalificacion).reversed())
                .toList();
    }

    public Contenido obtenerMayorDuracion(){
        return contenido.stream()
                .sorted( Comparator.comparing(Contenido::getDuracion).reversed())
                .findFirst()
                .orElse(null);
    }

    public Contenido obtenerMenorDuracion(){
        return contenido.stream()
                .sorted(Comparator.comparing(Contenido::getDuracion))
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

    public List<Contenido> getContenido() {
        return contenido;
    }

    public List<Promocionable> getContenidoPromocionable()
    {
        return contenido.stream()
                .filter( contenido -> contenido instanceof Promocionable)
                .map(contenidoProm -> (Promocionable) contenidoProm )
                .toList();
    }
}

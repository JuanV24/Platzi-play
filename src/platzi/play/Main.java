package platzi.play;

import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Plataforma;
import platzi.play.plataforma.Usuario;
import platzi.play.util.ScannerUtils;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Main {
    //final para constantes
    public static final String VERSION = "1.0.0";
    public static final String NOMBRE_PLATAFORMA = "Welcome Platzi play: ";
    public static final int OPCION_SALIDA = 6;

    public static void main(String[] args) {
        System.out.println(NOMBRE_PLATAFORMA + VERSION);

        //Creando objeto de tipo plataforma
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);

        //Cargamos las peliculas y enviamos como parametro la plataforma.
            cargarPeliculas(plataforma);

        while(true) {
            int opcion = ScannerUtils.capturarNumero("-------BIENVENIDO A PLATZI PLAY\n" +
                    "1. AGREGAR CONTENIDO \n" +
                    "2. MOSTRAR TODO \n" +
                    "3. BUSCAR POR TITULO \n" +
                    "4. ELIMINAR \n" +
                    "5. BUSCAR POR GENERO \n"+
                    "6. SALIR");



            switch (opcion) {
                case 1:
                        //Agregando contenido

                    //capturando datos
                    String nombre = ScannerUtils.capturarTexto("Cual es el nombre del contenido");
                    String genero = ScannerUtils.capturarTexto("Cual es el genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Cual es la duración del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Cual es la calificacion");
                    LocalDate fecha = ScannerUtils.capturarFecha();

                    //Creando la pelicula
                    Pelicula pelicula = new Pelicula(nombre,duracion,genero,calificacion, fecha);

                    //Agregando la pelicula a la plataforma
                    plataforma.Agregar(pelicula);

                    System.out.println("Pelicula registrada con exito.");

                    break;
                case 2:
                    //Mostrando "t0do" el contenido
                    plataforma.MostrarTitulosForeach();
                    break;
                case 3:
                    //Buscando por título

                    //Capturando el nombre de la pelicula
                    String titulo = ScannerUtils.capturarTexto("Cual es el nombre del contenido");

                    //adquiriendo la pelicula retornada
                    Pelicula peliculaBuscada = plataforma.BuscarTituloStream(titulo);

                    if(peliculaBuscada != null){
                        System.out.println(peliculaBuscada.obtenerFichaTecnica());
                    }else {
                        System.out.println(titulo +" El nombre buscado no existe dentro de " +plataforma.getNombrePlataforma());
                    }
                    break;
                case 4:

                    //Eliminado contenido
                    String titutoEliminar = ScannerUtils.capturarTexto("Ingrese el titulo a eliminar");

                    Pelicula peliculaEncontrada = plataforma.EliminarPorTitulo(titutoEliminar);

                    if(peliculaEncontrada != null){
                        System.out.println("La pelicula "+ peliculaEncontrada.getTitulo() +" ha sido eliminada");
                    }else{
                        System.out.println(titutoEliminar+" El nombre buscado no existe dentro de" +plataforma.getNombrePlataforma());
                    }


                    break;
                case 5:

                    //capturamos datos
                    String generoBuscar = ScannerUtils.capturarTexto("Ingrese el genero de saber las peliculas");

                    //lo guardamos en una lista
                    List <Pelicula> contenidoPorGenero = plataforma.BuscarporGenero(generoBuscar);

                    //Mensaje de que hemos encontrado algo
                    System.out.println("Se encontraron:  "+ contenidoPorGenero.size() +" Peliculas");

                    //recorremos la lista hecha con el contenido encontrado
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() ));


                    break;

                case OPCION_SALIDA:
                    //Saliendo del sistema
                    System.out.println("Saliendo de platzi play.....");
                    System.exit(0);
                    break;
            }
        }

    }

    private static void cargarPeliculas(Plataforma plataforma) {
        LocalDate fecha = LocalDate.now();

        plataforma.Agregar(new Pelicula("Shrek", 90, "Animada",5, fecha));
        plataforma.Agregar(new Pelicula("Inception", 148, "Ciencia Ficción",4.3, fecha));
        plataforma.Agregar(new Pelicula("Titanic", 195, "Drama", 4.6, fecha));
        plataforma.Agregar(new Pelicula("John Wick", 101, "Acción", 3.2, fecha));
        plataforma.Agregar(new Pelicula("El Conjuro", 112, "Terror", 3.0, fecha));
        plataforma.Agregar(new Pelicula("Coco", 105, "Animada", 4.7, fecha));
        plataforma.Agregar(new Pelicula("Interstellar", 169, "Ciencia Ficción", 5,fecha));
        plataforma.Agregar(new Pelicula("Joker", 122, "Drama",3.6, fecha));
        plataforma.Agregar(new Pelicula("Toy Story", 81, "Animada", 4.5, fecha));
        plataforma.Agregar(new Pelicula("Avengers: Endgame", 181, "Acción", 3.9, fecha));
    }
}

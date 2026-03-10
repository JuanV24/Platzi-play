package platzi.play;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.FileUtils;
import platzi.play.util.ScannerUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import java.time.LocalDate;


public class Main {
    //final para constantes
    public static final String VERSION = "1.0.0";
    public static final String NOMBRE_PLATAFORMA = "Welcome Platzi play: ";
    public static final int OPCION_SALIDA = 10;

    public static void main(String[] args) {
        System.out.println(NOMBRE_PLATAFORMA + VERSION);

        //Creando objeto de tipo plataforma
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);

        //Cargamos las peliculas y enviamos como parametro la plataforma.
            cargarPeliculas(plataforma);

        while(true) {
            int opcion = ScannerUtils.capturarNumero("-------BIENVENIDO A PLATZI PLAY-------\n" +
                    "**** CON MAS DE: "+ plataforma.getDuracionTotal() +" MINUTOS DE CONTENIDO \n"  +
                    "1. AGREGAR CONTENIDO \n" +
                    "2. MOSTRAR TODO \n" +
                    "3. BUSCAR POR TITULO \n" +
                    "4. ELIMINAR \n" +
                    "5. BUSCAR POR GENERO \n"+
                    "6. MOSTRAR LAS MÁS POPULARES \n"+
                    "7. PELICULAS CON MEJOR CALIFICACIÓN \n" +
                    "8. PELICULA MAS LARGA Y MAS CORTA \n"+
                    "9. REPRODUCIR\n" +
                    "10. SALIR");



            switch (opcion) {
                case 1:
                        //Agregando contenido
            try {
                //capturando datos
                String nombre = ScannerUtils.capturarTexto("Cual es el nombre del contenido");
                Genero genero = ScannerUtils.capturaGenero("Ingresa el genero del contenido: ");
                int duracion = ScannerUtils.capturarNumero("Cual es la duración del contenido");
                double calificacion = ScannerUtils.capturarDecimal("Cual es la calificacion");
                LocalDate fecha = ScannerUtils.capturarFecha();

                //Creando la pelicula
                Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion, fecha);

                //Agregando la pelicula a la plataforma
                plataforma.Agregar(pelicula);

                System.out.println("Pelicula registrada con exito.");
            } catch (PeliculaExistenteException e) {
                System.out.println(e.getMessage());
            }

                    break;
                case 2:
                    //Mostrando "t0do" el contenido
                    List<ResumenContenido> titulosResumidos = plataforma.getResumenContenido();
                    titulosResumidos.forEach( resumen -> System.out.println(resumen.toString())); // metodo reference con lambdas

                    //El codigo de bajo seria lo mismo que el de arriba
                    //titulos.foreach( titulo -> System.out.print(titulo)


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

                    //capturamos datos  //ahora convertimos un string a un género de nuestro Enum
                    Genero generoBuscar = ScannerUtils.capturaGenero("Ingresa el genero a buscar");

                    //lo guardamos en una lista
                    List <Pelicula> contenidoPorGenero = plataforma.BuscarporGenero(generoBuscar);

                    //Mensaje de que hemos encontrado algo
                    System.out.println("Se encontraron:  "+ contenidoPorGenero.size() +" Peliculas");

                    //recorremos la lista hecha con el contenido encontrado
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() +" \n" ));


                    break;

                case 6:

                    int cantidad = ScannerUtils.capturarNumero("Cuantos resultados deseas ver?");

                    List<Pelicula> contenidosPopulares = plataforma.getPopulares(cantidad);
                   contenidosPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica()+"\n" )); //otro metodo de imprimir
                     break;
                case 7:

                    List<Pelicula> contenidosMasCalificados = plataforma.getCalicacionMayor();

                    contenidosMasCalificados.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                    break;
                case 8:
                    Pelicula peliculaLarga = plataforma.obtenerMayorDuracion();
                    Pelicula peliculaCorta = plataforma.obtenerMenorDuracion();

                    System.out.println("La pelicula con mayor duración es: "+ peliculaLarga.obtenerFichaTecnica() + "\n");
                    System.out.println("La pelicula con menor duracion es: "+ peliculaCorta.obtenerFichaTecnica());
                    break;
                case 9:
                    String nombre = ScannerUtils.capturarTexto("Ingresa el nombre del contenido");
                    Pelicula contenido = plataforma.BuscarTitulo(nombre);

                    if(contenido != null){
                        plataforma.reproducir(contenido);
                    }else{
                        System.out.println(nombre+ "No existe dentro de: "+plataforma.getNombrePlataforma());
                    }



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
        plataforma.getContenido().addAll(FileUtils.leerContenido());

    }
}

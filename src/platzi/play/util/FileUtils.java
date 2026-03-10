package platzi.play.util;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Plataforma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static final String NOMBRE_ARCHIVO = "contenido.txt";
    public static final String SEPARADOR = "|";

    public static List<Pelicula> leerContenido(){
        List<Pelicula> contenidos = new ArrayList<>();

        //Manejando errores
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));

            lineas.forEach( contenido -> {
                String[] datos = contenido.split("\\"+SEPARADOR);

                if(datos.length == 5){
                    String titulo = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    Genero genero = Genero.valueOf(datos[2].toUpperCase());
                    double calificacion = datos[3].isBlank() ? 0 : Double.parseDouble(datos[3]);
                    LocalDate fechaEstreno = LocalDate.parse(datos[4]);

                    Pelicula pelicula = new Pelicula(titulo,duracion, genero, calificacion, fechaEstreno);
                    contenidos.add(pelicula);
                }

            });


        } catch (IOException e) {

            System.out.println("Error: "+ e.getMessage());
        }

        return contenidos;
    }

    public static void escribirContenido(Pelicula pelicula){
        try {
            String linea = String.join(SEPARADOR, pelicula.getTitulo(),
                    String.valueOf(pelicula.getDuracion()), pelicula.getGenero().name(),
                    String.valueOf(pelicula.getCalificacion()),
                    pelicula.getFehaEstreno().toString());

            //Ruta del archivo, la linea con los datos, salto de linea con separator, si el archivo no existe que lo cree, Agregue al final el contenido
            Files.writeString(Paths.get(NOMBRE_ARCHIVO),
                    linea + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }
        catch (IOException e){
            System.out.println("Error: "+ e.getMessage());
        }

        }


}

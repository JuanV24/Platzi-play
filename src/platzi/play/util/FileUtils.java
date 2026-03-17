package platzi.play.util;

import platzi.play.contenido.Contenido;
import platzi.play.contenido.Documental;
import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;

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

    public static List<Contenido> leerContenido(){
        List<Contenido> contenidos = new ArrayList<>();

        //Manejando errores
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));

            lineas.forEach( contenido -> {
                String[] datos = contenido.split("\\"+SEPARADOR);

                String tipo = datos[0];

                if(("PELICULA".equals(tipo) && datos.length == 6) || ("DOCUMENTAL".equals(tipo) && datos.length == 7)){
                    String titulo = datos[1];
                    int duracion = Integer.parseInt(datos[2]);
                    Genero genero = Genero.valueOf(datos[3].toUpperCase());
                    double calificacion = datos[4].isBlank() ? 0 : Double.parseDouble(datos[4]);
                    LocalDate fechaEstreno = LocalDate.parse(datos[5]);

                    Contenido contenidoNuevo ;

                    if("PELICULA".equals(tipo))
                    {
                        contenidoNuevo = new Pelicula(titulo,duracion,genero,calificacion,fechaEstreno);
                    }else{
                            String narrador = datos[6];
                            contenidoNuevo = new Documental(titulo,duracion,genero,calificacion,fechaEstreno,narrador);
                    }
                    contenidos.add(contenidoNuevo);
                }

            });


        } catch (IOException e) {

            System.out.println("Error: "+ e.getMessage());
        }

        return contenidos;
    }

    public static void escribirContenido(Contenido contenido){
        try {
            String linea = String.join(SEPARADOR, contenido.getTitulo(),
                    String.valueOf(contenido.getDuracion()), contenido.getGenero().name(),
                    String.valueOf(contenido.getCalificacion()),
                    contenido.getFehaEstreno().toString());

            String lineaFinal;

            if (contenido instanceof Documental documental){
                lineaFinal = "DOCUMENTAL"+ SEPARADOR+  linea + SEPARADOR + documental.getNarrador();
            }else{
                lineaFinal = "PELICULA"+ SEPARADOR + linea ;
            }


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

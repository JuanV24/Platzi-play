package platzi.play.util;

import java.time.LocalDate;
import java.util.Scanner;

public class ScannerUtils {
    //Este atributo no depende de ningún objeto si no que de la clase en general}
public static final Scanner SCANNER = new Scanner(System.in);

    public static String capturarTexto(String mensaje){
        System.out.println(mensaje+ ": ");
        return SCANNER.nextLine();
    }
    public static int capturarNumero( String mensaje){
        System.out.println(mensaje + ": ");

        //Validación para ingresar capturar números enteros
        while (!SCANNER.hasNextInt()){
            System.out.println("Dato incorrecto " + mensaje +": ");
            //Descartamos la entrada
            SCANNER.next(); //El valor que ingreso el usuario se descarta
        }

        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double capturarDecimal( String mensaje){
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextDouble()){
            System.out.println("Dato incorrecto "+ mensaje +":");
            SCANNER.next();
        }

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }

    public static LocalDate capturarFecha (){
        System.out.println("Ingresa el dia:");
        int dia = SCANNER.nextInt();
        SCANNER.nextLine();

        System.out.println("Ingresa el mes: ");
        int mes = SCANNER.nextInt();
        SCANNER.nextLine();

        System.out.println("Ingresa el año: ");
        int anio = SCANNER.nextInt();
        SCANNER.nextLine();

        //Convirtiendo los datos a una fecha
        LocalDate fecha = LocalDate.of(anio,mes,dia);

        //retornando la fecha
        return fecha;
    }

}

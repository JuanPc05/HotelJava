package application.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FormValidationUtil {

    private final static Scanner sc = new Scanner(System.in);

    public static int validateInt(String prompt){

        while(true){

            try{

                System.out.println(prompt);
                int value = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
                return value;

            }catch (InputMismatchException e){
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                sc.nextLine(); // Limpiar el buffer
            }
        }

    }


    public static double validateDouble(String prompt){

        while(true){

            try{

                System.out.println(prompt);
                double value = sc.nextDouble();
                sc.nextLine(); // Limpiar el buffer
                return value;

            }catch (InputMismatchException e){
                System.out.println("Entrada no válida. Por favor, ingrese un número decimal.");
                sc.nextLine(); // Limpiar el buffer
            }
        }

    }

    public static boolean validateBoolean(String prompt){

        while(true){

            try{

                System.out.println(prompt);
                boolean value = sc.nextBoolean();
                sc.nextLine(); // Limpiar el buffer
                return value;

            }catch (InputMismatchException e){
                System.out.println("Entrada no válida. Por favor, ingrese un valor lógico.");
                sc.nextLine(); // Limpiar el buffer
            }
        }

    }


    public static String validateString(String prompt) throws InputMismatchException{

        while(true){

                System.out.println(prompt);
                String value = sc.nextLine().trim();
                if(!value.isEmpty()){
                    return value;
                }

                throw new InputMismatchException ("Entrada no válida. Por favor, ingrese un número entero.");

        }
    }


    public static LocalDate validateDate(String message) {
        // Definimos el formato estricto que queremos aceptar
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim(); // .trim() elimina espacios accidentales

            try {
                // Intentamos convertir el texto a LocalDate
                return LocalDate.parse(input, formatter);

            } catch (DateTimeParseException e) {
                // Si falla, mostramos un error amigable y el bucle vuelve a empezar
                System.out.println("❌ Error: Formato de fecha inválido. Por favor, use el formato YYYY-MM-DD (ejemplo: 2024-12-31).");
            }
        }
    }

}

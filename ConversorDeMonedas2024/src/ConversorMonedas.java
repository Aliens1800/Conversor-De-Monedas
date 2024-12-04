import java.io.IOException;

public class ConversorMonedas {
    public static void main(String[] args) {
        Menu menu = new Menu();
        try {
            menu.mostrarMenu();
        } catch (IOException e) {
            System.out.println("Error al obtener los datos de la API: " + e.getMessage());
        }
    }
}


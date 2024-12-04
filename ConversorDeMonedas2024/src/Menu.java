import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final Conversiones conversiones = new Conversiones();
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<String> historial = new ArrayList<>();

    public void mostrarMenu() throws IOException {
        boolean continuar = true;

        System.out.println("**********************************************");
        System.out.println("*****¡Bienvenido al conversor de monedas!*****");
        System.out.println("**********************************************");

        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Peso mexicano a Dólar");
            System.out.println("2. Dólar a Peso mexicano");
            System.out.println("3. Dólar a Real brasileño");
            System.out.println("4. Real brasileño a Dólar");
            System.out.println("5. Dólar a Peso colombiano");
            System.out.println("6. Peso colombiano a Dólar");
            System.out.println("7. Mostrar historial");
            System.out.println("8. Salir");

            System.out.println("**********************************************");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> realizarConversion("MXN", "USD");
                case 2 -> realizarConversion("USD", "MXN");
                case 3 -> realizarConversion("USD", "BRL");
                case 4 -> realizarConversion("BRL", "USD");
                case 5 -> realizarConversion("USD", "COP");
                case 6 -> realizarConversion("COP", "USD");
                case 7 -> mostrarHistorial();
                case 8 -> {
                    System.out.println("- Gracias por usar el conversor de monedas.");
                    continuar = false;
                }
                default -> System.out.println("- Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void realizarConversion(String base, String destino) throws IOException {
        System.out.print("- Ingrese la cantidad en " + base + ": ");
        double cantidad = scanner.nextDouble();
        double tipoCambio = conversiones.obtenerTipoCambio(base, destino);
        double resultado = cantidad * tipoCambio;

        String conversion = String.format("El resultado de la conversión es; %.2f %s = %.2f %s", cantidad, base, resultado, destino);
        historial.add(conversion);
        System.out.println(conversion);
    }

    private void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("- No hay conversiones realizadas.");
        } else {
            System.out.println("\nHistorial de conversiones:");
            for (String conversion : historial) {
                System.out.println(conversion);
            }
        }
    }
}

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Conversiones {
    private final String API_KEY = "66bde6d3b9cb557a1d2f47ca";
    private final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public double obtenerTipoCambio(String base, String destino) throws IOException {
        URL url = new URL(API_URL + base);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("No se pudo conectar con la API. Código de respuesta: " + responseCode);
        }

        StringBuilder inline = new StringBuilder();
        try (Scanner scanner = new Scanner(url.openStream())) {
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
        }

        JsonObject jsonObject = JsonParser.parseString(inline.toString()).getAsJsonObject();

        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

        return conversionRates.get(destino).getAsDouble();
    }
}


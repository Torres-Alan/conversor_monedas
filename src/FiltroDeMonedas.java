// 8) Filtro de monedas específicas usando Gson

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FiltroDeMonedas {
    public static void main(String[] args) {
        try {
            // Clave de la API (reemplazar por la real)
            String apiKey = "";
            String baseCurrency = "USD";
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

            // Creo el cliente y la solicitud
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            // Envío la solicitud y obtengo la respuesta
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            String json = respuesta.body();

            // Parseo el JSON
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            // Verifico si fue exitosa
            if (!jsonObject.has("result") || !jsonObject.get("result").getAsString().equals("success")) {
                System.out.println("La respuesta no fue exitosa. Verificar la clave o la URL.");
                return;
            }

            // Accedo a las tasas de cambio
            JsonObject conversiones = jsonObject.getAsJsonObject("conversion_rates");

            // Lista de monedas específicas para filtrar
            String[] monedasFiltradas = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

            System.out.println("Tasas de cambio filtradas:");
            for (String moneda : monedasFiltradas) {
                if (conversiones.has(moneda)) {
                    double valor = conversiones.get(moneda).getAsDouble();
                    System.out.println(moneda + ": " + valor);
                } else {
                    System.out.println(moneda + ": no disponible");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// 7) Analizando la respuesta en formato JSON

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AnalizadorJson {
    public static void main(String[] args) {
        try {
            // Aquí va mi clave personal de la API (reemplazar por la real)
            String apiKey = "";

            // Moneda base que estoy consultando (puedo cambiarla por EUR, MXN, etc.)
            String baseCurrency = "USD";

            // Armo la URL con la clave y la moneda base
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

            // Creo el cliente HTTP que se encarga de hacer la solicitud
            HttpClient cliente = HttpClient.newHttpClient();

            // Construyo la solicitud GET para obtener los datos en formato JSON
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json") // Aseguro que quiero JSON como respuesta
                    .GET()
                    .build();

            // Envío la solicitud y guardo la respuesta como texto (String)
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            // Aquí capturo el cuerpo de la respuesta (el JSON completo como texto)
            String json = respuesta.body();

            // Analizo ese JSON convirtiéndolo en un objeto para poder acceder a sus partes
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            // Obtengo el campo "base_code" del JSON (indica la moneda base)
            String base = jsonObject.get("base_code").getAsString();
            System.out.println("Moneda base: " + base);

            // Obtengo el objeto interno "conversion_rates" que contiene todas las tasas de cambio
            JsonObject conversiones = jsonObject.getAsJsonObject("conversion_rates");

            // Arreglo de las monedas que me interesa mostrar (puedo modificar este arreglo)
            String[] monedasObjetivo = {"USD", "MXN", "EUR", "JPY"};

            System.out.println("\nTasas de cambio relevantes:");

            // Recorro las monedas que me interesan y las imprimo si están disponibles
            for (String moneda : monedasObjetivo) {
                if (conversiones.has(moneda)) {
                    double valor = conversiones.get(moneda).getAsDouble();
                    System.out.println(moneda + ": " + valor);
                } else {
                    System.out.println(moneda + ": no disponible");
                }
            }

        } catch (Exception e) {
            // En caso de que algo falle, imprimo el error para depurar
            e.printStackTrace();
        }
    }
}

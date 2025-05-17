// 9) Conversor de monedas usando tasas reales

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorMonedas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Pido al usuario la clave de origen y destino
            System.out.print("Moneda de origen (ej: USD): ");
            String monedaOrigen = scanner.nextLine().toUpperCase();

            System.out.print("Moneda de destino (ej: MXN): ");
            String monedaDestino = scanner.nextLine().toUpperCase();

            System.out.print("Monto a convertir: ");
            double monto = scanner.nextDouble();

            // Clave de la API (reemplazar por la real)
            String apiKey = "";
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaOrigen;

            // Creo el cliente HTTP
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            // Mando la solicitud y obtengo la respuesta
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            String json = respuesta.body();

            // Analizo el JSON
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            // Verifico que la respuesta sea válida
            if (!jsonObject.has("result") || !jsonObject.get("result").getAsString().equals("success")) {
                System.out.println("La API respondió con error. Verifica la moneda o la clave.");
                return;
            }

            // Accedo a las tasas de cambio
            JsonObject conversiones = jsonObject.getAsJsonObject("conversion_rates");

            // Verifico que la moneda destino exista
            if (!conversiones.has(monedaDestino)) {
                System.out.println("La moneda destino no fue encontrada en la API.");
                return;
            }

            // Obtengo la tasa de la moneda destino
            double tasaDestino = conversiones.get(monedaDestino).getAsDouble();

            // Aplico la conversión
            double resultado = monto * tasaDestino;

            // Muestro el resultado
            System.out.println("\n" + monto + " " + monedaOrigen + " = " + resultado + " " + monedaDestino);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

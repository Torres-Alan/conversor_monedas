//10) Interactuando con el usuario

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiKey = "";

        int opcion;

        do {
            System.out.println("\n== Conversor de Monedas ==");
            System.out.println("1) Dólar (USD) => Peso argentino (ARS)");
            System.out.println("2) Dólar (USD) => Real brasileño (BRL)");
            System.out.println("3) Dólar (USD) => Peso colombiano (COP)");
            System.out.println("4) Peso argentino (ARS) => Dólar (USD)");
            System.out.println("5) Salir");
            System.out.print("Elija una opción válida: ");
            opcion = scanner.nextInt();

            if (opcion == 5) {
                System.out.println("¡Gracias por usar el conversor!");
                break;
            }

            String monedaOrigen = "";
            String monedaDestino = "";

            switch (opcion) {
                case 1 -> {
                    monedaOrigen = "USD";
                    monedaDestino = "ARS";
                }
                case 2 -> {
                    monedaOrigen = "USD";
                    monedaDestino = "BRL";
                }
                case 3 -> {
                    monedaOrigen = "USD";
                    monedaDestino = "COP";
                }
                case 4 -> {
                    monedaOrigen = "ARS";
                    monedaDestino = "USD";
                }
                default -> {
                    System.out.println("Opción no válida.");
                    continue;
                }
            }

            System.out.print("Ingrese el monto a convertir: ");
            double monto = scanner.nextDouble();

            try {
                String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaOrigen;

                HttpClient cliente = HttpClient.newHttpClient();
                HttpRequest solicitud = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Accept", "application/json")
                        .GET()
                        .build();

                HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
                String json = respuesta.body();

                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

                if (!jsonObject.has("result") || !jsonObject.get("result").getAsString().equals("success")) {
                    System.out.println("Error al obtener datos de la API.");
                    continue;
                }

                JsonObject conversiones = jsonObject.getAsJsonObject("conversion_rates");

                if (!conversiones.has(monedaDestino)) {
                    System.out.println("La moneda destino no fue encontrada.");
                    continue;
                }

                double tasa = conversiones.get(monedaDestino).getAsDouble();
                double resultado = monto * tasa;

                System.out.println(monto + " " + monedaOrigen + " = " + resultado + " " + monedaDestino);

            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }

        } while (true);

        scanner.close();
    }
}

//5) Construyendo la Solicitud (HttpRequest)

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHttpPersonalizado {
    public static void main(String[] args) {
        try {
            // Clave mía de la API (debo poner la correcta aquí)
            String apiKey = "";
            String baseCurrency = "MXN"; // Aquí puedo cambiar la moneda base si quiero (por ejemplo, "EUR, MXN, USD")

            // Armo la URL según la moneda base
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

            // Creo el cliente HTTP (solo una vez por proyecto)
            HttpClient cliente = HttpClient.newHttpClient();

            // Creo la solicitud, aquí puedo agregar headers si algún día los necesito
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json") // Header opcional pero útil para asegurar que reciba JSON
                    .GET()
                    .build();

            // Mando la solicitud y obtengo la respuesta como texto plano
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            // Muestro el código HTTP para saber si todo salió bien (200 = OK)
            System.out.println("Código de estado para ''Cliente Personalizado'': " + respuesta.statusCode());

            // Muestro el contenido que recibí (JSON con tasas de cambio)
            System.out.println("Respuesta JSON para ''Cliente Personalizado'':");
            System.out.println(respuesta.body());

        } catch (Exception e) {
            // Si algo falla, imprimo el error
            e.printStackTrace();
        }
    }
}

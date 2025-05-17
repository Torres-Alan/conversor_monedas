//4) Construyendo el Cliente para Solicitudes (HttpClient)

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHttp {
    public static void main(String[] args) {
        try {
            // Clave personal de la API (hay que cambiarla por la real)
            String apiKey = "";
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

            // Creo un cliente HTTP que me permite hacer peticiones
            HttpClient cliente = HttpClient.newHttpClient();

            // Aquí preparo la solicitud que voy a mandar (tipo GET)
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Mando la solicitud y guardo la respuesta como texto (String)
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            // Imprimo el código de respuesta para ver si fue exitoso (debe ser 200)
            System.out.println("Código de estado para ''Cliente Http'': " + respuesta.statusCode());

            // Imprimo el contenido completo que recibí (debería ser un JSON)
            System.out.println("Respuesta JSON para ''Cliente Http'':");
            System.out.println(respuesta.body());

        } catch (Exception e) {
            // Si algo sale mal, muestro el error en consola
            e.printStackTrace();
        }
    }
}

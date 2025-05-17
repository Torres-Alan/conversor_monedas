//6) Construyendo la Respuesta (HttpResponse)

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHttpResponse {
    public static void main(String[] args) {
        try {
            // 🔐 Mi clave de API (reemplazar por la real)
            String apiKey = "";
            String baseCurrency = "MXN"; // Puedo cambiar la moneda base aquí
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

            // 🛰️ Creo el cliente HTTP
            HttpClient cliente = HttpClient.newHttpClient();

            // 📦 Armo la solicitud con un header para asegurar formato JSON
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            // 📬 Envío la solicitud y recibo la respuesta como texto plano
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            // ✅ Imprimo el código de estado HTTP (ej. 200 si todo bien)
            System.out.println("Código de estado HTTP para ''Cliente HttpResponse'': " + respuesta.statusCode());

            // 📄 Imprimo los headers (información adicional que devuelve el servidor)
            System.out.println("\nEncabezados de la respuesta para ''ClienteHttpResponse'':");
            respuesta.headers().map().forEach((clave, valor) -> System.out.println(clave + ": " + valor));

            // 📦 Imprimo el cuerpo de la respuesta (el JSON completo)
            System.out.println("\nCuerpo de la respuesta (JSON):");
            System.out.println(respuesta.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

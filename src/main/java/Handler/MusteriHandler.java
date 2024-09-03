package Handler;

import Model.Musteri;
import Service.MusteriService;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;

public class MusteriHandler implements HttpHandler {
    private MusteriService musteriService;
    private Gson gson;

    public MusteriHandler() {
        this.musteriService = new MusteriService();
        this.gson = new Gson();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";
        int statusCode = 200;

        switch (method) {
            case "GET":
                response = handleGetRequest(exchange);
                break;
            case "POST":
                response = handlePostRequest(exchange);
                break;
            case "PUT":
                response = handlePutRequest(exchange);
                break;
            case "DELETE":
                response = handleDeleteRequest(exchange);
                break;
            default:
                response = "Geçersiz metod";
                statusCode = 405;
                break;
        }

        exchange.sendResponseHeaders(statusCode, response.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private String handleGetRequest(HttpExchange exchange) {
        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String[] pathSegments = path.split("/");

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "musteriler".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                Musteri musteri = musteriService.getMusteriById(id);

                if (musteri != null) {
                    return gson.toJson(musteri);
                } else {
                    return "Müşteri bulunamadı";
                }
            } catch (NumberFormatException e) {
                return "Geçersiz ID formatı";
            }
        } else {
            return "Geçersiz URL";
        }
    }

    private String handlePostRequest(HttpExchange exchange) throws IOException {
        Musteri yeniMusteri = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Musteri.class);

        boolean success = musteriService.createMusteri(yeniMusteri);

        if (success) {
            return "Müşteri başarıyla eklendi";
        } else {
            exchange.sendResponseHeaders(500, 0);
            return "Müşteri eklenemedi";
        }
    }

    private String handlePutRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String[] pathSegments = path.split("/");

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "musteriler".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                Musteri guncelMusteri = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Musteri.class);
                guncelMusteri.setMusteriID(id);

                boolean success = musteriService.updateMusteri(guncelMusteri);

                if (success) {
                    return "Müşteri başarıyla güncellendi";
                } else {
                    exchange.sendResponseHeaders(500, 0);
                    return "Müşteri güncellenemedi";
                }
            } catch (NumberFormatException e) {
                return "Geçersiz ID formatı";
            }
        } else {
            return "Geçersiz URL";
        }
    }

    private String handleDeleteRequest(HttpExchange exchange) {
        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String[] pathSegments = path.split("/");

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "musteriler".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);

                boolean success = musteriService.deleteMusteri(id);

                if (success) {
                    return "Müşteri başarıyla silindi";
                } else {
                    exchange.sendResponseHeaders(500, 0);
                    return "Müşteri silinemedi";
                }
            } catch (NumberFormatException e) {
                return "Geçersiz ID formatı";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return "Geçersiz URL";
        }
    }
}

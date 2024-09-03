package Handler;

import Model.Yazar;
import Service.YazarService;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public class YazarHandler implements HttpHandler {
    private YazarService yazarService;
    private Gson gson;

    public YazarHandler() {
        this.yazarService = new YazarService();
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

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "yazarlar".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                Yazar yazar = yazarService.getYazarById(id);

                if (yazar != null) {
                    return gson.toJson(yazar);
                } else {
                    return "Yazar bulunamadı";
                }
            } catch (NumberFormatException e) {
                return "Geçersiz ID formatı";
            }
        } else {
            return "Geçersiz URL";
        }
    }

    private String handlePostRequest(HttpExchange exchange) throws IOException {
        Yazar yeniYazar = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Yazar.class);

        boolean success = yazarService.createYazar(yeniYazar);

        if (success) {
            return "Yazar başarıyla eklendi";
        } else {
            exchange.sendResponseHeaders(500, 0);
            return "Yazar eklenemedi";
        }
    }

    private String handlePutRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String[] pathSegments = path.split("/");

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "yazarlar".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                Yazar guncelYazar = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Yazar.class);
                guncelYazar.setYazarID(id);

                boolean success = yazarService.updateYazar(guncelYazar);

                if (success) {
                    return "Yazar başarıyla güncellendi";
                } else {
                    exchange.sendResponseHeaders(500, 0);
                    return "Yazar güncellenemedi";
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

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "yazarlar".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);

                boolean success = yazarService.deleteYazar(id);

                if (success) {
                    return "Yazar başarıyla silindi";
                } else {
                    exchange.sendResponseHeaders(500, 0);
                    return "Yazar silinemedi";
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

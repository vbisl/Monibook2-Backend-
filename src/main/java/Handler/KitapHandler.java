package Handler;

import Model.Kitap;
import Service.KitapService;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public class KitapHandler implements HttpHandler {
    private KitapService kitapService;
    private Gson gson;

    public KitapHandler() {
        this.kitapService = new KitapService();
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

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "kitaplar".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                Kitap kitap = kitapService.getKitapById(id);

                if (kitap != null) {
                    return gson.toJson(kitap);
                } else {
                    return "Kitap bulunamadı";
                }
            } catch (NumberFormatException e) {
                return "Geçersiz ID formatı";
            }
        } else {
            return "Geçersiz URL";
        }
    }

    private String handlePostRequest(HttpExchange exchange) throws IOException {
        Kitap yeniKitap = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Kitap.class);

        boolean success = kitapService.createKitap(yeniKitap);

        if (success) {
            return "Kitap başarıyla eklendi";
        } else {
            exchange.sendResponseHeaders(500, 0);
            return "Kitap eklenemedi";
        }
    }

    private String handlePutRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String[] pathSegments = path.split("/");

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "kitaplar".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                Kitap guncelKitap = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Kitap.class);
                guncelKitap.setKitapID(id);

                boolean success = kitapService.updateKitap(guncelKitap);

                if (success) {
                    return "Kitap başarıyla güncellendi";
                } else {
                    exchange.sendResponseHeaders(500, 0);
                    return "Kitap güncellenemedi";
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

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "kitaplar".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);

                boolean success = kitapService.deleteKitap(id);

                if (success) {
                    return "Kitap başarıyla silindi";
                } else {
                    exchange.sendResponseHeaders(500, 0);
                    return "Kitap silinemedi";
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

package Handler;

import Model.Kategori;
import Service.KategoriService;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;

public class KategoriHandler implements HttpHandler {
    private KategoriService kategoriService;
    private Gson gson;

    public KategoriHandler() {
        this.kategoriService = new KategoriService();
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

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "kategoriler".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                Kategori kategori = kategoriService.getKategoriById(id);

                if (kategori != null) {
                    return gson.toJson(kategori);
                } else {
                    return "Kategori bulunamadı";
                }
            } catch (NumberFormatException e) {
                return "Geçersiz ID formatı";
            }
        } else {
            return "Geçersiz URL";
        }
    }

    private String handlePostRequest(HttpExchange exchange) throws IOException {
        Kategori yeniKategori = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Kategori.class);

        boolean success = kategoriService.createKategori(yeniKategori);

        if (success) {
            return "Kategori başarıyla eklendi";
        } else {
            exchange.sendResponseHeaders(500, 0);
            return "Kategori eklenemedi";
        }
    }

    private String handlePutRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String[] pathSegments = path.split("/");

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "kategoriler".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                Kategori guncelKategori = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), Kategori.class);
                guncelKategori.setKategoriID(id);

                boolean success = kategoriService.updateKategori(guncelKategori);

                if (success) {
                    return "Kategori başarıyla güncellendi";
                } else {
                    exchange.sendResponseHeaders(500, 0);
                    return "Kategori güncellenemedi";
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

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "kategoriler".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                boolean success = kategoriService.deleteKategori(id);

                if (success) {
                    return "Kategori başarıyla silindi";
                } else {
                    exchange.sendResponseHeaders(500, 0);
                    return "Kategori silinemedi";
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

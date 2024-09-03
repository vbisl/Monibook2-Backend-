package Handler;

import Model.YayinEvi;
import Service.YayinEviService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class YayinEviHandler implements HttpHandler {
    private YayinEviService yayinEviService;
    private Gson gson;

    public YayinEviHandler() {
        this.yayinEviService = new YayinEviService();
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

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "yayinEvleri".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                YayinEvi yayinEvi = yayinEviService.getYayinEviById(id);
                return yayinEvi != null ? gson.toJson(yayinEvi) : "Yayın evi bulunamadı";
            } catch (NumberFormatException e) {
                return "Geçersiz ID formatı";
            }
        } else {
            return gson.toJson(yayinEviService.getAllYayinEvleri());
        }
    }

    private String handlePostRequest(HttpExchange exchange) throws IOException {
        YayinEvi yayinEvi = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), YayinEvi.class);
        boolean success = yayinEviService.createYayinEvi(yayinEvi);
        return success ? "Yayın evi eklendi" : "Yayın evi eklenemedi";
    }

    private String handlePutRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String[] pathSegments = path.split("/");

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "yayinEvleri".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                YayinEvi guncelYayinEvi = gson.fromJson(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8), YayinEvi.class);
                guncelYayinEvi.setYayinEviID(id);

                boolean success = yayinEviService.updateYayinEvi(guncelYayinEvi);

                if (success) {
                    return "Yayınevi başarıyla güncellendi";
                } else {
                    exchange.sendResponseHeaders(500, 0);
                    return "YayınEvi güncellenemedi";
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
    private String handleDeleteRequest(HttpExchange exchange) {
        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String[] pathSegments = path.split("/");

        if (pathSegments.length > 3 && "api".equals(pathSegments[1]) && "yayinEvleri".equals(pathSegments[2])) {
            try {
                int id = Integer.parseInt(pathSegments[3]);
                boolean success = yayinEviService.deleteYayinEvi(id);
                return success ? "Yayın evi silindi" : "Yayın evi silinemedi";
            } catch (NumberFormatException e) {
                return "Geçersiz ID formatı";
            }
        } else {
            return "Geçersiz URL";
        }
    }
}

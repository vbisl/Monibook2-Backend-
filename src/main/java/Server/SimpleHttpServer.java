package Server;

import Handler.*;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class SimpleHttpServer {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/api/kitaplar", new KitapHandler());
            server.createContext("/api/yazarlar", new YazarHandler());
            server.createContext("/api/yayinEvleri", new YayinEviHandler());
            server.createContext("/api/musteriler",  new MusteriHandler());
            server.createContext("/api/kategoriler", new KategoriHandler());
            // Use a custom executor for better performance
            server.setExecutor(Executors.newFixedThreadPool(10));
            server.start();
            System.out.println("Server is listening on port 8080");

            // Add shutdown hook for graceful shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down server...");
                server.stop(0);
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

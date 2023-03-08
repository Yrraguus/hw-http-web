// https://github.com/netology-code/jspr-homeworks/tree/master/01_web

package ru.netology;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final var validPaths = List.of("/index.html", "/spring.svg", "/spring.png", "/resources.html", "/styles.css", "/app.js", "/links.html", "/forms.html", "/classic.html", "/events.html", "/events.js");
        final ExecutorService service = Executors.newFixedThreadPool(64);

        try (final var serverSocket = new ServerSocket(9999)) {
            while (true) {
                final var socket = serverSocket.accept();
                {
                    service.submit(new Server(validPaths, socket));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



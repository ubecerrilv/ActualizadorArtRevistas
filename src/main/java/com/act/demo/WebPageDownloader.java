package com.act.demo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebPageDownloader {

    public static void main(String[] args) {
        // Cambia esta URL por la que necesites descargar
        String webUrl = "https://revistas.unam.mx/index.php/";
        printHtml(webUrl);
    }

    public static void printHtml(String urlString) {
        try {
            // Crear un objeto URL
            URL url = new URL(urlString);

            // Abrir una conexión HTTP a la URL
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

            // Establecer el User-Agent para simular una solicitud desde un navegador
            httpConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

            // Crear un BufferedReader para leer el InputStream desde la URL
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()))) {
                String inputLine;

                // Leer y imprimir cada línea del HTML
                while ((inputLine = reader.readLine()) != null) {
                    System.out.println(inputLine);
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("La URL es inválida: " + urlString);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al intentar leer la página: " + urlString);
            e.printStackTrace();
        }
    }
}

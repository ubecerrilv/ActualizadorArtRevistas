package com.act.demo;

import com.microsoft.playwright.*;

public class App {
    public static void main(String[] args) {
    	try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();
            page.navigate("https://example.com"); // Cambia la URL por la página que desees

            // Extraer el contenido de la etiqueta <head>
            String headContent = page.locator("html").innerHTML();
            System.out.println("Contenido de <head>:");
            System.out.println(headContent);
        }
    }
}

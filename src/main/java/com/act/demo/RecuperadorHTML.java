package com.act.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

@Service
public class RecuperadorHTML {
	
	public String obtenerHTML(String url) {
		String response = "";
		try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();

            page.navigate(url);
            
            // Extraer el contenido de la etiqueta <html>
            String contenido = page.locator("body").innerHTML();

            //System.out.println(contenido);
            
            

            // Usar un conjunto para almacenar las URLs únicas
            Set<String> urlUnicas = new AutomataRevistas().revistas(contenido, url.substring(url.indexOf("//")+2));

            // Imprimir las URLs únicas
            for (String link : urlUnicas) {
                response +=link+"\n";
            }
            
            // Cerrar el navegador
            browser.close();
            System.out.println("a");
        }

		return response;
	}
}

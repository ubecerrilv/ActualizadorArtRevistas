package com.act.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

@Service
public class RecuperadorHTML {
	
	public String obtenerHTML(String url) {
		String response = "";
		try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();
            page.navigate(url);

            // Esperar a que la página esté completamente cargada
            page.waitForLoadState(LoadState.NETWORKIDLE);
            
            // Obtener todos los enlaces de la página
            List<ElementHandle> links = page.querySelectorAll("a[href]");
            
            // Usar un conjunto para almacenar las URLs únicas
            Set<String> urlUnicas = new HashSet<>();
            
            // Iterar sobre los enlaces encontrados y agregar las URLs únicas al conjunto
            for (ElementHandle link : links) {
                String href = link.getAttribute("href");
                if (href.matches(url+"[^/]+")) {
                    urlUnicas.add(href);
                }
            }
            
            // Imprimir las URLs únicas
            for (String link : urlUnicas) {
                response +=link+"\n";
            }
            
            // Cerrar el navegador
            browser.close();
        }

		return response;
	}
}

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
		String contenido = "";
        Set<String> urlUnicas = new HashSet<String>();
		try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();
            page.navigate(url);
            
            // Extraer el contenido de la etiqueta <html>
            contenido = page.content();
            browser.close();
        }

		return contenido;
	}
}

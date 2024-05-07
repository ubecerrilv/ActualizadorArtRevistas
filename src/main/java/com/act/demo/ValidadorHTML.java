package com.act.demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.stereotype.Service;

@Service
public class ValidadorHTML {

    public boolean esOJS(String url){
        boolean res;
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();
            page.navigate(url);

            String html = page.content();

            // Verificar que sea OJS
            res = html.contains("<meta name=\"generator\" content=\"Open Journal Systems");
            browser.close();
        }

        return res;
    }
}

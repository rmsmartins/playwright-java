package org.example;

import com.microsoft.playwright.*;

public class PrimeiroTeste {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://example.com");
            System.out.println("Título da página: " + page.title());
            browser.close();
        }
    }
}

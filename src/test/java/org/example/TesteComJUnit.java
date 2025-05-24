package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteComJUnit {
    static Playwright playwright;
    static Browser browser;
    Page page;

    @BeforeAll
    static void iniciarPlaywright() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void fecharTudo() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void abrirNovaPagina() {
        page = browser.newPage();
    }

    @AfterEach
    void fecharPagina() {
        page.close();
    }

    @Test
    void tituloDaPaginaDeveSerExampleDomain() {
        page.navigate("https://example.com");
        assertEquals("Example Domain", page.title());
    }
}

package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteLoginSauceDemo {
    static Playwright playwright;
    static Browser browser;
    Page page;

    @BeforeAll
    static void iniciar() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void terminar() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void novaPagina() {
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/")));
        page = context.newPage();
    }

    @AfterEach
    void fecharPagina() {
        page.context().close();
    }

    @Test
    void deveEfetuarLoginComSucesso() {
        page.navigate("https://www.saucedemo.com/");
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
        page.click("#login-button");

        // Espera por um elemento que só aparece depois do login
        page.waitForSelector(".inventory_list");

        // Screenshot depois do login
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot-login.png")));

        // Verifica se o login teve sucesso
        assertTrue(page.isVisible(".inventory_item"), "Deve aparecer a lista de produtos após login.");
    }
}

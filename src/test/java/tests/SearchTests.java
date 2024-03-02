package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Owner("Victor Berezovsky")
@Story("Дипломный проект")
@Feature("Автоматизация тестовых сценариев Android версии Википедии")
@Severity(SeverityLevel.NORMAL)

public class SearchTests extends TestBase {

    @Test
    @DisplayName("Проверка обнаружения результатов поиска")
    void checkResultTest() {
        step("Отправляем запрос в википедии", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Java");
        });

        step("Проверяем, что обнаружены результаты поиска", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }


    @Test
    @DisplayName("Открытие первой найденной ссылки")
    void successfulSearchTest() {
        step("Отправляем запрос в википедии", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Java");
        });

        step("Нажимаем на первую найденную ссылку", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click());

        step("Проверяем получение ошибки", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldBe(visible));
    }



    @Test
    @DisplayName("Проверка наличия заголовка")
    void checkNewsHeaderTest() {
        step("Проверка наличия заголовка в новостях", () -> {
            $(id("org.wikipedia.alpha:id/view_card_header_title")).shouldHave(text("In the news"));
        });
    }

}


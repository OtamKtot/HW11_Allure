package qa.guru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

@Feature("Issue in repository")
@Owner("OtamKtot")
public class SelenideTest {
    @Test
    @DisplayName("Test on Selenide and Listener")
    public void testIssueSearch(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open ("https://github.com");

        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("OtamKtot/HW09_JUnit-5");
        $("#query-builder-test").submit();

        $(linkText("OtamKtot/HW09_JUnit-5")).click();
        $("#issues-tab").click();
        $(withText("Test")).should(Condition.exist);
    }
}

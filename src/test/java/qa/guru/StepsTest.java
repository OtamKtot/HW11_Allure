package qa.guru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Feature("Issue in repository")
@Owner("OtamKtot")
public class StepsTest {
    private static final String REPOSITORY = "OtamKtot/HW09_JUnit-5";
    private static final String ISSUE = "Test";

    @Test
    @DisplayName("Test with Lambda function in steps")
    @Story("Creating Issue")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value="AllureTesting", url="https://github.com")
    public void testLambdaStep(){
        SelenideLogger.addListener("allure",new AllureSelenide());

        step("Opening main page", () -> open("https://github.com"));
        step("Looking for repository " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Click on repository link " + REPOSITORY, () -> $(linkText(REPOSITORY)).click());
        step("Opening tab issues", () -> $("#issues-tab").click());
        step("Checking if the Issue with text exist " + ISSUE, () -> {
            $(withText(ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Test with use of @Step annotation")
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
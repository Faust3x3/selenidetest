import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import javax.naming.ldap.Control;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class WebTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void checkForm() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Нальчик");
        $("[data-test-id=date] input").doubleClick();
        String planningDate = generateDate(5);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id=name] input").setValue("Иванова Анна-Мария");
        $("[data-test-id=phone] input").setValue("+79754568201");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(visible);
    }
}
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import javax.naming.ldap.Control;
import java.time.Duration;

public class WebTest {


    @Test
    void checkForm() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Нальчик");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys("08.03.2023");
        $("[data-test-id=name] input").setValue("Иванова Анна-Мария");
        $("[data-test-id=phone] input").setValue("+79754568201");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
package Tests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.jupiter.api.Assertions;
import java.util.concurrent.TimeUnit;


public class FirstTest {

    public static WebDriver driver;

    @Test
    public void TrendTree() throws InterruptedException {

        //подготовка
        System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://127.0.0.1:8043");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;


        //раскрытие дерева выбора перьев
        WebElement Trend = driver.findElement(By.id("74002"));
        WebElement ShadowRoot1 = expandRootElement(Trend);
        WebElement Toolbar = ShadowRoot1.findElement(By.cssSelector("ms-toolbar"));
        WebElement ShadowRoot2 = expandRootElement(Toolbar);
        WebElement TreeBttn = ShadowRoot2.findElement(By.cssSelector("div[class='hmi-j-tree btn']"));
        TreeBttn.click();
        Thread.sleep(1500);

        //выделение пера
        WebElement Checkbox = (WebElement) jse.executeScript("return document.querySelector(\"body > div:nth-child(7) > ms-popup > ms-tree\").shadowRoot.querySelector(\"#Объекты\\\\.Объект\\\\ 1\\\\.Параметр\\\\ 2\")");
        Checkbox.click();
        Thread.sleep(1500);

        //Применение выбора
        WebElement Popup = driver.findElement(By.xpath("//*/div/ms-popup"));
        WebElement BttnClose = Popup.findElement(By.xpath("//*/div/button"));
        BttnClose.click();
        Thread.sleep(1500);

        //Проверка в минилегенде
        WebElement element = (WebElement) jse.executeScript("return document.querySelector(\"#\\\\37 4002\").shadowRoot.querySelector(\"#legend\").shadowRoot.querySelector(\"table > tr:nth-child(1) > td:nth-child(2)\")");
        element.click();
        Assertions.assertEquals(element.getText(), "Параметр 2");
        driver.quit();
        }

    public WebElement expandRootElement(WebElement element) {
        return (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].shadowRoot",element);
    }
    }



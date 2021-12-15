package org.example.main_test;

import com.google.common.io.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class CitilinkTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void get_driver() {
        System.setProperty("webdriver.chrome.driver", Resources.getResource("chromedriver").getPath());
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(5).toMillis());
        driver.get("https://www.citilink.ru");
    }

    @Test
    public void login() {
        driver.findElement(By.xpath(".//div[@class='HeaderMenu__buttons  HeaderMenu__buttons_user']")).click();
        driver.findElement(By.name("login")).sendKeys("test_true");
        driver.findElement(By.name("pass")).sendKeys("123456789");
    }

    @Test
    public void register() {
        driver.findElement(By.xpath(".//div[@class='HeaderMenu__buttons  HeaderMenu__buttons_user']")).click();
        driver.findElement(By.xpath(".//span[@class='AuthGroup__tab-sign-up js--AuthGroup__tab-sign-up']")).click();
        WebElement name = driver.findElement(By.name("name"));
        name.sendKeys("Nikita");
        name.click();
        driver.findElement(By.xpath("//*[@class='SignUp__phone js--SignUp__phone']/div[1]/label[1]/input[1]")).sendKeys("+79666666666");
        Assert.assertTrue(driver.findElement(By.xpath(".//button[@type='submit']")).isEnabled());
    }
    @Test
    public void CheckTultip() {
        driver.findElement(By.xpath(".//div[@class='MainHeader__search']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//a[@title='Ноутбуки']")));
        WebElement tultip = driver.findElement(By.xpath(".//a[@title='Ноутбуки']"));
        Actions action = new Actions(driver);
        action.moveToElement(tultip).build().perform();
        Assert.assertEquals(tultip.getText(),"Ноутбуки");
    }
    @Test
    public void SortTest() {
        driver.findElement(By.xpath(".//button[@data-label='Каталог товаров']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@data-title='Смартфоны']")));
        driver.findElement(By.xpath(".//div[@data-title='Смартфоны']")).click();
        WebElement min_cost = driver.findElement(By.xpath("//*[@data-meta-name='FilterListGroupsLayout']/div[2]/div[2]/input[1]"));
        min_cost.clear();
        min_cost.sendKeys("16500");
        WebElement max_cost = driver.findElement(By.xpath("//*[@data-meta-name='FilterListGroupsLayout']/div[2]/div[2]/input[2]"));
        max_cost.clear();
        max_cost.sendKeys("17500");
        max_cost.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='от 16 500 ₽ до 17 500 ₽']")));
        List <WebElement> array = driver.findElements(By.xpath("//*[@class='ProductCardCategoryList__list']"));
        int cnt = 0;
        int check = 0;
        for (WebElement webElement : array) {
            String s = webElement.getText();
            String[] arr_s = s.split("\\r?\\n");
            String price = arr_s[arr_s.length - 1];
            if (price.equals("Узнать о поступлении")) {
                cnt += 1;
            } else {
                int pr = PriceToInt(price);
                if (pr <= 17500 && pr >= 16500) {
                    check += 1;
                }
            }
        }
        Assert.assertEquals(check, array.size() - cnt);

    }
    public int PriceToInt(String s) {
        s = s.replaceAll("\\s", "");
        String ans = s.substring(0, s.length() - 1);
        return Integer.parseInt(ans);
    }
}

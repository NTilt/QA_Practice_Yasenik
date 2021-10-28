package org.example.main_test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.google.common.io.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.List;


public class CitilinkTest {
    @Test
    public void login() {
        System.setProperty("webdriver.chrome.driver", Resources.getResource("chromedriver").getPath());
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.citilink.ru");
        driver.findElement(By.xpath(".//div[@class='HeaderMenu__buttons  HeaderMenu__buttons_user']")).click();
        driver.findElement(By.name("login")).sendKeys("test_true");
        driver.findElement(By.name("pass")).sendKeys("123456789");
    }
    @Test
    public void register() {
        System.setProperty("webdriver.chrome.driver", Resources.getResource("chromedriver").getPath());
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(5).toMillis());
        driver.get("https://www.citilink.ru");
        driver.findElement(By.xpath(".//div[@class='HeaderMenu__buttons  HeaderMenu__buttons_user']")).click();
        driver.findElement(By.xpath(".//span[@class='AuthGroup__tab-sign-up js--AuthGroup__tab-sign-up']")).click();
        driver.findElement(By.name("name")).sendKeys("Nikita");
        driver.findElement(By.name("name")).click();
        driver.findElement(By.xpath(".//input[@class=' InputBox__input js--InputBox__input js--SignUp__input-phone SignUp__input-phone__container-input']")).sendKeys("+79666666666");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//input[@class=' InputBox__input js--InputBox__input js--SignUp__input-phone SignUp__input-phone__container-input']")));
        //driver.findElement(By.xpath(".//input[@class=' InputBox__input js--InputBox__input  js--SignUp__input-email__container-input']")).sendKeys("ui_test@mail.ru");
        //driver.findElement(By.name("password")).sendKeys("qweety123");
        Assert.assertFalse(driver.findElement(By.xpath(".//input[@class=' InputBox__input js--InputBox__input js--SignUp__input-phone SignUp__input-phone__container-input']")).isEnabled());
        //Assert.assertFalse(driver.findElement(By.xpath(".//button[@type='submit']")).isEnabled());
    }
    @Test
    public void CheckTultip() {
        System.setProperty("webdriver.chrome.driver", Resources.getResource("chromedriver").getPath());
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(5).toMillis());
        driver.get("https://www.citilink.ru");
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/header/div[2]/div[2]/div[1]/div/div/form/div/div[1]/div/label/input")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='header-instant-search']/div/div/div/div/div/div[2]/div[1]/div/div[2]/a[1]/div")));
        WebElement tultip = driver.findElement(By.xpath("//*[@id='header-instant-search']/div/div/div/div/div/div[2]/div[1]/div/div[2]/a[1]/div"));
        Actions action = new Actions(driver);
        action.moveToElement(tultip).build().perform();
        Assert.assertEquals(tultip.getText(),"Ноутбуки");
    }
    @Test
    public void SortTest() {
        System.setProperty("webdriver.chrome.driver", Resources.getResource("chromedriver").getPath());
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(5).toMillis());
        driver.get("https://www.citilink.ru");
        driver.findElement(By.xpath(".//button[@data-label='Каталог товаров']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@data-title='Смартфоны']")));
        driver.findElement(By.xpath(".//div[@data-title='Смартфоны']")).click();
        driver.findElement(By.xpath("//*[@id='app-filter']/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[1]")).clear();
        driver.findElement(By.xpath("//*[@id='app-filter']/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[1]")).sendKeys("16500");
        driver.findElement(By.xpath("//*[@id='app-filter']/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[2]")).clear();
        driver.findElement(By.xpath("//*[@id='app-filter']/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[2]")).sendKeys("17500");
        driver.findElement(By.xpath("//*[@id='app-filter']/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[2]")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='от 16 500 ₽ до 17 500 ₽']")));
        List <WebElement> array = driver.findElements(By.xpath(".//div[@class='product_data__gtm-js product_data__pageevents-js ProductCardHorizontal js--ProductCardInListing js--ProductCardInWishlist']"));
        int cnt = 0;
        int check = 0;
        for (int i = 0; i < array.size(); i++) {
            String s = array.get(i).getText();
            String[] arr_s = s.split("\\r?\\n");
            String price = arr_s[arr_s.length - 1];
            if (price.equals("Узнать о поступлении")){
                cnt += 1;
            }
            else {
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

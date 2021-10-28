package org.example.main_test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;

import java.security.Key;


public class test {
    @Test
    public void test() {
        assertTrue(main.luckyTicket("123321"));
    }

    @Test
    public void test1() {
        assertFalse(main.luckyTicket("123341"));
    }

    @BeforeMethod
    public void openPage() {open("https://www.citilink.ru/");}

    @Test
    public void loginTest()  {
        $("div.MainHeader__city").click();
        $(By.xpath(".//span[contains(text(),'Маркс')]")).click();
        assertEquals($("div.MainHeader__city").getText().trim(), "Маркс");
    }

    @Test
    public void clickTest()  {
        $(By.xpath(".//div[@class='MainHeader__menu']")).click();
        $(By.xpath(".//a[contains(text(),'Акции')]")).click();
        //assertEquals($("div.MainHeader__city").getText().trim(), "Маркс");
    }

    @Test
    public void click_for_login()  {
        $(By.xpath(".//div[@class='HeaderMenu__buttons  HeaderMenu__buttons_user']")).click();
        $(By.xpath(".//*[@id='authorization_popup']/div[1]/form/div[1]/div/label/input")).sendKeys("asfafafa");
        $(By.xpath(".//*[@id='authorization_popup']/div[1]/form/div[2]/div/label/input")).sendKeys("asfafafa");
        $(By.xpath(".//*[@id='authorization_popup']/div[1]/form/div[4]/div[1]/button")).click();
    }
    @Test
    public void click_for_catalog()  {
        open("https://www.citilink.ru/catalog/smartfony");
        $(By.xpath("//*[@id='app-filter']/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[1]")).clear();
        $(By.xpath("//*[@id='app-filter']/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[1]")).sendKeys("16500");
        $(By.xpath("//*[@id='app-filter']/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[2]")).clear();
        $(By.xpath("//*[@id='app-filter']/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[2]")).sendKeys("17500");
        $(By.xpath("//*[@id='app-filter']/div/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/input[2]")).sendKeys(Keys.ENTER);
        WebElement filtr = $(By.xpath(".//div[@class = 'ProductCardCategoryList__grid-container']//section[@class = 'ProductGroupList js--ProductGroupList']"));
        System.out.println(filtr.getText());
//        List <WebElement> array = filtr.findElements(By.xpath(".//div[@class='ProductCardHorizontal js--ProductCardInListing js--ProductCardInWishlist']"));
//        for (int i = 1; i < array.size(); i++) {
//            System.out.println(array.get(i).getText().trim());
//        }



    }



}

package com.example.Laboration3Cucumber;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
public class StepDefinition {
    static WebDriver driver;
    static WebElement logo;
    static WebElement start;
    static WebElement program;
    static WebElement kanal;
    static  WebElement availability;
    @Given("Chrome web driver in available")
    public void chrome_web_driver_in_available() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("start-maximized");
        options.addArguments("–no-sandbox");
        driver = new ChromeDriver(options);
    }

    @When("User visits SVTPlay")
    public void user_visits_svt_play() {
        driver.get("https://www.svtplay.se/");
    }

    @Then("Title should be {string}")
    public void title_should_be(String string) {
        String svtTitle1=  driver.getTitle();
        assertEquals("SVT Play", svtTitle1);
        driver.quit();
    }
//test 2

    @When("User accept cookies")
    public void user_accept_cookies() {
        WebElement element =  driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/button[3]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[2]/button[3]")));
        element.click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.className("sc-4f221cd2-1")));
    }
    @Then("logo should visible")
    public void logo_should_visible() {
        logo = driver.findElement(By.className("sc-31022b15-0"));
        logo.isEnabled();
        logo.isDisplayed();
    }
    @Then("Text should be {string}")
    public void text_should_be(String string) {
        String svt =logo.getText();
        assertEquals( "SVT Play logotyp", svt );
        driver.quit();
    }

    // Test 3

    @When("User Navigate to Links")
    public void user_navigate_to_links() {
        start = driver.findElement(By.cssSelector("[type=\"start\"]"));
        String start1 =start.getText();
        program = driver.findElement(By.cssSelector("[type=\"programs\"]"));
        String program1 =start.getText();
        kanal = driver.findElement(By.cssSelector("[type=\"channels\"]"));
        String kanal1 =start.getText();
    }
    @Then("Main many should be {string}, {string}, {string}")
    public void main_many_should_be(String string, String string2, String string3) {
        String start1 =start.getText();
        String program1 =program.getText();
        String kanal1 =kanal.getText();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        assertEquals("KANALER",kanal1);
        assertEquals("PROGRAM",program1);
        assertEquals("START", start1);
        driver.quit();
    }

    //Test 4 Tillgänglighet
    @Then("Availability is visible with right linktext {string}")
    public void availability_is_visible_with_right_linktext(String string) {
        WebElement linkText1 = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a/span[2]"));
        linkText1.isDisplayed();
        String text=  linkText1.getText();
        assertEquals("Tillgänglighet i SVT Play", text);
        driver.quit();
    }


    //Test 5  Tillgänglighets rubrik

    @When("User Navigate to Availability")
    public void user_navigate_to_availability() {
        availability =driver.findElement(By.cssSelector("a[href='https://kontakt.svt.se/guide/tillganglighet']"));
        availability.click();
    }
    @Then("Right contents heading should be {string}")
    public void right_contents_heading_should_be(String string) {
        WebElement h1Text = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/h1"));
        String heading=  h1Text.getText();
        assertEquals("Så arbetar SVT med tillgänglighet", heading);
        driver.quit();
    }

//Test 6

    @When("User navigate to Program")
    public void user_navigate_to_program() {
        program = driver.findElement(By.cssSelector("[type=\"programs\"]"));
        program.click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("sc-a9073dc0-0")));

    }
    @Then("It should count amount of categories  {int}")
    public void it_should_count_amount_of_categories(Integer int1) {
        List<WebElement> programList = driver.findElements(By.className("sc-a9073dc0-0"));
        assertEquals(17, programList.size());  // Antal program list ändrar i SVT play.Det är inte fast
        driver.quit();

    }

// *****************   (Ytterligare 5 olika test för SVT Play) ***************************
    //Test1: Använd sök förmular och hitta söktexter, kontrollera om det hittats rätt

    @When("User search Filmer by using search function")
    public void user_search_filmer_by_using_search_function() {
        WebElement programSearch = driver.findElement(By.cssSelector("input.sc-13b24589-0"));  //className("sc-8961da81-13"));

        programSearch.sendKeys("Filmer");
        programSearch.sendKeys(Keys.ENTER);

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("sc-da02705a-8")));
    }
    @Then("Right search categories should shows {string}")
    public void right_search_categories_should_shows(String string) {

        WebElement checkSearch = driver.findElement(By.className("sc-da02705a-8"));
        String text=   checkSearch.getText();
        assertEquals("Filmer", text);
        driver.quit();
    }

    //Test2: Navigera till Kanal och hitta "Tv-tablå"

    @When("User Navigate to Channel to find out TV tables")
    public void user_navigate_to_channel_to_find_out_tv_tables() {
        WebElement kanalLink = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/header/div[2]/div/div/nav/ul/li[3]/a"));
        kanalLink.click();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"tv-tabla\"]")));
    }
    @Then("Content findings should be {string}")
    public void content_findings_should_be(String string) {
        WebElement svt1 = driver.findElement(By.xpath("//*[@id=\"tv-tabla\"]"));
        String svat1check= svt1.getText();
        assertEquals("Tv-tablå", svat1check);
        driver.quit();
    }


    //Test3:  Kontrollera att, kan tillbacka  till Start sida från nyheter
    @When("User navigate to news page")
    public void user_navigate_to_news_page() {

        WebElement nyheter = driver.findElement(By.cssSelector("[href='/lista/svtId_jnD6vny/nyheter']"));
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[href='/lista/svtId_jnD6vny/nyheter']")));
        nyheter.click();
    }
    @Then("User aims to back start page")
    public void user_aims_to_back_start_page() {
        WebElement start =driver.findElement(By.cssSelector("[type=\"start\"]"));
        start.click();
    }
    @Then("Start page title should be {string}")
    public void start_page_title_should_be(String string) {
        String svtTitle1=  driver.getTitle();
        assertEquals("SVT Play", svtTitle1);
        driver.quit();
    }


    //Test4: Kontrollera att kan Navigera till Barn katagorier
    @When("User navigate to child category")
    public void user_navigate_to_child_category() {
        WebElement start1 =driver.findElement(By.cssSelector("[href='/kategori/barn']"));
        start1.click();
    }
    @Then("Child category should displayed & text should be {string}")
    public void child_category_should_displayed_text_should_be(String string) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.sc-6b1c5b48-1")));
        WebElement barn =driver.findElement(By.cssSelector("span.sc-6b1c5b48-1"));
        String barn1= barn.getText();
        assertEquals("BARN", barn1);
        driver.quit();
    }
// Test5: Kontrollera att svt länk fungerar

    @When("User click link svt.se")
    public void user_click_link_svt_se() {

        WebElement svt1 = driver.findElement(By.xpath("//*[@href=\"https://www.svt.se\"]"));
        svt1.click();

    }
    @Then("User Should get svt website & title should be {string}")
    public void user_should_get_svt_website_title_should_be(String string) {
        String svtTitle1=  driver.getTitle();
        assertEquals("SVT Nyheter", svtTitle1);
        driver.quit();
    }

    //last

    @Then("It should shows categories {string},{string}, {string},{string}, {string}, {string}, {string}, {string}, {string}, {string}, {string},{string}, {string}, {string}, {string}, {string}, {string}")
    public void it_should_shows_categories(String categories0, String categories1, String categories2, String categories3, String categories4, String categories5, String categories6, String categories7, String categories8, String categories9, String categories10, String categories11, String categories12, String categories13, String categories14, String categories15, String categories16) {

        List<WebElement> programList = driver.findElements(By.cssSelector("a.sc-a9073dc0-1"));

        assertAll(
                () -> assertEquals("Serier msg", categories0, programList.get(0).getText()),
                () -> assertEquals("Serier msg", categories1, programList.get(1).getText()),
                () -> assertEquals("Serier msg", categories2, programList.get(2).getText()),
                () -> assertEquals("Serier msg", categories3, programList.get(3).getText()),
                () -> assertEquals("Serier msg", categories4, programList.get(4).getText()),
                () -> assertEquals("Serier msg", categories5, programList.get(5).getText()),
                () -> assertEquals("Serier msg", categories6, programList.get(6).getText()),
                () -> assertEquals("Serier msg", categories7, programList.get(7).getText()),
                () -> assertEquals("Serier msg", categories8, programList.get(8).getText()),
                () -> assertEquals("Serier msg", categories9, programList.get(9).getText()),
                () -> assertEquals("Serier msg", categories10, programList.get(10).getText()),
                () -> assertEquals("Serier msg", categories11, programList.get(11).getText()),
                () -> assertEquals("Serier msg", categories12, programList.get(12).getText()),
                () -> assertEquals("Serier msg", categories13, programList.get(13).getText()),
                () -> assertEquals("Serier msg", categories14, programList.get(14).getText()),
                () -> assertEquals("Serier msg", categories15, programList.get(15).getText()),
                () -> assertEquals("Serier msg", categories16, programList.get(16).getText())
        );

        driver.quit();
    }

}

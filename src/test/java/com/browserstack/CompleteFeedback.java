package com.browserstack;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class CompleteFeedback extends SeleniumTest {

    @Test
    public void firstCase() {

        driver.get("https://stage.psn.cx/widget-preview?code=PSN-eh35zwa");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for up to 10 secs for the frame
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("psn-widget-button-frame")));

        // Wait for the iframeButton to be clickable and Click the iframeButton
        WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframeButton = waits.until(ExpectedConditions.elementToBeClickable(By.className("widget-button")));
        iframeButton.click();

        // Switch back to the default content (outside the iframe)
        driver.switchTo().defaultContent();

        // Switch to the iframe containing the selection (1-10) elements
        WebElement iframeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("psn-widget-content-frame")));
        driver.switchTo().frame(iframeElement);

        // Locate and click the "10" element with explicit wait
        WebElement button10 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@value='d307b836-becd-4073-9800-bab0decc4ea1']")));
        button10.click();

        WebElement smileyFaceButton = driver.findElement(By.xpath("//div[@class='button-group']//img[@alt='😃']"));
        smileyFaceButton.click();

        WebElement firstNextPageButton = driver.findElement(By.id("1f30269f-7aa9-4c9c-b087-547422a389f4"));
        firstNextPageButton.click();


        // Locate feedback textbox
        WebElement textBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("01edeca6-00da-4170-a33b-77364ed7de99")));
        // Generate a meaningful comment using Java Faker
        Faker faker = new Faker();
        String fakeComment = faker.lorem().paragraph();
        // Input the fake comment into the textbox
        textBox.sendKeys(fakeComment);

        // Click the agreement label
        WebElement agreementLabel = driver.findElement(By.className("agreement-text"));
        agreementLabel.click();

        // Locate and close agreement page
        WebElement modalBody = driver.findElement(By.className("modal-body"));
        modalBody.click();

        // Locate and click to checkbox for accept agreement
        WebElement accept = driver.findElement(By.xpath("//div[@class='button has-check']"));
        accept.click();

        // Locate and click to checkbox for choose favorite
        WebElement favorite = driver.findElement(By.cssSelector(".button__check.is-checkbox"));
        favorite.click();
        WebElement secondNextPageButton = driver.findElement(By.id("5ef0f5ea-2495-435a-9cdd-11ea7f5f93b7"));
        secondNextPageButton.click();

        // Wait for the input field to be present and clickable
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailInput = waiting.until(ExpectedConditions.presenceOfElementLocated(By.id("45be6d13-e704-4167-af03-1c6cf2d2b324")));
        // Generate a random email address
        String randomEmail = faker.internet().emailAddress();
        // Fill the input field with the random email address
        emailInput.sendKeys(randomEmail);

        // Locate the name field by ID
        WebElement nameInput = driver.findElement(By.id("08f93e5a-4b2b-4e06-8468-f32586ed3ca1"));
        // Generate a random name
        String randomName = faker.name().fullName();
        // Fill the input field with the random name
        nameInput.sendKeys(randomName);

        // Locate and click the listbox and pick-click country option
        WebElement listBox = driver.findElement(By.xpath("//div[@class='selected-flag']"));
        listBox.click();
        WebDriverWait sleeps = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement flagOption = sleeps.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-flag-key='flag_no_194']")));
        flagOption.click();

        // Generate a random phone number and fill the field with the random phone number
        String randomPhoneNumber = "535" + faker.number().digits(7);
        WebElement phoneNumberInput = driver.findElement(By.className("form-control"));
        phoneNumberInput.sendKeys(randomPhoneNumber);

        //Locate and click submit button
        WebElement submitButton = driver.findElement(By.id("4d805800-70e6-4417-8c31-63b5f9b54e99"));
        submitButton.click();

        //Validate the last message
        WebDriverWait sleep = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = sleep.until(ExpectedConditions.visibilityOfElementLocated(By.className("label")));
        assert message.isDisplayed() : "Validation message is not displayed.";




    }

}

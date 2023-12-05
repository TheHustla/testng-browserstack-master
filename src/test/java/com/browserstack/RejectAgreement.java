package com.browserstack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class RejectAgreement extends SeleniumTest {
    @Test
    public void secondCase() {
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

        // Switch to the iframe containing the nextpage button
        WebElement iframeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("psn-widget-content-frame")));
        driver.switchTo().frame(iframeElement);

        //locate and click nextpage button
        WebElement firstNextPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("1f30269f-7aa9-4c9c-b087-547422a389f4")));
        firstNextPageButton.click();

        // Reject agreement and kicked to first page
        WebDriverWait sleeps = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement reject = sleeps.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'I don')]")));
        reject.click();
        WebElement secondNextPageButton = driver.findElement(By.id("5ef0f5ea-2495-435a-9cdd-11ea7f5f93b7"));
        secondNextPageButton.click();

        // Perform assertions on the component for validation
        WebDriverWait sleep = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement scoreComponent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='component score']")));
        Assert.assertTrue(scoreComponent.isDisplayed(), "Score component is not displayed.");
    }
}

package com.proofpointassignment.proofpointlogin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvalidProofpointCommunityLogin {

    protected WebDriverWait wait;
    protected WebDriver driver;

    WebElement outputMessage;

    InvalidProofpointCommunityLogin(WebDriver driver) {
        assertNotNull(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.outputMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error > .uiOutputRichText")));

    }


    /**
     * Returns the output message as displayed in the output message bar
     * @return String outputMessage
     */
    public String getLoginOutput() {
        assertNotNull(outputMessage);
        return outputMessage.getText();
    }
}

package com.proofpointassignment.proofpointlogin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvalidProofpointCommunityLogin {

    protected WebDriverWait wait;
    protected WebDriver driver;

    @FindBy (css = ".error > .uiOutputRichText")
    WebElement outputMessage;

    InvalidProofpointCommunityLogin(WebDriver driver) {
        assertNotNull(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }


    /**
     * Returns the output message as displayed in the output message bar
     * @return String outputMessage
     */
    public String getLoginOutput() {
        assertNotNull(outputMessage);
        wait.until(ExpectedConditions.visibilityOf(outputMessage));
        return outputMessage.getText();
    }
}

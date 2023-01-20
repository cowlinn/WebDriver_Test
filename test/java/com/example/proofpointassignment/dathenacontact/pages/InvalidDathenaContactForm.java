package com.proofpointassignment.dathenacontact.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.asynchttpclient.util.Assertions.assertNotNull;

// page_url = https://www.proofpoint.com/us/contact
public class InvalidDathenaContactForm {

    protected WebDriver driver;

    protected WebDriverWait wait;

    @FindBy(css = "div.mktoErrorMsg")
    public WebElement missingElement;


    public InvalidDathenaContactForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        assertNotNull(missingElement, "Form does not contain error message for missing field!");
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(missingElement));
        return missingElement.getText();
    }
}
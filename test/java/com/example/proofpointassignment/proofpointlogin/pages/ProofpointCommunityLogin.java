package com.proofpointassignment.proofpointlogin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


// page_url = https://proofpointcommunities.force.com/community/s/login/?utm_source=navigation&utm_medium=banner&utm_campaign=public

/**
 * ProofpointCommunityLogin page encapsulates the main sign-in page.
 */
public class ProofpointCommunityLogin {
    protected WebDriver driver;

    protected WebDriverWait wait;

    @FindBy(css = "input[placeholder = 'Password']")
    protected WebElement passwordInput;

    @FindBy(className = "slds-button")
    protected WebElement loginButton;

    @FindBy(css = "input[placeholder = 'Email']")
    protected WebElement emailInput;

    public ProofpointCommunityLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder = 'Email']")));
        passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder = 'Password']")));
        loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".slds-button")));


    }



    /**
     * Fills email field with given input
     * @param email to be supplied into field
     */
    public void setEmailInput(String email) {
        assertNotNull(emailInput);
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(email);
    }

    /**
     * Fills password field with given input
     * @param password to be supplied into field
     */
    public void setPasswordInput(String password) {
        assertNotNull(passwordInput);
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);
    }

    /**
     * Presses the login button
     */
    public void pressLoginButton() {
        assertNotNull(loginButton);
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
    }

    /**
     * Triggers a sequence of events to attempt an invalid login based on credentials passed in
     * @param invalidEmail supplied invalid email
     * @param invalidPassword supplied invalid password
     * @return invalidLoginPage page object
     */
    public InvalidProofpointCommunityLogin attemptLoginSequence(String invalidEmail, String invalidPassword) {
        setEmailInput(invalidEmail);
        setPasswordInput(invalidPassword);
        pressLoginButton();
        return new InvalidProofpointCommunityLogin(driver);
    }




}
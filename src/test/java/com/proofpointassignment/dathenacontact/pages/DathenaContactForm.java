package com.proofpointassignment.dathenacontact.pages;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// page_url =  https://www.dathena.io/dathena-contact-us

public class DathenaContactForm {

    private static final String VALID_FIRST_NAME = "Bob";
    private static final String VALID_LAST_NAME = "Tan";
    private static final String VALID_COUNTRY_SELECT = "Singapore";
    private static final String VALID_EMAIL = "bobtan@example.org";
    private static final String VALID_COMPANY = "Acorn Crafts";
    private static final String VALID_TITLE = "Software Engineer";
    private static final String VALID_PHONE = "91234567";


    protected WebDriver driver;

    protected WebDriverWait wait;

    List<WebElement> requiredFields;
    
    @FindBy(id = "FirstName")
    public WebElement firstName;

    @FindBy(id = "LastName")
    public WebElement lastName;

    @FindBy(id = "Country")
    public WebElement country;

    public Select countryDropDown;


    @FindBy(css = "button")
    public WebElement submitButton;

    @FindBy(id = "Email")
    public WebElement email;

    @FindBy(id = "Company")
    public WebElement company;

    @FindBy(id = "Title")
    public WebElement jobTitle;

    @FindBy(id = "Phone")
    public WebElement phone;


    public DathenaContactForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

        List<WebElement> requiredFields = Arrays.asList(firstName, lastName, email, company, jobTitle, phone, country);
        this.requiredFields = requiredFields;
        for (WebElement element: requiredFields) {
            assertNotNull(element);
        }
        countryDropDown = new Select(country);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    /**
     * Fills in all required fields with sample data
     */
    public void fillAllRequiredFields() {
        wait.until(ExpectedConditions.visibilityOfAllElements(requiredFields)); //Defensive measure against elements not loading properly
        firstName.sendKeys(VALID_FIRST_NAME);
        lastName.sendKeys(VALID_LAST_NAME);
        email.sendKeys(VALID_EMAIL);
        company.sendKeys(VALID_COMPANY);
        jobTitle.sendKeys(VALID_TITLE);
        phone.sendKeys(VALID_PHONE);
        countryDropDown.selectByVisibleText(VALID_COUNTRY_SELECT);
    }

    /**
     * Utility function to first fill all required fields
     * Then remove the specified field in the argument
     * Guarantees that each test case has only 1 missing field at a time
     * @param missingField
     */
    public void fillAllFieldsExceptSpecified(String missingField) {
        //Initial call to fill all required fields
        fillAllRequiredFields();

        switch (missingField) {

        case "firstName":
            firstName.clear();
            return;

        case "lastName":
            lastName.clear();
            return;

        case "country":
            countryDropDown.selectByIndex(0);
            return;

        case "email":
            email.clear();
            return;

        case "company":
            company.clear();
            return;

        case "phone":
            phone.clear();
            return;

        case "job":
            jobTitle.clear();
            return;

        default:
            throw new InvalidArgumentException("Invalid field name supplied");
        }
    }
    
    public InvalidDathenaContactForm submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
        return new InvalidDathenaContactForm(driver);
    }
}
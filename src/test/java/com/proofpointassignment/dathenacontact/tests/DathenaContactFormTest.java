package com.proofpointassignment.dathenacontact.tests;

import com.proofpointassignment.dathenacontact.pages.DathenaContactForm;
import com.proofpointassignment.dathenacontact.pages.InvalidDathenaContactForm;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests contact form against specified missing fields
 */
public class DathenaContactFormTest {
    protected static WebDriver driver;
    private static final String URL = "https://www.proofpoint.com/us/contact";
    private static final String MISSING_REQUIRED_FIELD_ERROR_DEFAULT = "This field is required. ";

    private static final String MISSING_REQUIRED_EMAIL = "Must be valid email.\n" +
            "example@yourdomain.com";

    private static final String MISSING_REQUIRED_PHONE = "Must be a phone number.\n" +
            "503-555-1212";

    /**
     * Initializes webdriver instance
     * Tests are run in headless option for better performance
     */
    public DathenaContactFormTest() {
        System.setProperty("webdriver.chrome.driver", "/C://Program Files//chromedriver_win32//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    /**
     * Ensure that each test runs with a fresh browser instance
     */
    @BeforeEach
    public void setUp() {
        driver.navigate().to(URL);
    }

    /**
     * Utility function that populates DathenaContactForm with all required fields
     * except for the field specified
     * Compares the resulting error message in the error form with the one specified
     * @param specifiedField field that will be empty in the form
     * @param errorMessage expected error message
     */
    public void missing_field_generator(String specifiedField, String errorMessage) {
        DathenaContactForm contactForm = new DathenaContactForm(driver);
        contactForm.fillAllFieldsExceptSpecified(specifiedField);
        InvalidDathenaContactForm invalidContactForm = contactForm.submitForm();
        assertEquals(invalidContactForm.getErrorMessage(), errorMessage);
    }

    /**
     * Tests for missing firstName field
     */

    @Test
    public void missing_first_name_field() {
        missing_field_generator("firstName", MISSING_REQUIRED_FIELD_ERROR_DEFAULT);
    }

    /**
     * Tests for missing country field
     * Selects the first option in the dropdown (default option)
     */
    @Test
    public void missing_country_field() {
        missing_field_generator("country", MISSING_REQUIRED_FIELD_ERROR_DEFAULT);
    }

    /**
     * Tests for missing lastName field
     */
    @Test
    public void missing_last_name_field() {
        missing_field_generator("lastName", MISSING_REQUIRED_FIELD_ERROR_DEFAULT);
    }

    /**
     * Tests for missing email field
     */
    @Test
    public void missing_email_field() {
        missing_field_generator("email", MISSING_REQUIRED_EMAIL);
    }

    /**
     * Tests for missing company name field
     */
    @Test
    public void missing_company_field() {
        missing_field_generator("company", MISSING_REQUIRED_FIELD_ERROR_DEFAULT);
    }

    /**
     * Tests for missing job title field
     */
    @Test
    public void missing_job_title_field() {
        missing_field_generator("job", MISSING_REQUIRED_FIELD_ERROR_DEFAULT);
    }

    /**
     * Tests for missing phone number field
     */
    @Test
    public void missing_phone_number_field() {
        missing_field_generator("phone", MISSING_REQUIRED_PHONE);
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }
}

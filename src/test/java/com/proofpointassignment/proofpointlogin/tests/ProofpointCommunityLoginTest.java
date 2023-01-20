package com.proofpointassignment.proofpointlogin.tests;

import com.proofpointassignment.proofpointlogin.pages.InvalidProofpointCommunityLogin;
import com.proofpointassignment.proofpointlogin.pages.ProofpointCommunityLogin;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProofpointCommunityLoginTest {
    protected static WebDriver driver;
    public static final String URL = "https://proofpointcommunities.force.com/community/s/login/?utm_source=navigation&utm_medium=banner&utm_campaign=public";
    public static final String INVALID_CREDENTIALS_EXPECTED_MESSAGE = "Your login attempt has failed. Make sure the username and password are correct.";

    /**
     * Initializes webdriver instance
     * Tests are run in headless option for better performance
     */
    public ProofpointCommunityLoginTest() {
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
     * Negative test for invalid login credentials
     * Should test fail, dump relevant details
     */
    @Test
    public void invalidLogin() {
        ProofpointCommunityLogin loginPage = new ProofpointCommunityLogin(driver);
        InvalidProofpointCommunityLogin invalidLoginPage = loginPage.attemptLoginSequence("random characters", "password");
        assertEquals(invalidLoginPage.getLoginOutput(), INVALID_CREDENTIALS_EXPECTED_MESSAGE);
        //Initially, I wanted to find a way to dump more specific error messages (i.e: HTTP response codes, network payload data etc.)
        //However, with reference to the Page Object Model, it might not be the best practice to do so as this is information the user is usually not concerned with
        // Essentially, I will only assert "correctness" of the Acceptance Test based on what the user can see from the browser
        // https://www.selenium.dev/documentation/test_practices/discouraged/http_response_codes/
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }

}

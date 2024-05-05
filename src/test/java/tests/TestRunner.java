package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pages.HomePage;

public abstract class TestRunner {

    private static final String BASE_URL = "https://demo.opencart.com/";
    private static final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private static final Long IMPLICITLY_WAIT_MILISECONDS = 3000L;
    private static WebDriver driver;
    protected boolean isTestSuccessful = true;
    protected static Logger logger = LogManager.getLogger(TestRunner.class);

    private void takeScreenShot(String testname)  {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_" + testname + "_screenshot.png"));
        } catch (IOException e) {
            //TODO add logging
        }
    }

    private void takePageSource(String testname) {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = driver.getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_" + testname + "_source.html");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
    }

    @BeforeAll
    public static void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(IMPLICITLY_WAIT_MILISECONDS)); // 0 by default
        logger.info("@BeforeAll executed");
    }

    @BeforeEach
    public void setupThis(TestInfo testInfo) {
        isTestSuccessful = true;
        driver.get(BASE_URL);
        logger.info("@BeforeEach executed");
		logger.info("Executing test " + testInfo.getDisplayName());
    }
    
    @AfterEach
    public void tearThis(TestInfo testInfo) throws InterruptedException {
        if (!isTestSuccessful) {
            takeScreenShot(testInfo.getDisplayName());
            takePageSource(testInfo.getDisplayName());
            logger.error("Test " + testInfo.getDisplayName() + "is failed");
        }
        else{
            logger.info("Test " + testInfo.getDisplayName() + "is completed");
        }
        logger.info("@AfterEach executed");
    }
    
    @AfterAll
    public static void tear() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("@AfterAll executed");
    }
    
    protected static HomePage loadApplication() {
        driver.get(BASE_URL);
        return new HomePage(driver);
    }
}
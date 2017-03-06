import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ReporterType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Created by surendra.gupta on 27-02-2017.
 */
public class NewRmsTraining  {
//extends mvnforreports
    public WebDriver driver  = null;
    public String sWebUrl  = "http://rav-vm-vdi-065/RMSTraining/";
    public String stopic = "fun";
    public String sdate, ddate = "28/02/2017";
    public String dcomments = "Are you done?", scomments;
    public String dpresenter = "Surendra Gupta", spresenter;

    //Declaration for xtent report
    // Extent reports and test variable
    public static ExtentReports extent; //extent represents extent report
    public static ExtentTest extest; //represents test for which report would be generated

    // Report html created under /reports/*
    private static long millis = System.currentTimeMillis();
    public static String reportLocation = "reports/detailTestReport_" + millis + ".html";

    @BeforeTest
    public void setup() throws Exception
    {
//
        System.setProperty("webdriver.chrome.driver", "D:\\salenium\\Trining\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver.get(sWebUrl);
        driver.navigate().to(sWebUrl);

        //Initialize extend reports
        extent = new ExtentReports(reportLocation, true);
        extent.startReporter(ReporterType.DB, (new File(reportLocation)).getParent() + File.separator + "extent.db");
        ///extent.db file is required for generating extent report
        extest= extent.startTest("MyFirstTest"); //name your test

        //Printing Cookies
        System.out.println("Printing Cookies");
        Set<Cookie> cookiesList =  driver.manage().getCookies();
        for(Cookie getcookies :cookiesList) {
            System.out.println(getcookies );
        }
        driver.manage().window().maximize();

    }



    public void getscreenshot(String partfname) throws Exception
    {

       // System.out.println("In screenshot");
        long millis = System.currentTimeMillis();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
        FileUtils.copyFile(scrFile, new File("D:\\salenium\\Screens\\screenshot" + partfname + millis +".png"));
    }



    @Test(priority = 0)
    public void pagetittlecheck() throws Exception{
        String Cname = "PageTittle";

        // clicked on Training Tab
        Thread.sleep(4000);
        Actions action = new Actions(driver);
        //focusing mouse on main menu

        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"divMenu\"]/ul/li[2]"))).build().perform();
        Thread.sleep(5000);
        WebElement element=(new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"divMenu\"]/ul/li[2]/ul/li[1]/a")));
        element.click();

       /*  ************************Trying New way to open submenu
        driver.findElement(By.xpath("//*[@id=\"divMenu\"]/ul/li[2]")).click();
        // clicked on raise Training request
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"divMenu\"]/ul/li[2]/ul/li[1]/a")).click();
        Thread.sleep(4000);
        **************************************** */


        //Clicked on Training type
        driver.findElement(By.xpath("//*[@id=\"TrainingType\"]")).click();
        Thread.sleep(4000);

        // Now checking the title of the page
        //Just declaring the temp variable later I will take from excel data sheet
        String temp = "learn with fun";
        // Fetching the page tittle
        String pagetittle = driver.getTitle();
        // Now comparing for test case
        if(pagetittle.equalsIgnoreCase(temp) ){
            extest.log(LogStatus.PASS,"Tittle Pass ");
            System.out.println("Tittle Pass ");
            getscreenshot(Cname);
        } else{
            extest.log(LogStatus.FAIL,"Tittle Failed");
            System.out.println("Tittle Fail ");
            getscreenshot(Cname);
        }
        extent.flush();  //To output the result to the report
        extent.endTest(extest); //To end the test


        //------------------------------------------------------------------------------------
        //-------------- commenting the code as others have done the code --------------------

/*
        // Selecting Knowledg sharing to get the new page loaded
        Select dropdown = new Select(driver.findElement(By.id("TrainingType")));
        dropdown.selectByVisibleText("Knowledge Sharing Session");
        Thread.sleep(4000);
        //just clicking outside
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[2]/div[2]/form/div/div[2]/div[3]/div/div/div[1]/label")).click();

        //Selecting type from dropdown
        driver.findElement(By.xpath("//*[@id=\"Type\"]")).click();
        Thread.sleep(4000);
        dropdown = new Select(driver.findElement(By.id("Type")));
        dropdown.selectByVisibleText("Testing");
        Thread.sleep(4000);
        //just clicking outside
        driver.findElement(By.xpath("//*[@id=\"divGrid\"]/div[1]/div/div[1]/div[1]/label")).click();
        Thread.sleep(4000);
        //Entering the date
        //dateBox.sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//*[@id=\"Date\"]")).click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
        for (WebElement cell: columns) {
            //Select 13th Date
            if (cell.getText().equals("14")) {
                cell.findElement(By.linkText("14")).click();
                break;
            }
        }
        */

        /*
        WebElement month = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/span[1]"));
        Select monthCombo = new Select(month);
        monthCombo.selectByVisibleText("March");

        WebElement year = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/span[2]"));
        Select yearCombo = new Select(year);
        yearCombo.selectByVisibleText("2017");

        element.findElement(By.linkText("31"));  */

        //Entering Agenda, Comments, Topic
/*
        driver.findElement(By.xpath("//*[@id=\"Agenda\"]")).sendKeys("Agenda Test ");
        driver.findElement(By.xpath("//*[@id=\"Comments\"]")).sendKeys("Comments Test ");
        driver.findElement(By.xpath("//*[@id=\"Topic\"]")).sendKeys("Comments Test ");


        //Entering presenter
        driver.findElement(By.xpath("//*[@id=\"check\"]/img")).click();
        driver.findElement(By.xpath("//*[@id=\"viewreport_filter\"]/label/input")).sendKeys("Surendra Gupta");
        driver.findElement(By.name("Surendra Gupta")).click();
        driver.findElement(By.xpath("//*[@id=\"BtnSecondSelect\"]")).click();

        //Submitting the main form
        // Temporary not submitting the record
        //driver.findElement(By.id("BtnSubmit"));

        //Back to home screen
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"divMenu\"]/ul/li[1]")).click();
        ////*[@id="divMenu"]/ul/li[1]

       */
    }
    @Test(priority = 1)
    public void ttypelistcheck() throws Exception{
        String Cname = "ttypelistcheck";

        // Selecting Knowledg sharing to get the new page loaded
        Select dropdown = new Select(driver.findElement(By.id("TrainingType")));
        dropdown.selectByVisibleText("Knowledge Sharing Session");
        Thread.sleep(4000);
        //just clicking outside
        driver.findElement(By.xpath("//*[@id=\"body\"]/div[2]/div[2]/form/div/div[2]/div[3]/div/div/div[1]/label")).click();

        //Cheking the Training Type
        boolean isDisplayedStatus, isEnabledStatus;
        WebElement webelementTrainingType = driver.findElement(By.xpath("//*[@id='TrainingType']"));
        isDisplayedStatus = webelementTrainingType.isDisplayed();
        isEnabledStatus = webelementTrainingType.isEnabled();

        System.out.println("Training Type Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
        if(isDisplayedStatus == false || isEnabledStatus == false)
        {
            extest.log(LogStatus.FAIL,"Training Type Failed");
            System.out.println("Training Type fail ");
            getscreenshot(Cname);

        }
        else
        {
            extest.log(LogStatus.PASS,"Training Type Pass ");
            System.out.println("Training Type pass ");
            getscreenshot(Cname);
        }
        extent.flush();  //To output the result to the report

        // Checking the type
        WebElement webelementTopic = driver.findElement(By.xpath("//*[@id='Topic']"));
        isDisplayedStatus = webelementTopic.isDisplayed();
        isEnabledStatus = webelementTopic.isEnabled();

        System.out.println("Topic Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
        if(isDisplayedStatus == false || isEnabledStatus == false)
        {
            extest.log(LogStatus.FAIL,"Topic Field Failed");
            System.out.println("Topic Field fail ");
            getscreenshot(Cname);

        }
        else
        {
            extest.log(LogStatus.PASS,"Topic Field Pass ");
            System.out.println("Topic Field pass ");
            getscreenshot(Cname);
        }

        extent.flush();  //To output the result to the report
        //Checking Agenda
        WebElement webelementAgenda = driver.findElement(By.xpath("//*[@id='Agenda']"));
        isDisplayedStatus = webelementAgenda.isDisplayed();
        isEnabledStatus = webelementAgenda.isEnabled();

        System.out.println("Agenda Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
        if(isDisplayedStatus == false || isEnabledStatus == false)
        {
            extest.log(LogStatus.FAIL,"Agenda Field Failed");
            System.out.println("Agenda Field fail ");
            getscreenshot(Cname);

        }
        else
        {
            extest.log(LogStatus.PASS,"Agenda Field Pass ");
            System.out.println("Agenda Field pass ");
            getscreenshot(Cname);
        }

        extent.flush();  //To output the result to the report

        //*[@id="Presenter"]
        WebElement webelementPresenter = driver.findElement(By.xpath("//*[@id='Presenter']"));
        isDisplayedStatus = webelementPresenter.isDisplayed();
        isEnabledStatus = webelementPresenter.isEnabled();

        System.out.println("Presenter Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
        if(isDisplayedStatus == false || isEnabledStatus == false)
        {
            extest.log(LogStatus.FAIL,"Presenter Field Failed");
            System.out.println("Presenter Field fail ");
            getscreenshot(Cname);

        }
        else
        {
            extest.log(LogStatus.PASS,"Presenter Field Pass ");
            System.out.println("Presenter Field pass ");
            getscreenshot(Cname);
        }

        extent.flush();  //To output the result to the report

        //*[@id="Date"]
        WebElement webelementDate = driver.findElement(By.xpath("//*[@id='Date']"));
        isDisplayedStatus = webelementDate.isDisplayed();
        isEnabledStatus = webelementDate.isEnabled();

        System.out.println("Date Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
        if(isDisplayedStatus == false || isEnabledStatus == false)
        {
            extest.log(LogStatus.FAIL,"Date Field Failed");
            System.out.println("Date Field fail ");
            getscreenshot(Cname);

        }
        else
        {
            extest.log(LogStatus.PASS,"Date Field Pass ");
            System.out.println("Date Field pass ");
            getscreenshot(Cname);
        }

        extent.flush();  //To output the result to the report
        extent.endTest(extest); //To end the test

    }


    @Test(priority = 2)
    public void ksstypecheck() throws Exception{
        String Cname = "ksstypecheck";

        //Selecting type from dropdown
        driver.findElement(By.xpath("//*[@id=\"Type\"]")).click();
        Thread.sleep(4000);
        Select dropdown = new Select(driver.findElement(By.id("Type")));
        dropdown.selectByVisibleText("Testing");
        Thread.sleep(4000);
        //just clicking outside
        driver.findElement(By.xpath("//*[@id=\"divGrid\"]/div[1]/div/div[1]/div[1]/label")).click();
        Thread.sleep(4000);

        WebElement mySelectElement = driver.findElement(By.id("Type"));
        dropdown= new Select(mySelectElement);
        String typevalue = dropdown.getFirstSelectedOption().getText();

        // Temporary value
        String temp = "Testing";
        System.out.println("Currently set dropdown value: " + typevalue);
        if(typevalue.equalsIgnoreCase(temp))
        {
            extest.log(LogStatus.PASS, "Type Pass ");
            System.out.println("Type Pass ");
            getscreenshot(Cname);
        }
        else
        {
            extest.log(LogStatus.FAIL,"Request raised Failed");
            System.out.println("Request raised fail ");
            getscreenshot(Cname);
        }
        extent.flush();  //To output the result to the report
        extent.endTest(extest); //To end the test



    }
    @Test(priority = 3)
    public void topiccheck() throws Exception{

        String Cname = "ttypelistcheck";
        boolean isDisplayedStatus, isEnabledStatus;
        Thread.sleep(2000);
        WebElement webelementTopic = driver.findElement(By.xpath("//*[@id='Topic']"));
        isDisplayedStatus = webelementTopic.isDisplayed();
        isEnabledStatus = webelementTopic.isEnabled();

        System.out.println("Topic Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
        /*if(isDisplayedStatus == true && isEnabledStatus == true)
        {
            extest.log(LogStatus.FAIL,"Date Field Failed");
            System.out.println("Date Field fail ");
            getscreenshot(Cname);

        } */
        if(isDisplayedStatus == false || isEnabledStatus == false)
        {
            extest.log(LogStatus.FAIL,"Topic Field Failed");
            System.out.println("Topic Field fail ");
            getscreenshot(Cname);

        }
        else
        {
            extest.log(LogStatus.PASS,"Topic Field Pass ");
            System.out.println("Topic Field pass ");
            getscreenshot(Cname);
            // Later we will Enter from here as the case is passed
        }

        extent.flush();  //To output the result to the report
        extent.endTest(extest); //To end the test



    }


    @Test(priority=4)
    //validate Agenda field
    public void agendacheck() throws Exception
    {
        driver.findElement(By.cssSelector("#Agenda")).sendKeys("Test agenda");
        Thread.sleep(1000);
    }

    @Test(priority=5)
    public void presentercheck() throws Exception
    {
        //Presenter should be selected
        driver.findElement(By.xpath("//*[@id='check']/img")).click();
        driver.findElement(By.xpath("//*[@id='viewreport_filter']/label/input")).sendKeys("p1581");
        driver.findElement(By.xpath("//*[@id='Monty_Jagwani']")).click();
        driver.findElement(By.xpath("//*[@id='BtnSecondSelect']")).click();
        Thread.sleep(1000);
    }



    @Test(priority=6)
    public void mdatecheck() throws Exception
    {
        //Date selection
        driver.findElement(By.xpath(".//*[@id='Date']")).click();
        WebElement StartdateWidget = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[2]/a"));
        driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[2]/a")).click();
        StartdateWidget.findElements(By.tagName("tr"));
        Thread.sleep(1000);
    }




    @Test(priority=7)
    public void submit() throws Exception
    {

        // Entering Random Value to fill all the fields
        driver.findElement(By.xpath("//*[@id=\"Comments\"]")).sendKeys("Comments Test ");
        driver.findElement(By.xpath("//*[@id=\"Topic\"]")).sendKeys("Comments Test ");


        // Click on Submit
        driver.findElement(By.id("BtnSubmit")).submit();
        Thread.sleep(3000);
        driver.navigate().to("http://rav-vm-vdi-065/RMSTraining");
        //driver.findElement(By.xpath("//*[@id='BtnSubmit']")).click();
        //Thread.sleep(2000);


    }


    ///--------------------------------------------------------------------------
    //-------- Code By Surendra -------------------------------------------------


    @Test(priority = 8)
    public void raisecheck() throws Exception{
        String Cname = "raisecheck";

        // clicked on Training Tab
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"divMenu\"]/ul/li[2]")).click();
        Thread.sleep(4000);
        // clicked on View Training request
        driver.findElement(By.xpath("//*[@id=\"divMenu\"]/ul/li[2]/ul/li[2]/a")).click();
        Thread.sleep(4000);
        // clicked on Knowledge sharing session radio button
        driver.findElement(By.xpath("//*[@class=\"nav-div\"]/span[4]/input")).click();
        Thread.sleep(4000);
        //entered search text to search my entered topic
        driver.findElement(By.xpath("//*[@id=\"viewreport_filter\"]/label/input")).sendKeys(stopic);
        Thread.sleep(4000);




        //Now checking if the raised training request is present
        String topicvalue = driver.findElement(By.xpath("//*[@id=\"tblBody\"]/tr/td[2]")).getText();
        Thread.sleep(4000);
        //Just declaring the temp variable later I will take from excel data sheet
        String temp = "learn with fun";
        // Now comparing for test case
        if(topicvalue.equalsIgnoreCase(temp) ){
            extest.log(LogStatus.PASS,"Request raised Pass ");
            System.out.println("Request Raised is pass ");
            getscreenshot(Cname);
        } else{
            extest.log(LogStatus.FAIL,"Request raised Failed");
            System.out.println("Request raised fail ");
            getscreenshot(Cname);
        }
        extent.flush();  //To output the result to the report
        extent.endTest(extest); //To end the test


        //

    }

    @Test(priority = 9)
    public void ttypecheck() throws Exception {
        String Cname = "ttypecheck";
        //clicked on View eye image
        driver.findElement(By.xpath("//*[@id=\"tblBody\"]/tr/td[7]/a/img")).click();
        Thread.sleep(4000);

        //Now checking if the training type is present
        WebElement mySelectElement = driver.findElement(By.id("TrainingType"));
        Select dropdown= new Select(mySelectElement);
        String ttypevalue = dropdown.getFirstSelectedOption().getText();

        //String ttypevalue = driver.findElement(By.xpath("//*[@id=\"TrainingType\"]")).getText();
        Thread.sleep(4000);
        //Just declaring the temp variable later I will take from excel data sheet
        String temp = "Knowledge Sharing Session";
        // Now comparing for test case
        if (ttypevalue.equalsIgnoreCase(temp)) {
            extest.log(LogStatus.PASS, "Training Type Pass ");
            System.out.println("Training Type Pass ");
            getscreenshot(Cname);
        } else {
            extest.log(LogStatus.FAIL, "Training type Failed");
            System.out.println("Training type Failed");
            getscreenshot(Cname);
        }
        extent.flush();  //To output the result to the report
        extent.endTest(extest); //To end the test

    }

        @Test(priority = 10)
        public void typecheck() throws Exception{

            String Cname = "Typecheck";

            //Now checking if the type is present
           // String typevalue = driver.findElement(By.xpath("//*[@id=\"Type\"]")).getText();
            WebElement mySelectElement = driver.findElement(By.id("Type"));
            Select dropdown= new Select(mySelectElement);
            String typevalue = dropdown.getFirstSelectedOption().getText();

            Thread.sleep(4000);
            //Just declaring the temp variable later I will take from excel data sheet
            String temp = "Testing";
            // Now comparing for test case
            if (typevalue.equalsIgnoreCase(temp)) {
                extest.log(LogStatus.PASS, "Type Pass ");
                System.out.println("Type Pass ");
                getscreenshot(Cname);
            } else {
                extest.log(LogStatus.FAIL, "Type Failed");
                System.out.println("Type Failed");
                getscreenshot(Cname);
            }
            extent.flush();  //To output the result to the report
            extent.endTest(extest); //To end the test


    }

        @Test(priority = 11)
        public void agendscheck() throws Exception{

            String Cname = "Agendacheck";
            //Now checking if the training type is present
            String agendavalue = driver.findElement(By.xpath("//*[@id=\"Agenda\"]")).getText();
            Thread.sleep(4000);
            //Just declaring the temp variable later I will take from excel data sheet
            String temp = "learn with fun";
            // Now comparing for test case
            if (agendavalue.equalsIgnoreCase(temp)) {
                extest.log(LogStatus.PASS, "Agenda Pass ");
                System.out.println("Agenda Pass ");
                getscreenshot(Cname);
            } else {
                extest.log(LogStatus.FAIL, "Agenda Failed");
                System.out.println("Agenda Failed");
                getscreenshot(Cname);
            }
            extent.flush();  //To output the result to the report
            extent.endTest(extest); //To end the test

        }


    @Test(priority = 12)
    public void datecheck() throws Exception{
        //public static void main(String[] args) throws InterruptedException {
        String Cname = "datecheck";

       /* ********************* Temporary closing as other partner absent (only closing for priority 1)
       // clicked on Training Tab
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"divMenu\"]/ul/li[2]")).click();
        Thread.sleep(4000);
        // clicked on View Training request
        driver.findElement(By.xpath("//*[@id=\"divMenu\"]/ul/li[2]/ul/li[2]/a")).click();
        Thread.sleep(4000);
        // clicked on Knowledge sharing session radio button
        driver.findElement(By.xpath("//*[@class=\"nav-div\"]/span[4]/input")).click();
        Thread.sleep(4000);
        //entered search text to search my entered topic
        driver.findElement(By.xpath("//*[@id=\"viewreport_filter\"]/label/input")).sendKeys(stopic);
        Thread.sleep(4000);
        ************************************************* */

        /* ********************* Temporary closing as other partner absent (only closing for priority 2)
        //clicked on View eye image
        driver.findElement(By.xpath("//*[@id=\"tblBody\"]/tr/td[7]/a/img")).click();
        Thread.sleep(4000);
        ************************************************************* */


        //Selected value of Date field
        WebElement element = driver.findElement(By.xpath("//*[@id=\"Date\"]"));
        sdate = element.getAttribute("value");
        Thread.sleep(4000);
        //Matching the selected date and date enterd on raising the request
        if(sdate.equalsIgnoreCase(ddate) ){
            extest.log(LogStatus.PASS,"Date Pass ");
            System.out.println("Date is pass ");
            getscreenshot(Cname);
        } else{
            extest.log(LogStatus.FAIL,"Date Fail");
            System.out.println("Date is fail ");
            getscreenshot(Cname);
        }
        extent.flush();  //To output the result to the report
        extent.endTest(extest); //To end the test
    }


        @Test(priority = 13)
    public void Commentscheck() throws Exception{
       // **********************
        //Copy paste the above code if discreate code is required later
        //**********************
            String Cname = "commentcheck";
        Thread.sleep(4000);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"Comments\"]"));
        scomments = element.getAttribute("value");

        //Matching the selected comments and comments enterd on raising the request
        if(scomments.equalsIgnoreCase(dcomments) ){
            extest.log(LogStatus.PASS,"Comments Pass ");
            System.out.println("Comments is pass ");
            getscreenshot(Cname);
        } else{
            extest.log(LogStatus.FAIL,"Comments Failed");
            System.out.println("Comments is fail ");
        }
        extent.flush();  //To output the result to the report
        extent.endTest(extest); //To end the test


    }
    @Test(priority = 14)
    public void Presentercheck() throws Exception {
        // **********************
        //Copy paste the above code if discreate code is required later
        //**********************
        String Cname = "Presentercheck";
        Thread.sleep(4000);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"Presenter\"]"));
        spresenter = element.getAttribute("value");

        //Matching the selected comments and comments enterd on raising the request
        if(spresenter.equalsIgnoreCase(dpresenter) ){
            extest.log(LogStatus.PASS,"Presenter Pass ");
            System.out.println("Presenter is pass ");
            getscreenshot(Cname);
        } else{
            extest.log(LogStatus.FAIL,"Presenter Failed");
            System.out.println("Presenter is fail ");
        }
        extent.flush();  //To output the result to the report
        extent.endTest(extest); //To end the test

    }


    @AfterTest
    public void setclose() throws Exception {
        //Close the browser
         driver.quit();

    }

}


/* Code By Vipul---------------------------------------------------
//------------------------------------------------------------------

package RMSTraningMavenProject.RMSTraningMavenProjectArtifact;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;

        import org.apache.poi.ss.usermodel.Cell;
        import org.apache.poi.xssf.usermodel.XSSFCell;
        import org.apache.poi.xssf.usermodel.XSSFRow;
        import org.apache.poi.xssf.usermodel.XSSFSheet;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.interactions.Actions;
        import org.openqa.selenium.support.ui.Select;
        import org.testng.annotations.AfterTest;
        import org.testng.annotations.BeforeTest;
        import org.testng.annotations.Test;
        import com.relevantcodes.extentreports.ExtentReports;
        import com.relevantcodes.extentreports.ExtentTest;
        import com.relevantcodes.extentreports.IExtentTestClass;
        import com.relevantcodes.extentreports.LogStatus;
        import com.relevantcodes.extentreports.ReporterType;

public class RunTest
{
    public static ExtentReports extent; //extent represents extent report
    public static ExtentTest extest; //represents test for which report would be generated
    private static long millis = System.currentTimeMillis();
    public static String reportLocation = "reports/detailTestReport_" + millis + ".html";
    public String TestCase1ExecutionStatus = "Not Run";
    public String TestCase2ExecutionStatus = "Not Run";
    public String TestCase3ExecutionStatus = "Not Run";
    public String TestCase4ExecutionStatus = "Not Run";
    public String TestCase1FailureReason = "";
    public String TestCase2FailureReason = "";
    public String TestCase3FailureReason = "";
    public String TestCase4FailureReason = "";
    public WebDriver driver = null;
    public static String trainingType = "Testing";


    @BeforeTest
    public void preExecution() throws InterruptedException, IOException
    {
        System.out.println("Started Pre Test Execution Activities - preExecution Function");

        //Initialize extend reports
        extent = new ExtentReports(reportLocation, true);
        extent.startReporter(ReporterType.DB, (new File(reportLocation)).getParent() + File.separator + "extent.db"); ///extent.db file is required for generating extent report
        //name your test

    }

    @Test(priority = 0)
    public void main0() throws Exception
    {
        //System.out.println("Started Test Execution Activities - testExecution Function");

        FileInputStream fileInputStream = new FileInputStream("D://BloodSpot_250117//RMSTrainingApplication//Test_Cases.xlsx");
        XSSFWorkbook testCaseWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet testCasesSheet = testCaseWorkbook.getSheet("Test_Cases");
        XSSFSheet testDataSheet = testCaseWorkbook.getSheet("Test_Data");

        int testCasesCount = testCasesSheet.getLastRowNum();
        int testDataCount = testDataSheet.getLastRowNum();

        for(int rowNo = 1; rowNo <= testCasesCount; rowNo++)
        {
            XSSFRow testCaseRow = testCasesSheet.getRow(rowNo);
            XSSFCell testDataCell = testCaseRow.getCell(0);
            testDataCell.setCellType(Cell.CELL_TYPE_STRING);
            String testCaseID = testDataCell.getStringCellValue();
            if(testCaseID.equals("TC_001"))
            {
                System.out.println("Started Execution for " + testCaseID);

                for(int testDataRowNo = 1; testDataRowNo<=testDataCount; testDataRowNo++ )
                {
                    XSSFRow testDataRow = testDataSheet.getRow(testDataRowNo);
                    testDataCell = testDataRow.getCell(0);
                    testDataCell.setCellType(Cell.CELL_TYPE_STRING);
                    String testDataSheetID = testDataCell.getStringCellValue();

                    if(testDataSheetID.equals(testCaseID))
                    {
                        testDataCell = testDataRow.getCell(1);
                        testDataCell.setCellType(Cell.CELL_TYPE_STRING);
                        String testDatasetID = testDataCell.getStringCellValue();
                        System.out.println("Started Execution for " + testDatasetID);

                        System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Automation\\chromedriver.exe");
                        driver = new ChromeDriver();
                        String websiteURL = testDataRow.getCell(2).toString();
                        driver.get(websiteURL);
                        driver.manage().window().maximize();

                        if(driver.getTitle().equals("TrainingPlan Training Module"))
                        {
                            System.out.println("Test Case " + testCaseID + " PASSED for TestDataSet " + testDatasetID);
                            TestCase1ExecutionStatus = "Pass";
                        }
                        else
                        {
                            System.out.println(testCaseID + " Failed for TestDataSet " + testDatasetID + "Actual Page Title = " + driver.getTitle());
                            TestCase1FailureReason = TestCase1FailureReason + testCaseID + " Failed for TestDataSet " + testDatasetID + "Actual Page Title = " + driver.getTitle() + "\n";
                            TestCase1ExecutionStatus = "Fail";
                        }

                        Thread.sleep(5000);

                        driver.close();
                    }

                }
            }

        }

    }

    @Test(priority = 1)
    public void main1() throws Exception
    {
        //System.out.println("Started Test Execution Activities - testExecution Function");

        FileInputStream fileInputStream = new FileInputStream("D://BloodSpot_250117//RMSTrainingApplication//Test_Cases.xlsx");
        XSSFWorkbook testCaseWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet testCasesSheet = testCaseWorkbook.getSheet("Test_Cases");
        XSSFSheet testDataSheet = testCaseWorkbook.getSheet("Test_Data");

        int testCasesCount = testCasesSheet.getLastRowNum();
        int testDataCount = testDataSheet.getLastRowNum();

        for(int rowNo = 1; rowNo <= testCasesCount; rowNo++)
        {
            XSSFRow testCaseRow = testCasesSheet.getRow(rowNo);
            XSSFCell testDataCell = testCaseRow.getCell(0);
            testDataCell.setCellType(Cell.CELL_TYPE_STRING);
            String testCaseID = testDataCell.getStringCellValue();
            XSSFCell testDataCell1 = testCaseRow.getCell(1);
            testDataCell1.setCellType(Cell.CELL_TYPE_STRING);
            String testCaseDescription = testDataCell1.getStringCellValue();
            if(testCaseID.equals("TC_002"))
            {
                extest= extent.startTest(testCaseID + " Test Description: " + testCaseDescription);
                System.out.println("Started Execution for " + testCaseID + " Test Condition: " + testCaseDescription);

                for(int testDataRowNo = 1; testDataRowNo<=testDataCount; testDataRowNo++ )
                {
                    XSSFRow testDataRow = testDataSheet.getRow(testDataRowNo);
                    testDataCell = testDataRow.getCell(0);
                    testDataCell.setCellType(Cell.CELL_TYPE_STRING);
                    String testDataSheetID = testDataCell.getStringCellValue();

                    if(testDataSheetID.equals(testCaseID))
                    {
                        testDataCell = testDataRow.getCell(1);
                        testDataCell.setCellType(Cell.CELL_TYPE_STRING);
                        String testDatasetID = testDataCell.getStringCellValue();
                        System.out.println("Started Execution for " + testDatasetID);

                        System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Automation\\chromedriver.exe");
                        driver = new ChromeDriver();
                        String websiteURL = testDataRow.getCell(2).toString();
                        driver.get(websiteURL);
                        driver.manage().window().maximize();

                        Actions action= new Actions(driver);

                        WebElement menuObject = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]")); //Mouse hover Training menu
                        action.moveToElement(menuObject).build().perform();
                        Thread.sleep(1000);

                        WebElement subMenuObject = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]/ul/li[1]")); //Click on Raise Training Request submenu
                        action.moveToElement(subMenuObject).build().perform();
                        action.click().build().perform();

                        Thread.sleep(8000);
                        //Test Case 2
                        boolean isDisplayedStatus, isEnabledStatus;
                        WebElement webelementTrainingType = driver.findElement(By.xpath("//*[@id='TrainingType']"));
                        isDisplayedStatus = webelementTrainingType.isDisplayed();
                        isEnabledStatus = webelementTrainingType.isEnabled();

                        System.out.println("Training Type Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
                        if(isDisplayedStatus == false || isEnabledStatus == false)
                        {
                            TestCase2ExecutionStatus = "Fail";
                            TestCase2FailureReason = TestCase2FailureReason + "Failed for TestDataSet: " + testDatasetID + " Training Type Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus + "\n";
                        }
                        else
                        {
                            TestCase2ExecutionStatus = "Pass";
                        }

                        Select dropdownTrainingType = new Select(webelementTrainingType);
                        dropdownTrainingType.selectByVisibleText("Knowledge Sharing Session");
                        Thread.sleep(7000);

                        WebElement webelementTopic = driver.findElement(By.xpath("//*[@id='Topic']"));
                        isDisplayedStatus = webelementTopic.isDisplayed();
                        isEnabledStatus = webelementTopic.isEnabled();

                        System.out.println("Topic Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
                        if(isDisplayedStatus == false || isEnabledStatus == false)
                        {
                            TestCase2ExecutionStatus = "Fail";
                            TestCase2FailureReason = TestCase2FailureReason + "Failed for TestDataSet: " + testDatasetID + " Topic Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus + "\n";
                        }
                        else
                        {
                            TestCase2ExecutionStatus = "Pass";
                        }

                        WebElement webelementAgenda = driver.findElement(By.xpath("//*[@id='Agenda']"));
                        isDisplayedStatus = webelementAgenda.isDisplayed();
                        isEnabledStatus = webelementAgenda.isEnabled();

                        System.out.println("Agenda Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
                        if(isDisplayedStatus == false || isEnabledStatus == false)
                        {
                            TestCase2ExecutionStatus = "Fail";
                            TestCase2FailureReason = TestCase2FailureReason + "Failed for TestDataSet: " + testDatasetID + " Agenda Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus + "\n";
                        }
                        else
                        {
                            TestCase2ExecutionStatus = "Pass";
                        }

                        //*[@id="Presenter"]
                        WebElement webelementPresenter = driver.findElement(By.xpath("//*[@id='Presenter']"));
                        isDisplayedStatus = webelementPresenter.isDisplayed();
                        isEnabledStatus = webelementPresenter.isEnabled();

                        System.out.println("Presenter Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
                        if(isDisplayedStatus == false || isEnabledStatus == false)
                        {
                            TestCase2ExecutionStatus = "Fail";
                            TestCase2FailureReason = TestCase2FailureReason + "Failed for TestDataSet: " + testDatasetID + " Presenter Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus + "\n";
                        }
                        else
                        {
                            TestCase2ExecutionStatus = "Pass";
                        }

                        //*[@id="Date"]
                        WebElement webelementDate = driver.findElement(By.xpath("//*[@id='Date']"));
                        isDisplayedStatus = webelementDate.isDisplayed();
                        isEnabledStatus = webelementDate.isEnabled();

                        System.out.println("Date Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
                        if(isDisplayedStatus == false || isEnabledStatus == false)
                        {
                            TestCase2ExecutionStatus = "Fail";
                            TestCase2FailureReason = TestCase2FailureReason + "Failed for TestDataSet: " + testDatasetID + " Date Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus + "\n";
                        }
                        else
                        {
                            TestCase2ExecutionStatus = "Pass";
                        }

                        driver.close();
                    }

                }
            }

        }

    }

    @Test(priority = 2)
    public void main2() throws Exception
    {
        //System.out.println("Started Test Execution Activities - testExecution Function");

        FileInputStream fileInputStream = new FileInputStream("D://BloodSpot_250117//RMSTrainingApplication//Test_Cases.xlsx");
        XSSFWorkbook testCaseWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet testCasesSheet = testCaseWorkbook.getSheet("Test_Cases");
        XSSFSheet testDataSheet = testCaseWorkbook.getSheet("Test_Data");

        int testCasesCount = testCasesSheet.getLastRowNum();
        int testDataCount = testDataSheet.getLastRowNum();

        for(int rowNo = 1; rowNo <= testCasesCount; rowNo++)
        {
            XSSFRow testCaseRow = testCasesSheet.getRow(rowNo);
            XSSFCell testDataCell = testCaseRow.getCell(0);
            testDataCell.setCellType(Cell.CELL_TYPE_STRING);
            String testCaseID = testDataCell.getStringCellValue();
            XSSFCell testDataCell1 = testCaseRow.getCell(1);
            testDataCell1.setCellType(Cell.CELL_TYPE_STRING);
            String testCaseDescription = testDataCell1.getStringCellValue();
            if(testCaseID.equals("TC_003"))
            {
                extest= extent.startTest(testCaseID + " Test Description: " + testCaseDescription);
                System.out.println("Started Execution for " + testCaseID + " Test Condition: " + testCaseDescription);

                for(int testDataRowNo = 1; testDataRowNo<=testDataCount; testDataRowNo++ )
                {
                    XSSFRow testDataRow = testDataSheet.getRow(testDataRowNo);
                    testDataCell = testDataRow.getCell(0);
                    testDataCell.setCellType(Cell.CELL_TYPE_STRING);
                    String testDataSheetID = testDataCell.getStringCellValue();

                    if(testDataSheetID.equals(testCaseID))
                    {
                        testDataCell = testDataRow.getCell(1);
                        testDataCell.setCellType(Cell.CELL_TYPE_STRING);
                        String testDatasetID = testDataCell.getStringCellValue();
                        System.out.println("Started Execution for " + testDatasetID);

                        System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Automation\\chromedriver.exe");
                        driver = new ChromeDriver();
                        String websiteURL = testDataRow.getCell(2).toString();
                        String trainingTypeDropdownValue = testDataRow.getCell(3).toString();
                        String typeDropdownValue = testDataRow.getCell(4).toString();
                        driver.get(websiteURL);
                        driver.manage().window().maximize();

                        Actions action= new Actions(driver);

                        WebElement menuObject = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]")); //Mouse hover Training menu
                        action.moveToElement(menuObject).build().perform();
                        Thread.sleep(1000);

                        WebElement subMenuObject = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]/ul/li[1]")); //Click on Raise Training Request submenu
                        action.moveToElement(subMenuObject).build().perform();
                        action.click().build().perform();

                        Thread.sleep(10000);
                        //Test Case 3
                        boolean isDisplayedStatus, isEnabledStatus;
                        WebElement webelementTrainingType = driver.findElement(By.xpath("//*[@id='TrainingType']"));
                        isDisplayedStatus = webelementTrainingType.isDisplayed();
                        isEnabledStatus = webelementTrainingType.isEnabled();

                        System.out.println("Training Type Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
                        if(isDisplayedStatus == false || isEnabledStatus == true)
                        {
                            TestCase3ExecutionStatus = "Fail";
                            TestCase3FailureReason = TestCase3FailureReason + "Failed for TestDataSet: " + testDatasetID + " Training Type Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus + "\n";
                        }

                        Select dropdownTrainingType = new Select(webelementTrainingType);
                        dropdownTrainingType.selectByVisibleText(trainingTypeDropdownValue);
                        Thread.sleep(4000);

                        WebElement webelementType = driver.findElement(By.xpath("//*[@id='Type']"));
                        isDisplayedStatus = webelementType.isDisplayed();
                        isEnabledStatus = webelementType.isEnabled();

                        System.out.println("Type Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
                        if(isDisplayedStatus == false || isEnabledStatus == false)
                        {
                            TestCase3ExecutionStatus = "Fail";
                            TestCase3FailureReason = TestCase3FailureReason + "Failed for TestDataSet: " + testDatasetID + " Type Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus + "\n";
                        }
                        if(isDisplayedStatus == true && isEnabledStatus == true)
                        {
                            Select dropdownType = new Select(webelementType);
                            dropdownType.selectByVisibleText(typeDropdownValue);
                            WebElement selectedValue = dropdownType.getFirstSelectedOption();
                            System.out.println("Currently set dropdown value: " + selectedValue.getText());
                            if(selectedValue.getText().equals(typeDropdownValue))
                            {
                                TestCase3ExecutionStatus = "Pass";
                            }
                            else
                            {
                                TestCase3ExecutionStatus = "Fail";
                                TestCase3FailureReason = TestCase3FailureReason + "Currently set Type dropdown value = " + selectedValue.getText() + "\n";
                            }
                        }


                        driver.close();
                    }

                }
            }

        }

    }

    @Test(priority = 3)
    public void main3() throws Exception
    {
        //System.out.println("Started Test Execution Activities - testExecution Function");

        FileInputStream fileInputStream = new FileInputStream("D://BloodSpot_250117//RMSTrainingApplication//Test_Cases.xlsx");
        XSSFWorkbook testCaseWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet testCasesSheet = testCaseWorkbook.getSheet("Test_Cases");
        XSSFSheet testDataSheet = testCaseWorkbook.getSheet("Test_Data");

        int testCasesCount = testCasesSheet.getLastRowNum();
        int testDataCount = testDataSheet.getLastRowNum();

        for(int rowNo = 1; rowNo <= testCasesCount; rowNo++)
        {
            XSSFRow testCaseRow = testCasesSheet.getRow(rowNo);
            XSSFCell testDataCell = testCaseRow.getCell(0);
            testDataCell.setCellType(Cell.CELL_TYPE_STRING);
            String testCaseID = testDataCell.getStringCellValue();
            XSSFCell testDataCell1 = testCaseRow.getCell(1);
            testDataCell1.setCellType(Cell.CELL_TYPE_STRING);
            String testCaseDescription = testDataCell1.getStringCellValue();
            if(testCaseID.equals("TC_004"))
            {
                extest= extent.startTest(testCaseID + " Test Description: " + testCaseDescription);
                System.out.println("Started Execution for " + testCaseID + " Test Condition: " + testCaseDescription);

                for(int testDataRowNo = 1; testDataRowNo<=testDataCount; testDataRowNo++ )
                {
                    XSSFRow testDataRow = testDataSheet.getRow(testDataRowNo);
                    testDataCell = testDataRow.getCell(0);
                    testDataCell.setCellType(Cell.CELL_TYPE_STRING);
                    String testDataSheetID = testDataCell.getStringCellValue();

                    if(testDataSheetID.equals(testCaseID))
                    {
                        testDataCell = testDataRow.getCell(1);
                        testDataCell.setCellType(Cell.CELL_TYPE_STRING);
                        String testDatasetID = testDataCell.getStringCellValue();
                        System.out.println("Started Execution for " + testDatasetID);

                        System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Automation\\chromedriver.exe");
                        driver = new ChromeDriver();
                        String websiteURL = testDataRow.getCell(2).toString();
                        String trainingTypeDropdownValue = testDataRow.getCell(3).toString();
                        String typeDropdownValue = testDataRow.getCell(4).toString();
                        String topicValue = testDataRow.getCell(5).toString();
                        driver.get(websiteURL);
                        driver.manage().window().maximize();

                        Actions action= new Actions(driver);

                        WebElement menuObject = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]")); //Mouse hover Training menu
                        action.moveToElement(menuObject).build().perform();
                        Thread.sleep(1000);

                        WebElement subMenuObject = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]/ul/li[1]")); //Click on Raise Training Request submenu
                        action.moveToElement(subMenuObject).build().perform();
                        action.click().build().perform();

                        Thread.sleep(10000);
                        //Test Case 4
                        boolean isDisplayedStatus, isEnabledStatus;
                        WebElement webelementTrainingType = driver.findElement(By.xpath("//*[@id='TrainingType']"));
                        isDisplayedStatus = webelementTrainingType.isDisplayed();
                        isEnabledStatus = webelementTrainingType.isEnabled();

                        System.out.println("Training Type Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);

                        Select dropdownTrainingType = new Select(webelementTrainingType);
                        dropdownTrainingType.selectByVisibleText(trainingTypeDropdownValue);
                        Thread.sleep(7000);

                        WebElement webelementType = driver.findElement(By.xpath("//*[@id='Type']"));
                        isDisplayedStatus = webelementType.isDisplayed();
                        isEnabledStatus = webelementType.isEnabled();

                        System.out.println("Type Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
                        if(isDisplayedStatus == false || isEnabledStatus == false)
                        {
                            TestCase4ExecutionStatus = "Fail";
                            TestCase4FailureReason = TestCase4FailureReason + "Failed for TestDataSet: " + testDatasetID + " Type Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus + "\n";
                        }
                        Thread.sleep(2000);
                        if(isDisplayedStatus == true && isEnabledStatus == true)
                        {
                            Select dropdownType = new Select(webelementType);
                            dropdownType.selectByVisibleText(typeDropdownValue);
                            //WebElement selectedValue = dropdownType.getFirstSelectedOption();
                            System.out.println("Currently set dropdown value: " + dropdownType.getFirstSelectedOption().getText() + " Expected Value: " + trainingType);
                            //if(       (dropdownType.getFirstSelectedOption().getText()).equals("Testing")  )
                            if (trainingType.equals(dropdownType.getFirstSelectedOption().getText().toString() )  )
                            {
                                TestCase4ExecutionStatus = "Pass";
                            }
                            else
                            {
                                System.out.println(trainingType);
                                System.out.println(dropdownType.getFirstSelectedOption().getText().toString());
                                TestCase4ExecutionStatus = "Fail";
                                //TestCase4FailureReason = TestCase4FailureReason + "Failed for TestDataSet: " + testDatasetID + "Currently set Type dropdown value = " + selectedValue.getText() + "\n";
                                TestCase4FailureReason = TestCase4FailureReason + "Failed for TestDataSet: " + testDatasetID + "Currently set Type dropdown value = " + dropdownType.getFirstSelectedOption().getText() + "\n";
                            }
                        }
                        Thread.sleep(2000);
                        WebElement webelementTopic = driver.findElement(By.xpath("//*[@id='Topic']"));
                        isDisplayedStatus = webelementTopic.isDisplayed();
                        isEnabledStatus = webelementTopic.isEnabled();

                        System.out.println("Topic Field: -" +  "Displayed " + isDisplayedStatus + " -- Enabled " + isEnabledStatus);
                        if(isDisplayedStatus == true && isEnabledStatus == true)
                        {
                            webelementTopic.sendKeys(topicValue);
                            System.out.println("Entered Topic Value: " + webelementTopic.getText() + " Expected Topic Value: " + topicValue);
							/*if(webelementTopic.getText().equals(topicValue))
							{
								TestCase4ExecutionStatus = "Pass";
							}
							else
							{
								TestCase4ExecutionStatus = "Fail";
								TestCase4FailureReason = TestCase4FailureReason + "Currently set Topic field value = " + webelementTopic.getText().toString() + "\n";
							}*/
/*
                        }

                        driver.close();
                    }

                }
            }

        }

    }

    @AfterTest
    public void postExecution()
    {
        System.out.println("Started Post Test Execution Activities - postExecution Function");


        if(TestCase1ExecutionStatus.equals("Fail"))
        {
            extest.log(LogStatus.FAIL, "Test Case 1 Failed" + "\n" + "Failure Reason: " + TestCase1FailureReason);
        }
        else if(TestCase1ExecutionStatus.equalsIgnoreCase("Pass"))
        {
            extest.log(LogStatus.PASS, "Test Case 1 Passed");
        }
        else
        {
            extest.log(LogStatus.SKIP, "Test Case 1 - Not Executed");
        }
        if(TestCase2ExecutionStatus.equals("Fail"))
        {
            extest.log(LogStatus.FAIL, "Test Case 2 Failed" + "\n" + "Failure Reason: " + TestCase2FailureReason);
        }
        else if(TestCase2ExecutionStatus.equalsIgnoreCase("Pass"))
        {
            extest.log(LogStatus.PASS, "Test Case 2 Passed");
        }
        else
        {
            extest.log(LogStatus.SKIP, "Test Case 2 - Not Executed");
        }
        if(TestCase3ExecutionStatus.equals("Fail"))
        {
            extest.log(LogStatus.FAIL, "Test Case 3 Failed" + "\n" + "Failure Reason: " + TestCase3FailureReason);
        }
        else if(TestCase3ExecutionStatus.equalsIgnoreCase("Pass"))
        {
            extest.log(LogStatus.PASS, "Test Case 3 Passed");
        }
        else
        {
            extest.log(LogStatus.SKIP, "Test Case 3 - Not Executed");
        }

        if(TestCase4ExecutionStatus.equals("Fail"))
        {
            extest.log(LogStatus.FAIL, "Test Case 4 Failed" + "\n" + "Failure Reason: " + TestCase4FailureReason);
        }
        else if(TestCase4ExecutionStatus.equalsIgnoreCase("Pass"))
        {
            extest.log(LogStatus.PASS, "Test Case 4 Passed");
        }
        else
        {
            extest.log(LogStatus.SKIP, "Test Case 4 - Not Executed");
        }

        extent.flush();  //To output the result to the report
        extent.endTest(extest); //To end the test
    }
}
*/

//---------------------------------------------------------------------------------------
//-------- Code by Monty ----------------------------------------------------------------
/*
package Assignment1;
//import java.util.concurrent.TimeUnit;

import java.awt.List;
import java.sql.Date;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Assignment1 {

	private static final int priority = 0;
	private String URL1 = "http://rav-vm-vdi-065/RMSTraining/";
	WebDriver driver = null;
	WebElement element;

	@BeforeTest
	public void beforeMethod()throws Exception
	{

		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL1);

	}


	@Test
	public void main()throws Exception{

	   	// Click on Training Tab
	   	Actions actions = new Actions(driver);
	   	WebElement mainMenu = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]"));
	    actions.moveToElement(mainMenu);
	    actions.click().build().perform();

	  //Click on View Training Request Sub-Tab
	    driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]/ul/li[1]/a")).click();



	 // Selecting Knowledge sharing to get the new page loaded
	    Select dropdown = new Select(driver.findElement(By.id("TrainingType")));
	    dropdown.selectByVisibleText("Knowledge Sharing Session");
	    Thread.sleep(4000);



	    // Selecting Type to get the new page loaded
		Select dropdownTrainingType = new Select(driver.findElement(By.id("Type")));
		dropdownTrainingType.selectByVisibleText("Testing");
		Thread.sleep(4000);
	}


		@Test(priority=1)
		//validate Agenda field
		public void agenda() throws Exception
		{
		driver.findElement(By.cssSelector("#Agenda")).sendKeys("Test agenda");
		Thread.sleep(1000);
		}

		 @Test(priority=2)
		public void presenter() throws Exception
		{
		//Presenter should be selected
	     driver.findElement(By.xpath("//*[@id='check']/img")).click();
	     driver.findElement(By.xpath("//*[@id='viewreport_filter']/label/input")).sendKeys("p1581");
	     driver.findElement(By.xpath("//*[@id='Monty_Jagwani']")).click();
	     driver.findElement(By.xpath("//*[@id='BtnSecondSelect']")).click();
	     Thread.sleep(1000);
		}



		 @Test(priority=3)
		 public void date() throws Exception
		 {
			//Date selection
		     driver.findElement(By.xpath(".//*[@id='Date']")).click();
		     WebElement StartdateWidget = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[2]/a"));
		     driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[2]/a")).click();
		     StartdateWidget.findElements(By.tagName("tr"));
		     Thread.sleep(1000);
		 }


		// Validate Topic Field
	    /* driver.findElement(By.cssSelector("#Topic")).sendKeys("Test Topic");
	     Thread.sleep(1000);*/



/*
    @Test(priority=4)
    public void submit() throws Exception
    {
        // Click on Submit
        driver.findElement(By.id("BtnSubmit")).submit();
        Thread.sleep(3000);
        driver.navigate().to("http://rav-vm-vdi-065/RMSTraining");
        //driver.findElement(By.xpath("//*[@id='BtnSubmit']")).click();
        //Thread.sleep(2000);
    }



    @AfterTest

    public void closedriver() throws Exception
    {

        //Closing the browser
        driver.close();
    }


}
 */

/*
//---------------------------------------------------------------------------------------
//-------- Code By Anand ----------------------------------------------------------------

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ReporterType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InterruptedIOException;

/**
 * Created by Saurav Anand on 2/24/2017.


@Test
public class AppTest {

    /**
     * Created by Saurav Anand on 2/24/2017.
     */
    //@org.testng.annotations.Test
/*

    WebDriver driver = null;
    WebElement element;

    public static ExtentReports extent; //extent represents extent report
    public static ExtentTest extest; //represents test for which report would be generated

    // Report html created under /reports/*
    private static long millis = System.currentTimeMillis();
    public static String reportLocation = "reports/detailTestReport_" + millis + ".html";


    //Open RMS website
    @Test
    public void SetUp () throws InterruptedException {
        //Initialise the browser
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://rav-vm-vdi-065/RMSTraining/");   /* 1. open Rave Technologies*/
/*        Thread.sleep(5000);

        //Initialize extend reports
        extent = new ExtentReports(reportLocation, true);
        extent.startReporter(ReporterType.DB, (new File(reportLocation)).getParent() + File.separator + "extent.db"); ///extent.db file is required for generating extent report


    }
    //2) Click on training button and Raise Training Request
    @Test(priority=1)

    public void Run () throws InterruptedIOException, InterruptedException {
        extest= extent.startTest("My_Maven_test"); //name your test
        //Click on the training button
        element = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]"));
        element.click();
        Thread.sleep(5000);


        if(element.getText().equalsIgnoreCase("View Training Request") ){
            extest.log(LogStatus.PASS,"pass "+element.getText());
            element = driver.findElement(By.xpath("//*[@id='divMenu']/ul/li[2]/ul/li[2]")); // Click View Training request
            element.click();
            extent.flush();  //To output the result to the report
            extent.endTest(extest); //To end the test

        } else{
            extest.log(LogStatus.FAIL,"This is not the correct page");
        }

        Thread.sleep(6000);


    }


    //*[@id="category"]

    @Test(priority=2)
    public void SelectKSSRadiobutton() {
        //Click on the KSS
        element=driver.findElement(By.xpath("//*[@value='1210']"));
        element.click();

    }

    @Test(priority=3)

    public void SelectAndClickTopic() {

        //Enter the search criteria for training to be viewed

        element=driver.findElement(By.xpath("//*[@id='viewreport_filter']/label/input"));
        element.click();
        element.sendKeys("HV Topic");



    }

    //@AfterTest
    //public void Close() {
    // driver.close();
    // }


}

 */
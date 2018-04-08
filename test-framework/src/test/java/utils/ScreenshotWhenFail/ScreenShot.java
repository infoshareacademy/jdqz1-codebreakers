package utils.ScreenshotWhenFail;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;

public class ScreenShot extends TestWatcher {

    private static String watchedLog;

    protected WebDriver driver ;

    @Rule
    public final TestRule watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            watchedLog += description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n";

            try {
                take(driver, "/home/miloszwozniak/projects/jdqz1-codebreakers/test-framework/src/test" +
                        "/resources" +
                        "/test" +
                        ".png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    /**

     * This function will take screenshot

     * @param webdriver

     * @param fileWithPath

     * @throws Exception

     */

    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        File DestFile=new File(fileWithPath);

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);

    }
}
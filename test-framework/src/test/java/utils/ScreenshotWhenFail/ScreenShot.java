package utils.ScreenshotWhenFail;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;

public class ScreenShot extends TestWatcher {

    protected WebDriver driver;

    public ScreenShot (WebDriver driver){
        this.driver = driver;
    }

        @Override
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            try {
                this.takeSnapShot("/home/miloszwozniak/projects/jdqz1-codebreakers/test-framework/src/test" +
                        "/resources" +
                        "/" + description.getMethodName() +
                        ".png");
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }



    /**

     * This function will take screenshot

     * @param webdriver

     * @param fileWithPath

     * @throws Exception

     */

    public void takeSnapShot(String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        File DestFile=new File(fileWithPath);

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);

    }
}
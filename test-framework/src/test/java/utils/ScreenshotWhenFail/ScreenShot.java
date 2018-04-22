package utils.ScreenshotWhenFail;

import org.apache.commons.io.FileUtils;
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

    public void takeSnapShot(String fileWithPath) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by vadimzubovich on 4/22/2016.
 */
public class FileUploadTest extends TestBase {

    @Test
    public void uploadTest() throws Exception {

        driver.get("http://the-internet.herokuapp.com/upload");
        WebElement upload = driver.findElement(By.id("file-upload"));
        upload.sendKeys("your/path/here.file");
        driver.findElement(By.id("file-submit")).click();
        //make assertions here
        driver.quit();
    }
}

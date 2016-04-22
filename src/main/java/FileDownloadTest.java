import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by vadimzubovich on 4/22/2016.
 */
public class FileDownloadTest extends TestBase {

    @Test
    public void robotDownloadTest() throws AWTException, InterruptedException {

        Robot robot = new Robot();

        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector("a[href*='some-file.txt']")).click();

        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(5000);
    }

    @Test
    public void httpRequestFileDownload() throws IOException {
        driver.get("http://the-internet.herokuapp.com/download");
        String link = driver.findElement(By.cssSelector("a[href*='some-file.txt']")).getAttribute("href");

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpHead request = new HttpHead(link);
        HttpResponse response = httpClient.execute(request);
        String contentType = response.getFirstHeader("Content-Type").getValue();
        int contentLength = Integer.parseInt(response.getFirstHeader("Content-Length").getValue());

        assertEquals(contentType, "application/octet-stream"); //you can indicate any MIME type here
        assertNotEquals(contentLength, 0);
    }
}

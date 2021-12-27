package DockerValidation.SeveralImages;

import DockerValidation.Utils.DockerManagement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChromeTest {
    DockerManagement dockerManagement = new DockerManagement();

    @BeforeTest
    public void startDocker() throws IOException, InterruptedException {
        dockerManagement.start();
    }

    @AfterTest
    public void stopDocker() throws IOException, InterruptedException {
        dockerManagement.stop();
    }

    @Test
    public void chromeTest() throws MalformedURLException {
        URL localMachine = new URL("http://localhost:4444/wd/hub");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(localMachine, desiredCapabilities);

        remoteWebDriver.get("http://google.com");
        System.out.println(remoteWebDriver.getTitle());
        remoteWebDriver.close();
    }
}

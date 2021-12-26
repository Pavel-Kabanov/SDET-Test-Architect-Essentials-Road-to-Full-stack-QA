package DockerValidation.SeveralImages;

import DockerValidation.Utils.StartDocker;
import DockerValidation.Utils.StopDocker;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChromeTest {
    @BeforeTest
    public void startDocker() throws IOException, InterruptedException {
        File fileToDelete = new File("docker/log/docker.log");
        fileToDelete.delete();
        System.out.println("Log file successfully deleted");

        StartDocker startDocker = new StartDocker();
        startDocker.startFile();
    }

    @AfterTest
    public void stopDocker() throws IOException {
        StopDocker stopDocker = new StopDocker();
        stopDocker.stopFile();
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

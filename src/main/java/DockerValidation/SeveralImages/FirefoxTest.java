package DockerValidation.SeveralImages;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxTest {
    @Test
    public void firefoxTest() throws MalformedURLException {
        URL localMachine = new URL("http://localhost:4444/wd/hub");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(localMachine, desiredCapabilities);

        remoteWebDriver.get("http://gmail.com");
        System.out.println(remoteWebDriver.getTitle());
        remoteWebDriver.close();
    }
}

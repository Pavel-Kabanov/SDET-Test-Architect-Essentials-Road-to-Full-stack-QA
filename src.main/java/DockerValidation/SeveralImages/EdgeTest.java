package DockerValidation.SeveralImages;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class EdgeTest {
    public static void main(String[] args) throws MalformedURLException {
        URL localMachine = new URL("http://localhost:4444/wd/hub");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("edge");
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(localMachine, desiredCapabilities);

        remoteWebDriver.get("http://gismeteo.ru");
        System.out.println(remoteWebDriver.getTitle());
    }
}

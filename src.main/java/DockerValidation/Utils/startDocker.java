package DockerValidation.Utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class startDocker {
    @Test
    public void startFile() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start docker\\dockerUp.bat");

        String dockerLogFile = "docker/log/docker.log";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(dockerLogFile));
        String currentLine = bufferedReader.readLine();
        int counter = 0;
        boolean flag = false;

        while (currentLine != null) {
            if (currentLine.contains("from DOWN to UP")) {
                counter++;
                System.out.println(counter + " line.");
            }
            if (counter == 3) {
                flag = true;
                break;
            }
            currentLine = bufferedReader.readLine();
        }
        bufferedReader.close();
        Assert.assertTrue(flag);
        Thread.sleep(3000);

    }
}

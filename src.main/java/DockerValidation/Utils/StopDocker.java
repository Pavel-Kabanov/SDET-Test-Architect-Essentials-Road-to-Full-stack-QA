package DockerValidation.Utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StopDocker {
    public void stopFile() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start docker\\dockerDown.bat"); // Starting Docker
        String dockerLogFile = "docker/log/docker.log";
        int counter = 0;
        boolean flag = false;

        while (!flag) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dockerLogFile));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                if (currentLine.contains("Shutdown complete")) {
                    counter++;
                    System.out.println(counter + " line.");
                }
                if (counter == 7) {
                    flag = true;
                    break;
                }
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        Assert.assertTrue(flag);
//        File fileToDelete = new File("docker/log/docker.log");
//        Assert.assertTrue(fileToDelete.delete());
//        System.out.println("Log file successfully deleted");
    }
}

package DockerValidation.Utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class startDocker {
    @Test
    public void startFile() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start docker\\dockerUp.bat");

        String dockerLogFile = "docker/log/docker.log";
        new File(dockerLogFile);
        int counter = 0;
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 45);
        long stopNow = calendar.getTimeInMillis();

        while (System.currentTimeMillis() < stopNow) {
            if (flag) break;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dockerLogFile));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null && !flag) {
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
        }
        Assert.assertTrue(flag);
        Thread.sleep(3000);

    }
}

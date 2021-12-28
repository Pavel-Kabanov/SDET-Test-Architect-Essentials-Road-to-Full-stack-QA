package DockerValidation.Utils;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class DockerManagement {
    String dockerLogFile = "docker/log/docker.log";

    public void start() throws IOException, InterruptedException {
        String startBatFileName = "dockerUp";
        String serverStartFlag = "from DOWN to UP";
        int counterStringFlag = 3;

        oldLogFileDeletion();
        new File(dockerLogFile);
        management(startBatFileName, serverStartFlag, counterStringFlag);
    }

    public void stop() throws IOException, InterruptedException {
        String downBatFileName = "dockerDown";
        String serverStopFlag = "Shutdown complete";
        int counterStringFlag = 7;

        management(downBatFileName, serverStopFlag, counterStringFlag);
    }

    private void management(String batFileName, String stringFlag, int counterStringFlag) throws IOException, InterruptedException {
        int counter = 0;
        boolean flag = false;

        // Starting Docker
        Runtime runtime = Runtime.getRuntime();
//        runtime.exec("cmd /c start docker\\" + batFileName + ".bat"); // Use it for Windows
        runtime.exec("./docker/" + batFileName + ".sh");

        // Delay for file creation
        Thread.sleep(3000);

        // Calendar is needed to waiting server start
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 45);
        long stopNow = calendar.getTimeInMillis();

        while (System.currentTimeMillis() < stopNow) {
            if (flag) break;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dockerLogFile));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null && !flag) {
                if (currentLine.contains(stringFlag)) {
                    counter++;
                    System.out.println(counter + " " + stringFlag);
                }
                if (counter == counterStringFlag) {
                    flag = true;
                    break;
                }
                currentLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        Assert.assertTrue(flag);
    }

    private void oldLogFileDeletion() {
        File fileToDelete = new File("docker/log/docker.log");
        fileToDelete.delete();
        System.out.println("*** Log file successfully deleted ***");
    }
}

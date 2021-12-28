package DockerValidation.Utils;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class DockerManagement {
    String dockerLogFile = "/var/jenkins_home/workspace/DockerGitHub/docker/log/docker.log";

    public void start() throws IOException, InterruptedException {
        String startScriptFileName = "dockerUp";
        String serverStartFlag = "from DOWN to UP";
        int counterStringFlag = 3;

        oldLogFileDeletion();
        new File(dockerLogFile);
        management(startScriptFileName, serverStartFlag, counterStringFlag);
    }

    public void stop() throws IOException, InterruptedException {
        String downScriptFileName = "dockerDown";
        String serverStopFlag = "Shutdown complete";
        int counterStringFlag = 7;

        management(downScriptFileName, serverStopFlag, counterStringFlag);
    }

    private void management(String scriptFileName, String stringFlag, int counterStringFlag) throws IOException, InterruptedException {
        int counter = 0;
        boolean flag = false;

        // Starting Docker
//        Runtime runtime = Runtime.getRuntime();
//        runtime.exec("cmd /c start docker\\" + scriptFileName + ".bat"); // Use it for Windows
//        runtime.exec("bash -c \"./docker/" + scriptFileName + ".sh\"");

        // Delay for file creation
        Thread.sleep(10000);

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
        File fileToDelete = new File("/var/jenkins_home/workspace/DockerGitHub/docker/log/docker.log");
        if (!new File("/var/jenkins_home/workspace/DockerGitHub/docker/log").exists()) {
            new File("/var/jenkins_home/workspace/DockerGitHub/docker/log").mkdirs();
        }
        if (fileToDelete.exists()) {
            fileToDelete.delete();
            System.out.println("*** Log file successfully deleted ***");
        }
    }
}

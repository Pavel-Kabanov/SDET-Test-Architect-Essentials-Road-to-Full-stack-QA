package StreamsTutorial;

import org.testng.annotations.Test;

import java.util.ArrayList;

public class StreamsTutorial {
    @Test
    public void regular() {
        ArrayList<String> names = new ArrayList<>();
        names.add("A");
        names.add("F");
        names.add("V");
        names.add("D");
        names.add("R");
        names.add("Q");
        names.add("S");
        names.add("Ac");
        names.add("Ab");
        names.add("Ad");
        names.add("Ar");
        names.add("Ae");

        int countA = 0;

        for (String actual : names) {
            if (actual.startsWith("A")) {
                countA++;
            }
        }

        System.out.println(countA);
    }

}

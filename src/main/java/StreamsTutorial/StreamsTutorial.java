package StreamsTutorial;

import jdk.jfr.Name;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

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

    @Test
    @Name("Stream filter by letter 'A'")
    public void streamFilterByLetterA() {
        Long count = listOfNames().filter(s -> s.startsWith("A")).count();
        System.out.println(count);

        System.out.print("Stream filter by letter 'A' test: ");
        listOfNames().forEach(System.out::print);
    }

    private Stream<String> listOfNames() {
        return Stream.of("A", "Ab", "Ac", "Ad", "D");
    }
}

package StreamsTutorial;

import jdk.jfr.Name;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
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
        Long count = listOfFirstNames().filter(s -> s.startsWith("A")).count();
        System.out.println(count);

        System.out.print("Stream filter by letter 'A' test: ");
        listOfFirstNames().forEach(System.out::print);
    }

    @Test
    public void streamMap() {
        listOfFirstNames()
                .filter(s -> s.startsWith("A"))
                .map(String::toUpperCase)
                .forEach(System.out::print);

        System.out.println();

        listOfFirstNames()
                .filter(s -> s.startsWith("A"))
                .sorted()
                .map(String::toUpperCase)
                .forEach(System.out::print);

        System.out.println();

        Stream<String> allNames = Stream.concat(listOfFirstNames(), listOfLastNames());
//        allNames
//                .sorted()
//                .forEach(System.out::print);

        boolean flag = allNames.anyMatch(s -> s.equalsIgnoreCase("za"));
        Assert.assertTrue(flag);
    }

    @Test
    public void streamCollect() {
        List<String> list = listOfLastNames()
                .filter(s -> s.endsWith("A"))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(list.get(0));

        List<Integer> integerArray = Arrays.asList(1, 3, 4, 5, 2, 4, 5, 6, 8, 3, 1, 2, 3, 4, 5, 2, 1);
        integerArray
                .stream()
                .distinct()
                .sorted()
                .forEach(System.out::print);
    }

    private Stream<String> listOfFirstNames() {
        return Stream.of("Az", "Ab", "Ac", "Ad", "D", "aB");
    }

    private Stream<String> listOfLastNames() {
        return Stream.of("zA", "bA", "cA", "dA", "D", "bA");
    }
}

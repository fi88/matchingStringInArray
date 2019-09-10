package my.test;

import my.coding.challenge.Finder;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
* Test
**/
public class FinderTest {

    static String CHAR_REPOSITORY = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";

    @Test
    public void testStringsWithDifferentLengths() {
        Finder finder = new Finder(Arrays.asList("ABCXY"));
        List<String> listOfMatched = finder.find("ABC");
        Assert.assertTrue(listOfMatched.size() == 0);
    }

    @Test
    public void testStringsWithSameLengthsButDifferentCharacters() {
        Finder finder = new Finder(Arrays.asList("ABCXY"));
        List<String> listOfMatched = finder.find("AYCBA");
        Assert.assertTrue(listOfMatched.size() == 0);
    }

    @Test
    public void testStringsWithSameLengthsAndCharacters() {
        String str1 = "ABCCXAY";
        Finder finder = new Finder(Arrays.asList(str1, "ABC12TU"));
        List<String> listOfMatched = finder.find("YAAXCBC");
        Assert.assertTrue(listOfMatched.size() == 1);
        Assert.assertTrue(listOfMatched.get(0).equals(str1));
    }

    @Test
    public void testEdgeCases() {
        //null string
        Finder finder = new Finder(Arrays.asList("AAA"));
        Assert.assertTrue(finder.find(null).size() == 0);

        //empty array
        finder = new Finder(Arrays.asList());
        Assert.assertTrue(finder.find("").size() == 0);
        Assert.assertTrue(finder.find(null).size() == 0);

        //null array
        List<String> list = null;
        finder = new Finder(list);
        Assert.assertTrue(finder.find("").size() == 0);
        Assert.assertTrue(finder.find(null).size() == 0);
    }

    /**
     * Performance test: find a string match from a list of randomly generated large strings.
     */
    @Test
    public void testLargeStringAndArray() {
        int stringSize = 20000;
        int arraySize = 20000;
        int repositoryLength = CHAR_REPOSITORY.length();
        Random random = new Random(System.currentTimeMillis());

        //build the random list of strings
            long begin = System.currentTimeMillis();
        List<String> strings = new ArrayList<>(arraySize);
        for (int i = 1; i <= arraySize; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 1; j <= stringSize; j++) {
                stringBuilder.append(CHAR_REPOSITORY.charAt(Math.abs(random.nextInt() % repositoryLength)));
            }
            strings.add(stringBuilder.toString());
        }
            long end = System.currentTimeMillis();
        System.out.println("Time to generate " + arraySize + " strings (in sec): " + (end-begin)/1000);

        String stringToFind = strings.get(arraySize/2);
            begin = System.currentTimeMillis();
        Finder finder = new Finder(strings);
        List<String> matchedStrings = finder.find(stringToFind);
            end = System.currentTimeMillis();
        Assert.assertTrue(matchedStrings.size() == 1);
        Assert.assertTrue(matchedStrings.get(0).equals(stringToFind));
        System.out.println("Time to find matches " + arraySize + " strings (in sec): " + (end-begin)/1000);
    }
}

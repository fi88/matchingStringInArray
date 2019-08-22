package my.coding.challenge;

import java.util.*;
import java.util.stream.Collectors;

public class Finder {

    private List<String> listString;

    public List<String> getListString() {
        return listString;
    }

    public void setListString(List<String> listString) {
        this.listString = Optional.ofNullable(listString).orElseGet(() -> Arrays.asList());
    }

    public Finder(List<String> strings) {
        this.listString = new ArrayList<>();
        this.listString.addAll(Optional.ofNullable(strings).orElseGet(() -> Arrays.asList()));
    }

    public Finder(String[] strings) {
        this.listString = new ArrayList<>();
        this.listString.addAll(Arrays.asList(Optional.ofNullable(strings).orElseGet(() -> new String[0])));
    }

    /**
     * This method returns a list of string that matches the given string
     * The matched string has the exact same characters as the given string (regardless of the character order)
     * @param str a string to check
     * @return a list of matched string
     */
    public List<String> find(String str) {
        if (str == null) return Arrays.asList();
        List<String> result = new ArrayList<>();
        result =
        this.listString.stream().filter(s -> checkMatched(s, str))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * This method checks whether 2 strings are matched to each other
     * The matched string has the exact same characters as the other string (regardless of the character order)
     * @param str1 string 1
     * @param str2 string 2
     * @return true if 2 given strings are matched to each other
     */
    private boolean checkMatched(String str1, String str2) {
        // if 2 strings are of different lengths, they are not matched
        if (str1.length() != str2.length()) return false;
        boolean isMatched = true;
        Map<Character, Integer> charOccurrences1 = new HashMap<>();
        Map<Character, Integer> charOccurrences2 = new HashMap<>();

        // first, count the occurrence of every character in STR1
        for(int i = 0; i < str1.length(); i++) {
            char character = str1.charAt(i);
            if (!charOccurrences1.containsKey(character)) {
                charOccurrences1.put(character, 1);
            } else {
                charOccurrences1.put(character, charOccurrences1.get(character) + 1);
            }
        }

        // then go through STR2 to check ...
        for(int i = 0; i < str2.length(); i++) {
            char character = str2.charAt(i);
            // if a character in STR2 doesn't exist in STR1, return false
            if (!charOccurrences1.keySet().contains(character)) {
                isMatched = false;
                break;
            }
            if (!charOccurrences2.containsKey(character)) {
                charOccurrences2.put(character, 1);
            } else {
                charOccurrences2.put(character, charOccurrences2.get(character) + 1);
            }
            // if a character appears in STR2 more times than it in STR1, return false
            if (charOccurrences2.get(character) > charOccurrences1.get(character)) {
                isMatched = false;
                break;
            }
        }

        return isMatched;
    }
}

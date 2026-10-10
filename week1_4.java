import java.util.*;

public class week1_4 {
    static char findFirstNonRepeatingChar(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        for (char c : text.toCharArray()) {
            if (freq.get(c) == 1) return c;
        }
        return '\0'; // no unique char
    }

    public static void main(String[] args) {
        String input = "swiss";
        char result = findFirstNonRepeatingChar(input);
        if (result == '\0') System.out.println("No Non-Repeating Character Found");
        else System.out.println("First Non-Repeating Character: '" + result + "'");
    }
}


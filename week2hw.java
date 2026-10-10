import java.util.*;

public class week2hw {

    // 1. ATM PIN Length Validator
    static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    // 2. Word Reversal Encoder
    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            StringBuilder sb = new StringBuilder(word);
            result.append(sb.reverse()).append(" ");
        }
        return result.toString().trim();
    }

    // 3. Product Inventory CSV Parser
    static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Product: " + fields[0].trim() +
                           " | SKU: " + fields[1].trim() +
                           " | Qty: " + fields[2].trim());
    }

    // 4. Library ISBN Normalizer & Validator
    static String normalizeCode(String raw) {
        String code = raw.trim();
        if (code.length() < 3) return code;
        String pub = code.substring(0, 3).toUpperCase();
        return pub + code.substring(3);
    }

    static String validateAndFormat(String code) {
        if (code.length() != 13) return "Invalid: wrong length";
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) return "Invalid: publisher code must be 3 letters";
        }
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) return "Invalid: body must be digits";
        }
        String pub = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7);
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pub).append("] YEAR: ").append(year)
          .append(" | CATALOG: ").append(catalog);
        return sb.toString();
    }

    // 5. Stop-Word-Filtered Word Frequency Report
    static void printFilteredWordFrequency(String feedback) {
        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");
        String[] words = cleaned.split("\\s+");
        Set<String> stopWords = new HashSet<>(Arrays.asList("the","was","and","a","is","of","in"));
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            if (!stopWords.contains(word) && word.length() > 0) {
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }
        }
        // Sort by frequency descending
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Basic Utility Suite Menu ---");
            System.out.println("1. ATM PIN Length Validator");
            System.out.println("2. Word Reversal Encoder");
            System.out.println("3. Product Inventory CSV Parser");
            System.out.println("4. Library ISBN Normalizer & Validator");
            System.out.println("5. Stop-Word-Filtered Word Frequency Report");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter PIN: ");
                    String pin = sc.nextLine();
                    checkPinLength(pin);
                    break;
                case 2:
                    System.out.print("Enter sentence: ");
                    String sentence = sc.nextLine();
                    System.out.println(reverseEachWord(sentence));
                    break;
                case 3:
                    System.out.print("Enter CSV line: ");
                    String csv = sc.nextLine();
                    parseInventoryRecord(csv);
                    break;
                case 4:
                    System.out.print("Enter raw ISBN code: ");
                    String raw = sc.nextLine();
                    String normalized = normalizeCode(raw);
                    System.out.println(validateAndFormat(normalized));
                    break;
                case 5:
                    System.out.print("Enter feedback paragraph: ");
                    String feedback = sc.nextLine();
                    printFilteredWordFrequency(feedback);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}


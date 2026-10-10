import java.util.*;

public class week1hw {

    // 1. Exam Hall Seat Duplication Checker
    static void checkDuplicateSeats(int[] seatNumbers) {
        boolean duplicateFound = false;
        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    duplicateFound = true;
                }
            }
        }
        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    // 2. Typing Speed Test Accuracy Checker
    static void checkTypingAccuracy(String original, String typed) {
        if (original.length() != typed.length()) {
            System.out.println("Error: Strings must be of equal length.");
            return;
        }
        int matched = 0;
        int firstMismatchPos = -1;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1;
            }
        }
        double accuracy = ((double) matched / original.length()) * 100;
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ", matched, original.length(), accuracy);
        if (firstMismatchPos == -1) {
            System.out.println("No Mismatches");
        } else {
            System.out.println("First Mismatch at position " + firstMismatchPos +
                               " ('" + original.charAt(firstMismatchPos - 1) +
                               "' vs '" + typed.charAt(firstMismatchPos - 1) + "')");
        }
    }

    // 3. Traffic Signal Streak Analyzer
    static void findLongestStreak(String signalLog) {
        int maxLen = 1, currLen = 1;
        char maxChar = signalLog.charAt(0);
        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currLen++;
            } else {
                currLen = 1;
            }
            if (currLen > maxLen) {
                maxLen = currLen;
                maxChar = signalLog.charAt(i);
            }
        }
        System.out.println("Longest Streak: '" + maxChar + "' repeated " + maxLen + " times");
    }

    // 4. Warehouse Inventory Balancer
    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0, totalB = 0;
        for (int val : sectionA) totalA += val;
        for (int val : sectionB) totalB += val;

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        int highest = sectionA[0];
        String location = "Section A, Item 1";
        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > highest) {
                highest = sectionA[i];
                location = "Section A, Item " + (i + 1);
            }
        }
        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > highest) {
                highest = sectionB[i];
                location = "Section B, Item " + (i + 1);
            }
        }

        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB +
                           " | Status: " + status + " | Highest Quantity: " + highest +
                           " (" + location + ")");
    }

    // 5. Movie Review Word Length Profiler
    static void classifyWordLengths(String review) {
        String[] words = review.split("\\s+");
        int shortCount = 0, mediumCount = 0, longCount = 0;
        for (String word : words) {
            int len = word.length();
            if (len >= 1 && len <= 4) shortCount++;
            else if (len <= 8) mediumCount++;
            else longCount++;
        }
        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Utility Suite Menu ---");
            System.out.println("1. Exam Hall Seat Duplication Checker");
            System.out.println("2. Typing Speed Test Accuracy Checker");
            System.out.println("3. Traffic Signal Streak Analyzer");
            System.out.println("4. Warehouse Inventory Balancer");
            System.out.println("5. Movie Review Word Length Profiler");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    int[] seats1 = {101, 102, 103, 102, 105};
                    int[] seats2 = {101, 102, 103, 104, 105};
                    System.out.println("Checking seats1:");
                    checkDuplicateSeats(seats1);
                    System.out.println("Checking seats2:");
                    checkDuplicateSeats(seats2);
                    break;
                case 2:
                    System.out.print("Enter original text: ");
                    String original = sc.nextLine();
                    System.out.print("Enter typed text: ");
                    String typed = sc.nextLine();
                    checkTypingAccuracy(original, typed);
                    break;
                case 3:
                    System.out.print("Enter signal log: ");
                    String log = sc.nextLine();
                    findLongestStreak(log);
                    break;
                case 4:
                    int[] sectionA = {20, 15, 30};
                    int[] sectionB = {25, 10, 30};
                    analyzeInventory(sectionA, sectionB);
                    break;
                case 5:
                    System.out.print("Enter movie review: ");
                    String review = sc.nextLine();
                    classifyWordLengths(review);
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


import java.util.*;

public class week2 {

    // 1. Vowel & Consonant Counter
    static void countVowelsAndConsonants(String text) {
        int vowels = 0, consonants = 0;
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') continue;
            if ("aeiou".indexOf(c) != -1) vowels++;
            else if (c >= 'a' && c <= 'z') consonants++;
        }
        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    // 2. CSV Student Record Parser
    static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Name: " + fields[0].trim() +
                           " | Roll No: " + fields[1].trim() +
                           " | Dept: " + fields[2].trim());
    }

    // 3. File Extension Validator
    static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1) return "Rejected — invalid file type";
        String ext = filename.substring(dotIndex + 1);
        if (ext.equalsIgnoreCase("pdf") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("zip")) {
            return "Accepted";
        }
        return "Rejected — invalid file type";
    }

    // 4. Masked Phone Number Formatter
    static String maskPhoneNumber(String phone) {
        if (phone.length() != 10 || !phone.matches("\\d{10}")) {
            return "Invalid phone number";
        }
        String last4 = phone.substring(6);
        return "XXXXXX-" + last4;
    }

    // 5. Bank Transaction Reference Generator & Validator
    static String normalizeReference(String raw) {
        String ref = raw.trim();
        if (ref.length() < 3) return ref;
        String bankCode = ref.substring(0, 3).toUpperCase();
        return bankCode + ref.substring(3);
    }

    static String validateAndFormat(String reference) {
        if (reference.length() != 14) return "Invalid: wrong length";
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) return "Invalid: bank code must be 3 letters";
        }
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) return "Invalid: body must be digits";
        }
        String bank = reference.substring(0, 3);
        String date = reference.substring(3, 9); // ddMMyy
        String seq = reference.substring(9);     // 5 digits
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bank).append("] DATE: ")
          .append(date.substring(0,2)).append("/")
          .append(date.substring(2,4)).append("/")
          .append(date.substring(4,6))
          .append(" | SEQ: ").append(seq);
        return sb.toString();
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Text Utility Suite Menu ---");
            System.out.println("1. Vowel & Consonant Counter");
            System.out.println("2. CSV Student Record Parser");
            System.out.println("3. File Extension Validator");
            System.out.println("4. Masked Phone Number Formatter");
            System.out.println("5. Bank Transaction Reference Generator & Validator");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter text: ");
                    String text = sc.nextLine();
                    countVowelsAndConsonants(text);
                    break;
                case 2:
                    System.out.print("Enter CSV line: ");
                    String csv = sc.nextLine();
                    parseStudentRecord(csv);
                    break;
                case 3:
                    System.out.print("Enter filename: ");
                    String fname = sc.nextLine();
                    System.out.println(validateFileExtension(fname));
                    break;
                case 4:
                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine();
                    System.out.println(maskPhoneNumber(phone));
                    break;
                case 5:
                    System.out.print("Enter raw reference: ");
                    String raw = sc.nextLine();
                    String normalized = normalizeReference(raw);
                    System.out.println(validateAndFormat(normalized));
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


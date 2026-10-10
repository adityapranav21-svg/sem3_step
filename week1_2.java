public class week1_2 {
    static boolean isPalindromeIterative(String text) {
        int i = 0, j = text.length() - 1;
        while (i < j) {
            if (text.charAt(i) != text.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }

    static boolean isPalindromeRecursive(String text) {
        return checkRecursive(text, 0, text.length() - 1);
    }

    private static boolean checkRecursive(String text, int i, int j) {
        if (i >= j) return true;
        if (text.charAt(i) != text.charAt(j)) return false;
        return checkRecursive(text, i + 1, j - 1);
    }

    static boolean isPalindromeArrayReversal(String text) {
        char[] arr = text.toCharArray();
        String reversed = new StringBuilder(new String(arr)).reverse().toString();
        return text.equals(reversed);
    }

    public static void main(String[] args) {
        String input = "madam";
        System.out.println("Iterative: " + (isPalindromeIterative(input) ? "Palindrome" : "Not Palindrome"));
        System.out.println("Recursive: " + (isPalindromeRecursive(input) ? "Palindrome" : "Not Palindrome"));
        System.out.println("Array Reversal: " + (isPalindromeArrayReversal(input) ? "Palindrome" : "Not Palindrome"));
    }
}


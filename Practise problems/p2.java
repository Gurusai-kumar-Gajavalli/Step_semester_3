public class p2{
    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) return true;
        if (text.charAt(0) != text.charAt(text.length() - 1)) return false;
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] arr = text.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        String reversed = new String(arr);
        return text.equals(reversed);
    }

    public static void main(String[] args) {
        String[] tests = {"madam", "hello"};
        for (String t : tests) {
            String it = isPalindromeIterative(t) ? "Palindrome" : "Not Palindrome";
            String rec = isPalindromeRecursive(t) ? "Palindrome" : "Not Palindrome";
            String arr = isPalindromeArrayReversal(t) ? "Palindrome" : "Not Palindrome";
            System.out.println("\"" + t + "\" | Iterative: " + it + " | Recursive: " + rec + " | Array Reversal: " + arr);
        }
    }
}
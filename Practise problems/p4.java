public class p4 {
    public static char findFirstNonRepeatingChar(String text) {
        int[] counts = new int[256];
        for (int i = 0; i < text.length(); i++) {
            counts[text.charAt(i)]++;
        }
        for (int i = 0; i < text.length(); i++) {
            if (counts[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }
        return '\0';
    }

    public static void main(String[] args) {
        String[] tests = {"swiss", "aabbcc"};
        for (String t : tests) {
            char res = findFirstNonRepeatingChar(t);
            if (res == '\0') {
                System.out.println("\"" + t + "\" | No Non-Repeating Character Found");
            } else {
                System.out.println("\"" + t + "\" | First Non-Repeating Character: '" + res + "'");
            }
        }
    }
}
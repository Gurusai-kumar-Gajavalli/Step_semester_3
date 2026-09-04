public class p3 {
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) return;
        char longestColor = signalLog.charAt(0);
        int maxLength = 1;
        char currentColor = signalLog.charAt(0);
        int currentLength = 1;
        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentColor) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    longestColor = currentColor;
                }
                currentColor = signalLog.charAt(i);
                currentLength = 1;
            }
        }
        if (currentLength > maxLength) {
            maxLength = currentLength;
            longestColor = currentColor;
        }
        System.out.println("Longest Streak: '" + longestColor + "' repeated " + maxLength + " times");
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}
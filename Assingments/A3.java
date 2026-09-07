import java.util.Arrays;

public class A3 {
    public static class Canteen implements Comparable<Canteen> {
        private String canteenCode;
        private String canteenName;
        private int trustScore;

        public Canteen(String canteenCode, String canteenName, int trustScore) {
            this.canteenCode = canteenCode;
            this.canteenName = canteenName;
            this.trustScore = trustScore;
        }

        public Canteen(String canteenCode, String canteenName) {
            this(canteenCode, canteenName, 3);
        }

        @Override
        public int compareTo(Canteen other) {
            int scoreCompare = Integer.compare(other.trustScore, this.trustScore);
            if (scoreCompare != 0) return scoreCompare;

            int codeCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);
            if (codeCompare != 0) return codeCompare;

            return Integer.compare(this.canteenName.length(), other.canteenName.length());
        }
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        Canteen[] sorted = Arrays.copyOf(canteens, canteens.length);
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = i + 1; j < sorted.length; j++) {
                if (sorted[i].compareTo(sorted[j]) > 0) {
                    Canteen temp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = temp;
                }
            }
        }
        return sorted;
    }

    public static void main(String[] args) {
        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };
        Canteen[] ranked = rankCanteens(canteens);
        for (Canteen c : ranked) {
            System.out.print("\"" + c.canteenCode + "\" ");
        }
    }
}
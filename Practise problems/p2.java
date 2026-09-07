import java.util.Arrays;

public class p2 {
    public static class FareSplitter {
        private String tripId;
        private double totalFare;
        private int passengerCount;

        public FareSplitter(String tripId, double totalFare, int passengerCount) {
            if (totalFare < 0 || passengerCount <= 0) {
                throw new IllegalArgumentException("Invalid fare or passenger count");
            }
            this.tripId = tripId;
            this.totalFare = totalFare;
            this.passengerCount = passengerCount;
        }

        public FareSplitter(String tripId, double totalFare) {
            this(tripId, totalFare, 1);
        }

        public FareSplitter(String tripId) {
            this(tripId, 0.0, 1);
        }

        public double[] fareBreakdown() {
            double[] shares = new double[passengerCount];
            int totalPaise = (int) Math.round(totalFare * 100);
            int baseShare = totalPaise / passengerCount;
            int remainder = totalPaise % passengerCount;

            for (int i = 0; i < passengerCount; i++) {
                shares[i] = baseShare / 100.0;
                if (i >= passengerCount - remainder) {
                    shares[i] = (baseShare + 1) / 100.0;
                }
            }
            return shares;
        }

        public boolean isConfirmationOverdue(int confirmed, int expected) {
            return confirmed < expected;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FareSplitter("TRIP001", 100000, 3).fareBreakdown()));
        System.out.println(Arrays.toString(new FareSplitter("TRIP003").fareBreakdown()));
    }
}
public class A4 {
    public static final class SurgeFeeCalculator {
        private final double minimumSurgePercent;

        public SurgeFeeCalculator(double minimumSurgePercent) {
            this.minimumSurgePercent = minimumSurgePercent;
        }

        public final double calculateSurgeFee(double orderValue, int delayMinutes) {
            if (orderValue < 0 || delayMinutes < 0) {
                throw new IllegalArgumentException("Values cannot be negative");
            }
            if (delayMinutes == 0) return 0.0;

            double fee = 0.0;
            int remainingMinutes = delayMinutes;

            if (remainingMinutes > 15) {
                fee += (remainingMinutes - 15) * 0.02 * orderValue;
                remainingMinutes = 15;
            }
            if (remainingMinutes > 5) {
                fee += (remainingMinutes - 5) * 0.01 * orderValue;
                remainingMinutes = 5;
            }
            if (remainingMinutes > 0) {
                fee += remainingMinutes * 0.005 * orderValue;
            }

            double minimumFloor = orderValue * (minimumSurgePercent / 100.0);
            return Math.max(fee, minimumFloor);
        }
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0);
        System.out.println("Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 16));
    }
}
public class p4 {
    public static final class BoardingPenaltyCalculator {
        private final double minimumPenaltyPercent;

        public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
            this.minimumPenaltyPercent = minimumPenaltyPercent;
        }

        public final double calculatePenalty(double ticketFare, int minutesLate) {
            if (ticketFare < 0 || minutesLate < 0) {
                throw new IllegalArgumentException("Values cannot be negative");
            }
            if (minutesLate == 0) return 0.0;

            double penalty = 0.0;
            int remainingMinutes = minutesLate;

            if (remainingMinutes > 15) {
                penalty += (remainingMinutes - 15) * 0.02 * ticketFare;
                remainingMinutes = 15;
            }
            if (remainingMinutes > 5) {
                penalty += (remainingMinutes - 5) * 0.01 * ticketFare;
                remainingMinutes = 5;
            }
            if (remainingMinutes > 0) {
                penalty += remainingMinutes * 0.005 * ticketFare;
            }

            double minimumFloor = ticketFare * (minimumPenaltyPercent / 100.0);
            return Math.max(penalty, minimumFloor);
        }
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0);
        System.out.println("Rs " + calc.calculatePenalty(1000, 0));
        System.out.println("Rs " + calc.calculatePenalty(1000, 1));
        System.out.println("Rs " + calc.calculatePenalty(1000, 16));
    }
}
public class p5 {
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

    public static class BusTicketAccount {
        String bookingId;
        double ticketFare;
        
        static final String DEPOT_SYSTEM_ID;
        static {
            DEPOT_SYSTEM_ID = "DEPOT-001";
        }

        public BusTicketAccount(String bookingId, double ticketFare) {
            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        public final double calculatePenalty(int minutesLate) {
            return new BoardingPenaltyCalculator(1.0).calculatePenalty(ticketFare, minutesLate);
        }
    }

    public static class SleeperAccount extends BusTicketAccount {
        public SleeperAccount(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }
    }

    public static void processAccount(BusTicketAccount account, double amount, int minutesLate) {
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts.length != amounts.length || accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException("Batch array lengths must match identically.");
        }

        int processed = 0, nullSkipped = 0, sleeperCount = 0, regularCount = 0;
        double grandTotalPenalties = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }
            
            processed++;
            if (accounts[i] instanceof SleeperAccount) {
                sleeperCount++;
            } else {
                regularCount++;
            }
            
            grandTotalPenalties += accounts[i].calculatePenalty(minutesLateArray[i]);
            processAccount(accounts[i], amounts[i], minutesLateArray[i]);
        }
        
        System.out.println(processed + " processed | " + nullSkipped + " null skipped | " + 
                           sleeperCount + " sleeper | " + regularCount + " regular | grand total penalties = Rs " + grandTotalPenalties);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new SleeperAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}
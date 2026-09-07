public class A5 {
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

    public static class DeliveryAccount {
        String studentId;
        double orderValue;
        
        static final String BATCH_ID;
        static {
            BATCH_ID = "RECON-NIGHTLY";
        }

        public DeliveryAccount(String studentId, double orderValue) {
            this.studentId = studentId;
            this.orderValue = orderValue;
        }

        public DeliveryAccount(String studentId) {
            this(studentId, 0.0);
        }

        public final double calculateSurgeFee(int delayMinutes) {
            return new SurgeFeeCalculator(1.0).calculateSurgeFee(orderValue, delayMinutes);
        }
    }

    public static class Premium extends DeliveryAccount {
        public Premium(String studentId, double orderValue) {
            super(studentId, orderValue);
        }
    }

    public static void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException("Parallel arrays must have matching lengths.");
        }

        int processed = 0, nullSkipped = 0, premiumCount = 0, regularCount = 0;
        double grandTotalSurgeFees = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }
            
            processed++;
            if (accounts[i] instanceof Premium) {
                premiumCount++;
            } else {
                regularCount++;
            }
            
            grandTotalSurgeFees += accounts[i].calculateSurgeFee(delayMinutesArray[i]);
            processAccount(accounts[i], amounts[i], delayMinutesArray[i]);
        }
        
        System.out.println(processed + " processed | " + nullSkipped + " null skipped | " + 
                           premiumCount + " premium | " + regularCount + " regular | grand total surge fees = Rs " + grandTotalSurgeFees);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}
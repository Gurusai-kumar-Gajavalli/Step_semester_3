import java.util.Arrays;

public class A5 {
    private static final String BATCH_SYSTEM_NAME;
    
    static {
        BATCH_SYSTEM_NAME = "PageTurner Library Ledger";
    }

    public static class LoanReceipt {
        private final String memberId;
        private final String[] bookIds;

        public LoanReceipt(String memberId, String[] bookIds) {
            this.memberId = memberId;
            if (bookIds == null) throw new IllegalArgumentException("construction rejected");
            
            for (String code : bookIds) {
                if (code == null || !code.matches("BK-\\d{3}")) {
                    throw new IllegalArgumentException("construction rejected");
                }
            }
            this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
        }

        public String[] getBookIds() {
            return Arrays.copyOf(bookIds, bookIds.length);
        }

        public LoanReceipt withCorrectedBookId(int index, String newId) {
            String[] newIds = getBookIds();
            if (index >= 0 && index < newIds.length) {
                newIds[index] = newId;
            }
            return new LoanReceipt(this.memberId, newIds);
        }
    }

    public static class ReferenceOnlyLoanReceipt extends LoanReceipt {
        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0, nulls = 0, referenceOnly = 0, regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nulls++;
            } else {
                processed++;
                if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }
        return processed + " processed | " + nulls + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        try {
            new LoanReceipt("LIB-8841", new String[]{"BK-100", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]);

        LoanReceipt[] batch = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(processNightlyCirculation(batch));
    }
}
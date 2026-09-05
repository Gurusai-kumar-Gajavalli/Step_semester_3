import java.util.Arrays;

public class p5 {
    
    // Shared state initialized via static block
    private static final String BATCH_LOG_PREFIX;
    
    static {
        BATCH_LOG_PREFIX = "Nightly Ledger Process: ";
    }

    public static class DischargeSummary {
        private final String patientId;
        private final String[] medicationCodes;

        public DischargeSummary(String patientId, String[] medicationCodes) {
            this.patientId = patientId;
            
            if (medicationCodes == null) {
                throw new IllegalArgumentException("construction rejected");
            }
            
            for (String code : medicationCodes) {
                // Validates format: "MED-" followed by exactly one uppercase letter
                if (code == null || !code.matches("MED-[A-Z]")) {
                    throw new IllegalArgumentException("construction rejected");
                }
            }
            
            // Defensive copy on the way in
            this.medicationCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
        }

        public String[] getMedicationCodes() {
            // Defensive copy on the way out
            return Arrays.copyOf(medicationCodes, medicationCodes.length);
        }

        // Wither pattern: returning a brand-new object instead of mutating
        public DischargeSummary withCorrectedMedication(int index, String newCode) {
            String[] newCodes = getMedicationCodes();
            if (index >= 0 && index < newCodes.length) {
                newCodes[index] = newCode;
            }
            return new DischargeSummary(this.patientId, newCodes);
        }
    }

    public static class CriticalCareDischargeSummary extends DischargeSummary {
        private final int icuDays;

        public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
            super(patientId, medicationCodes);
            this.icuDays = icuDays;
        }
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0;
        int nulls = 0;
        int critical = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nulls++;
            } else {
                processed++;
                // Dispatch logic using instanceof
                if (summary instanceof CriticalCareDischargeSummary) {
                    critical++;
                } else {
                    routine++;
                }
            }
        }
        return processed + " processed | " + nulls + " null skipped | " + critical + " critical-care | " + routine + " routine";
    }

    public static void main(String[] args) {
        // Test Case 1: Invalid entry format drops the entire construction
        try {
            new DischargeSummary("MT2026-0142", new String[]{"MED-A", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Test Case 2: Defensive copying prevents external mutation of the internal array
        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "MED-B"});
        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println(d.getMedicationCodes()[0]);

        // Test Case 3: Process the nightly batch containing varied summary types and nulls
        DischargeSummary[] batch = {
            new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary("MT002", new String[]{"MED-Y"})
        };
        System.out.println(processNightlyBatch(batch));
    }
}
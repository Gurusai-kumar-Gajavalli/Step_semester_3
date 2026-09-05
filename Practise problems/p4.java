public class p4 {
    public static class PatientProfile {
        private String patientId;
        private String name;
        private boolean discharged;
        private String lockerPinHash;

        // No-argument constructor
        public PatientProfile() {
            this(null, null);
        }

        // Name-only constructor
        public PatientProfile(String name) {
            this(null, name);
        }

        // Master constructor (id + name)
        public PatientProfile(String patientId, String name) {
            this.patientId = patientId;
            this.name = name;
            this.discharged = false;
        }

        public String getPatientId() {
            return patientId;
        }

        // Write-once setter for Patient ID
        public void setPatientId(String id) {
            if (this.patientId == null) {
                this.patientId = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isDischarged() {
            return discharged;
        }

        public void setDischarged(boolean discharged) {
            this.discharged = discharged;
        }

        // Write-only setter for Locker PIN with one-way deterministic transformation
        public void setLockerPin(String pin) {
            if (pin != null && pin.matches("\\d{4,6}")) {
                this.lockerPinHash = Integer.toHexString(pin.hashCode());
            } else {
                throw new IllegalArgumentException("PIN must be a 4-6 digit numeric string.");
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Name-only constructor leaves ID waiting for intake
        System.out.println("Test 1: " + new PatientProfile("Arjun Iyer").getPatientId());

        // Test Case 2: Full constructor sets ID immediately
        System.out.println("Test 2: " + new PatientProfile("MT2026-0142", "Arjun Iyer").getPatientId());

        // Test Case 3: Write-once property ignores secondary assignments
        PatientProfile p = new PatientProfile();
        p.setPatientId("MT2026-0142");
        p.setPatientId("HACKED-0000"); // This assignment is silently ignored
        System.out.println("Test 3: " + p.getPatientId());
        
        // Test Case 4: Setting the write-only PIN (no getter to retrieve it)
        p.setLockerPin("4092"); 
        System.out.println("Test 4: PIN accepted successfully.");
    }
}
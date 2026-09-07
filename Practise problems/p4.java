public class p4 {
    /* 
       BROKEN VERSION:
       class BrokenStudent {
           static String name; // Wrong: Name is unique to each student, static shares it globally.
           static String regNo; // Wrong: Registration number must be unique per student.
           static int attendance; // Wrong: Each student has their own attendance record.
       }
    */

    public static class SrmStudent {
        String name;
        String regNo;
        int attendance;
        
        static String university = "SRMIST";
        static int admissionCount = 0;

        public SrmStudent(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            admissionCount++;
            this.regNo = "RA23110030101" + admissionCount;
        }

        public void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        public static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + admissionCount);
        }
    }

    public static void main(String[] args) {
        SrmStudent s1 = new SrmStudent("Ravi", 80);
        SrmStudent s2 = new SrmStudent("Meera", 90);
        
        s1.printIdCard();
        s2.printIdCard();
        SrmStudent.printTotalAdmissions();
    }
}
public class p1 {
    public static class SrmStudent {
        String name;
        String regNo;
        int attendance;

        public SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        public boolean isEligible() {
            return attendance >= 75;
        }

        public void addAttendanceUpdate(int newAttendance) {
            this.attendance = newAttendance;
        }

        // Justification: classAverage is static because it computes an aggregate metric 
        // over multiple students, rather than relying on the state of one specific instance.
        public static double classAverage(SrmStudent[] students) {
            if (students.length == 0) return 0;
            double sum = 0;
            for (SrmStudent s : students) {
                sum += s.attendance;
            }
            return sum / students.length;
        }
    }

    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "R1", 82),
            new SrmStudent("Anitha", "R2", 68),
            new SrmStudent("Karthik", "R3", 91),
            new SrmStudent("Meera", "R4", 74),
            new SrmStudent("Suresh", "R5", 60)
        };

        for (SrmStudent s : students) {
            System.out.println(s.name + " " + s.attendance + "% " + (s.isEligible() ? "Eligible" : "Detained"));
        }
        System.out.println("Class average: " + SrmStudent.classAverage(students) + "%");
    }
}
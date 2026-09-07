public class p5 {
    
    // Pulled from P2 to make P5 standalone
    public static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        public FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        public void pay(double amount) {
            if (amount > 0) {
                this.amountPaid += amount;
            }
        }

        public double getDue() {
            return totalFee - amountPaid;
        }
    }

    public static class HostelFeeAccount extends FeeAccount {
        public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }
    }

    // Pulled from P3 to make P5 standalone
    public static class HostelRoom {
        String roomNo;
        int beds;
        int occupied;

        public HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }
    }

    // The Week 3 Practice 5 Capstone Class
    public static class SrmStudent {
        String name;
        String regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;

        static int totalStudents = 0;

        public SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = room;
            totalStudents++;
        }

        public String fullStatus() {
            String roomDisplay = (room != null) ? room.roomNo : "unallotted";
            return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomDisplay;
        }
    }

    public static void main(String[] args) {
        HostelRoom r1 = new HostelRoom("C-214", 3, 2);
        HostelRoom r2 = new HostelRoom("C-507", 2, 1);

        HostelFeeAccount f1 = new HostelFeeAccount("RA1", 200000, 60000);
        HostelFeeAccount f2 = new HostelFeeAccount("RA2", 200000, 20000);
        HostelFeeAccount f3 = new HostelFeeAccount("RA3", 200000, 0);
        
        f3.pay(-500); // Rejected payment

        SrmStudent s1 = new SrmStudent("Ravi", "RA1", f1, r1);
        SrmStudent s2 = new SrmStudent("Anitha", "RA2", f2, r2);
        SrmStudent s3 = new SrmStudent("Karthik", "RA3", f3, null);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
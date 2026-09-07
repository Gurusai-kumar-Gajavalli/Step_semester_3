public class A5 {
    public static class CompanyEmployeeRecord {
        String name;
        String empId;
        A2.Employee employee;
        A3.ParkingSlot slot;

        static int totalRecords = 0;

        public CompanyEmployeeRecord(String name, String empId, A2.Employee employee, A3.ParkingSlot slot) {
            this.name = name;
            this.empId = empId;
            this.employee = employee;
            this.slot = slot;
            totalRecords++;
        }

        public String fullProfile() {
            double pay = employee.getSalary();
            if (employee instanceof A2.ManagerEmployee) {
                pay = ((A2.ManagerEmployee) employee).effectiveSalary();
            } else if (employee instanceof A2.InternEmployee) {
                pay = ((A2.InternEmployee) employee).effectiveSalary();
            }

            String slotDisplay = (slot != null) ? slot.slotNo : "no parking assigned";
            return name + " | Pay: Rs " + pay + " | Slot: " + slotDisplay;
        }
    }

    public static void main(String[] args) {
        A2.ManagerEmployee m = new A2.ManagerEmployee("E1", "Divya", 70000, 8000);
        A2.Employee e = new A2.Employee("E2", "Karan", 40000);
        A2.InternEmployee i = new A2.InternEmployee("E3", "Meera", 12000, 10000);

        A3.ParkingSlot s1 = new A3.ParkingSlot("A1", 1, 1);
        A3.ParkingSlot s2 = new A3.ParkingSlot("A2", 1, 1);

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "E1", m, s1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E2", e, s2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "E3", i, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
// Problem 2: CSV Student Record Parser[cite: 14]
public class p2 {
    public static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length == 3) {
            System.out.println("Name: " + fields[0].trim() + " | Roll No: " + fields[1].trim() + " | Dept: " + fields[2].trim());
        } else {
            System.out.println("Invalid Record");
        }
    }

    public static void main(String[] args) {
        parseStudentRecord("Ananya Verma, RA2211003010123,CSE");
        parseStudentRecord("Ananya Verma,CSE");
    }
}
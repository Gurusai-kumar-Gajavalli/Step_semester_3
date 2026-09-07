public class A1 {
    public static class BookIssue {
        String title;
        String borrowerName;
        int daysOverdue;

        public BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = daysOverdue;
        }

        public double fineAmount() {
            return daysOverdue > 0 ? daysOverdue * 5.0 : 0.0;
        }

        public boolean isSeverelyOverdue() {
            return daysOverdue > 14;
        }

        // Justification: totalFineCollected aggregates data across an array of independent 
        // BookIssue objects, making it a utility function of the class rather than an instance.
        public static double totalFineCollected(BookIssue[] issues) {
            double total = 0;
            for (BookIssue issue : issues) {
                total += issue.fineAmount();
            }
            return total;
        }
    }

    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "B1", 18),
            new BookIssue("Effective Java", "B2", 5),
            new BookIssue("Refactoring", "B3", 0),
            new BookIssue("DSA Handbook", "B4", 21),
            new BookIssue("Design Patterns", "B5", 9)
        };

        for (BookIssue b : issues) {
            System.out.println(b.title + " " + b.daysOverdue + " days - " + (b.isSeverelyOverdue() ? "Severely overdue" : "OK"));
        }
        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}
import java.util.HashSet;

public class p1 {
    public static class BusTicket {
        private String passengerName;
        private String destination;
        private boolean checkedIn = false;

        public BusTicket(String passengerName, String destination) {
            if (passengerName == null || passengerName.trim().isEmpty() || 
                destination == null || destination.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.passengerName = passengerName;
            this.destination = destination;
        }

        public void markCheckedIn() {
            if (!checkedIn) {
                checkedIn = true;
            } else {
                System.out.println("Ticket already checked in.");
            }
        }
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;
        HashSet<String> seenBookings = new HashSet<>();

        for (String[] raw : rawBookings) {
            try {
                BusTicket ticket = new BusTicket(raw[0], raw[1]);
                String uniqueKey = ticket.passengerName.trim().toLowerCase() + "|" + ticket.destination.trim().toLowerCase();
                
                if (seenBookings.contains(uniqueKey)) {
                    duplicates++;
                } else {
                    seenBookings.add(uniqueKey);
                    valid++;
                }
            } catch (Exception e) {
                rejected++;
            }
        }
        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] input = {{"Divya", "Chennai"}, {"", "Bangalore"}, {"Ravi123", "Pune"}, {"Divya", "Chennai"}, {" ", " "}};
        processBatch(input);
    }
}
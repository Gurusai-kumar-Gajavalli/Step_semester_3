public class A1 {
    public static class FoodOrder {
        private String studentName;
        private String dishName;
        private boolean delivered = false;

        public FoodOrder(String studentName, String dishName) {
            if (studentName == null || studentName.trim().isEmpty() || 
                dishName == null || dishName.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid order details");
            }
            this.studentName = studentName;
            this.dishName = dishName;
        }

        public void markDelivered() {
            if (!delivered) {
                delivered = true;
            } else {
                System.out.println("Warning: Order was already marked delivered.");
            }
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0, rejected = 0;
        for (String[] raw : rawOrders) {
            try {
                new FoodOrder(raw[0], raw[1]);
                valid++;
            } catch (Exception e) {
                rejected++;
            }
        }
        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] input = {
            {"Ravi", "Paneer Butter Masala"}, 
            {"", "Chole Bhature"},
            {"Meera", " "}, 
            {"Divya", "Veg Biryani"}
        };
        processBatch(input);
    }
}
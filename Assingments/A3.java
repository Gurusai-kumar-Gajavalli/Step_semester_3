public class A3 {
    public static class ParkingSlot {
        String slotNo;
        int capacity;
        int occupiedCount;

        public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        public void allot(String vehicleNo) {
            if (occupiedCount < capacity) {
                occupiedCount++;
                System.out.println(vehicleNo + " allotted to slot " + slotNo);
            }
        }
    }

    public static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }
        return null;
    }

    public static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot available = findAvailableSlot(slots);
        if (available != null) {
            available.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }

    public static void main(String[] args) {
        ParkingSlot[] slotsAvailable = { new ParkingSlot("A1", 4, 3), new ParkingSlot("A2", 5, 5) };
        safeAllot(slotsAvailable, "TN09AB1234");

        ParkingSlot[] slotsFull = { new ParkingSlot("A1", 4, 4), new ParkingSlot("A2", 5, 5) };
        safeAllot(slotsFull, "TN09AB1234");
    }
}
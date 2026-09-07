public class p3 {
    public static class HostelRoom {
        String roomNo;
        int beds;
        int occupied;

        public HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        public void allot(String name) {
            if (occupied < beds) {
                occupied++;
                System.out.println(name + " allotted to room " + roomNo);
            }
        }
    }

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom room : rooms) {
            if (room.occupied < room.beds) {
                return room;
            }
        }
        return null;
    }

    // Justification: Passing the array does not copy the rooms because Java passes object 
    // references by value. The array holds references pointing to the original room objects in memory.
    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom available = findAvailableRoom(rooms);
        if (available != null) {
            available.allot(studentName);
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }

    public static void main(String[] args) {
        HostelRoom[] slotsAvailable = { new HostelRoom("C-214", 3, 2), new HostelRoom("C-507", 2, 2) };
        safeAllot(slotsAvailable, "Divya");

        HostelRoom[] slotsFull = { new HostelRoom("C-214", 3, 3), new HostelRoom("C-507", 2, 2) };
        safeAllot(slotsFull, "Divya");
    }
}
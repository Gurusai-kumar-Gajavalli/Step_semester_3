public class A4 {
    public static class LibraryMember {
        String name;
        String memberId;
        int booksIssued;

        static String libraryName = "PageTurner Library";
        static int memberCount = 0;

        public LibraryMember(String name) {
            this.name = name;
            this.booksIssued = 0;
            memberCount++;
            this.memberId = "LM-100" + memberCount;
        }

        public void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        public static void printTotalMembers() {
            System.out.println("Total members: " + memberCount);
        }
    }

    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember("Aditi");
        LibraryMember m2 = new LibraryMember("Rohan");

        m1.printMemberCard();
        m2.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}
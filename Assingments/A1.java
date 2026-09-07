public class A1 {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) return "ALLOWED";
        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }
        if (fieldModifier.equals("default")) {
            return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";
        }
        if (fieldModifier.equals("protected")) {
            return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";
        }
        return "DENIED";
    }

    public static String summarizeByModifier(String[][] attempts) {
        int privAllowed = 0, privDenied = 0;
        int defAllowed = 0, defDenied = 0;
        int protAllowed = 0, protDenied = 0;
        int pubAllowed = 0, pubDenied = 0;

        for (String[] attempt : attempts) {
            String mod = attempt[0];
            String res = classifyAccess(mod, attempt[1]);
            boolean allowed = res.equals("ALLOWED");

            if (mod.equals("private")) { if(allowed) privAllowed++; else privDenied++; }
            else if (mod.equals("default")) { if(allowed) defAllowed++; else defDenied++; }
            else if (mod.equals("protected")) { if(allowed) protAllowed++; else protDenied++; }
            else if (mod.equals("public")) { if(allowed) pubAllowed++; else pubDenied++; }
        }

        return "private: " + privAllowed + " allowed / " + privDenied + " denied | " +
               "default: " + defAllowed + " allowed / " + defDenied + " denied | " +
               "protected: " + protAllowed + " allowed / " + protDenied + " denied | " +
               "public: " + pubAllowed + " allowed / " + pubDenied + " denied";
    }

    public static class LibraryMember {
        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
            if (membershipId == null || membershipId.trim().length() < 4) {
                throw new IllegalArgumentException("construction rejected");
            }
            this.membershipId = membershipId;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));
        
        String[][] batch = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(batch));

        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
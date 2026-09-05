public class p2 {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) return "ALLOWED";
        
        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }
        
        if (fieldModifier.equals("default")) {
            return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";
        }
        
        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) return "ALLOWED";
            if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) return "ALLOWED";
            return "DENIED"; // This correctly denies SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE
        }
        
        return "DENIED";
    }

    public static String describeContext(String accessorContext) {
        String[] words = accessorContext.split("_");
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i].substring(0, 1).toUpperCase());
            sb.append(words[i].substring(1).toLowerCase());
            
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        // Test cases from the problem description
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
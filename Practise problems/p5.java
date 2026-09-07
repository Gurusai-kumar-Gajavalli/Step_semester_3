// Problem 5: Bank Transaction Reference Generator & Validator[cite: 14]
public class p5 {
    public static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed;
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String reference) {
        String normalized = normalizeReference(reference);
        if (normalized.length() != 14) return "Invalid: wrong length";
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalized.charAt(i))) return "Invalid: bank code must be 3 letters";
        }
        
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(normalized.charAt(i))) return "Invalid: non-digit in body";
        }
        
        String bankCode = normalized.substring(0, 3);
        String date = normalized.substring(3, 5) + "/" + normalized.substring(5, 7) + "/" + normalized.substring(7, 9);
        String seq = normalized.substring(9);
        
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ").append(date).append(" | SEQ: ").append(seq);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(validateAndFormat("   hdf03022600042  "));
        System.out.println(validateAndFormat("12F03022600042"));
    }
}
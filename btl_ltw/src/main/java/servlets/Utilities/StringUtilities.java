package servlets.Utilities;
import java.util.regex.Pattern;

public class StringUtilities {
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Chấp nhận số điện thoại có 10 hoặc 11 chữ số, bắt đầu bằng 0 và không chứa ký tự đặc biệt
        String regex = "^(0[0-9]{9,10})$";
        return Pattern.matches(regex, phoneNumber);
    }
    
}

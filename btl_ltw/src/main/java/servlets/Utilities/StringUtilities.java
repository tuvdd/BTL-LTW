package servlets.Utilities;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;

public class StringUtilities {
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Chấp nhận số điện thoại có 10 hoặc 11 chữ số, bắt đầu bằng 0 và không chứa ký
        // tự đặc biệt
        String regex = "^(0[0-9]{9,10})$";
        return Pattern.matches(regex, phoneNumber);
    }

    public static String getRequestURLWithoutPageParam(HttpServletRequest request) {
        String currentUrl = request.getRequestURI() + "?"
                + (request.getQueryString() != null ? request.getQueryString() : "");
        int index = currentUrl.indexOf("page=");
        if (index != -1) {
            currentUrl = currentUrl.substring(0, index);
            while (currentUrl.charAt(currentUrl.length() - 1) == '&') {
                currentUrl = currentUrl.substring(0, currentUrl.length() - 1);
            }
        }

        return currentUrl;
    }

    public static String formatPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        String formattedNumber = decimalFormat.format(price);
        return formattedNumber;
    }
}

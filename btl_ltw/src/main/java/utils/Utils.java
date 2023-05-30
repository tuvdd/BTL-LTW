package utils;
public class Utils {
    public static boolean isExistNotNumberChar(String s) {
        for (char c : s.toCharArray()) {
            if (c < '0' || c > '9')
                return true;
        }
        return false;
    }
}

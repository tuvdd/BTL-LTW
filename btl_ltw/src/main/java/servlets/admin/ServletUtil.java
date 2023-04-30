package servlets.admin;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ServletUtil {
	public static String[] SesstionAttributes = { "id" };

	public static boolean IsSessionExsited(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		UUID uuid = (UUID) session.getAttribute("id");
		return (uuid != null && !uuid.toString().equals(""));
	}
}

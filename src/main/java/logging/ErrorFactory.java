package logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ErrorFactory {
    public static final String BAD_REQUEST = "BAD_REQUEST";

    public static void feedbackError(HttpServletRequest req, HttpServletResponse res, int statusCode, String nameError, String msg) throws IOException {
        res.setStatus(statusCode);
        String encodedMsg = URLEncoder.encode(msg, StandardCharsets.UTF_8);
        String jspPath = req.getContextPath() + "/error.jsp";
        String redirectUrl = String.format("%s?error_code=%d&error_name=%s&msg=%s",
                jspPath,
                statusCode,
                nameError,
                encodedMsg);
        res.sendRedirect(redirectUrl);//sua thanh json de lam viec
    }
}
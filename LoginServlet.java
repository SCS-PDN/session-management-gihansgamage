import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Get username & password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. Validate credentials (hardcoded users)
        if ((username.equals("student1") && password.equals("pass1")) ||
            (username.equals("student2") && password.equals("pass2")) ||
            (username.equals("admin") && password.equals("admin123"))) {

            // 3a. Create session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // 3b. Store username in cookie
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(3600); // 1 hour
            response.addCookie(userCookie);

            // 3c. Redirect to DashboardServlet
            response.sendRedirect("DashboardServlet");
        } else {
            // 4. If invalid, redirect back to login.html
            response.sendRedirect("login.html");
        }
    }
}

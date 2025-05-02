import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 1. Check if user is logged in (session)
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // 2. Create a list of courses (hardcoded)
        List<Course> courses = Arrays.asList(
            new Course("101", "Web Programming", "Dr. Silva"),
            new Course("102", "Software Engineering", "Dr. Perera"),
            new Course("103", "Database Systems", "Dr. Fernando")
        );

        // 3. Get enrolled courses from session
        @SuppressWarnings("unchecked")
        List<String> enrolledCourses = (List<String>) session.getAttribute("enrolled");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }

        // 4. Store courses in request attribute
        request.setAttribute("courses", courses);
        request.setAttribute("enrolledCourses", enrolledCourses);

        // 5. Forward to dashboard.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}

//Servlet that responds to StudentSearch.jsp page with the student object, the ID of whom was sent by the servlet request

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentSearchServlet
 */
@WebServlet("/StudentSearchServlet")
public class StudentSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txtID = request.getParameter("txtID");
		int ID = Integer.parseInt(txtID);
		try {
			StudentDAO studentDAO = new StudentDAO();
			Student requestedStudent = null;
			requestedStudent = studentDAO.getStudentById(ID);
			request.setAttribute("student", requestedStudent);
			request.getRequestDispatcher("StudentSearch.jsp").forward(request, response);
		} catch (Exception ex) {
			System.out.println("The database is temporarily unavailable. Please try again later. \n");
			System.out.println("=== System error message (for the developer's eyes only) === \n" + ex.getMessage());
		}

	}

}

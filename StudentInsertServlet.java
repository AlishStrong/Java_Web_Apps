//Servlet that receives request with attributes from StudentInsertPage.jsp and allows to insert a new object (student) to the database.

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ExerciseSet1.Student;
import ExerciseSet1.StudentDAO;

/**
 * Servlet implementation class StudentInsertServlet
 */
@WebServlet("/StudentInsertServlet")
public class StudentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txtID = request.getParameter("txtID");
		int ID = Integer.parseInt(txtID);
		String firstname = request.getParameter("txtName");
		String lastname = request.getParameter("txtSurname");
		String streetaddress = request.getParameter("txtAddress");
		int postcode = Integer.parseInt(request.getParameter("txtPostcode"));
		String postoffice = request.getParameter("txtPostoffice");
		try {
			StudentDAO studentDAO = new StudentDAO();
			Student newStudent = new Student(ID, firstname, lastname, streetaddress, postcode, postoffice);
			int result = studentDAO.insertStudent(newStudent);
			if (result == 0) {
				String message = "Student data saved successfully.";
				request.setAttribute("message", message);
			}
			if (result == 1) {
				String message = "Cannot save the student data. Student id "+ ID +" is already in use.";
				request.setAttribute("message", message);
			}
			if (result == -1) {
				String message = "The database is temporarily unavailable. Please try again later.";
				request.setAttribute("message", message);
			}
		} catch (Exception ex) {
			System.out.println("The database is temporarily unavailable. Please try again later. \n");
			System.out.println("=== System error message (for the developer's eyes only) === \n" + ex.getMessage());
			String message = "The database is temporarily unavailable. Please try again later.";
			request.setAttribute("message", message);
		}
		request.getRequestDispatcher("StudentInsertPage.jsp").forward(request, response);
	}
}

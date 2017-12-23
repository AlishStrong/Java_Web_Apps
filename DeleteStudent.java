//Servlet that receives ID of a student from StudentList.jsp page and sends the right response. 
//In StudentList.jsp the table will have column with delete options; 
//when pressed and comfirmed, the respective student will be deleted from the database

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ExerciseSet1.StudentDAO;

/**
 * Servlet implementation class DeleteStudent
 */
@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txtID = request.getParameter("studentId");
		int ID = Integer.parseInt(txtID);
		try {
			StudentDAO studentDAO = new StudentDAO();
			studentDAO.deleteStudent(ID);
			String message = "Student deleted from the database";
			request.setAttribute("delete", message);
			request.getRequestDispatcher("StudentList.jsp").forward(request, response);
		} catch (Exception ex) {
			System.out.println("The database is temporarily unavailable. Please try again later. \n");
			System.out.println("=== System error message (for the developer's eyes only) === \n" + ex.getMessage());
		}

	}

}

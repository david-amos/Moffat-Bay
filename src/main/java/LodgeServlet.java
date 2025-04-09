

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 * Servlet implementation class LodgeServlet
 */
@WebServlet("/LodgeServlet")
public class LodgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LodgeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log("in doPost");
		HttpSession session = request.getSession(true);
		String submitValue = request.getParameter("submit");
		if (submitValue != null) {
			if (submitValue.equals("Signup")){
				//A signup request was submitted
				log("in signup submit");
				// retrieving form values
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String userEmail = request.getParameter("userEmail");
				String birthDate = request.getParameter("birthDate");
				String phoneNumber = request.getParameter("phoneNumber");
				String password = request.getParameter("password");
				try {
					Connection connection = null;
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "test_user", "testPW");
		            Statement statement = connection.createStatement();
		            String query = "INSERT INTO TESTMOFFATBAYDB.USER VALUES ('"+userEmail+"','"+firstName+"','"+
		            lastName+"','"+phoneNumber+"','"+birthDate+"','"+password+"');";
		            statement.executeUpdate(query);
		            log("User Inserted");
		            session.setAttribute("User", userEmail);
		            ServletContext sc = getServletContext();
		            // reroute to landing when logged in
			        RequestDispatcher rd = sc.getRequestDispatcher("/Landing.jsp");
			        rd.forward(request, response);
				}
				catch(Exception e){
					
				}
			}
		}
		doGet(request, response);
	}

}

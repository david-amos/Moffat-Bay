

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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
/**
 * Servlet implementation class LodgeServlet
 */
@WebServlet("/LodgeServlet")
public class LodgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String STATIC_KEY = "F45gK0ieu7o2UBB3";
	private static final String ALGORITHM = "AES";
       
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
				String confirmPassword = request.getParameter("confirmPassword");
				String error = null;
				try {
					if (!password.equals(confirmPassword)) {
						error = "Passwords do not match";
						throw new Exception("Passwords do not match");
					}
					if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")) {
						error="Password does not meet requierments";
						throw new Exception("Password does not meet requierments");
					}
					Connection connection = null;
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "test_user", "testPW");
		            Statement statement = connection.createStatement();
		            String query = "INSERT INTO TESTMOFFATBAYDB.USER VALUES ('"+userEmail+"','"+firstName+"','"+
		            lastName+"','"+phoneNumber+"','"+birthDate+"','"+encryptPass(password)+"');";
		            int sqlCode = statement.executeUpdate(query);
		            if (sqlCode != 1){ 
		            	error = "An account already exists for this email";
		            	throw new Exception("SQL error");
		            }
		            log("User Inserted");
		            
		            session.setAttribute("User", userEmail);
		            ServletContext sc = getServletContext();
		            // reroute to landing when logged in
			        RequestDispatcher rd = sc.getRequestDispatcher("/Landing.jsp");
			        rd.forward(request, response);
				}
				catch(Exception e){
					if (e.getMessage().startsWith("Duplicate")) { 
						error = "An account already exists for this email";
					}
					else if (error == null){
						error = "Something went wrong";
					}
					request.setAttribute("registrationError", error);
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/UserRegistration.jsp");
					log("Something went wrong");
			        rd.forward(request, response);
				}
			}
		}
		// other post requests like login here
		doGet(request, response);
	}
	
	// Encryption and decryption made with the help of Google AI Overview
	private static String encryptPass(String pass) throws Exception {
        SecretKeySpec key = new SecretKeySpec(STATIC_KEY.getBytes("UTF-8"), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(pass.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
	private static String decryptPass(String encryptedPass) throws Exception {
        SecretKeySpec key = new SecretKeySpec(STATIC_KEY.getBytes("UTF-8"), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPass);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, "UTF-8");
    }

}

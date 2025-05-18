import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import beans.Reservation;
import beans.Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/* Moffat Bay Lodge Servlet
 * Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas 
 * This file controls the backend code of the application
 * The doPost method controls the actions on a form submission
 * 
 */

@WebServlet("/LodgeServlet")
public class LodgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String STATIC_KEY = "F45gK0ieu7o2UBB3";
	private static final String ALGORITHM = "AES";

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public LodgeServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletContext sc = getServletContext();
        // reroute to landing when at first load
        RequestDispatcher rd = sc.getRequestDispatcher("/Landing.jsp");
        rd.forward(request, response);
	}
	// Handles form submissions
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		            connection.close();
		            if (sqlCode != 1){ 
		            	error = "An account already exists for this email";
		            	throw new Exception("SQL error");
		            }
		            log("User Inserted");
		            Date birthDateDate = new SimpleDateFormat("yyy-MM-dd").parse(birthDate);
		            User user = new User(userEmail,firstName,lastName,phoneNumber,birthDateDate,encryptPass(password));
		            session.setAttribute("User", user);
		            ServletContext sc = getServletContext();
		            // reroute to landing when logged in
			        RequestDispatcher rd = sc.getRequestDispatcher("/Landing.jsp");
			        rd.forward(request, response);

			} catch (Exception e) {
				if (e.getMessage().startsWith("Duplicate")) {
					error = "An account already exists for this email";
				} else if (error == null) {
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
		//doGet(request, response);
		if (submitValue != null) {
			if (submitValue.equals("Sign in")){						
				//A sign in request was submitted
				log("sign in submit");
				//String userEmail = request.getParameter("userEmail");
				//String password = request.getParameter("password");
				try {
					String sql = "SELECT * FROM TESTMOFFATBAYDB.USER WHERE UserEmail=? AND Password=?";  //SQL
					String userEmail = request.getParameter("userEmail");
					String password = request.getParameter("password");
					Connection connection = null;  //mysql connection
					PreparedStatement statement = null;   //mysql statement
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "test_user", "testPW");
		            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, 
	                        ResultSet.CONCUR_UPDATABLE);
		            statement.setString(1,userEmail);
		            statement.setString(2,encryptPass(password));
		            ResultSet rs = statement.executeQuery();
		            rs.last();                              //move the cursor to the last row
		            int numberOfRows = rs.getRow();         //get the number of rows
		            rs.beforeFirst();                       //back to initial state
		            if (numberOfRows > 0);
		            	else { throw new Exception("SQL error");}
		            log("User Signed In");
		            rs.next();
		            User user = new User(userEmail,rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),encryptPass(password));
		            session.setAttribute("User", user);
		            ServletContext sc = getServletContext();
		            // reroute to landing when logged in
			        RequestDispatcher rd = sc.getRequestDispatcher("/Landing.jsp");
			        rd.forward(request, response);
				}
				catch(Exception e){
					log(e.getMessage());
					request.setAttribute("loginError", "Please try again");
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/Login.jsp");
					log("Something went wrong");
			        rd.forward(request, response);
				}		
			}
		}
		if (submitValue != null) {
			if (submitValue.equals("Reserve")){	
				try {
					if (request.getSession().getAttribute("User") == null) {
						throw new Exception("Please sign in before booking");
					}
					int roomTypeID;
					switch(request.getParameter("roomType")){
						case "Double Full":
							roomTypeID = 1;
							break;
						case "Queen":
							roomTypeID = 2;
							break;
						case "Double Queen":
							roomTypeID = 3;
							break;
						case "King":
							roomTypeID = 4;
							break;
						default:
							throw new Exception("No room type selected");
					}
					LocalDateTime arrivalDateTime = LocalDateTime.parse(request.getParameter("arrivalDateTime"),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
					LocalDateTime departureDateTime = LocalDateTime.parse(request.getParameter("departureDateTime"),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
					long daysDifference = ChronoUnit.DAYS.between(arrivalDateTime.toLocalDate(), departureDateTime.toLocalDate());
					if (daysDifference < 1 ) {
						throw new Exception("Departure date must be at least one day after arrival");
					}
					User user = (User)session.getAttribute("User");
					int numAdults = Integer.parseInt(request.getParameter("numAdults"));
					int numChildren = Integer.parseInt(request.getParameter("numChildren"));
					Reservation reservation = new Reservation(user.getUserEmail(),request.getParameter("addressLine1"),request.getParameter("addressLine2"),request.getParameter("addressCity"),
							request.getParameter("addressState"),request.getParameter("addressZip"),arrivalDateTime,departureDateTime,numAdults,numChildren, roomTypeID);
					session.setAttribute("Reservation", reservation);
					String sql = "SELECT * FROM TESTMOFFATBAYDB.ROOM WHERE RoomTypeID =?";
					Connection connection = null;  //mysql connection
					PreparedStatement statement = null;   //mysql statement
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "test_user", "testPW");
		            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, 
	                        ResultSet.CONCUR_UPDATABLE);
		            statement.setString(1,roomTypeID+"");
		            ResultSet rs = statement.executeQuery();
		            rs.last();                              //move the cursor to the last row
		            int numberOfRows = rs.getRow();         //get the number of rows
		            rs.beforeFirst();                       //back to initial state
		            if (numberOfRows > 0);
		            	else { throw new Exception("SQL error");}
		            log("User Signed In");
		            rs.next();
		            Room room = new Room(roomTypeID, rs.getString(2),rs.getDouble(3));
		            //store room information in session for reservation summary page
		            session.setAttribute("Room", room);
					log("Reserve submitted, not yet confirmed");
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/Summary.jsp");
			        rd.forward(request, response);
				}
				catch(Exception e) {
					log(e.getMessage());
					request.setAttribute("reservationError", e.getMessage());
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/Reservations.jsp");
					log("Something went wrong");
			        rd.forward(request, response);
				}
			}
		}
		if (submitValue != null) {
			if (submitValue.equals("Logout")){	
				session.setAttribute("User",null);
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/Login.jsp");
			}
		}
		if (submitValue != null) {
			if (submitValue.equals("Make Reservation")){	
				try {
					if (request.getSession().getAttribute("User") == null) {
						throw new Exception("Please sign in before booking");
					}
					Reservation reservation = (Reservation)session.getAttribute("Reservation");
					String userEmail = reservation.getUserEmail();
					String address1 = reservation.getAddressLine1();
					String address2 = reservation.getAddressLine2();
					String city = reservation.getAddressCity();
					String state = reservation.getAddressState();
					String zip = reservation.getAddressZip();
					LocalDateTime arrival = reservation.getArrivalDateTime();
					LocalDateTime departure = reservation.getDepartureDateTime();
					int numAdults = reservation.getNumAdults();
					int numChildren = reservation.getNumChildren();
					int roomTypeID = reservation.getRoomTypeID();
					String error = null;
							
					Connection connection = null;
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "test_user", "testPW");
		            Statement statement = connection.createStatement();
		            String query = "INSERT INTO TESTMOFFATBAYDB.RESERVATION (UserEmail, AddressLine1, AddressLine2, AddressCity, AddressState, AddressZip,"
		            		+ " ArrivalDateTime, DepartureDateTime, NumAdults, NumChildren, RoomTypeID) VALUES ('"+userEmail+"','"+address1+"','"+
		            address2+"','"+city+"','"+state+"','"+zip+"','"+arrival+"','"+departure+"','"+numAdults+"','"+numChildren+"','"+roomTypeID+"');";
		            int sqlCode = statement.executeUpdate(query);
		            connection.close();
		            if (sqlCode != 1){ 
		            	error = "An account already exists for this email";
		            	throw new Exception("SQL error");
		            }
		            log("Reservation Inserted");
		            ServletContext sc = getServletContext();
		            // reroute to landing when logged in
			        RequestDispatcher rd = sc.getRequestDispatcher("/Landing.jsp");
			        rd.forward(request, response);		            
				}
				catch(Exception e) {
					log(e.getMessage());
					request.setAttribute("reservationError", "Please try again");
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/Reservations.jsp");
					log("Something went wrong");
			        rd.forward(request, response);
				}				
			}
		}
		if (submitValue != null) {
			if (submitValue.equals("Search")){
				try {
					String sql = "SELECT * FROM TESTMOFFATBAYDB.RESERVATION WHERE UserEmail=? OR ReservationID=?";  //SQL
					String searchText = request.getParameter("searchText");
					Connection connection = null;  //mysql connection
					PreparedStatement statement = null;   //mysql statement
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "test_user", "testPW");
		            statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, 
	                        ResultSet.CONCUR_UPDATABLE);
		            statement.setString(1,searchText);
		            statement.setString(2,searchText);
		            ResultSet rs = statement.executeQuery();
		            rs.last();                              //move the cursor to the last row
		            int numberOfRows = rs.getRow();         //get the number of rows
		            rs.beforeFirst();                       //back to initial state
		            if (numberOfRows > 0);
		            	else { throw new Exception("SQL error");}
		            log("Search Query successfuly");
		            ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		            while(rs.next()){
		            	reservations.add(new Reservation(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
		            			rs.getTimestamp(8).toLocalDateTime(),rs.getTimestamp(9).toLocalDateTime(),rs.getInt(10),rs.getInt(11),rs.getInt(12)));
		            }
		            
		            session.setAttribute("searchResults", reservations);
		            log("search results stored to session");
		            log(reservations.toString());
		            ServletContext sc = getServletContext();
			        RequestDispatcher rd = sc.getRequestDispatcher("/Lookup.jsp");
			        rd.forward(request, response);	
					
				}
				catch(Exception e) {
					log(e.getMessage());
					request.setAttribute("searchError", "No results found");
					session.setAttribute("searchResults", null);
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/Lookup.jsp");
					log("Something went wrong");
			        rd.forward(request, response);
				}
			}
			}
		if (submitValue != null) {
			if (submitValue.equals("Logout")){	
				session.setAttribute("User",null);
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			}
		}		
	}

	private static String encryptPass(String pass) throws Exception {
		SecretKeySpec key = new SecretKeySpec(STATIC_KEY.getBytes("UTF-8"), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedBytes = cipher.doFinal(pass.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}
}

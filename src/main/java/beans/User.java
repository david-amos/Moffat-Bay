package beans;

import java.util.Date;
/* Moffat Bay Lodge User Bean
 * Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas 
 * This is an object class to store user info
 */
public class User {
	private String UserEmail;
	private String FirstName;
	private String LastName;
	private String PhoneNumber;
	private Date birthDate;
	private String Password;
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public User(String userEmail, String firstName, String lastName, String phoneNumber, Date birthDate,
			String password) {
		super();
		UserEmail = userEmail;
		FirstName = firstName;
		LastName = lastName;
		PhoneNumber = phoneNumber;
		this.birthDate = birthDate;
		Password = password;
	}
	public User() {
		super();
	}
	
	

}

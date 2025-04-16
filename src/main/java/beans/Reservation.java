package beans;

import java.time.LocalDateTime;
/* Moffat Bay Lodge Reservation Bean
 * Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas 
 * This is an object class to store reservation info
 */
public class Reservation {
	String userEmail;
	String addressLine1;
	String addressLine2;
	String addressCity;
	String addressState;
	String addressZip;
	LocalDateTime arrivalDateTime;
	LocalDateTime departureDateTime;
	int numAdults;
	int numChildren;
	int roomTypeID;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	public String getAddressState() {
		return addressState;
	}
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}
	public String getAddressZip() {
		return addressZip;
	}
	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}
	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}
	public void setDepartureDateTime(LocalDateTime departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
	public int getNumAdults() {
		return numAdults;
	}
	public void setNumAdults(int numAdults) {
		this.numAdults = numAdults;
	}
	public int getNumChildren() {
		return numChildren;
	}
	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}
	public int getRoomTypeID() {
		return roomTypeID;
	}
	public void setRoomTypeID(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}
	public Reservation(String userEmail, String addressLine1, String addressLine2, String addressCity,
			String addressState, String addressZip, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime,
			int numAdults, int numChildren, int roomTypeID) {
		super();
		this.userEmail = userEmail;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZip = addressZip;
		this.arrivalDateTime = arrivalDateTime;
		this.departureDateTime = departureDateTime;
		this.numAdults = numAdults;
		this.numChildren = numChildren;
		this.roomTypeID = roomTypeID;
	}
	public Reservation() {
		super();
	}

}

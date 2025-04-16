package beans;
/* Moffat Bay Lodge Room Bean
 * Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas 
 * This is an object class to store room info
 */
public class Room {
	int roomTypeID;
	String roomDescription;
	Double roomPrice;
	public int getRoomTypeID() {
		return roomTypeID;
	}
	public void setRoomTypeID(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public Double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public Room(int roomTypeID, String roomDescription, Double roomPrice) {
		super();
		this.roomTypeID = roomTypeID;
		this.roomDescription = roomDescription;
		this.roomPrice = roomPrice;
	}
	public Room() {
		super();
	}

}


public class Shipment {
	
	String itemCode;
	int quantity;
	String shipmentDate;
	
	//Constructor
	public Shipment(String itemCode, int quantity, String shipmentDate) {
		this.itemCode = itemCode;
		this.quantity = quantity;
		this.shipmentDate = shipmentDate;
	}
	
	
	public String getItemCode() {
		return itemCode;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getShipmentDate() {
		return shipmentDate;
	}
	
	
	public String toString() {
		String output = itemCode + " " + quantity + " " + shipmentDate;
		return output;
	}
	
	
	

}

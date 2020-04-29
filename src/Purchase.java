import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase {
	
	String itemCode;
	int quantity;
	String purchaseDate;
	
	//Constructor
	public Purchase(String itemCode, int quantity) {
		this.itemCode = itemCode;
		this.quantity = quantity;
		Date currentDate = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyy:MM:dd");
		this.purchaseDate = df.format(currentDate);
	}
	
	//Constructor
	public Purchase(String itemCode, int quantity, String purchaseDate) {
		this.itemCode = itemCode;
		this.quantity = quantity;
		this.purchaseDate = purchaseDate;
	}
	
	
	public String getItemCode() {
		return itemCode;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getPurchaseDate() {
		return purchaseDate;
	}
	
	
	public String toString() {
		String output = itemCode + " " + quantity + " " + purchaseDate;
		return output;
	}

}

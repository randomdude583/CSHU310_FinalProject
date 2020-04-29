
public class Item {

	String itemCode;
	String itemDescription;
	Double price;
	
	
	//Constructor
	public Item(String itemCode, String itemDescription) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.price = 0.0;
	} 
	
	//Constructor
	public Item(String itemCode, String itemDescription, Double price) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.price = price;
	} 
	
	
	
	public String getItemCode() {
		return itemCode;
	}
	
	public String getItemDescription() {
		return itemDescription;
	} 
	
	public double getPrice() {
		return price;
	}
	
	public String toString() {
		String output = itemCode + " " + itemDescription + " " + price;
		return output;
	}
	
	
	
	
	
	
	
	
	
}

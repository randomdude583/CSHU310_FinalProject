import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLHandler {
	
	
	
	
	
	//--------------------------------  CREATE  --------------------------------
	
	public static Item createItem(String itemCode, String itemDescription, double price) throws SQLException{
		Connection connection = null;
        Item item = new Item(itemCode, itemDescription, price);

        connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();

        String sql = String.format("INSERT INTO items (itemCode, itemDescription, price) VALUES ('%s' , '%s', %s);",
                item.getItemCode(),
                item.getItemDescription(),
                item.getPrice());
        sqlStatement.executeUpdate(sql);
        connection.close();

        return item;
	}
	
	
	
	public static Purchase createPurchase(String itemCode, int purchaseQuantity) throws SQLException{
		Connection connection = null;
        Purchase purchase = new Purchase(itemCode, purchaseQuantity);

        connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();

        String sql = String.format("INSERT INTO purchases ( itemID, quantity ) VALUES((SELECT id FROM items WHERE  itemCode = \"%s\" LIMIT 1), %s);" ,
                purchase.getItemCode(),
                purchase.getQuantity());
        sqlStatement.executeUpdate(sql);
        connection.close();

        return purchase;
	}
	
	
	
	public static Shipment createShipment(String itemCode, int shipmentQuantity, String shipmentDate) throws SQLException{
		Connection connection = null;
        Shipment shipment = new Shipment(itemCode, shipmentQuantity, shipmentDate);

        connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
		try {
			myDate = formatter.parse(shipment.getShipmentDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        String sql = String.format("INSERT INTO shipments ( itemID, quantity, shipmentDate ) VALUES((SELECT id FROM items WHERE  itemCode = \"%s\" LIMIT 1), %s, \"%s\");",
                shipment.getItemCode(),
                shipment.getQuantity(),
        		sqlDate);
        sqlStatement.executeUpdate(sql);
        connection.close();

        return shipment;
	} 
	
	
	//--------------------------------  READ  --------------------------------
	
	
	
	public static List<Item> getItems(String itemCode) throws SQLException{
		  Connection connection = null;


	        connection = MySqlDatabase.getDatabaseConnection();
	        Statement sqlStatement = connection.createStatement();
	        String sql = "";
	        sql = "SELECT * FROM items WHERE itemCode LIKE \"" + itemCode + "\";";
	        
	        ResultSet resultSet = sqlStatement.executeQuery(sql);

	        List<Item> items = new ArrayList<Item>();

	        while (resultSet.next()) {
	            String Code = resultSet.getString(2);
	            String itemDescription = resultSet.getString(3);
	            double price = resultSet.getDouble(4);

	            Item item = new Item(Code, itemDescription, price);
	            items.add(item);
	        }
	        resultSet.close();
	        connection.close();
	        return items;
	}
	
	public static List<Shipment> getShipments(String itemCode) throws SQLException{
		Connection connection = null;


        connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();
        
        String sql = "";
        sql = "SELECT items.itemCode, shipments.quantity, shipments.shipmentDate FROM shipments LEFT JOIN items ON shipments.itemID = items.id WHERE itemCode LIKE \"" + itemCode +"\";";
        	
        ResultSet resultSet = sqlStatement.executeQuery(sql);

        List<Shipment> shipments = new ArrayList<Shipment>();

        while (resultSet.next()) {
            String Code = resultSet.getString(1);
            int quantity = resultSet.getInt(2);
            String shipmentDate = resultSet.getString(3);

            Shipment shipment = new Shipment(Code, quantity, shipmentDate);
            shipments.add(shipment);
        }
        resultSet.close();
        connection.close();
        return shipments;
	}
	
	public static List<Purchase> getPurchases(String itemCode) throws SQLException{
		Connection connection = null;


        connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();

        String sql = "";
        sql = "SELECT items.itemCode, purchases.quantity, purchases.purchaseDate FROM purchases LEFT JOIN items ON purchases.itemID = items.id WHERE itemCode LIKE \"" + itemCode + "\";";
        
        ResultSet resultSet = sqlStatement.executeQuery(sql);

        List<Purchase> purchases = new ArrayList<Purchase>();

        while (resultSet.next()) {
            String Code = resultSet.getString(1);
            int quantity = resultSet.getInt(2);
            String purchaseDate = resultSet.getString(3);

            Purchase purchase = new Purchase(Code, quantity, purchaseDate);
            purchases.add(purchase);
        }
        resultSet.close();
        connection.close();
        return purchases;
	}
	
	public static List<Object[]> itemsAvailable(String itemCode) throws SQLException{
		Connection connection = null;
		
		connection = MySqlDatabase.getDatabaseConnection();
		Statement sqlStatement = connection.createStatement();
		
		String sql = "";
		
		sql = "SELECT shipmentInventory.itemCode, shipmentInventory.itemDescription, shipmentInventory.price, (shipmentInventory.shipmentQuantity - purchaseInventory.purchaseQuantity) as InventoryQuantity FROM (SELECT items.itemCode, items.itemDescription, items.price, SUM(ifnull(quantity, 0)) AS shipmentQuantity FROM items LEFT JOIN shipments ON items.id = shipments.itemID GROUP BY items.id) AS shipmentInventory LEFT JOIN (SELECT items.itemCode, items.itemDescription, items.price, SUM(ifnull(quantity, 0)) AS purchaseQuantity FROM items LEFT JOIN purchases ON items.id = purchases.itemID GROUP BY items.id) AS purchaseInventory ON shipmentInventory.itemCode = purchaseInventory.itemCode WHERE shipmentInventory.itemCode LIKE \"" + itemCode + "\";";

        ResultSet resultSet = sqlStatement.executeQuery(sql);
        
        List<Object[]> items = new ArrayList<Object[]>();
        
        while (resultSet.next()) {
            String Code = resultSet.getString(1);
            String Description = resultSet.getString(2);
            Double price = resultSet.getDouble(3);
            int quantity = resultSet.getInt(4);

            Object[] item = new Object[] {Code, Description, price, quantity};
            items.add(item);
        }
        resultSet.close();
        connection.close();
        return items;
	}
	
	
	
	//--------------------------------  UPDATE  --------------------------------
	
	
	public static void updateItem(String itemCode, double price) throws SQLException{
		Connection connection = null;
		
		connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();
				
		String sql = String.format("UPDATE items SET price = %s WHERE itemCode = \"%s\";", price, itemCode);
		sqlStatement.executeUpdate(sql);
        connection.close();
	}
	
	
	
	
	//--------------------------------  DELETE  --------------------------------
	
	
	public static void deleteItem(String itemCode) throws SQLException{
		Connection connection = null;

		 connection = MySqlDatabase.getDatabaseConnection();
	        String sql = "CALL delete_item(?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setString(1, itemCode);
	        
	        preparedStatement.execute();
	        connection.close();
	}
	
	
	public static void deleteShipment(String itemCode) throws SQLException{
		Connection connection = null;

        connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();

        String sql = String.format("DELETE FROM shipments WHERE itemID = (SELECT id FROM items WHERE  itemCode = \"%s\" LIMIT 1) LIMIT 1;", itemCode);
        sqlStatement.executeUpdate(sql);
        connection.close();
	}
	
	
	public static void deletePurchase(String itemCode) throws SQLException{
		Connection connection = null;

        connection = MySqlDatabase.getDatabaseConnection();
        Statement sqlStatement = connection.createStatement();

        String sql = String.format("DELETE FROM purchases WHERE itemID = (SELECT id FROM items WHERE  itemCode = \"%s\" LIMIT 1) LIMIT 1;", itemCode);
        sqlStatement.executeUpdate(sql);
        connection.close();
	}

}

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;


public class Project {
	
	public static void main(String[] args) {
		
		//Check arguments
		checkArgs(args);
		
		//--------------------------------  CREATE  --------------------------------
		
		if(args[0].contentEquals("CreateItem")) {
			String itemCode = args[1];
			String itemDescription = args[2];
			double price = 0.0;
			if(args.length == 4) {
				price = Double.parseDouble(args[3]);
			} 
			
			try {
	            Item item = SQLHandler.createItem(itemCode, itemDescription, price);
	            System.out.println(item.toString());
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to create Item");
	            System.out.println(sqlException.getMessage());
	        }
			
			
			
		} else if(args[0].contentEquals("CreatePurchase")) {
			String itemCode = args[1];
			int purchaseQuantity = Integer.parseInt(args[2]);
			
			try {
	            Purchase purchase = SQLHandler.createPurchase(itemCode, purchaseQuantity);
	            System.out.println(purchase.toString());
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to create Purchase");
	            System.out.println(sqlException.getMessage());
	        }
			
			

		} else if(args[0].contentEquals("CreateShipment")) {
			String itemCode = args[1];
			int shipmentQuantity = Integer.parseInt(args[2]);
			String shipmentDate = args[3];
			
			try {
	            Shipment shipment = SQLHandler.createShipment(itemCode, shipmentQuantity, shipmentDate);
	            System.out.println(shipment.toString());
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to create Item");
	            System.out.println(sqlException.getMessage());
	        }
			
		
		//--------------------------------  READ  --------------------------------
			
		} else if(args[0].contentEquals("GetItems")) {
			String itemCode = args[1];
			
			try {
	            List<Item> items = SQLHandler.getItems(itemCode);
	            for (Item item : items) {
	                System.out.println(item.toString());
	            }
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to get items");
	            System.out.println(sqlException.getMessage());
	        }
			
			
			
		} else if(args[0].contentEquals("GetShipments")) {
			String itemCode = args[1];
			
			try {
	            List<Shipment> shipments = SQLHandler.getShipments(itemCode);
	            for (Shipment shipment : shipments) {
	                System.out.println(shipment.toString());
	            }
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to get shipments");
	            System.out.println(sqlException.getMessage());
	        }
			
			
			
		} else if(args[0].contentEquals("GetPurchases")) {
			String itemCode = args[1];
			
			try {
	            List<Purchase> purchases = SQLHandler.getPurchases(itemCode);
	            for (Purchase purchase : purchases) {
	                System.out.println(purchase.toString());
	            }
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to get purchases");
	            System.out.println(sqlException.getMessage());
	        }
			
		} else if(args[0].contentEquals("ItemsAvailable")) {
			String itemCode = args[1];
			
			try {
	            List<Object[]> items = SQLHandler.itemsAvailable(itemCode);
	            for (Object[] item : items) {
	                System.out.println(item[0] + " " + item[1] + " " + item[2] + " " + item[3]);
	            }
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to get purchases");
	            System.out.println(sqlException.getMessage());
	        }
			
			
		//--------------------------------  UPDATE  --------------------------------
			
		} else if(args[0].contentEquals("UpdateItem")) {
			String itemCode = args[1];
			double price = Double.parseDouble(args[2]);
			
			try {
	            SQLHandler.updateItem(itemCode, price);
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to update item");
	            System.out.println(sqlException.getMessage());
	        }
			
			
		//--------------------------------  DELETE  --------------------------------
			
		} else if(args[0].contentEquals("DeleteItem")) {
			String itemCode = args[1];
			
			try {
	            SQLHandler.deleteItem(itemCode);
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to delete item");
	            System.out.println(sqlException.getMessage());
	        }
			
			
		} else if(args[0].contentEquals("DeleteShipment")) {
			String itemCode = args[1];
			
			try {
	            SQLHandler.deleteShipment(itemCode);
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to delete shipment");
	            System.out.println(sqlException.getMessage());
	        }
			
			
		} else if(args[0].contentEquals("DeletePurchase")) {
			String itemCode = args[1];
			
			try {
	            SQLHandler.deletePurchase(itemCode);
	        } catch (SQLException sqlException) {
	            System.out.println("Failed to delete purchase");
	            System.out.println(sqlException.getMessage());
	        }
			
			
		//------------------------  INITIALIZE DATABASE  ---------------------------
			
		} else if(args[0].contentEquals("InitializeDB")) {
			try {
				SQLHandler.initDB();
			} catch (SQLException sqlException) {
				System.out.println("Failed to initialize database");
				System.out.println(sqlException.getMessage());
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
 	public static void checkArgs(String[] args) {
 		//Check if any arguments exist
 		if(args.length == 0) {
 			printUsage();
 			System.exit(1);
 		}
 		
 		
 		//Print Usage
 		if(args[0].contentEquals("/?")) {
 			printUsage();
 			System.exit(1);
 		
 		
		//CreateItem <itemCode> <itemDescription> <price>
 		} else if(args[0].contentEquals("CreateItem")) {
			if(args.length != 3 && args.length != 4) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("ItemCode too long!");
				printUsage();
				System.exit(1);
			} else if(args[2].length() > 50) {
				System.out.println("Item Description too long!");
				printUsage();
				System.exit(1);
			} else {
				if(args.length == 4) {
					try {
						double price = Double.parseDouble(args[3]);
					} catch(Exception e) {
						System.out.println("Price in wrong format!");
						printUsage();
						System.exit(1);
					}
				}
			}
			
			
		//CreatePurchase <itemCode> <PurchaseQuantity> 
		} else if(args[0].contentEquals("CreatePurchase")) {
			if(args.length != 3) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("Item Code too long!");
				printUsage();
				System.exit(1);
			} else {
				try {
					int quantity = Integer.parseInt(args[2]);
					if(quantity < 0) {
						System.out.println("Quantity Cannot be Negative!");
						printUsage();
						System.exit(1);
					}
				} catch(Exception e) {
					System.out.println("Purchase Quantity in Wrong Format!");
					printUsage();
					System.exit(1);
				}
			}
			
		
		//CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>
		} else if(args[0].contentEquals("CreateShipment")) {
			if(args.length != 4) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("Item Code too long!");
				printUsage();
				System.exit(1);
			} else {	
				try {
					int quantity = Integer.parseInt(args[2]);
					if(quantity < 0) {
						System.out.println("Quantity Cannot be Negative!");
						printUsage();
						System.exit(1);
					}
				} catch(Exception e) {
					System.out.println("Quantity in Wrong Format!");
					printUsage();
					System.exit(1);
				}
				
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			        dateFormat.setLenient(false);
			        dateFormat.parse(args[3].trim());
				} catch(Exception e) {
					System.out.println("Date in Wrong Format!");
					printUsage();
					System.exit(1);
				}
			}
			
			
		//GetItems <itemCode>
		} else if(args[0].contentEquals("GetItems")) {
			if(args.length != 2) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("Item Code too long!");
				printUsage();
				System.exit(1);
			}
	
			
		//GetShipments <itemCode>
		} else if(args[0].contentEquals("GetShipments")) {
			if(args.length != 2) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("Item Code too long!");
				printUsage();
				System.exit(1);
			}
			
			
		//GetPurchases <itemCode>
		} else if(args[0].contentEquals("GetPurchases")) {
			if(args.length != 2) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("Item Code too long!");
				printUsage();
				System.exit(1);
			}
			
		
		//ItemsAvailable <itemCode>
		} else if(args[0].contentEquals("ItemsAvailable")) {
			if(args.length != 2) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("Item Code too long!");
				printUsage();
				System.exit(1);
			}
			
			
		//UpdateItem <itemCode> <price>	
		} else if(args[0].contentEquals("UpdateItem")) {
			if(args.length != 3) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("Item Code too long!");
				printUsage();
				System.exit(1);
			} else {
				try {
					double price = Double.parseDouble(args[2]);
				} catch(Exception e) {
					System.out.println("Price in wrong format!");
					printUsage();
					System.exit(1);
				}
			}
			
			
		//DeleteItem <itemCode> 
		} else if(args[0].contentEquals("DeleteItem")) {
			if(args.length != 2) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("Item Code too long!");
				printUsage();
				System.exit(1);
			}
			
			
		//DeleteShipment <itemCode>
		} else if(args[0].contentEquals("DeleteShipment")) {
			if(args.length != 2) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("Item Code too long!");
				printUsage();
				System.exit(1);
			}
			
			
		//DeletePurchase <itemCode> 
		} else if(args[0].contentEquals("DeletePurchase")) {
			if(args.length != 2) {
				System.out.println("Wrong Format!");
				printUsage();
				System.exit(1);
			} else if(args[1].length() > 10) {
				System.out.println("Item Code too long!");
				printUsage();
				System.exit(1);
			}
			
		} else if(args[0].contentEquals("InitializeDB")) {
			
			
			
		} else {
			System.out.println("Not a valid command!");
			printUsage();
		}
		
	}
	
	
	public static void printUsage() {
		System.out.println("");
		System.out.println("java Project <method> <arguments>..");
		System.out.println("");
		System.out.println("Methods:");
		System.out.println("-----------------------------------");
		System.out.println("CreateItem <itemCode> <itemDescription> <price>");
		System.out.println("CreatePurchase <itemCode> <PurchaseQuantity> ");
		System.out.println("CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>");
		System.out.println("GetItems <itemCode>");
		System.out.println("GetShipments <itemCode>");
		System.out.println("GetPurchases <itemCode>");
		System.out.println("ItemsAvailable <itemCode>");
		System.out.println("UpdateItem <itemCode> <price>");
		System.out.println("DeleteItem <itemCode>");
		System.out.println("DeleteShipment <itemCode>");
		System.out.println("DeletePurchase <itemCode>");
		System.out.println("InitializeDB");
		System.out.println("");
		System.out.println("Arguments");
		System.out.println("----------------------------------");
		System.out.println("itemCode:  String with maximum of 10 characters");
		System.out.println("itemDescription: String with maximum of 50 characters");
		System.out.println("price: Double value");
		System.out.println("PurchaseQuantity: integer value. Must be positive");
		System.out.println("ShipmentQuantity: integer value. Must be positive");
		System.out.println("Format Dates as yyyy-MM-dd");
		System.out.println("\n\n");
		
	}
	

}

# CSHU310_FinalProject
Final Project for CS-HU-310, Database Systems Usage. 



This project simulates a basic inventory system for a concession stand.

## USAGE:
- Before Use, add the following system variables to ~./bashrc 
  - export CLASSPATH=/opt/mysql/mysql-connector-java-8.0.18.jar:.:$CLASSPATH
  - export MYSQL_DATABASE=
  - export MYSQL_HOST=
  - export MYSQL_PORT=
  - export MYSQL_USERNAME=
  - export MYSQL_PASSWORD=
    
- In a terminal, compile the program by running 
  `$javac Project.java`
  
- Run `$java Project InitializeDB` to initialize the tables. 
  -WARNING, THIS WILL ERASE EXISTING DATA

- Run the program by running 
  `$java Project <arguments>`
  

## Methods:
- CreateItem <itemCode> <itemDescription> <price>
- CreatePurchase <itemCode> <PurchaseQuantity>
- CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>
- GetItems <itemCode>
- GetShipments <itemCode>
- GetPurchases <itemCode>
- ItemsAvailable <itemCode>
- UpdateItem <itemCode> <price>
- DeleteItem <itemCode>
- DeleteShipment <itemCode>
- DeletePurchase <itemCode>
- InitializeDB

## Arguments
- itemCode:  String with maximum of 10 characters. Use '%' to match all records
- itemDescription: String with maximum of 50 characters
- price: Double value
- PurchaseQuantity: integer value. Must be positive
- ShipmentQuantity: integer value. Must be positive


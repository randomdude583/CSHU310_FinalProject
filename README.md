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
  
- Using a sql editor, set up the tables by executing the SQL code in the following files
  - create_items
  - create_purchases
  - create_shipments
  
- In a terminal, compile the program by running 
  `$javac Project.java`

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

## Arguments
- itemCode:  String with maximum of 10 characters
- itemDescription: String with maximum of 50 characters
- price: Double value
- PurchaseQuantity: integer value. Must be positive
- ShipmentQuantity: integer value. Must be positive


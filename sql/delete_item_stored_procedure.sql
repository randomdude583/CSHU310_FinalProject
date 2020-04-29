DELIMITER //
CREATE PROCEDURE create_car(IN _itemCode varchar(10))
BEGIN
    DELETE FROM items WHERE itemCode = _itemCode;
END //
DELIMITER ;
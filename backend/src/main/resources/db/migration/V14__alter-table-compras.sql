ALTER TABLE compras
MODIFY data_compra DATETIME;

ALTER TABLE compras
ADD COLUMN valor_compra DECIMAL(10,2);
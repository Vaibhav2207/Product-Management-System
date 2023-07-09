

select * from products;
CREATE TABLE products (
    Product_id int IDENTITY(1,1) PRIMARY KEY,
    Product_name  varchar(255) unique,
    Product_type varchar(255),
    Product_place varchar(255),
	Product_warranty int
)
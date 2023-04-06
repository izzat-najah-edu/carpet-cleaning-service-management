-- Use this SQL code to fill the database with data

-- Admins:
INSERT INTO admin (username, password)
VALUES ('admin', 'admin');

-- Customers:
INSERT INTO customer (name, phone, address)
VALUES ('John Smith', '1234567890', '123 Main St, Anytown, USA'),
       ('Sarah Lee', '2345678901', '456 Elm St, Anytown, USA'),
       ('Michael Brown', '3456789012', '789 Oak St, Anytown, USA'),
       ('Emily Davis', '4567890123', '321 Maple St, Anytown, USA'),
       ('David Garcia', '5678901234', '654 Pine St, Anytown, USA'),
       ('Olivia Hernandez', '6789012345', '987 Cedar St, Anytown, USA'),
       ('James Jackson', '7890123456', '246 Birch St, Anytown, USA'),
       ('Sophia Martin', '8901234567', '135 Walnut St, Anytown, USA'),
       ('Benjamin Nelson', '9012345678', '864 Cherry St, Anytown, USA'),
       ('Emma Perez', '0123456789', '753 Spruce St, Anytown, USA'),
       ('William Rodriguez', '2345678901', '246 Oak St, Anytown, USA'),
       ('Isabella Sanchez', '3456789012', '753 Pine St, Anytown, USA'),
       ('Ethan Taylor', '4567890123', '864 Maple St, Anytown, USA'),
       ('Mia Thompson', '5678901234', '246 Cedar St, Anytown, USA'),
       ('Alexander Walker', '6789012345', '753 Birch St, Anytown, USA'),
       ('Ava White', '7890123456', '864 Walnut St, Anytown, USA'),
       ('Daniel Wilson', '8901234567', '246 Cherry St, Anytown, USA'),
       ('Charlotte Wright', '9012345678', '753 Spruce St, Anytown, USA'),
       ('Ethan Young', '0123456789', '864 Pine St, Anytown, USA'),
       ('Elizabeth Adams', '2345678901', '246 Maple St, Anytown, USA');

-- Orders:
INSERT INTO `order` (customer_id)
VALUES (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7),
       (8),
       (9),
       (10);

-- Products:
INSERT INTO product (customer_id, description)
VALUES (1, 'Large carpet'),
       (1, 'Small rug'),
       (2, 'Curtains'),
       (3, 'Sofa cover'),
       (4, 'Large carpet'),
       (5, 'Small rug'),
       (6, 'Curtains'),
       (7, 'Sofa cover'),
       (8, 'Large carpet'),
       (9, 'Small rug');

-- Order_Product:
INSERT INTO order_product (order_id, product_id, special_treatment, price)
VALUES (1, 1, 'Delicate cleaning', 100),
       (1, 2, 'Delicate cleaning', 50),
       (2, 3, 'Regular cleaning', 80),
       (3, 4, 'Regular cleaning', 90),
       (4, 5, 'Delicate cleaning', 100),
       (5, 6, 'Delicate cleaning', 50),
       (6, 7, 'Regular cleaning', 80),
       (7, 8, 'Regular cleaning', 90),
       (8, 9, 'Delicate cleaning', 100),
       (9, 10, 'Delicate cleaning', 50);

-- Mark some orders as finished
UPDATE order_product
SET finished = TRUE
WHERE order_id IN (1, 2, 4, 5, 7, 8);

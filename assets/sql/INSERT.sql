-- Use this SQL code to fill the database with data

-- admins:
insert into admin (username, password) values ('admin', 'admin'); -- test admin

-- customers:
INSERT INTO customer (name, phone, address) VALUES ('John Smith', '1234567890', '123 Main St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Sarah Lee', '2345678901', '456 Elm St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Michael Brown', '3456789012', '789 Oak St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Emily Davis', '4567890123', '321 Maple St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('David Garcia', '5678901234', '654 Pine St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Olivia Hernandez', '6789012345', '987 Cedar St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('James Jackson', '7890123456', '246 Birch St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Sophia Martin', '8901234567', '135 Walnut St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Benjamin Nelson', '9012345678', '864 Cherry St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Emma Perez', '0123456789', '753 Spruce St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('William Rodriguez', '2345678901', '246 Oak St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Isabella Sanchez', '3456789012', '753 Pine St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Ethan Taylor', '4567890123', '864 Maple St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Mia Thompson', '5678901234', '246 Cedar St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Alexander Walker', '6789012345', '753 Birch St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Ava White', '7890123456', '864 Walnut St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Daniel Wilson', '8901234567', '246 Cherry St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Charlotte Wright', '9012345678', '753 Spruce St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Ethan Young', '0123456789', '864 Pine St, Anytown, USA');
INSERT INTO customer (name, phone, address) VALUES ('Elizabeth Adams', '2345678901', '246 Maple St, Anytown, USA');

-- products:
INSERT INTO product (customer_id, description) VALUES (7, 'Living Room Carpet Cleaning');
INSERT INTO product (customer_id, description) VALUES (8, 'Bedroom Carpet Cleaning');
INSERT INTO product (customer_id, description) VALUES (9, 'Stairway Carpet Cleaning');
INSERT INTO product (customer_id, description) VALUES (10, 'Area Rug Cleaning');
INSERT INTO product (customer_id, description) VALUES (11, 'Upholstery Cleaning');
INSERT INTO product (customer_id, description) VALUES (12, 'Tile & Grout Cleaning');
INSERT INTO product (customer_id, description) VALUES (13, 'Mattress Cleaning');
INSERT INTO product (customer_id, description) VALUES (14, 'Carpet Deodorizing');
INSERT INTO product (customer_id, description) VALUES (15, 'Carpet Protection');
INSERT INTO product (customer_id, description) VALUES (16, 'Carpet Stain Removal');
INSERT INTO product (customer_id, description) VALUES (17, 'Carpet Steam Cleaning');
INSERT INTO product (customer_id, description) VALUES (18, 'Pet Urine Removal Treatment');
INSERT INTO product (customer_id, description) VALUES (19, 'Commercial Carpet Cleaning');
INSERT INTO product (customer_id, description) VALUES (20, 'Odor Elimination');
INSERT INTO product (customer_id, description) VALUES (21, 'Carpet Shampooing');
INSERT INTO product (customer_id, description) VALUES (22, 'Carpet Stretching');
INSERT INTO product (customer_id, description) VALUES (23, 'Water Extraction');
INSERT INTO product (customer_id, description) VALUES (24, 'Carpet Repair');
INSERT INTO product (customer_id, description) VALUES (7, 'Basement Carpet Cleaning');
INSERT INTO product (customer_id, description) VALUES (8, 'Rug Cleaning');
INSERT INTO product (customer_id, description, special_treatment) VALUES (10, 'Carpet cleaning (2 rooms)', 'Deep clean stains');
INSERT INTO product (customer_id, description, special_treatment) VALUES (9, 'Upholstery cleaning (sofa)', 'Remove pet hair');
INSERT INTO product (customer_id, description, special_treatment) VALUES (12, 'Carpet cleaning (3 rooms)', 'Deodorize');
INSERT INTO product (customer_id, description, special_treatment) VALUES (11, 'Area rug cleaning', 'Treat tough stains');
INSERT INTO product (customer_id, description, special_treatment) VALUES (8, 'Tile and grout cleaning', 'Seal grout');
INSERT INTO product (customer_id, description, special_treatment) VALUES (16, 'Carpet cleaning (1 room)', 'Remove high traffic dirt');
INSERT INTO product (customer_id, description, special_treatment) VALUES (14, 'Carpet and upholstery cleaning', 'Sanitize');
INSERT INTO product (customer_id, description, special_treatment) VALUES (19, 'Stain removal', 'Remove red wine stains');

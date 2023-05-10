-- Test database:

create table admin
(
    username varchar(256) primary key,
    password varchar(256) not null
);

create table customer
(
    id      int auto_increment primary key,
    name    varchar(256) unique not null,
    phone   varchar(10),
    address varchar(256),
    email   varchar(256) unique
);

CREATE TABLE `order`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

create table product
(
    id          int primary key auto_increment,
    customer_id int,
    description varchar(256) not null,
    foreign key (customer_id) references customer (id)
);

create table order_product
(
    order_id          int,
    product_id        int,
    special_treatment varchar(256),
    finished          tinyint default (false),
    price             int check ( price >= 0 ),
    primary key (order_id, product_id),
    foreign key (order_id) references `order` (id),
    foreign key (product_id) references product (id)
);

-- Admins:
INSERT INTO admin (username, password)
VALUES ('admin', 'admin');

-- Customers:
INSERT INTO customer (name, phone, address, email)
VALUES ('John Smith', '1234567890', '123 Main St, Anytown, USA', 'izzat5233@hotmail.com'),
       ('Sarah Lee', '2345678901', '456 Elm St, Anytown, USA', 'sarah.lee@example.com'),
       ('Michael Brown', '3456789012', '789 Oak St, Anytown, USA', 'michael.brown@example.com'),
       ('Emily Davis', '4567890123', '321 Maple St, Anytown, USA', 'emily.davis@example.com'),
       ('David Garcia', '5678901234', '654 Pine St, Anytown, USA', 'david.garcia@example.com'),
       ('Olivia Hernandez', '6789012345', '987 Cedar St, Anytown, USA', 'olivia.hernandez@example.com'),
       ('James Jackson', '7890123456', '246 Birch St, Anytown, USA', 'james.jackson@example.com'),
       ('Sophia Martin', '8901234567', '135 Walnut St, Anytown, USA', 'sophia.martin@example.com'),
       ('Benjamin Nelson', '9012345678', '864 Cherry St, Anytown, USA', 'benjamin.nelson@example.com'),
       ('Emma Perez', '0123456789', '753 Spruce St, Anytown, USA', 'emma.perez@example.com'),
       ('William Rodriguez', '2345678901', '246 Oak St, Anytown, USA', 'william.rodriguez@example.com'),
       ('Isabella Sanchez', '3456789012', '753 Pine St, Anytown, USA', 'isabella.sanchez@example.com'),
       ('Ethan Taylor', '4567890123', '864 Maple St, Anytown, USA', 'ethan.taylor@example.com'),
       ('Mia Thompson', '5678901234', '246 Cedar St, Anytown, USA', 'mia.thompson@example.com'),
       ('Alexander Walker', '6789012345', '753 Birch St, Anytown, USA', 'alexander.walker@example.com'),
       ('Ava White', '7890123456', '864 Walnut St, Anytown, USA', 'ava.white@example.com'),
       ('Daniel Wilson', '8901234567', '246 Cherry St, Anytown, USA', 'daniel.wilson@example.com'),
       ('Charlotte Wright', '9012345678', '753 Spruce St, Anytown, USA', 'charlotte.wright@example.com'),
       ('Ethan Young', '0123456789', '864 Pine St, Anytown, USA', 'ethan.young@example.com'),
       ('Elizabeth Adams', '2345678901', '246 Maple St, Anytown, USA', 'elizabeth.adams@example.com');

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

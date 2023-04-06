-- Use this SQL code to create the database from scratch

create database carpet_cleaning_service_management;
create user ccsm identified with 'ccsm1234';
grant all privileges on carpet_cleaning_service_management.* to ccsm;

create table admin
(
    username varchar(256) primary key,
    password varchar(256) not null
);

create table customer
(
    id      int auto_increment primary key,
    name    varchar(256) unique not null,
    phone   char(10),
    address varchar(256)
);

create table `order`
(
    id          int primary key auto_increment,
    customer_id int,
    foreign key (customer_id) references customer (id)
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
    finished          boolean default (false),
    price             int check ( price >= 0 ),
    primary key (order_id, product_id),
    foreign key (order_id) references `order` (id),
    foreign key (product_id) references product (id)
)

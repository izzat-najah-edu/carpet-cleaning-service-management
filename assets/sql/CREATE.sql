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
    name    varchar(256) not null,
    phone   char(10),
    address varchar(256)
);

create table product
(
    number            int,
    customer_id       int,
    description       varchar(256),
    picture           varchar(256), -- path to file
    special_treatment varchar(256),
    primary key (number, customer_id),
    foreign key (customer_id) references customer (id)
);

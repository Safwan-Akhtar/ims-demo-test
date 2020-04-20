create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment not null, first_name varchar(40), surname varchar(40));
create table if not exists ims.item(product_id int primary key auto_increment not null, product_name varchar(40), product_price int);
create table if not exists ims.orders(order_id int primary key auto_increment not null, cust_id int not null, total_price int not null, foreign key (cust_id) references customers (id));
create table if not exists ims.orderline(order_id int not null, product_id int not null, foreign key (order_id) references orders (order_id), foreign key (product_id) references item (product_id));
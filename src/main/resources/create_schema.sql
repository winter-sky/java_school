use online_store;

drop table IF EXISTS item_params;
drop table IF EXISTS order_items;
drop table IF EXISTS params;
drop table IF EXISTS orders;
drop table IF EXISTS clients;
drop table IF EXISTS logins;
drop table IF EXISTS client_addresses;
drop table IF EXISTS items;
drop table IF EXISTS categories;

create table logins (
  login_id INTEGER(18) NOT NULL AUTO_INCREMENT,
  login VARCHAR(256) NOT NULL,
  password VARCHAR(256) NOT NULL,
  role VARCHAR(18)   NOT NULL,
  CONSTRAINT user_id_pk PRIMARY KEY (login_id)
);

create table client_addresses (
  client_address_id INTEGER(18) NOT NULL AUTO_INCREMENT,
  country  VARCHAR(256) NOT NULL,
  zip_code VARCHAR(10) NOT NULL, -- In some countries indexes contain letters (e.g. USA), so VARCHAR
  street VARCHAR(256) NOT NULL,
  building INTEGER(4) NOT NULL,
  apartment  INTEGER(4) NOT NULL,
  CONSTRAINT client_address_pk PRIMARY KEY (client_address_id)
);

create table clients (
  client_id INTEGER(18) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(256) NOT NULL,
  last_name VARCHAR(256) NOT NULL,
  birthdate DATE NOT NULL, -- TODO: maybe not null, discuss
  email VARCHAR(256) NOT NULL,
  client_login INTEGER(18) NOT NULL,
  client_address INTEGER(18) NOT NULL,
  CONSTRAINT client_id_pk PRIMARY KEY (client_id),
  FOREIGN KEY (client_login) REFERENCES logins (login_id),
  FOREIGN KEY (client_address) REFERENCES client_addresses (client_address_id)
);

create table orders (
  order_id  INTEGER (18) NOT NULL AUTO_INCREMENT,
  orders_client INTEGER(18)  NOT NULL,
  orders_client_address INTEGER(18)  NOT NULL,
  payment_method  VARCHAR(18)    NOT NULL, -- TODO: may need to foreign key to payment_method table, but probably java enumeration is ok
  delivery_method VARCHAR(18)    NOT NULL, -- TODO: may need to foreign key to delivery_method table, but probably java enumeration is ok
  payment_state VARCHAR(18)    NOT NULL, -- TODO: may need to foreign key to payment_status table, but probably java enumeration is ok
  order_status VARCHAR(18)    NOT NULL, -- TODO: may need to foreign key to order_status table, but probably java enumeration is ok
CONSTRAINT order_id_pk PRIMARY KEY (order_id),
FOREIGN KEY (orders_client) REFERENCES clients (client_id),
FOREIGN KEY (orders_client_address) REFERENCES client_addresses (client_address_id)
);

create table categories (
  catergory_id INTEGER(18) NOT NULL AUTO_INCREMENT,
  category_name VARCHAR(60)  NOT NULL,
  category_level INTEGER(1) NOT NULL,
  parent_id INTEGER(18) NULL,

  FOREIGN KEY (parent_id) REFERENCES categories (catergory_id),
  CONSTRAINT catergory_id_pk PRIMARY KEY (catergory_id)
);
insert into categories (category_id, category_name, category_level,parent_id )
values (1,'Book Category',0,null);
insert into categories (category_id, category_name, category_level,parent_id )
values (2,'Art',1,1);
insert into categories (category_id, category_name, category_level,parent_id )
values (3,'Literature & Fiction',1,1);

create table items (
  item_id INTEGER(18) NOT NULL AUTO_INCREMENT,
  item_name VARCHAR(60) NOT NULL,
  item_category INTEGER(18) NOT NULL,
  price DOUBLE(30, 2) NOT NULL, -- Price in roubles, 2 digits after dot.
  weight DOUBLE(8, 3)  NOT NULL, -- Weight in kilograms.
  volume VARCHAR(60) NOT NULL, -- TODO: what volume means. Is it dimensions like 34x20x60?
  available_count INTEGER(18) NOT NULL,
  pic VARCHAR(256) NOT NULL,
  FOREIGN KEY (item_category) REFERENCES categories (catergory_id),
  CONSTRAINT item_id_pk PRIMARY KEY (item_id)
);

create table order_items (
  order_items_id INTEGER(18) NOT NULL AUTO_INCREMENT,
  orders INTEGER(18) NOT NULL,
  items  INTEGER(18) NOT NULL,
  quantity  INTEGER(5)  NOT NULL,
  CONSTRAINT order_items_id_pk PRIMARY KEY (order_items_id),
  FOREIGN KEY (orders) REFERENCES orders (order_id),
  FOREIGN KEY (items) REFERENCES items (item_id)
);

create table params (
  param_id INTEGER(18) NOT NULL AUTO_INCREMENT,
  param_name VARCHAR(60)  NOT NULL,
  CONSTRAINT param_id_pk PRIMARY KEY (param_id)
);

create table item_params (
  item_param_id INTEGER(18) NOT NULL AUTO_INCREMENT,
  item  INTEGER(18) NOT NULL,
  param  INTEGER(18) NOT NULL,
  FOREIGN KEY (item) REFERENCES items (item_id),
  FOREIGN KEY (param) REFERENCES params (param_id),
  CONSTRAINT item_param_id_pk PRIMARY KEY (item_param_id)
)
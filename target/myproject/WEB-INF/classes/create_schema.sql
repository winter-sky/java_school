use online_store;

drop table if exists Customes;

create table Customers
(
  customer_id integer NOT NULL AUTO_INCREMENT,
  first_name varchar(512) NOT NULL,
  last_name varchar (512) NOT NULL,
  birth_date DATE NOT NULL,
  email varchar (512) NOT NULL,
  CONSTRAINT customer_id_pk PRIMARY KEY (customer_id)
);
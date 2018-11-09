use online_store;

insert into categories ( category_name, category_level,parent_id )
values ('Book Category',0,null);
insert into categories ( category_name, category_level,parent_id )
values ('Art',1,1);
insert into categories ( category_name, category_level,parent_id )
values ('Literature & Fiction',1,1);
insert into categories ( category_name, category_level,parent_id )
values ('Architecture',2,2);
insert into categories ( category_name, category_level,parent_id )
values ('Drawing',2,2);
insert into categories ( category_name, category_level,parent_id )
values ('Painting',2,2);
insert into categories ( category_name, category_level,parent_id )
values ('British & Irish',2,3);
insert into categories ( category_name, category_level,parent_id )
values ('Genre Fiction',2,3);
insert into categories ( category_name, category_level,parent_id )
values ('Mythology & Folk Tales',2,3);
insert into categories ( category_name, category_level,parent_id )
values ('Fairy Tales',3,9);
insert into categories ( category_name, category_level,parent_id )
values ('Folklore',3,9);
insert into categories ( category_name, category_level,parent_id )
values ('Mythology',3,9);
insert into categories ( category_name, category_level,parent_id )
values ('Pen & Ink Drawing',3,5);
insert into categories ( category_name, category_level,parent_id )
values ('Oil Painting',3,6);
insert into categories ( category_name, category_level,parent_id )
values ('Architecture History',3,4);
insert into categories ( category_name, category_level,parent_id )
values ('Romantic',4,15);
insert into categories ( category_name, category_level,parent_id )
values ('Gothic',3,8);

insert into params (author, language, format)
values ('Lewis Carroll','english','hardcover');
insert into params (author, language, format)
values ('Pic Candle','english','paperback');
insert into params (author, language, format)
values ('Ivan Bilibin','russian','hardcover');
insert into params (author, language, format)
values ('Linda Cateura','english','hardcover');
insert into params (author, language, format)
values ('Robert O\'Byrne','english','hardcover');
insert into params (author, language, format)
values ('Huw Lewis-Jones','english','hardcover');
insert into params (author, language, format)
values ('Gaston Leroux','French','Paperback');
insert into params (author, language, format)
values ('Jiaqi Guan','Chinese','Paperback');

insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('Alice in Wonderland',10,1500,2,'20x80x10',5,'/resources/alice_in_the_wonderland.jpg',1);
insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('Kawaii Doodle Cuties',13,1500,2,'20x80x10',5,'/resources/Kawaii_Doodle_Cuties.jpg',2);
insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('Russian Fairy Tales',12,1500,2,'20x80x10',5,'/resources/bilibin_russian_fairy_tales.jpg',3);
insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('Oil Painting Secrets From a Master',14,1500,2,'20x80x10',5,'/resources/Oil_Painting_Secrets_From_A_Master.jpg',4);
insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('The Irish Aesthete: Ruins of Ireland',16,2500,2,'20x80x10',5,'/resources/The Irish Aesthete Ruins of Ireland.jpg',5);
insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('The Writer\'s Map: An Atlas of Imaginary Lands',7,3000,2,'20x80x10',5,'/resources/An Atlas of Imaginary Lands.jpg',6);
insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('Le Fantôme de l\'Opéra (French Edition)',17,2000,2,'20x80x10',5,'/resources/Le Fantôme de lOpéra.jpg',7);
insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('Qiao Gu Niang de Que Qiao (Chinese Edition)',11,1300,2,'20x80x10',5,'/resources/Qiao Gu Niang de Que Qiao.jpg',8);

insert into roles(username,role)
values('admin','ROLE_ADMIN');
insert into logins(login,password,enabled)
values('admin','$2a$10$KMpGzt6iF58WojlOMDDxc.Lo/Q2VWEs2zGWBCVuTALsy1Uh73hSjW',true);

insert into roles(username,role)
values('Alice','ROLE_USER');
insert into logins(login,password,enabled)
values('Alice','$2a$10$OJCGVWOt8.KUo01T43EuguoCrtmWlovQZz34zEDqYPpkT.g1vmxDm',true);

insert into client_addresses (country,city, zip_code, street,building,apartment)
values ('USA','New York','NY 10028','Oxford Street ','691','139');

insert into clients (first_name, last_name, birthdate, email, client_login, client_address)
values ('Alice','Taiga','1989-04-15','alice@gmail.com',2,1);

INSERT INTO orders (orders_client, orders_client_address, payment_method, delivery_method, payment_state,
                    order_status, order_price, order_date)
VALUES (1, 1, 'MASTERCARD', 'PICKUP','PAID', 'SHIPPED', 3400, '2018-04-04');
INSERT INTO orders (orders_client, orders_client_address, payment_method, delivery_method, payment_state,
                    order_status, order_price, order_date)
VALUES (1, 1, 'MASTERCARD', 'COURIER_DELIVERY', 'PAID', 'DELIVERED', 2400, '2018-04-04');
INSERT INTO orders (orders_client, orders_client_address, payment_method, delivery_method, payment_state,
                    order_status, order_price, order_date)
VALUES (1, 1, 'MASTERCARD', 'PICKUP', 'PAID', 'SHIPPED', 1400, '2018-04-04');
INSERT INTO orders (orders_client, orders_client_address, payment_method, delivery_method, payment_state,
                    order_status, order_price, order_date)
VALUES (1, 1, 'VISA', 'EMS', 'PAID', 'DELIVERED', 3400, '2018-04-04');
INSERT INTO orders (orders_client, orders_client_address, payment_method, delivery_method, payment_state,
                    order_status, order_price, order_date)
VALUES (1, 1, 'PAYPAL', 'PICKUP', 'PAID', 'SHIPPED', 2400, '2018-04-04');
INSERT INTO orders (orders_client, orders_client_address, payment_method, delivery_method, payment_state,
                    order_status, order_price, order_date)
VALUES (1, 1, 'VISA', 'EMS', 'PAID', 'DELIVERED', 1400, '2018-04-04');
INSERT INTO orders (orders_client, orders_client_address, payment_method, delivery_method, payment_state,
                    order_status, order_price, order_date)
VALUES (1, 1, 'PAYPAL', 'COURIER_DELIVERY', 'AWAITING_PAYMENT', 'PAYMENT_AWAITING', 1400, '2018-04-04');

INSERT INTO order_items (orders, items, item_quantity) VALUES (1, 1, 5);
INSERT INTO order_items (orders, items, item_quantity) VALUES (1, 3, 7);
INSERT INTO order_items (orders, items, item_quantity) VALUES (1, 3, 13);
INSERT INTO order_items (orders, items, item_quantity) VALUES (1, 4, 2);
INSERT INTO order_items (orders, items, item_quantity) VALUES (1, 5, 1);
INSERT INTO order_items (orders, items, item_quantity) VALUES (1, 6, 1);
INSERT INTO order_items (orders, items, item_quantity) VALUES (3, 1, 3);
INSERT INTO order_items (orders, items, item_quantity) VALUES (3, 4, 7);
INSERT INTO order_items (orders, items, item_quantity) VALUES (3, 3, 123);
INSERT INTO order_items (orders, items, item_quantity) VALUES (4, 7, 2);
INSERT INTO order_items (orders, items, item_quantity) VALUES (4, 8, 1);
INSERT INTO order_items (orders, items, item_quantity) VALUES (4, 8, 1);




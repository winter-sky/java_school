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

insert into params (author, language, format)
values ('Lewis Carroll','english','hardcover');
insert into params (author, language, format)
values ('Pic Candle','english','paperback');
insert into params (author, language, format)
values ('Ivan Bilibin','russian','hardcover');
insert into params (author, language, format)
values ('Linda Cateura ','english','hardcover');

insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('Alice in Wonderland',10,1500,2,'20x80x10',5,'/resources/alice_in_the_wonderland.jpg',1);
insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('Kawaii Doodle Cuties',13,1500,2,'20x80x10',5,'/resources/Kawaii_Doodle_Cuties.jpg',2);
insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('Russian Fairy Tales',12,1500,2,'20x80x10',5,'/resources/bilibin_russian_fairy_tales.jpg',3);
insert into items(item_name,item_category,price,weight, volume,available_count,pic,params_id)
values ('Oil Painting Secrets From a Master',14,1500,2,'20x80x10',5,'/resources/Oil_Painting_Secrets_From_A_Master.jpg',4);


insert into roles(username,role)
values('admin','ROLE_ADMIN');
insert into logins(login,password,enabled)
values('admin','$2a$10$KMpGzt6iF58WojlOMDDxc.Lo/Q2VWEs2zGWBCVuTALsy1Uh73hSjW',true);

insert into roles(username,role)
values('Alice','ROLE_USER');
insert into logins(login,password,enabled)
values('Alice','$2a$10$QtVoMT6I5o0NwPCfNzeMEuKNr2ZJ4fxd2rSF6HgEHcbzz27nqbu32',true);

insert into client_addresses (country,city, zip_code, street,building,apartment)
values ('USA','New York','NY 10028','Oxford Street ','691','139');

insert into clients (first_name, last_name, birthdate, email, client_login, client_address)
values ('Alice','Taiga','1989-04-15','alice@gmail.com',2,1);
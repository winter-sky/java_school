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

insert into roles(username,role)
values('Leonid','ROLE_USER');
insert into logins(login,password,enabled)
values('Leonid','$2a$10$OJCGVWOt8.KUo01T43EuguoCrtmWlovQZz34zEDqYPpkT.g1vmxDm',true);

insert into client_addresses (country,city, zip_code, street,building,apartment)
values ('USA','New York','NY 10028','Oxford Street ','691','139');

insert into clients (first_name, last_name, birthdate, email, client_login)
values ('Alice','Taiga','1989-04-15','alice@gmail.com',2);

insert into clients_client_addresses (id_client_addresses, id_clients)
values (1,1);



insert into client_addresses (country,city, zip_code, street,building,apartment)
values ('USA','New York','NY 10028','Oxford Street ','127','998');
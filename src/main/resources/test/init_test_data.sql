delete from iterms;
insert into items(item_name,item_category,price,weight, volume,available_count,pic)
values ('Alice in Wonderland',1,1500,2,'20x80x10',5,'file://C://arts//cp5_9cW_xvE.jpg');

delete from categories;
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
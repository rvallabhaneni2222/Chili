insert into yrole (rolename) values ('user');
insert into yuser (username,password_hash) values ('user','user');
insert into  yuser_roles (yuser,roles) values ((select user_id from yuser where username='user'),(select role_id from yrole where rolename ='user'));
insert into yrole (rolename) values ('admin');
insert into yuser (username,password_hash) values ('admin','admin');
insert into  yuser_roles (yuser,roles) values ((select user_id from yuser where username='admin'),(select role_id from yrole where rolename ='user'));
insert into  yuser_roles (yuser,roles) values ((select user_id from yuser where username='admin'),(select role_id from yrole where rolename ='admin'));
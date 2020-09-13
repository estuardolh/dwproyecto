create database dw_database;
create user 'usuario'@'%' identified by 'usuario';
grant all on dw_database.* to 'usuario'@'%';
use dw_database;
create table usuario(id bigint AUTO_INCREMENT, usuario varchar(20), clave varchar(20), nombre varchar(20),
PRIMARY KEY (ID));

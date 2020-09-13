create database dw_database;
create user 'usuario'@'%' identified by 'usuario';
grant all on dw_database.* to 'usuario'@'%';

use dw_database;

drop table estilo;
create table estilo(id bigint AUTO_INCREMENT
, nombre varchar(40)
, PRIMARY KEY (ID));

drop table estilo_subseccion;
create table estilo_subseccion(id bigint AUTO_INCREMENT
, estilo_id bigint
, seccion_nombre varchar(40)
, CONSTRAINT fk_estilo_id
    FOREIGN KEY (estilo_id) 
    REFERENCES estilo(id)
, PRIMARY KEY (ID));

drop table estilo_subseccion_detalle;
create table estilo_subseccion_detalle(id bigint AUTO_INCREMENT
, subseccion_id bigint
, llave varchar(40)
, valor varchar(40)
, CONSTRAINT fk_subseccion_id
    FOREIGN KEY (subseccion_id) 
    REFERENCES estilo_subseccion(id)
, PRIMARY KEY (ID));

drop table menu;
create table menu(id bigint AUTO_INCREMENT
, nombre varchar(40)
, menu_padre_id bigint
, archivo_html varchar(80)
, CONSTRAINT fk_menu_padre_id
    FOREIGN KEY (menu_padre_id) 
    REFERENCES menu(id)
, PRIMARY KEY (ID));

drop table perfil;
create table perfil(id bigint AUTO_INCREMENT
, nombre varchar(20)
, menu_id bigint
, CONSTRAINT fk_menu_id
    FOREIGN KEY (menu_id) 
    REFERENCES menu(id)
, PRIMARY KEY (ID));

drop table usuario;
create table usuario(id bigint AUTO_INCREMENT
, usuario varchar(20) UNIQUE
, clave varchar(20)
, nombre varchar(20)
, perfil_id bigint
, intentos bigint
, CONSTRAINT fk_perfil_id
    FOREIGN KEY (perfil_id) 
    REFERENCES perfil(id)
, PRIMARY KEY (ID));

desc perfil;
insert into estilo values(1,'verde');
insert into estilo_subseccion values(1,1,'p');
insert into estilo_subseccion_detalle values(1,1,'font-color','green');
insert into menu values(1,'Inicio',null,null);
insert into perfil values(1,'Admin',1);
insert into usuario values(1, 'root', 'root', 'root', 1,0);

commit;
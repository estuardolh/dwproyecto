create database dw_database;
create user 'usuario'@'%' identified by 'usuario';
grant all on dw_database.* to 'usuario'@'%';

use dw_database;

drop table menu;
create table menu(id bigint AUTO_INCREMENT
, posicion bigint default 0
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
, PRIMARY KEY (ID));

drop table usuario;
create table usuario(id bigint AUTO_INCREMENT
, usuario varchar(20) UNIQUE
, clave varchar(20)
, nombre varchar(20)
, perfil_id bigint
, intentos bigint
, menu_id bigint
, color_barra varchar(20)
, color_fondo varchar(20)
, color_letra varchar(20)
, CONSTRAINT fk_menu_id
    FOREIGN KEY (menu_id) 
    REFERENCES menu(id)
, CONSTRAINT fk_perfil_id
    FOREIGN KEY (perfil_id) 
    REFERENCES perfil(id)
, PRIMARY KEY (ID));

insert into menu values(1,0,'Usuario',null,null);
insert into menu values(2,0,'Agregar',1,'/usuarios/agregar');
insert into menu values(3,1,'Listar',1,'/usuarios/');
insert into menu values(13,2,'Editar',1,'/usuarios/editar');
insert into menu values(7,2,'Menu',null,null);
insert into menu values(8,0,'Agregar',7,'/menus/agregar');
insert into menu values(9,1,'Listar',7,'/menus/');
insert into menu values(10,3,'Perfil',null,null);
insert into menu values(11,0,'Agregar',10,'/perfiles/agregar');
insert into menu values(12,1,'Listar',10,'/perfiles/');
insert into menu values(14,0,'Importar',null,null);
insert into menu values(15,0,'Herramienta',14,'/importador/herramienta');
insert into menu values(16,0,'Herramienta',null,null);
insert into menu values(17,0,'Herramienta',16,'/herramientas/');

insert into perfil values(1,'Admin');
insert into usuario values(1, 'root', 'root', 'root', 1,0,1, '#007bff', '#FFFFFF', '#000000');

select * from menu;
select * from perfil;
select * from usuario;

commit;

-- importador
drop table herramienta;
create table herramienta (
id bigint AUTO_INCREMENT
, codigo varchar(30)
, nombre varchar(100)
, descripcion varchar(500)
, unidades bigint
, costo double(80,2)
, fecha_ingreso date
, PRIMARY KEY (ID));

SELECT count(*) FROM herramienta;
SELECT * FROM herramienta;
delete from herramienta where id < 1024;
commit;
create table IF NOT EXISTS
    pacientes(
        id int auto_increment primary key,
        nombre varchar(255),
        apellido varchar (255));

create table IF NOT EXISTS
    odontologos(
        id int auto_increment primary key,
        nombre varchar(255),
        apellido varchar (255),
        matricula int);
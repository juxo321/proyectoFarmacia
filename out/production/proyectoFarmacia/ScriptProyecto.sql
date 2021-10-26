CREATE DATABASE IF NOT EXISTS Farmacia;
USE Farmacia;

SET FOREIGN_KEY_CHECKS = 0;

create table Usuario(
	noUsuario int auto_increment primary key not null,
	nombre varchar(40) not null,
    contrasena varchar(10) not null,
    tipo varchar(20) not null

);

create table Venta(
	noVenta int auto_increment primary key not null,
	noUsuario int not null,
    cantidad int not null,
    fecha date not null,
    total float not null,
    foreign key (noUsuario) references Usuario(noUsuario)
);

create table ProductoVenta(
	id int auto_increment primary key not null,
    noVenta int not null,
	noProducto int not null,
	nombreProducto varchar(30) not null,
    cantidad int not null, 
    total float not null,
    foreign key (noVenta) references Venta(noVenta),
    foreign key (noProducto) references ProductoStock(noProductoStock)
);

create table ProductoStock(
	noProductoStock int primary key not null,
	nombreProducto varchar(30) not null,
    cantidad int not null, 
    tipo varchar(20) not null,
    precio float not null
);

create table Compra(
	noCompra int auto_increment primary key not null,
	noUsuario int not null,
    cantidad int not null,
    fecha date not null,
    total float not null,
    foreign key (noUsuario) references Usuario(noUsuario)
);

create table ProductoCompra(
	id int auto_increment primary key not null,
    noCompra int not null,
	noProducto int not null,
	nombreProducto varchar(30) not null,
    cantidad int not null, 
    total float not null,
    foreign key (noCompra) references Compra(noCompra),
    foreign key (noProducto) references Producto(noProducto)
);

create table Producto(
	noProducto int auto_increment primary key not null,
	nombreProducto varchar(30) not null,
    cantidad int not null, 
    tipo varchar(20) not null,
    precio float not null,
    noProvedor int not null,
    foreign key (noProvedor) references Provedor(noProvedor)
);

create table Provedor(
	noProvedor int auto_increment primary key not null,
	nombreProvedor varchar(30) not null,
    ciudad varchar(30) not null
);

create table Cliente(
	id int auto_increment primary key not null,
	nombreCliente varchar(30) not null,
    edad int not null,
    direccion varchar(40) not null
);


SET FOREIGN_KEY_CHECKS = 1;

insert into provedor values(0, "MedsurMedicamentos","Xalapa");
insert into provedor values(0, "pmfarma","CDMX");
insert into provedor values(0, "quiminet","Guadalajara");

insert into Producto values(0,"Vitacilina",20,"Pomada",22.50,1);
insert into Producto values(0,"Paracetamol",50,"Pastillas orales",20.50,1);
insert into Producto values(0,"Barmicil",35,"Pomada",30.00,1);
insert into Producto values(0,"Ketorolaco",25,"Antiinflamatorio",33.50,1);
insert into Producto values(0,"Ultra Bengue",15,"Anestesia",45.00,1);
insert into Producto values(0,"Plena Fem",35,"Suplemento",53.50,1);
insert into Producto values(0,"Aspirina",60,"Salicilato",30.00,2);
insert into Producto values(0,"Keoprofeno",60,"Antiinflamatorio",45.00,2);
insert into Producto values(0,"Ibuprofeno",60,"Antiinflamatorio",32.00,2);
insert into Producto values(0,"Omeprazol",60,"Inhibidores",76.20,2);
insert into Producto values(0,"Diclofenaco",60,"Inhibidores",56.50,2);
insert into Producto values(0,"Loratadina",60,"Descongestionante",80.00,2);
insert into Producto values(0,"Expectorantes",60,"Antitusivos",25.50,3);
insert into Producto values(0,"Alcohol Etílico",60,"Alcohol",20.00,3);
insert into Producto values(0,"Agua Oxigenada",60,"Desinfectante",30.00,3);
insert into Producto values(0,"Bicarbonato",60,"Compuesto solido",45.00,3);
insert into Producto values(0,"Repelente Mosquitos",60,"Producto Aerosol",38.00,3);
insert into Producto values(0,"Protector solar ",60,"Producto Crema",79.50,3);

insert into Usuario values (0,"Justin Hernandez",123456,"administrador");
insert into Usuario values (0,"Joaquin Garcia",45678,"empleado");
insert into Usuario values (0,"Adbel Hernández",11111,"administrador");
insert into Usuario values (0,"Ariana Tejada",22222,"empleado");
insert into Usuario values (0,"Bryan Usias",33333,"administrador");

Insert into compra values(0, 1, 7,"2021-06-13",35.00);
Insert into compra values(0,2,4, "2021-06-12",45.50);
Insert into compra values(0,3,5, "2021-06-18",30.00);
Insert into compra values(0,1,6,"2021-06-16",45.50);
Insert into compra values(0,2,2, "2021-06-18",25.00);
Insert into compra values(0,3,1, "2021-06-18",23.50);
Insert into compra values(0,1,3,"2021-06-13",35.40);
Insert into compra values(0,2,4,"2021-06-15",40.00);
Insert into compra values(0,3,5,"2021-06-14",40.00);
Insert into compra values(0,1,3,"2021-06-13",33.50);




Insert into venta values(0,1,50,"2021-06-12",200.00);
Insert into venta values(0,2,40,"2021-06-13",300.00);
Insert into venta values(0,3,25,"2021-06-15",250.00);
Insert into venta values(0,1,15,"2021-06-12",100.50);
Insert into venta values(0,2,30,"2021-06-18",250.50);
Insert into venta values(0,3,25,"2021-06-12",200.00);
Insert into venta values(0,1,15,"2021-06-13",300.00);
Insert into venta values(0,2,35,"2021-06-12",250.50);
Insert into venta values(0,3,12,"2021-06-18",150.50);
Insert into venta values(0,1,10,"2021-06-12",150.00);
Insert into venta values(0,2,23,"2021-06-18",175.00);

Insert into cliente values(0, "Justino H.", 20, "Av. Revolución");
Insert into cliente values(0, "Alfonso L.",35, "Av 1ro de Mayo");
Insert into cliente values(0, "Marcos M.",41, "Av. 20 de noviembre");
Insert into cliente values(0, "Carlos G.",22, "Av Constitución");
Insert into cliente values(0, "Francisco D.",34, "Av. Independencia");
Insert into cliente values(0, "Isela L.", 24, "Av. Miradores");
Insert into cliente values(0,"Silvia L.", 42, "Av. Degollado");
Insert into cliente values(0, "Octavio L.", 43," Av. Polanco");
Insert into cliente values(0, "Luisa Z.", 45, "Av. polanco");
Insert into cliente values(0, "Delia S.",48, "Av. Sauces");
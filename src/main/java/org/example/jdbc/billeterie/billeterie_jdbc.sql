create table clients (
id int not null primary key auto_increment,
nom VARCHAR(50),
prenom VARCHAR(50),
email VARCHAR(50)
);

create table evenements (
id int not null primary key auto_increment,
 nom VARCHAR(50),
date VARCHAR(50),
heure VARCHAR(50),
prix float,
nb_billet_vendu int,

clients_id int,
lieu_id int,
foreign key (clients_id) references clients(id),
foreign key (lieu_id) references lieu(id)



);

create table lieu(
id int not null primary key auto_increment,
nom VARCHAR(50),
adresse VARCHAR(100),
capacite integer
);


create database ecole_jdbc;
use ecole_jdbc;

create table etudiants (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nom VARCHAR(50),
prenom VARCHAR(50),
nb_classe INT,
date_diplome VARCHAR(20)
);

select * from etudiants;

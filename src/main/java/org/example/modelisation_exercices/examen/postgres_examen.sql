-- PRODUCTION DES TABLES EN POSTGRES

-- CREATE TABLE eleve (
--     id SERIAL PRIMARY KEY,
--     prenom VARCHAR(50) NOT NULL,
--     nom VARCHAR(50) NOT NULL,
--     date_naissance DATE NOT NULL
-- );

-- INSERT INTO eleve (prenom, nom, date_naissance)
-- VALUES 
-- ('Max', 'Lamenace', '1998-02-15'),
-- ('Gilbert', 'Test', '1954-06-15');

-- CREATE TABLE etablissement (
--     id SERIAL PRIMARY KEY,
--     code VARCHAR(50) NOT NULL,
--     nom VARCHAR(50) NOT NULL,
--     adresse VARCHAR(50) NOT NULL,
--     ville VARCHAR(50) NOT NULL
-- );

-- INSERT INTO etablissement (code, nom, adresse, ville)
-- VALUES 
-- ('1A', 'M2i', 'Avenue Horizon', 'VA');

-- CREATE TABLE examen (
--     id SERIAL PRIMARY KEY
   
-- );


-- CREATE TABLE epreuve (
--     id SERIAL PRIMARY KEY,
--     coefficient INT NOT NULL,
--     examen_id INT,
--     FOREIGN KEY (examen_id) REFERENCES examen(id)
-- );


-- INSERT INTO epreuve (coefficient, examen_id)
-- VALUES 
-- (2, NULL); 

-- CREATE TABLE enseignant (
--     id SERIAL PRIMARY KEY,
--     telephone VARCHAR(10) NOT NULL,
--     adresse VARCHAR(50),
--     ville VARCHAR(50),
--     matricule VARCHAR(50) NOT NULL
-- );

-- INSERT INTO enseignant (telephone, adresse, ville, matricule)
-- VALUES 
-- ('0666666666', 'Rue Rue', 'Lille', 'qs4dqs4d');

-- CREATE TABLE dossier_inscription (
--     id SERIAL PRIMARY KEY,
--     eleve_id INT,
--     examen_id INT,
--     etablissement_id INT,
--     FOREIGN KEY (eleve_id) REFERENCES eleve(id),
--     FOREIGN KEY (examen_id) REFERENCES examen(id),
--     FOREIGN KEY (etablissement_id) REFERENCES etablissement(id)
-- );

-- CREATE TABLE eleve_epreuve (
--     eleve_id INT,
--     epreuve_id INT,
--     PRIMARY KEY (eleve_id, epreuve_id),
--     FOREIGN KEY (eleve_id) REFERENCES eleve(id),
--     FOREIGN KEY (epreuve_id) REFERENCES epreuve(id)
-- );

-- CREATE TABLE etablissement_enseignant (
--     etablissement_id INT,
--     enseignant_id INT,
--     PRIMARY KEY (etablissement_id, enseignant_id),
--     FOREIGN KEY (etablissement_id) REFERENCES etablissement(id),
--     FOREIGN KEY (enseignant_id) REFERENCES enseignant(id)
-- );

-- CREATE TABLE enseignant_epreuve (
--     enseignant_id INT,
--     epreuve_id INT,
--     PRIMARY KEY (enseignant_id, epreuve_id),
--     FOREIGN KEY (enseignant_id) REFERENCES enseignant(id),
--     FOREIGN KEY (epreuve_id) REFERENCES epreuve(id)
-- );




-- REQUETES SPECIFIQUES

--  1 - Sélectionner tous les élèves et leurs dates de naissance.
-- SELECT prenom, nom, date_naissance FROM eleve;


 --2 - Trouver la note moyenne pour l'examen 'Baccalauréat'.

-- SELECT AVG(note) FROM notes JOIN examen ON notes.examen_id = examen.id WHERE examen.nom = 'Baccalauréat';


 --3 - Lister tous les enseignants travaillant à 'Paris'.

-- SELECT * FROM enseignant WHERE ville = 'Paris';

-- 4 - Compter le nombre d'élèves inscrits dans chaque établissement.

-- SELECT etablissement.nom, COUNT(dossier_inscription.eleve_id) 
-- FROM dossier_inscription 
-- JOIN etablissement ON dossier_inscription.etablissement_id = etablissement.id 
-- GROUP BY etablissement.nom;


-- 5 - Obtenir les noms de tous les élèves ayant passé un examen avec une note supérieure à 15.

-- SELECT eleve.prenom, eleve.nom FROM eleve 
-- JOIN notes ON eleve.id = notes.eleve_id 
-- WHERE notes.note > 15;


 --6 - Afficher les examens et les noms des épreuves correspondantes avec leurs coefficients.

-- SELECT examen.nom, epreuve.nom, epreuve.coefficient 
-- FROM epreuve 
-- JOIN examen ON epreuve.examen_id = examen.id;

--7 - Lister les établissements avec le nombre d'examens différents pris, triés par le nombre d'examens.
-- SELECT etablissement.nom, COUNT(DISTINCT dossier_inscription.examen_id) 
-- FROM dossier_inscription 
-- JOIN etablissement ON dossier_inscription.etablissement_id = etablissement.id 
-- GROUP BY etablissement.nom 
-- ORDER BY COUNT(DISTINCT dossier_inscription.examen_id) DESC;


-- 8 - Trouver tous les élèves qui n'ont passé aucun examen :
-- SELECT * FROM eleve 
-- WHERE id NOT IN (SELECT DISTINCT eleve_id FROM dossier_inscription);


-- 9 - Identifier les enseignants qui ont à la fois rédigé et corrigé la même épreuve :
-- 
-- SELECT enseignant.id, enseignant.nom 
-- FROM enseignant 
-- JOIN epreuve ON enseignant.id = epreuve.redacteur_id AND enseignant.id = epreuve.correcteur_id;

-- 10 - Montrer le dernier examen que chaque élève a passé, avec la date et la note :
-- SELECT eleve.prenom, eleve.nom, MAX(notes.date_examen), notes.note
-- FROM eleve
-- JOIN notes ON eleve.id = notes.eleve_id
-- GROUP BY eleve.prenom, eleve.nom;




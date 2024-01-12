-- -- Table Établissement
-- CREATE TABLE etablissement (
--     code_etablissement SERIAL PRIMARY KEY,
--     nom VARCHAR(255),
--     adresse VARCHAR(255),
--     ville VARCHAR(100)
-- );

-- -- Table Enseignant
-- CREATE TABLE enseignant (
--     matricule SERIAL PRIMARY KEY,
--     nom VARCHAR(255),
--     telephone VARCHAR(20),
--     adresse VARCHAR(255),
--     ville VARCHAR(100),
--     code_etablissement INT,
--     FOREIGN KEY (code_etablissement) REFERENCES etablissement(code_etablissement)
-- );

-- -- Table Examen
-- CREATE TABLE examen (
--     nom_examen VARCHAR(255) PRIMARY KEY,
--     date_examen DATE
-- );

-- -- Table Épreuve
-- CREATE TABLE epreuve (
--     id_epreuve SERIAL PRIMARY KEY,
--     nom_examen VARCHAR(255),
--     coefficient INT,
--     date_epreuve DATE,
--     FOREIGN KEY (nom_examen) REFERENCES examen(nom_examen)
-- );

-- -- Table Élève
-- CREATE TABLE eleve (
--     numero_inscription SERIAL PRIMARY KEY,
--     nom VARCHAR(255),
--     date_naissance DATE,
--     code_etablissement INT,
--     nom_examen VARCHAR(255),
--     FOREIGN KEY (code_etablissement) REFERENCES etablissement(code_etablissement),
--     FOREIGN KEY (nom_examen) REFERENCES examen(nom_examen)
-- );

-- -- Table Dossier d'Inscription
-- CREATE TABLE dossier_inscription (
--     numero_dossier SERIAL PRIMARY KEY,
--     numero_inscription INT,
--     date_depot DATE,
--     FOREIGN KEY (numero_inscription) REFERENCES eleve(numero_inscription)
-- );

-- -- Table Note
-- CREATE TABLE note (
--     id_note SERIAL PRIMARY KEY,
--     numero_inscription INT,
--     id_epreuve INT,
--     valeur_note INT,
--     FOREIGN KEY (numero_inscription) REFERENCES eleve(numero_inscription),
--     FOREIGN KEY (id_epreuve) REFERENCES epreuve(id_epreuve)
-- );

-- -- Table Redaction
-- CREATE TABLE redaction (
--     id_redaction SERIAL PRIMARY KEY,
--     id_epreuve INT,
--     matricule_enseignant INT,
--     FOREIGN KEY (id_epreuve) REFERENCES epreuve(id_epreuve),
--     FOREIGN KEY (matricule_enseignant) REFERENCES enseignant(matricule)
-- );

-- -- Table Correction
-- CREATE TABLE correction (
--     id_correction SERIAL PRIMARY KEY,
--     id_epreuve INT,
--     matricule_enseignant INT,
--     FOREIGN KEY (id_epreuve) REFERENCES epreuve(id_epreuve),
--     FOREIGN KEY (matricule_enseignant) REFERENCES enseignant(matricule)
-- );
-- INSERT INTO redaction (id_epreuve, matricule_enseignant) VALUES
-- (1, 1),  -- L'enseignant avec matricule 1 rédige l'épreuve 1
-- (2, 2);  -- L'enseignant avec matricule 2 rédige l'épreuve 2

-- -- Utilisation des mêmes id_epreuve et matricules d'enseignant
-- INSERT INTO correction (id_epreuve, matricule_enseignant) VALUES
-- (1, 1),  -- L'enseignant avec matricule 1 corrige l'épreuve 1
-- (2, 2);  -- L'enseignant avec matricule 2 corrige l'épreuve 2


-- -- Insertion dans Établissement
-- INSERT INTO etablissement (nom, adresse, ville) VALUES
-- ('Lycée Victor Hugo', '123 Rue de la Liberté', 'Paris'),
-- ('Collège Jean Moulin', '456 Avenue de la République', 'Lyon');

-- -- Insertion dans Enseignant
-- INSERT INTO enseignant (nom, telephone, adresse, ville, code_etablissement) VALUES
-- ('Jean Dupont', '0123456789', '789 Rue des Écoles', 'Paris', 1),
-- ('Marie Curie', '0987654321', '321 Avenue des Savoirs', 'Lyon', 2);

-- -- Insertion dans Examen
-- INSERT INTO examen (nom_examen, date_examen) VALUES
-- ('Baccalauréat 2024', '2024-06-20'),
-- ('Brevet 2024', '2024-06-25');

-- -- Insertion dans Épreuve
-- INSERT INTO epreuve (nom_examen, coefficient, date_epreuve) VALUES
-- ('Baccalauréat 2024', 2, '2024-06-21'),
-- ('Brevet 2024', 1, '2024-06-26');

-- -- Insertion dans Élève
-- INSERT INTO eleve (nom, date_naissance, code_etablissement, nom_examen) VALUES
-- ('Alice Martin', '2006-04-15', 1, 'Baccalauréat 2024'),
-- ('Lucas Durand', '2006-05-22', 2, 'Brevet 2024');

-- -- Insertion dans Dossier d'Inscription
-- INSERT INTO dossier_inscription (numero_inscription, date_depot) VALUES
-- (1, '2023-12-01'),
-- (2, '2023-12-05');

-- -- Insertion dans Note
-- INSERT INTO note (numero_inscription, id_epreuve, valeur_note) VALUES
-- (1, 1, 15),
-- (2, 2, 12);

--Sélectionner tous les élèves et leurs dates de naissance.
--SELECT nom, date_naissance FROM eleve;


--Trouver la note moyenne pour l'examen 'Baccalauréat'
-- SELECT AVG(n.valeur_note) AS moyenne
-- FROM note n
-- JOIN epreuve e ON n.id_epreuve = e.id_epreuve
-- JOIN examen ex ON e.nom_examen = ex.nom_examen
-- WHERE ex.nom_examen = 'Baccalauréat 2024';


--Lister tous les enseignants travaillant à 'Paris'.
--SELECT nom FROM enseignant WHERE ville = 'Paris';

--Compter le nombre d'élèves inscrits dans chaque établissement.
-- SELECT e.nom, COUNT(el.numero_inscription) AS nombre_eleves
-- FROM etablissement e
-- JOIN eleve el ON e.code_etablissement = el.code_etablissement
-- GROUP BY e.nom;

--Obtenir les noms de tous les élèves ayant passé un examen avec une note supérieure à 15.
-- SELECT DISTINCT el.nom
-- FROM eleve el
-- JOIN note n ON el.numero_inscription = n.numero_inscription
-- WHERE n.valeur_note > 15;

--Afficher les examens et les noms des épreuves correspondantes avec leurs coefficients.
-- SELECT ex.nom_examen, e.id_epreuve, e.coefficient
-- FROM examen ex
-- JOIN epreuve e ON ex.nom_examen = e.nom_examen;


--Lister les établissements avec le nombre d'examens différents pris, triés par le nombre d'examens.
-- SELECT et.nom, COUNT(DISTINCT el.nom_examen) AS nombre_examens
-- FROM etablissement et
-- JOIN eleve el ON et.code_etablissement = el.code_etablissement
-- GROUP BY et.nom
-- ORDER BY nombre_examens DESC;

--Trouver tous les élèves qui n'ont passé aucun examen.
-- SELECT el.nom
-- FROM eleve el
-- LEFT JOIN note n ON el.numero_inscription = n.numero_inscription
-- WHERE n.numero_inscription IS NULL;


--Identifier les enseignants qui ont à la fois rédigé et corrigé la même épreuve.
-- SELECT DISTINCT
--     e.nom AS enseignant,
--     ep.id_epreuve,
--     ep.nom_examen
-- FROM redaction r
-- JOIN correction c ON r.id_epreuve = c.id_epreuve AND r.matricule_enseignant = c.matricule_enseignant
-- JOIN enseignant e ON r.matricule_enseignant = e.matricule
-- JOIN epreuve ep ON r.id_epreuve = ep.id_epreuve;



--Montrer le dernier examen que chaque élève a passé, avec la date et la note.
-- SELECT el.nom, ex.nom_examen, ex.date_examen, MAX(n.valeur_note) AS note
-- FROM eleve el
-- JOIN examen ex ON el.nom_examen = ex.nom_examen
-- JOIN note n ON el.numero_inscription = n.numero_inscription
-- GROUP BY el.nom, ex.nom_examen, ex.date_examen;




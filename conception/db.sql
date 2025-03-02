CREATE SEQUENCE role_id_seq START 1;
CREATE SEQUENCE utilisateur_id_seq START 1;
CREATE SEQUENCE avion_id_seq START 1;
CREATE SEQUENCE vol_id_seq START 1;
CREATE SEQUENCE reservation_id_seq START 1;
CREATE SEQUENCE continent_id_seq START 1;
CREATE SEQUENCE ville_id_seq START 1;
CREATE SEQUENCE type_siege_id_seq START 1;
CREATE SEQUENCE modele_id_seq START 1;

CREATE TABLE role (
    id VARCHAR(6) PRIMARY KEY DEFAULT CONCAT('RLE', LPAD(nextval('role_id_seq')::TEXT, 3, '0')),
    valeur VARCHAR(250) UNIQUE NOT NULL
);
INSERT INTO role (valeur) VALUES ('User');
INSERT INTO role (valeur) VALUES ('Admin');

CREATE TABLE utilisateur (
    id VARCHAR(6) PRIMARY KEY DEFAULT CONCAT('USR', LPAD(nextval('utilisateur_id_seq')::TEXT, 3, '0')),
    nom VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    mdp VARCHAR(250) NOT NULL,
    id_role VARCHAR(6) REFERENCES role(id)
);
INSERT INTO utilisateur(nom, email, mdp, id_role)
VALUES
    ('Rabenaivo', 'rabenaivolucas@gmail.com', md5('rabenaivo'), 'RLE002'),
    ('Ralison', 'ralison@gmail.com', md5('ralison'), 'RLE001');

CREATE TABLE continent (
    id VARCHAR(6) PRIMARY KEY DEFAULT CONCAT('CNT', LPAD(nextval('continent_id_seq')::TEXT, 3, '0')),
    nom VARCHAR(50) NOT NULL
);
INSERT INTO continent (nom)
VALUES
    ('Afrique'),
    ('Amérique'),
    ('Asie'),
    ('Europe');


CREATE TABLE ville_desservie (
    id VARCHAR(6) PRIMARY KEY DEFAULT CONCAT('VIL', LPAD(nextval('ville_id_seq')::TEXT, 3, '0')),
    nom VARCHAR(100) NOT NULL,
    id_continent VARCHAR(6),
    FOREIGN KEY (id_continent) REFERENCES continent(id)
);
INSERT INTO ville_desservie (nom, id_continent)
VALUES
    ('Paris', 'CNT004'),
    ('Londres', 'CNT004'),
    ('New York', 'CNT002'),
    ('Tokyo', 'CNT003'),
    ('São Paulo', 'CNT002'),
    ('Le Caire', 'CNT001');


CREATE TABLE type_siege (
    id VARCHAR(6) PRIMARY KEY DEFAULT CONCAT('TSI', LPAD(nextval('type_siege_id_seq')::TEXT, 3, '0')),
    nom VARCHAR(50) NOT NULL
);
INSERT INTO type_siege (nom)
VALUES
    ('Économique'),
    ('Affaires'),
    ('Première classe'),
    ('Premium Économique');


CREATE TABLE modele (
    id VARCHAR(6) PRIMARY KEY DEFAULT CONCAT('MOD', LPAD(nextval('modele_id_seq')::TEXT, 3, '0')),
    nom VARCHAR(250)
);
INSERT INTO modele (nom)
VALUES
    ('Airbus A320'),
    ('Boeing 737'),
    ('Airbus A380'),
    ('Boeing 777'),
    ('Airbus A350'),
    ('Boeing 787 Dreamliner'),
    ('Embraer E190'),
    ('Bombardier CRJ900'),
    ('Airbus A321'),
    ('Boeing 767');


CREATE TABLE avion (
    id VARCHAR(6) PRIMARY KEY DEFAULT CONCAT('AVN', LPAD(nextval('avion_id_seq')::TEXT, 3, '0')),
    id_modele VARCHAR(6) REFERENCES modele(id),
    date_fabrication DATE
);
INSERT INTO avion (id_modele, date_fabrication)
VALUES
    ('MOD001', '2020-05-01'),
    ('MOD002', '2018-09-15'),
    ('MOD003', '2019-01-12'),
    ('MOD004', '2017-03-10');


CREATE TABLE avion_details_type_siege (
    id_type_siege VARCHAR(6) REFERENCES type_siege(id),
    id_avion VARCHAR(6) REFERENCES avion(id),
    nombre_place INT,
    PRIMARY KEY (id_type_siege, id_avion)
);

INSERT INTO avion_details_type_siege (id_type_siege, id_avion, nombre_place)
VALUES
-- Airbus A320
('TSI001', 'AVN001', 150),
('TSI002', 'AVN001', 20),
('TSI003', 'AVN001', 5),
('TSI004', 'AVN001', 5),

-- Boeing 737
('TSI001', 'AVN002', 160),
('TSI002', 'AVN002', 15),
('TSI003', 'AVN002', 8),
('TSI004', 'AVN002', 7),

-- Airbus A380
('TSI001', 'AVN003', 300),
('TSI002', 'AVN003', 60),
('TSI003', 'AVN003', 15),
('TSI004', 'AVN003', 25),

-- Boeing 777
('TSI001', 'AVN004', 250),
('TSI002', 'AVN004', 50),
('TSI003', 'AVN004', 12),
('TSI004', 'AVN004', 20);


CREATE TABLE vol (
    id VARCHAR(6) PRIMARY KEY DEFAULT CONCAT('VOL', LPAD(nextval('vol_id_seq')::TEXT, 3, '0')),
    id_avion VARCHAR(6) REFERENCES avion(id),
    id_ville_depart VARCHAR(6) REFERENCES ville_desservie(id),
    id_ville_arrivee VARCHAR(6) REFERENCES ville_desservie(id),
    date_depart TIMESTAMP,
    date_arrivee TIMESTAMP
);
INSERT INTO vol (id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee)
VALUES
    ('AVN001', 'VIL004', 'VIL004', '2025-03-15 08:00:00', '2025-03-15 10:30:00'),
    ('AVN002', 'VIL004', 'VIL002', '2025-03-16 09:00:00', '2025-03-16 12:00:00'),
    ('AVN003', 'VIL002', 'VIL003', '2025-03-17 14:00:00', '2025-03-17 18:00:00'),
    ('AVN004', 'VIL003', 'VIL005', '2025-03-18 16:00:00', '2025-03-18 20:00:00');

CREATE TABLE vol_type_siege (
                                id_vol VARCHAR(6) REFERENCES vol(id),
                                id_type_siege VARCHAR(6) REFERENCES type_siege(id),
                                prix DECIMAL(10, 2),
                                PRIMARY KEY (id_vol, id_type_siege)
);
INSERT INTO vol_type_siege (id_vol, id_type_siege, prix)
VALUES
    ('VOL001', 'TSI001', 150.00),
    ('VOL001', 'TSI002', 250.00),
    ('VOL001', 'TSI003', 500.00),
    ('VOL001', 'TSI004', 350.00),

    ('VOL002', 'TSI001', 160.00),
    ('VOL002', 'TSI002', 220.00),
    ('VOL002', 'TSI003', 450.00),
    ('VOL002', 'TSI004', 330.00),

    ('VOL003', 'TSI001', 180.00),
    ('VOL003', 'TSI002', 280.00),
    ('VOL003', 'TSI003', 600.00),
    ('VOL003', 'TSI004', 400.00),

    ('VOL004', 'TSI001', 170.00),
    ('VOL004', 'TSI002', 270.00),
    ('VOL004', 'TSI003', 550.00),
    ('VOL004', 'TSI004', 380.00);


CREATE TABLE reservation (
    id VARCHAR(6) PRIMARY KEY DEFAULT CONCAT('RES', LPAD(nextval('reservation_id_seq')::TEXT, 3, '0')),
    id_utilisateur VARCHAR(6) REFERENCES utilisateur(id),
    id_vol VARCHAR(6) REFERENCES vol(id),
    date_reservation TIMESTAMP
);
INSERT INTO reservation (id_utilisateur, id_vol, date_reservation)
VALUES
    ('USR001', 'VOL001', '2025-03-10 09:00:00'),
    ('USR002', 'VOL002', '2025-03-10 09:30:00');


CREATE TABLE reservation_details (
    id_reservation VARCHAR(6) REFERENCES reservation(id),
    id_type_siege VARCHAR(6) REFERENCES type_siege(id),
    nombre_places INT,
    PRIMARY KEY (id_reservation, id_type_siege)
);
INSERT INTO reservation_details (id_reservation, id_type_siege, nombre_places)
VALUES
    ('RES001', 'TSI001', 2),
    ('RES001', 'TSI002', 1),
    ('RES002', 'TSI001', 3),
    ('RES002', 'TSI003', 1);

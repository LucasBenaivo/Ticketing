create table role (
    id serial primary key ,
    valeur varchar(250)UNIQUE NOT NULL unique not null
);
insert into role (valeur) values ('User');
insert into role (valeur) values ('Admin');

create table utilisateur (
    id serial primary key,
    nom varchar(250) not null ,
    email varchar(250) not null ,
    mdp varchar(250) not null ,
    id_role int references role(id)
);
insert into utilisateur(nom, email, mdp, id_role) values ('Rabenaivo', 'rabenaivolucas@gmail.com', md5('rabenaivo'), 2);
insert into utilisateur(nom, email, mdp, id_role) values ('Ralison', 'ralison@gmail.com', md5('ralison'), 1);

CREATE TABLE continent (
    id serial primary key ,
    nom VARCHAR(50) NOT NULL
);
INSERT INTO continent (nom_continent) VALUES
    ('Afrique'),
    ('Amérique'),
    ('Asie'),
    ('Europe');

CREATE TABLE ville_desservie (
    id serial PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    id_continent INT,
    FOREIGN KEY (id_continent) REFERENCES continent(id)
);
INSERT INTO ville (nom, id_continent) VALUES
    ('Paris', 4),
    ('Londres', 4),
    ('New York', 2),
    ('Tokyo', 3),
    ('São Paulo', 2),
    ('Le Caire', 1);

CREATE TABLE type_siege (
    id serial PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
);
INSERT INTO type_siege (nom, description) VALUES
    ('Économique' ),
    ('Affaires'),
    ('Première classe'),
    ('Premium Économique');

create table modele (
    id  serial primary key ,
    nom VARCHAR(250)
);
INSERT INTO modele (nom) VALUES
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

create table avion (
    id serial primary key,
    id_modele int references modele(id),
    date_fabrication date
);
insert into avion (id_modele, date_fabrication) values
    (1, '2020-05-01'),
    (2, '2018-09-15'),  -- Boeing 737
    (3, '2019-01-12'),  -- Airbus A380
    (4, '2017-03-10');  -- Boeing 777

CREATE TABLE avion_details_type_siege (
    id_type_siege int REFERENCES type_siege(id),
    id_avion int REFERENCES avion(id),
    nombre_place int,
    PRIMARY KEY (id_type_siege, id_avion)
);
insert into avion_details_type_siege (id_type_siege, id_avion, nombre_place) values
-- Airbus A320
(1, 1, 150),  -- Économique pour Airbus A320
(2, 1, 20),   -- Affaires pour Airbus A320
(3, 1, 5),    -- Première classe pour Airbus A320
(4, 1, 5),    -- Premium Économique pour Airbus A320

-- Boeing 737
(1, 2, 160),  -- Économique pour Boeing 737
(2, 2, 15),   -- Affaires pour Boeing 737
(3, 2, 8),    -- Première classe pour Boeing 737
(4, 2, 7),    -- Premium Économique pour Boeing 737

-- Airbus A380
(1, 3, 300),  -- Économique pour Airbus A380
(2, 3, 60),   -- Affaires pour Airbus A380
(3, 3, 15),   -- Première classe pour Airbus A380
(4, 3, 25),   -- Premium Économique pour Airbus A380

-- Boeing 777
(1, 4, 250),  -- Économique pour Boeing 777
(2, 4, 50),   -- Affaires pour Boeing 777
(3, 4, 12),   -- Première classe pour Boeing 777
(4, 4, 20);   -- Premium Économique pour Boeing 777



CREATE TABLE vol (
    id serial PRIMARY KEY,
    id_avion int REFERENCES avion(id),
    id_ville_depart int REFERENCES ville(id),
    id_ville_arrivee int REFERENCES ville(id),
    date_depart timestamp,
    date_arrivee timestamp
);
insert into vol (id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee) values
                                                                                             (1, 1, 2, '2025-03-15 08:00:00', '2025-03-15 10:30:00'),  -- Airbus A320 (Paris -> Londres)
                                                                                             (2, 2, 3, '2025-03-16 09:00:00', '2025-03-16 12:00:00'),  -- Boeing 737 (Londres -> New York)
                                                                                             (3, 3, 4, '2025-03-17 14:00:00', '2025-03-17 18:00:00'),  -- Airbus A380 (New York -> Tokyo)
                                                                                             (4, 4, 5, '2025-03-18 16:00:00', '2025-03-18 20:00:00');  -- Boeing 777 (Tokyo -> São Paulo)


CREATE TABLE vol_type_siege (
    id_vol int REFERENCES vol(id),
    id_type_siege int REFERENCES type_siege(id),
    prix DECIMAL(10, 2),
    PRIMARY KEY (id_vol, id_type_siege)
);
insert into vol_type_siege (id_vol, id_type_siege, prix) values
-- Airbus A320
(1, 1, 150.00),  -- Économique
(1, 2, 250.00),  -- Affaires
(1, 3, 500.00),  -- Première classe
(1, 4, 350.00),  -- Premium Économique

-- Boeing 737
(2, 1, 160.00),  -- Économique
(2, 2, 220.00),  -- Affaires
(2, 3, 450.00),  -- Première classe
(2, 4, 330.00),  -- Premium Économique

-- Airbus A380
(3, 1, 180.00),  -- Économique
(3, 2, 280.00),  -- Affaires
(3, 3, 600.00),  -- Première classe
(3, 4, 400.00),  -- Premium Économique

-- Boeing 777
(4, 1, 170.00),  -- Économique
(4, 2, 270.00),  -- Affaires
(4, 3, 550.00),  -- Première classe
(4, 4, 380.00);  -- Premium Économique



CREATE TABLE reservation (
    id serial PRIMARY KEY,
    id_utilisateur int REFERENCES utilisateur(id),
    id_vol int REFERENCES vol(id),
    date_reservation timestamp
);
insert into reservation (id_utilisateur, id_vol, date_reservation) values
                                                                       (1, 1, '2025-03-10 09:00:00'),  -- Rabenaivo réserve un vol Paris -> Londres
                                                                       (2, 2, '2025-03-10 09:30:00');  -- Ralison réserve un vol Londres -> New York


CREATE TABLE reservation_details (
     id_reservation int REFERENCES reservation(id),
     id_type_siege int REFERENCES type_siege(id),
     nombre_places int,
     PRIMARY KEY (id_reservation, id_type_siege)
);
insert into reservation_details (id_reservation, id_type_siege, nombre_places) values
-- Rabenaivo réserve un vol Paris -> Londres
(1, 1, 2),  -- 2 places en Économique
(1, 2, 1),  -- 1 place en Affaires

-- Ralison réserve un vol Londres -> New York
(2, 1, 3),  -- 3 places en Économique
(2, 3, 1);  -- 1 place en Première classe




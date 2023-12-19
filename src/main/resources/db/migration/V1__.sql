CREATE TABLE commande
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    plat_id  BIGINT NOT NULL,
    quantite INT    NOT NULL,
    nom_plat VARCHAR(255) NULL,
    prix_plat DOUBLE NOT NULL,
    CONSTRAINT pk_commande PRIMARY KEY (id)
);

CREATE TABLE plat
(
    id  BIGINT AUTO_INCREMENT NOT NULL,
    nom VARCHAR(255) NULL,
    prix DOUBLE NOT NULL,
    CONSTRAINT pk_plat PRIMARY KEY (id)
);

ALTER TABLE commande
    ADD CONSTRAINT FK_COMMANDE_ON_PLAT FOREIGN KEY (plat_id) REFERENCES plat (id);
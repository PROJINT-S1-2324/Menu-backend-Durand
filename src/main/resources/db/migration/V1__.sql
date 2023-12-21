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

CREATE TABLE `role`
(
    id INT NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE role_permision
(
    role_id   INT NOT NULL,
    permision SMALLINT NULL
);

CREATE TABLE user
(
    id      INT AUTO_INCREMENT NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    roles_id INT NOT NULL,
    user_id  INT NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (roles_id, user_id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_user UNIQUE (user_id);

ALTER TABLE commande
    ADD CONSTRAINT FK_COMMANDE_ON_PLAT FOREIGN KEY (plat_id) REFERENCES plat (id);

ALTER TABLE role_permision
    ADD CONSTRAINT fk_role_permision_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);
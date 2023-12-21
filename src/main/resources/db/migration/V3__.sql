ALTER TABLE role_permision
DROP
COLUMN permision;

ALTER TABLE role_permision
    ADD permision VARCHAR(255) NULL;
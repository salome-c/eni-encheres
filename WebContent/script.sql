CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL UNIQUE,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL UNIQUE,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(64) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL
)

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)

INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES ('utilisateur', 'nom', 'prenom', 'utilisateur@mail.com', 'rue', '00000', 'ville', CONVERT(VARCHAR(64),HASHBYTES('SHA2_256', 'utilisateur'),2), 0, 1);
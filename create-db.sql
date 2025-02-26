CREATE TABLE User (
        mail VARCHAR(255) PRIMARY KEY,
        password VARCHAR(255) CHECK (LENGTH(password) >= 4),
        city VARCHAR(255)
);

CREATE TABLE Candidate (
        mail VARCHAR(255) PRIMARY KEY,
        lastname VARCHAR(255),
        firstname VARCHAR(255),
        FOREIGN KEY (mail) REFERENCES User(mail)
);

CREATE TABLE Company (
        mail VARCHAR(255) PRIMARY KEY,
        denomination VARCHAR(255),
        description VARCHAR(255),
        FOREIGN KEY (mail) REFERENCES User(mail)
);

CREATE TABLE QualificationLevel (
        label VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Field (
        label VARCHAR(255) PRIMARY KEY
);

CREATE TABLE Application (
        id INT PRIMARY KEY AUTO_INCREMENT,
        cv VARCHAR(255),
        appdate DATE,
        publisher VARCHAR(255),
        qualification VARCHAR(255),
        FOREIGN KEY (publisher) REFERENCES Candidate(mail),
        FOREIGN KEY (qualification) REFERENCES QualificationLevel(label)
);

CREATE TABLE JobOffer (
        id INT PRIMARY KEY AUTO_INCREMENT,
        title VARCHAR(255),
        taskdescription VARCHAR(255),
        publicationdate DATE,
        publisher VARCHAR(255),
        qualification VARCHAR(255),
        FOREIGN KEY (publisher) REFERENCES Company(mail),
        FOREIGN KEY (qualification) REFERENCES QualificationLevel(label)
);

CREATE TABLE ApplicationFields (
        appid INT,
        idfield VARCHAR(255),
        PRIMARY KEY (appid, idfield),
        FOREIGN KEY (appid) REFERENCES Application(id),
        FOREIGN KEY (idfield) REFERENCES Field(label)
);

CREATE TABLE OfferFields (
        offerid INT,
        idfield VARCHAR(255),
        PRIMARY KEY (offerid, idfield),
        FOREIGN KEY (offerid) REFERENCES JobOffer(id),
        FOREIGN KEY (idfield) REFERENCES Field(label)
);

CREATE TABLE Message (
        id INT PRIMARY KEY AUTO_INCREMENT,
        message VARCHAR(255),
        publicationdate DATE
);

CREATE TABLE OfferMessage (
        idmess INT PRIMARY KEY AUTO_INCREMENT,
        idoffer INT,
        FOREIGN KEY (idmess) REFERENCES Message(id),
        FOREIGN KEY (idoffer) REFERENCES JobOffer(id)
);

CREATE TABLE ApplicationMessage (
        idmess INT PRIMARY KEY AUTO_INCREMENT,
        idapp INT,
        FOREIGN KEY (idmess) REFERENCES Message(id),
        FOREIGN KEY (idapp) REFERENCES Application(id)
);

CREATE TABLE AppMessDest (
        idmessoffer INT,
        iddestapp INT,
        PRIMARY KEY (idmessoffer, iddestapp),
        FOREIGN KEY (idmessoffer) REFERENCES OfferMessage(idmess),
        FOREIGN KEY (iddestapp) REFERENCES Application(id)
);

CREATE TABLE OfferMessDest (
        idmessapp INT,
        iddestoffer INT,
        PRIMARY KEY (idmessapp, iddestoffer),
        FOREIGN KEY (idmessapp) REFERENCES ApplicationMessage(idmess),
        FOREIGN KEY (iddestoffer) REFERENCES JobOffer(id)
);

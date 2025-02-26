CREATE TABLE User (
    mail VARCHAR(255) PRIMARY KEY ,
    password VARCHAR(255),
    city VARCHAR(255)

);
CREATE TABLE Candidate(
    mail VARCHAR(255) PRIMARY KEY,
    lastname VARCHAR(255),
    firstname VARCHAR(255),
    FOREIGN KEY (Mail) REFERENCES User(mail)
);
CREATE TABLE Company(
    mail VARCHAR(255) PRIMARY KEY,
    denomination VARCHAR(255),
    FOREIGN KEY (mail) REFERENCES User(mail)
);
CREATE TABLE Application(
    id INT PRIMARY KEY AUTO_INCREMENT,
    cv VARCHAR(255),
    appdate DATE(DD/MM/YYYY),
    publisher VARCHAR(255),
    qualification VARCHAR(255),
    FOREIGN KEY (publisher) REFERENCES Candidate(mail),
    FOREIGN KEY (qualification) REFERENCES QualificationLevel(label)
);
CREATE TABLE JobOffer(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    taskdescription VARCHAR(255),
    publicationdate DATE(DD/MM/YYYY),
    publisher VARCHAR(255),
    qualification VARCHAR(255),
    FOREIGN KEY (publisher) REFERENCES Company(mail),
    FOREIGN KEY (qualification) REFERENCES QualificationLevel(label)
);
CREATE TABLE QualificationLevel(
    label VARCHAR(255) PRIMARY KEY
);
CREATE TABLE Field(
    label VARCHAR(255) PRIMARY KEY
);
CREATE TABLE ApplicationFields(
    appid INT PRIMARY KEY AUTO_INCREMENT,
    idfield INT AUTO_INCREMENT,
    FOREIGN KEY (appid) REFERENCES Application(id),
    FOREIGN KEY (idfield) REFERENCES Field(label)
    );

CREATE TABLE OfferFields(
    offerid INT PRIMARY KEY AUTO_INCREMENT,
    idfield INT AUTO_INCREMENT,
    FOREIGN KEY (offerid) REFERENCES JobOffer(id),
    FOREIGN KEY (idfield) REFERENCES Field(label)
);
CREATE TABLE Message(
    id INT PRIMARY KEY AUTO_INCREMENT,
    message VARCHAR(255),
    publicationdate DATE(DD/MM/YYYY)
);
CREATE TABLE AppMessDest(
  idmessoffer INT PRIMARY KEY AUTO_INCREMENT,
  iddestapp INT AUTO_INCREMENT,
  FOREIGN KEY (idmessoffer) REFERENCES OfferMessage(id),
    FOREIGN KEY (iddestapp) REFERENCES Application(id)
);
CREATE TABLE OfferMessage(
    idmess INT PRIMARY KEY AUTO_INCREMENT,
    idoffer INT AUTO_INCREMENT,
    FOREIGN KEY (idmess) REFERENCES Message(id),
    FOREIGN KEY (idoffer) REFERENCES JobOffer(id)
);
CREATE TABLE ApplicationMessage(
    idmess INT PRIMARY KEY AUTO_INCREMENT,
    idapp INT AUTO_INCREMENT,
    FOREIGN KEY (idmess) REFERENCES Message(id),
    FOREIGN KEY (idapp) REFERENCES Application(id)
);
CREATE TABLE OfferMessDest(
    idmessapp INT PRIMARY KEY AUTO_INCREMENT,
    iddestoffer INT AUTO_INCREMENT,
    FOREIGN KEY (idmessapp) REFERENCES ApplicationMessage(id),
    FOREIGN KEY (iddestoffer) REFERENCES JobOffer(id)
);

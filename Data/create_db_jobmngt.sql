drop table if exists userapp cascade;
drop table if exists candidate cascade;
drop table if exists company cascade;
drop table if exists qualificationlevel cascade;
drop table if exists field cascade;
drop table if exists application cascade;
drop table if exists joboffer cascade;
drop table if exists applicationfields cascade;
drop table if exists offerfields cascade;
drop table if exists message cascade;
drop table if exists offermessage cascade;
drop table if exists applicationmessage cascade;
drop table if exists appmessdest cascade;
drop table if exists offermessdest cascade;
drop table if exists sector;


CREATE TABLE userapp
(
    mail     VARCHAR(255) PRIMARY KEY                   not null,
    password VARCHAR(255) CHECK (LENGTH(password) >= 4) not null,
    city     VARCHAR(255)
);

CREATE TABLE candidate
(
    mail      VARCHAR(255) PRIMARY KEY not null,
    lastname  VARCHAR(255)             not null,
    firstname VARCHAR(255)             not null,
    FOREIGN KEY (mail) REFERENCES userapp (mail)
);

CREATE TABLE company
(
    mail         VARCHAR(255) PRIMARY KEY not null,
    denomination VARCHAR(255)             not null,
    description  VARCHAR(255),
    FOREIGN KEY (mail) REFERENCES userapp (mail)
);

CREATE TABLE qualificationlevel
(
    id    serial primary key,
    label VARCHAR(255) not null
);

CREATE TABLE field
(
    id    serial primary key,
    label VARCHAR(255) not null
);

CREATE TABLE application
(
    id            serial PRIMARY KEY,
    cv            VARCHAR(255) not null,
    appdate       DATE         not null,
    publisher     VARCHAR(255),
    qualification INT,
    FOREIGN KEY (publisher) REFERENCES candidate (mail),
    FOREIGN KEY (qualification) REFERENCES qualificationlevel (id)
);

CREATE TABLE joboffer
(
    id              serial PRIMARY KEY,
    title           VARCHAR(255) not null,
    taskdescription VARCHAR(255) not null,
    publicationdate DATE         not null,
    publisher       VARCHAR(255),
    qualification   INT,
    FOREIGN KEY (publisher) REFERENCES company (mail),
    FOREIGN KEY (qualification) REFERENCES qualificationlevel (id)
);

CREATE TABLE applicationfields
(
    appid   INT,
    idfield INT,
    PRIMARY KEY (appid, idfield),
    FOREIGN KEY (appid) REFERENCES application (id),
    FOREIGN KEY (idfield) REFERENCES field (id)
);

CREATE TABLE offerfields
(
    offerid INT,
    idfield INT,
    PRIMARY KEY (offerid, idfield),
    FOREIGN KEY (offerid) REFERENCES joboffer (id),
    FOREIGN KEY (idfield) REFERENCES field (id)
);

CREATE TABLE message
(
    id              serial PRIMARY KEY,
    message         VARCHAR(255),
    publicationdate DATE
);

CREATE TABLE offermessage
(
    idmess  serial PRIMARY KEY,
    idoffer INT,
    FOREIGN KEY (idmess) REFERENCES message (id),
    FOREIGN KEY (idoffer) REFERENCES joboffer (id)
);

CREATE TABLE applicationmessage
(
    idmess serial PRIMARY KEY,
    idapp  INT,
    FOREIGN KEY (idmess) REFERENCES message (id),
    FOREIGN KEY (idapp) REFERENCES application (id)
);

CREATE TABLE appmessdest
(
    idmessoffer INT,
    iddestapp   INT,
    PRIMARY KEY (idmessoffer, iddestapp),
    FOREIGN KEY (idmessoffer) REFERENCES offermessage (idmess),
    FOREIGN KEY (iddestapp) REFERENCES application (id)
);

CREATE TABLE offermessdest
(
    idmessapp   INT,
    iddestoffer INT,
    PRIMARY KEY (idmessapp, iddestoffer),
    FOREIGN KEY (idmessapp) REFERENCES applicationmessage (idmess),
    FOREIGN KEY (iddestoffer) REFERENCES joboffer (id)
);

-- +----------------------------------------------------------------------------------------------+
-- | Insert fields and qualification level                                            |
-- +----------------------------------------------------------------------------------------------+

-- Some fields

insert into field(label)
values ('Purchase/Logistic'); --  1
insert into field(label)
values ('Administration'); --  2
insert into field(label)
values ('Agriculture'); --  3
insert into field(label)
values ('Agrofood'); --  4
insert into field(label)
values ('Insurance'); --  5
insert into field(label)
values ('Audit/Advise/Expertise'); --  6
insert into field(label)
values ('Public works/Real estate'); --  7
insert into field(label)
values ('Trade'); --  8
insert into field(label)
values ('Communication/Art/Media/Fashion'); --  9
insert into field(label)
values ('Accounting'); -- 10
insert into field(label)
values ('Direction/Execution'); -- 11
insert into field(label)
values ('Distribution/Sale'); -- 12
insert into field(label)
values ('Electronic/Microelectronic'); -- 13
insert into field(label)
values ('Environment'); -- 14
insert into field(label)
values ('Finance/Bank'); -- 15
insert into field(label)
values ('Training/Teaching'); -- 16
insert into field(label)
values ('Hotel/Restaurant/Tourism'); -- 17
insert into field(label)
values ('Industry/Engineering/Production'); -- 18
insert into field(label)
values ('Computer science'); -- 19
insert into field(label)
values ('Juridique/Fiscal/Droit'); -- 20
insert into field(label)
values ('Marketing'); -- 21
insert into field(label)
values ('Public/Parapublic'); -- 22
insert into field(label)
values ('Human resources'); -- 23
insert into field(label)
values ('Health/Social/Biology/HHumanitarian'); -- 24
insert into field(label)
values ('Telecom/Networking');
-- 25

-- Some qualification levels

insert into qualificationlevel(label)
values ('Professional level'); --  1
insert into qualificationlevel(label)
values ('A-diploma'); --  2
insert into qualificationlevel(label)
values ('Licence'); --  3
insert into qualificationlevel(label)
values ('Master'); --  4
insert into qualificationlevel(label)
values ('PhD'); --  5

insert into userapp(mail, password, city)
values ('test@test.fr', 'test', 'Paris');
insert into userapp(mail, password, city)
values ('testcompany@test.fr', 'test', 'Paris');
insert into company(mail, denomination, description)
values ('testcompany@test.fr', 'TestCompany', 'This is a test company');
insert into userapp(mail, password, city)
values ('testcandidate@test.fr', 'test', 'Paris');
insert into candidate(mail, lastname, firstname)
values ('testcandidate@test.fr', 'Test', 'Candidate');
insert into joboffer(title, taskdescription, publicationdate, publisher, qualification)
values ('TestOffer', 'This is a test offer', '2020-01-01', 'testcompany@test.fr', 5);
insert into joboffer(title, taskdescription, publicationdate, publisher, qualification)
values ('TestOffer2', 'This is a test offer 2', '2020-01-01', 'testcompany@test.fr',3);
insert into joboffer(title, taskdescription, publicationdate, publisher, qualification)
values ('TestOffer3', 'This is a test offer 3', '2020-01-01', 'testcompany@test.fr',3);


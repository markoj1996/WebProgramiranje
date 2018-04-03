DROP SCHEMA IF EXISTS projekat;
CREATE SCHEMA projekat DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE projekat;

CREATE TABLE Korisnici (
	userName VARCHAR(10) NOT NULL, 
	password VARCHAR(10) NOT NULL,
    ime varchar(10),
    prezime varchar(20),
    email varchar(30),
    opis varchar(100),
    datumRegistracije date not null,
	role ENUM('Korisnik', 'Administrator') NOT NULL DEFAULT 'Korisnik', 
    blokiran bool default false,
    PRIMARY KEY(userName)
);
INSERT INTO Korisnici (userName, password, ime,prezime,email,opis,datumRegistracije,role,blokiran) VALUES ('a', 'a', 'a','a','a@gmail.com','opis','1.1.2018','Korisnik',false);
INSERT INTO Korisnici (userName, password, ime,prezime,email,opis,datumRegistracije,role,blokiran) VALUES ('b', 'b', 'b','b','b@gmail.com','opis','1.1.2018','Administrator',false);

CREATE TABLE Video (
	ID bigint NOT NULL auto_increment, 
	video VARCHAR(100) NOT NULL,
    slicica varchar(100),
    opis varchar(100),
    vidljivost enum('Javni','Unlisted','Privatni') not null default 'Javni',
    dozvoljeniKomentari bool default false,
    vidljivostRejtinga bool default false,
    blokiran bool default false,
    brojPregleda int not null,
    datumKreiranja date not null,
    vlasnik varchar(10),
    PRIMARY KEY(ID),
    FOREIGN KEY(vlasnik) REFERENCES Korisnici(userName) ON DELETE RESTRICT
);
INSERT into Video (video,slicica,opis,vidljivost,dozvoljeniKomentari,vidljivostRejtinga,blokiran,brojPregleda,datumKreiranja,vlasnik)
values ('https://www.youtube.com/embed/6fKlXCwlv0M','https://i.ytimg.com/vi/2IIyZ4kaFoc/hqdefault.jpg','Guelora Kange - Crvena Zvezda vs Krasnodar','Javni',false,false,false,12345,'1.1.2018','a');

CREATE TABLE LikeDislike(
	ID bigint NOT NULL auto_increment,
    likee bool default true,
    datumKreiranja date,
    video bigint,
    komentar int,
    PRIMARY KEY(ID),
    FOREIGN KEY(video) REFERENCES Video(ID) ON DELETE RESTRICT
);

INSERT into LikeDislike (likee,datumKreiranja,video) values (true,"27.3.2018",1);
INSERT into LikeDislike (likee,datumKreiranja,video) values (true,"27.3.2018",1);
INSERT into LikeDislike (likee,datumKreiranja,video) values (true,"27.3.2018",1);
INSERT into LikeDislike (likee,datumKreiranja,video) values (false,"27.3.2018",1);

CREATE TABLE LikeKorisniciVideo (
	ID bigint NOT NULL auto_increment,
    korisnik varchar(10) not null,
    likedislike bigint not null,
    primary key(ID),
	FOREIGN KEY(korisnik) REFERENCES korisnici(username) ON DELETE RESTRICT,
	FOREIGN KEY(likedislike) REFERENCES LikeDislike(ID) ON DELETE RESTRICT
);

INSERT into LikeKorisniciVideo (korisnik,likedislike) values ('a',1)
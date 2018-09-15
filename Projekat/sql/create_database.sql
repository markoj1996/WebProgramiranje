DROP SCHEMA IF EXISTS projekat;
CREATE SCHEMA projekat DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE projekat;

CREATE TABLE Korisnici (
	username VARCHAR(10) NOT NULL, 
	password VARCHAR(10) NOT NULL,
    ime varchar(20),
    prezime varchar(30),
    email varchar(30),
    opis varchar(100),
    datumregistracije date not null,
	role ENUM('Korisnik', 'Administrator') NOT NULL DEFAULT 'Korisnik', 
    blokiran bool default false,
    PRIMARY KEY(userName)
);
INSERT INTO Korisnici (username, password, ime,prezime,email,opis,datumregistracije,role,blokiran) VALUES ('a', 'a', 'Korisnik','1','a@gmail.com','opis','2018.9.15','Korisnik',false);
INSERT INTO Korisnici (username, password, ime,prezime,email,opis,datumregistracije,role,blokiran) VALUES ('b', 'b', 'Korisnik','2','b@gmail.com','opis','2018.9.15','Korisnik',false);
INSERT INTO Korisnici (username, password, ime,prezime,email,opis,datumregistracije,role,blokiran) VALUES ('c', 'c', 'Korisnik','3','c@gmail.com','opis','2018.9.15','Korisnik',false);
INSERT INTO Korisnici (username, password, ime,prezime,email,opis,datumregistracije,role,blokiran) VALUES ('admin', 'admin', 'Administrator','1','admin@gmail.com','opis','2018.9.15','Administrator',false);

CREATE TABLE Video (
	id bigint NOT NULL auto_increment, 
	video VARCHAR(100) NOT NULL,
    slicica varchar(100),
    opis varchar(100),
    vidljivost enum('Javni','Unlisted','Privatni') not null default 'Javni',
    dozvoljenikomentari bool default false,
    vidljivostrejtinga bool default false,
    blokiran bool default false,
    brojpregleda int not null,
    datumkreiranja date not null,
    vlasnik varchar(10),
    PRIMARY KEY(ID),
    FOREIGN KEY(vlasnik) REFERENCES Korisnici(userName) ON DELETE RESTRICT
);
INSERT into Video (video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik)
values ('https://www.youtube.com/embed/6fKlXCwlv0M','https://i.ytimg.com/vi/6fKlXCwlv0M/maxresdefault.jpg','Guelora Kange - Crvena Zvezda vs Krasnodar','Javni',true,true,false,12345,'2018.1.1','a');
INSERT into Video (video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik)
values ('https://www.youtube.com/embed/7QRSugEiiV4','https://i.ytimg.com/vi/QYVojM69vmA/maxresdefault.jpg','Top 10 Plays: 2018 NBA Season','Javni',true,true,false,1450625,'2018.4.8','a');
INSERT into Video (video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik)
values ('https://www.youtube.com/embed/nbLfmqnDDT4','https://i.ytimg.com/vi/nbLfmqnDDT4/maxresdefault.jpg','Russell Westbrook Top 10 Plays of Career','Javni',true,true,false,135235,'2018.5.18','a');
INSERT into Video (video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik)
values ('https://www.youtube.com/embed/J-uKf0lAXsQ','https://i.ytimg.com/vi/J-uKf0lAXsQ/maxresdefault.jpg','Best Football Goals 2018 | HD','Javni',true,true,false,457454,'2018.5.28','b');
INSERT into Video (video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik)
values ('https://www.youtube.com/embed/BRiqtUe46HA','https://meridian-sport.com/wp-content/uploads/2018/08/zvezda-salzburg-22451.jpg','Crvena zvezda-Liga sampiona','Javni',false,false,false,563453,'2018.8.3','c');
INSERT into Video (video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik)
values ('https://www.youtube.com/embed/vJX5Cuk5BaE','https://i.ytimg.com/vi/vJX5Cuk5BaE/maxresdefault.jpg','James Harden Top 10 Plays of Career','Privatni',false,false,false,0,'2018..9.15','b');

CREATE TABLE LikeDislike(
	id bigint NOT NULL,
    likee bool default true,
    datumkreiranja date,
    video bigint,
    komentar int,
    PRIMARY KEY(ID),
    FOREIGN KEY(video) REFERENCES Video(ID) ON DELETE RESTRICT
);

INSERT into LikeDislike (id,likee,datumkreiranja,video) values (1,true,"27.3.2018",1);

CREATE TABLE LikeKorisniciVideo (
	id bigint NOT NULL auto_increment,
    korisnik varchar(10) not null,
    likedislike bigint not null,
    primary key(ID),
	FOREIGN KEY(korisnik) REFERENCES korisnici(username) ON DELETE RESTRICT,
	FOREIGN KEY(likedislike) REFERENCES LikeDislike(id) ON DELETE RESTRICT
);

INSERT into LikeKorisniciVideo (korisnik,likedislike) values ('b',1);

CREATE TABLE Komentar (
	id bigint NOT NULL auto_increment,
    sadrzaj varchar(500),
    datum date,
    korisnik varchar(10),
    video bigInt,
    primary key(ID),
	FOREIGN KEY(korisnik) REFERENCES Korisnici(username) ON DELETE RESTRICT,
	FOREIGN KEY(video) REFERENCES Video(id) ON DELETE RESTRICT
);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("Komentar2","2018.2.1","b",1);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("Komentar1","2018.2.12","c",1);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("Komentar3","2018.3.2","b",1);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("gdadgagaggaga","2018.2.1","b",2);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("sfhsfhhsfshf","2018.2.12","c",2);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("afafsfasfasfasfaga","2018.3.2","b",2);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("gdadgagaggaga","2018.2.1","b",3);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("sfhsfhhsfshf","2018.2.12","c",3);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("afafsfasfasfasfaga","2018.3.2","b",3);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("gdadgagaggaga","2018.2.1","b",4);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("sfhsfhhsfshf","2018.2.12","c",4);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("afafsfasfasfasfaga","2018.3.2","b",4);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("gdadgagaggaga","2018.2.1","b",5);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("sfhsfhhsfshf","2018.2.12","c",5);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("afafsfasfasfasfaga","2018.3.2","b",5);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("gdadgagaggaga","2018.2.1","b",6);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("sfhsfhhsfshf","2018.2.12","c",6);
INSERT into Komentar (sadrzaj,datum,korisnik,video) values ("afafsfasfasfasfaga","2018.3.2","b",6);

CREATE TABLE Subscribe (
    korisnik varchar(10),
    korisnik2 varchar(10),
    primary key(korisnik,korisnik2),
	FOREIGN KEY(korisnik) REFERENCES Korisnici(username) ON DELETE RESTRICT,
	FOREIGN KEY(korisnik2) REFERENCES Korisnici(username) ON DELETE RESTRICT
);
INSERT into Subscribe (korisnik,korisnik2) values ("a","b");
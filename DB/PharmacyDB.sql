/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE pharmacy;
use pharmacy;

drop TABLE if exists pharmacy.medicament;
create TABLE pharmacy.medicament(
m_id int not null AUTO_INCREMENT,
name varchar(30),
dosage varchar(10),
instructions varchar(100),
price double,
primary key(m_id))ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop TABLE if exists pharmacy.clients;
create TABLE pharmacy.clients(c_id int not null AUTO_INCREMENT,
name varchar(30),
lastname varchar(30) not null,
EGN varchar(10) not null,
UNIQUE KEY (EGN),
primary key(c_id))ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop TABLE if exists pharmacy.drug_taken;
create TABLE pharmacy.drug_taken(drt_id int not null AUTO_INCREMENT,
dr_id int not null,
c_id int not null,
datetaken varchar(19),
primary key(drt_id))ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE pharmacy.drug_taken ADD FOREIGN KEY (dr_id) REFERENCES pharmacy.medicament (m_id);
ALTER TABLE pharmacy.drug_taken ADD FOREIGN KEY (c_id) REFERENCES pharmacy.cliends (c_id);



insert into medicament (name,dosage, instructions, price)
values ('Aspirin','1','Pri ypotreba da se izbqgva ypotrebata na alkohol.', '6,25'),
('Paracetamol','2','Bolko yspokoqvashto i temperaturoponijavashto deistvie','1,92'),
('Analgin','1', 'Pri glavobolie, zubobol, travmi,izgarqniq,bubrechni i jluchni koliki.', '1,97'),
('Ferveks','1','Za prostuda,povishena telesna temperatura,kihane,alergichen rinit.','8,30'),
('Efizol','1','Pri razvitieto na vuzpalenie na ystnata kyhinna i nosoglutkata.', '3,80'),
('Olint','4', 'Sprei za nos sus sudosvivash efekt.','7,07'),
('Ranitidin','2','Za lechenie na stomashna qzva,stres-qzva,dispepsiq','6,90');

insert into clients(name,lastname,EGN)
values  ('Vladimir','Zahariev', 9302053412),
('Kristiqn','Georgiev',8304125642),
('Lora','Petkova',8805156021),
('Ivelina','Popova', 9306224365),
('Hrisimir','Dakov', 9204235640);

insert into drug_taken (dr_id, c_id,datetaken)
values  (7,3,'2003-02-04 15:23:43'),
(5,1,'2003-05-04 17:34:12'),
(4,3,'2003-10-22 14:21:23'),
(3,2,'2004-12-21 20:23:11'),
(6,4,'2004-10-01 11:17:09'),
(1,5,'2005-08-03 08:19:16');


/*Procedure*/
use pharmacy; /*shte otpadne */
DELIMITER //
CREATE PROCEDURE show_clients ()
BEGIN 
SELECT * FROM clients;
END//
DELIMITER ;

USE  pharmacy;
DELIMITER //
CREATE PROCEDURE show_drug_taken ()
BEGIN 
SELECT drt_id,medicament.name as Product,clients.name AS Name,clients.lastname as Lastname,datetaken
FROM drug_taken 
RIGHT JOIN  medicament
ON drug_taken.dr_id = medicament.m_id
RIGHT JOIN  clients
ON drug_taken.c_id = clients.c_id
WHERE drt_id IS NOT NULL;
END//
DELIMITER ;

USE  pharmacy;
DELIMITER //
CREATE PROCEDURE search_client(IN SK VARCHAR(255) CHARSET utf8) 
BEGIN 
SELECT *
From clients
WHERE CONCAT(name,lastname,egn)
LIKE CONCAT('%',SK,'%');
END//
DELIMITER ;

USE  pharmacy;
DELIMITER //
CREATE PROCEDURE search_medicament(IN SK VARCHAR(255) CHARSET utf8) 
BEGIN 
SELECT *
From medicament
WHERE CONCAT(m_id,name)
LIKE CONCAT('%',SK,'%');
END//
/* В процес на измисляне е 
DELIMITER ;
USE  pharmacy;
DELIMITER //
CREATE PROCEDURE search_orders(IN SK VARCHAR(255) CHARSET utf8) 
BEGIN 
call show_drug_taken()
SELECT *
From medicament
WHERE CONCAT(drt_id,Product,Name,Lastname,datetaken)
LIKE CONCAT('%',SK,'%');
END//
DELIMITER ; */

USE  pharmacy;
DELIMITER //
CREATE PROCEDURE insert_client(IN cl_name VARCHAR(30) CHARSET utf8 ,IN cl_family VARCHAR(30) CHARSET utf8,IN cl_EGN INT ) 
BEGIN 
insert into clients(name,lastname,EGN)
values  (cl_name,cl_family,cl_EGN);
END//
DELIMITER ;







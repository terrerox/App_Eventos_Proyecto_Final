CREATE DATABASE PROYECTO_FINAL;
USE PROYECTO_FINAL;

CREATE TABLE EVENTOS (
	ID int NOT NULL AUTO_INCREMENT,
	NOMBRE varchar(255) NOT NULL,
	HORA_INICIO TIME NOT NULL,	
	HORA_FINAL TIME NOT NULL,	
	LUGAR varchar(255) NOT NULL,
	FECHA date NOT NULL,
	DETALLES text NOT NULL,
	PRIMARY KEY(ID)
);

INSERT INTO EVENTOS
VALUES
(NULL,'English Test','15:00','18:30','ITLA Language School - 105','2021-08-20','Take the english test intermediate TOEFL.'),
(NULL,'Math Work','20:00','20:30','Mi casa','2021-08-21','Terminal el trabajo final.'),
(NULL,'Hacer un cambio linea 09','01:00','01:50','Soto-tech','2021-08-22','Cambios del proyecto Soto_LAN'),
(NULL,'Sleep','21:00','06:30','Descansar','2021-08-23','Dormir PILA!');

SELECT *
FROM EVENTOS;

truncate table Eventos;

show databases;
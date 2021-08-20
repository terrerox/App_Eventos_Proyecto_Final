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

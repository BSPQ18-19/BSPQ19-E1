/* DELETE 'cinemaDB' database*/
DROP SCHEMA IF EXISTS testcinemaDB;
/* DELETE USER 'spqe1' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spqe1'@'localhost';

/* CREATE 'cinemaDB' DATABASE */
CREATE SCHEMA testcinemaDB;
/* CREATE THE USER 'spqe1' AT LOCAL SERVER WITH PASSWORD 'spqe1' */
CREATE USER IF NOT EXISTS 'spqe1'@'localhost' IDENTIFIED BY 'spqe1';

GRANT ALL ON testcinemaDB.* TO 'spqe1'@'localhost';

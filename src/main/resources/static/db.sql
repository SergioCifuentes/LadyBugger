CREATE DATABASE ladybugger
\c ladybugger

CREATE TABLE user (
  id SERIAL PRIMARY KEY,        
  email VARCHAR(50) NOT NULL UNIQUE, 
  password VARCHAR(50) NOT NULL,
  name NUMERIC(5,2) NOT NULL,
  middleName VARCHAR(50),
  lastName VARCHAR(50),
  creationDate DATE
);
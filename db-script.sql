CREATE TABLE users_managment(
   id SERIAL PRIMARY KEY,
   first_name VARCHAR NOT null,
   last_name VARCHAR NOT null, 
   email VARCHAR NOT null,
   CONSTRAINT users_managment_email_unique UNIQUE (email)
);
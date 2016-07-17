
CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

CREATE TABLE country (
  id SERIAL PRIMARY KEY NOT NULL ,
  county_name CHAR (30) NOT NULL UNIQUE );

CREATE TABLE city (
  id SERIAL PRIMARY KEY NOT NULL ,
  city_name CHAR (30) NOT NULL,
  country_id INTEGER REFERENCES country,
  UNIQUE (city_name, country_id));

CREATE TABLE street (
  id SERIAL PRIMARY KEY NOT NULL,
  street_name CHAR (30) NOT NULL,
  city_id INTEGER REFERENCES city,
  building_number CHAR(10),
  UNIQUE (street_name, city_id));

-- liquibase formatted sql

--changeset keval:pokemon-table
CREATE TABLE pokemon (
	id BIGINT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	image_url varchar(100) NOT NULL,
	CONSTRAINT pokemon_pk PRIMARY KEY (id),
	CONSTRAINT pokemon_unique_name UNIQUE KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;

--changeset keval:pokemon-table-power-col-added
ALTER TABLE pokemon.pokemon ADD power varchar(100) NULL;

--changeset keval:pokemon-table-image-url-changed
ALTER TABLE pokemon.pokemon MODIFY COLUMN image_url varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;

--changeset keval:pokemon-table-imageUrl-col-changed
ALTER TABLE pokemon.pokemon CHANGE image_url imageUrl varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL NULL;

--changeset keval:power-table-added
CREATE TABLE pokemon.power (
	powerId INT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT power_pk PRIMARY KEY (powerId),
	CONSTRAINT power_un UNIQUE KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;

--changeset keval:power-table-data-added
INSERT INTO pokemon.power (name)
	VALUES ('Grass'), ('Fire'), ('Water');

--changeset keval:pokemon-col-dropped
ALTER TABLE pokemon DROP COLUMN power;

--changeset keval:pokemon-power-added
ALTER TABLE pokemon ADD power INT NULL;

--changeset keval:foreign-key-added
ALTER TABLE pokemon ADD CONSTRAINT pokemon_FK FOREIGN KEY (power) REFERENCES power(powerId);




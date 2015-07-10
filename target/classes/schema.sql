DROP TABLE people IF EXISTS;
DROP TABLE relationships IF EXISTS;

CREATE TABLE people (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(80) NULL,
	lastname VARCHAR(80) NULL
);

CREATE TABLE relationships (
	p_id1 INT REFERENCES people (id),
	p_id2 INT REFERENCES people (id),
	PRIMARY KEY (p_id1, p_id2)
);

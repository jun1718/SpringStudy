CREATE TABLE mvc_user (
	account VARCHAR(80) PRIMARY KEY,
	password VARCHAR(80) NOT NULL,
	name VARCHAR(100) NOT NULL,
	reg_date TIMESTAMP DEFAULT NOW()
);
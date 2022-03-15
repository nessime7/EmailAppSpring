CREATE SCHEMA IF NOT EXISTS emailapp;

CREATE TABLE IF NOT EXISTS emailapp.company (
	id uuid NOT NULL,
	name varchar(255) NULL,
	website varchar(255) NULL,
	CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS emailapp.department (
	id uuid NOT NULL,
	budget int4 NOT NULL,
	"name" varchar(255) NULL,
	company_id uuid NULL,
	CONSTRAINT department_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS emailapp.email (
	id uuid NOT NULL,
	login varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	CONSTRAINT email_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS emailapp.employee (
	id uuid NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	manager_id uuid NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS emailapp.manager (
	id uuid NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	department_id uuid NULL,
	CONSTRAINT manager_pkey PRIMARY KEY (id)
);
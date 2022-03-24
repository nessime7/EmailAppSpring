CREATE SCHEMA IF NOT EXISTS emailapp;

CREATE TABLE IF NOT EXISTS emailapp.company (
	id uuid NOT NULL,
	name varchar(255),
	website varchar(255),
	CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS emailapp.department (
	id uuid NOT NULL,
	budget int4,
	name varchar(255),
	company_id uuid,
	CONSTRAINT department_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS emailapp.email (
	id uuid NOT NULL,
	login varchar(255),
	password varchar(255),
	CONSTRAINT email_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS emailapp.employee (
	id uuid NOT NULL,
	first_name varchar(255),
	last_name varchar(255),
	manager_id uuid NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS emailapp.manager (
	id uuid NOT NULL,
	first_name varchar(255),
	last_name varchar(255),
	department_id uuid NULL,
	CONSTRAINT manager_pkey PRIMARY KEY (id)
);
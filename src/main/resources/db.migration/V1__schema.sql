CREATE SCHEMA

CREATE TABLE emailapp.company (
	id uuid NOT NULL,
	name varchar(255) NULL,
	website varchar(255) NULL,
	CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE emailapp.department (
	id uuid NOT NULL,
	budget int4 NOT NULL,
	"name" varchar(255) NULL,
	company_id uuid NULL,
	CONSTRAINT department_pkey PRIMARY KEY (id)
);

-- emailapp.department foreign keys

ALTER TABLE emailapp.department ADD CONSTRAINT fkh1m88q0f7sc0mk76kju4kcn6f FOREIGN KEY (company_id) REFERENCES emailapp.company(id);


CREATE TABLE emailapp.email (
	id uuid NOT NULL,
	login varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	CONSTRAINT email_pkey PRIMARY KEY (id)
);


CREATE TABLE emailapp.employee (
	id uuid NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	manager_id uuid NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (id)
);

-- emailapp.employee foreign keys

ALTER TABLE emailapp.employee ADD CONSTRAINT fkfemnv0llvsjg4adl4xl1m0cxv FOREIGN KEY (manager_id) REFERENCES emailapp.manager(id);


CREATE TABLE emailapp.manager (
	id uuid NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	department_id uuid NULL,
	CONSTRAINT manager_pkey PRIMARY KEY (id)
);

-- emailapp.manager foreign keys

ALTER TABLE emailapp.manager ADD CONSTRAINT fk78bxps40sjq2glpliqw79ewqv FOREIGN KEY (department_id) REFERENCES emailapp.department(id);
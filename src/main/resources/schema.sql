DROP SCHEMA IF EXISTS crud CASCADE;
CREATE SCHEMA crud AUTHORIZATION postgres;

-- drop tables
-- DROP TABLE IF EXISTS crud.products;

-- DROP TABLE IF EXISTS crud.categories;

-- DROP TABLE IF EXISTS crud.brands;

-- DROP TABLE IF EXISTS crud.product_images;

-- DROP TABLE IF EXISTS crud.users;

-- create tables
CREATE TABLE crud.products (
	id serial4 NOT NULL,
	title varchar NOT NULL,
	description varchar NOT NULL,
	thumbnail varchar NOT NULL,
	price int4 NOT NULL,
	discountpercentage int2 NULL,
	rating int2 NOT NULL,
	stock int2 NOT NULL,
	brand_id int4 NOT NULL,
	category_id int4 NOT NULL,
	CONSTRAINT products_pk PRIMARY KEY (id)
);

CREATE TABLE crud.brands (
	id serial4 NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT brands_pk PRIMARY KEY (id)
);


CREATE TABLE crud.categories (
	id serial4 NOT NULL,
	name varchar NOT NULL,
	description varchar NULL,
	CONSTRAINT categories_pk PRIMARY KEY (id)
);

CREATE TABLE crud.product_images (
	id serial4 NOT NULL,
	image_source varchar NOT NULL,
	product_id int4 NULL,
	CONSTRAINT product_images_pk PRIMARY KEY (id)
);

CREATE TABLE crud.users (
	id serial4 NOT NULL,
	username varchar NOT NULL,
	email varchar NOT NULL,
	"password" varchar NOT NULL,
	"role" varchar NOT NULL,
	avatar_image varchar NULL,
	CONSTRAINT users_email_key UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_username_key UNIQUE (username)
);

-- crud.products foreign keys

ALTER TABLE crud.products ADD CONSTRAINT product_brand_fk FOREIGN KEY (brand_id) REFERENCES crud.brands(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE crud.products ADD CONSTRAINT product_category_fk FOREIGN KEY (category_id) REFERENCES crud.categories(id) ON DELETE CASCADE ON UPDATE CASCADE;

-- crud.product_images foreign keys
ALTER TABLE crud.product_images ADD CONSTRAINT product_images_fk FOREIGN KEY (product_id) REFERENCES crud.products(id) ON DELETE CASCADE ON UPDATE CASCADE;


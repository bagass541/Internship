CREATE TABLE IF NOT EXISTS groups (
	id SERIAL PRIMARY KEY,
	name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
	id SERIAL PRIMARY KEY,
	name VARCHAR NOT NULL,
	group_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS subcategories (
    id SERIAL PRIMARY KEY,
	name VARCHAR NOT NULL,
    category_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    price NUMERIC NOT NULL,
    delivery_time TIMESTAMP NOT NULL,
    description_id BIGINT NOT NULL,
    subcategory_id BIGINT NOT NULL,
    image_url VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS descriptions (
    id SERIAL PRIMARY KEY,
    weight INT NOT NULL,
    length INT NOT NULL,
    width INT NOT NULL
);
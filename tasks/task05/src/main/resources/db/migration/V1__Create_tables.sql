CREATE TABLE IF NOT EXISTS locations (
    id SERIAL PRIMARY KEY,
    room INT NOT NULL,
    place INT NOT NULL
);

CREATE TYPE computer_type as ENUM ('LOW', 'MID', 'HIGH');

CREATE TABLE IF NOT EXISTS computers (
    id SERIAL PRIMARY KEY,
    type computer_type NOT NULL,
    location_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS positions (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    salary NUMERIC(15, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    second_name VARCHAR NOT NULL,
    birthday DATE NOT NULL,
    position_id INT NOT NULL,
    phone_number VARCHAR
);

CREATE TABLE IF NOT EXISTS games (
    id SERIAL PRIMARY KEY,
    genre VARCHAR NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    second_name VARCHAR NOT NULL,
    birthday DATE,
    phone_number VARCHAR
);

CREATE TABLE IF NOT EXISTS reservations (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    computer_id INT NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS sessions (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    employee_id INT NOT NULL,
    game_id INT NOT NULL,
    computer_id INT NOT NULL,
    duration TIME NOT NULL
);
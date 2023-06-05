DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS items;

CREATE SEQUENCE user_seq;
CREATE TABLE users(
    id BIGINT PRIMARY KEY,
    owner BOOLEAN DEFAULT FALSE,
    renter BOOLEAN DEFAULT FALSE,
    email VARCHAR(50) NOT NULL,
    first_name VARCHAR(50),
    second_name VARCHAR(50),
    given_name VARCHAR(50),
    inn VARCHAR(50),
    birthday DATE,
    passport_number VARCHAR(50),
    passport_series VARCHAR(50),
    phone_number VARCHAR(50),
    registration_address VARCHAR(50),
    residence_address VARCHAR(50)
);

CREATE SEQUENCE category_seq;

CREATE TABLE categories(
    id INTEGER PRIMARY KEY,
    name VARCHAR(50)
);

CREATE SEQUENCE item_seq;

CREATE TABLE items(
    id BIGINT PRIMARY KEY,
    name VARCHAR(50),
    serial_number VARCHAR(50),
    photo VARCHAR(150),
    video VARCHAR(150),
    description VARCHAR(250),
    is_verified BOOLEAN NOT NULL DEFAULT TRUE,
    on_lease BOOLEAN NOT NULL DEFAULT FALSE,
    category_id INTEGER NOT NULL REFERENCES categories(id),
    renter_id BIGINT NOT NULL REFERENCES users(id),
    owner_id BIGINT REFERENCES users(id)
);
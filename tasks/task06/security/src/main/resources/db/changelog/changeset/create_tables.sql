CREATE TABLE IF NOT EXISTS users (
	id SERIAL PRIMARY KEY,
	username VARCHAR NOT NULL,
	password VARCHAR NOT NULL,
	is_account_non_expired BOOLEAN NOT NULL,
	is_account_non_locked BOOLEAN NOT NULL,
	is_credentials_non_expired BOOLEAN NOT NULL,
	is_enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
	id SERIAL PRIMARY KEY,
	authority VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS users_authorities (
	user_id BIGINT,
	authority_id BIGINT,
	PRIMARY KEY(user_id, authority_id)
);

CREATE TABLE IF NOT EXISTS tokens (
    id SERIAL PRIMARY KEY,
    access_token VARCHAR,
    refresh_token VARCHAR,
    is_logged_out BOOLEAN,
    user_id BIGINT
)
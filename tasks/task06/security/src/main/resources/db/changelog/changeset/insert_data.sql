INSERT INTO users(username, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES('bagas', '$2a$10$z9OFjbkRD75y0qkrXjjZa.tdPOaIN92Jdktv5dse8ZZa8y4dZVLdW', true, true, true, true);

INSERT INTO roles(authority) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users_roles(user_id, role_id) VALUES (101, 102);
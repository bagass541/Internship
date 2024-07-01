ALTER TABLE users_roles
ADD CONSTRAINT fk_user_users_authorities
FOREIGN KEY (user_id)
REFERENCES users(id);

ALTER TABLE users_roles
ADD CONSTRAINT fk_authority_user_authorities
FOREIGN KEY (role_id)
REFERENCES roles(id);

ALTER TABLE tokens
ADD CONSTRAINT fk_tokens_user
FOREIGN KEY (user_id)
REFERENCES users(id);
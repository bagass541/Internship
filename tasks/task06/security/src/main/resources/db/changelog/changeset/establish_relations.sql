ALTER TABLE users_authorities
ADD CONSTRAINT fk_user_users_authorities
FOREIGN KEY (user_id)
REFERENCES users(id);

ALTER TABLE users_authorities
ADD CONSTRAINT fk_authority_user_authorities
FOREIGN KEY (authority_id)
REFERENCES authorities(id);

ALTER TABLE tokens
ADD CONSTRAINT fk_tokens_user
FOREIGN KEY (user_id)
REFERENCES users(id);
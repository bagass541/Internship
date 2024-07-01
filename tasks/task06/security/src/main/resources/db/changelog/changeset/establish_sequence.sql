ALTER TABLE users
ALTER COLUMN id SET DEFAULT nextval('user_id_sequence');

ALTER TABLE roles
ALTER COLUMN id SET DEFAULT nextval('role_id_sequence');

ALTER TABLE tokens
ALTER COLUMN id SET DEFAULT nextval('token_id_sequence');
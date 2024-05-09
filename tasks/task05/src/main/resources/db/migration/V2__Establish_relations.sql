ALTER TABLE computers
ADD CONSTRAINT fk_location_id
FOREIGN KEY (location_id)
REFERENCES locations (id);

ALTER TABLE employees
ADD CONSTRAINT fk_position_id
FOREIGN KEY (position_id)
REFERENCES positions (id);

ALTER TABLE reservations
ADD CONSTRAINT fk_user_id
FOREIGN KEY (user_id)
REFERENCES users(id);

ALTER TABLE reservations
ADD CONSTRAINT fk_computer_id
FOREIGN KEY (computer_id)
REFERENCES computers(id);

ALTER TABLE sessions
ADD CONSTRAINT fk_user_id
FOREIGN KEY (user_id)
REFERENCES users(id);

ALTER TABLE sessions
ADD CONSTRAINT fk_game_id
FOREIGN KEY (game_id)
REFERENCES games(id);

ALTER TABLE sessions
ADD CONSTRAINT fk_computer_id
FOREIGN KEY (computer_id)
REFERENCES computers(id);

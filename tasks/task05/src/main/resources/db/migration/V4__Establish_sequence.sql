ALTER TABLE computers
ALTER COLUMN id SET DEFAULT nextval('computerId_sequence');

ALTER TABLE employees
ALTER COLUMN id SET DEFAULT nextval('employeesId_sequence');

ALTER TABLE reservations
ALTER COLUMN id SET DEFAULT nextval('reservationId_sequence');

ALTER TABLE sessions
ALTER COLUMN id SET DEFAULT nextval('sessionId_sequence');

ALTER TABLE locations
ALTER COLUMN id SET DEFAULT nextval('locationId_sequence');

ALTER TABLE positions
ALTER COLUMN id SET DEFAULT nextval('positionId_sequence');

ALTER TABLE games
ALTER COLUMN id SET DEFAULT nextval('gameId_sequence');

ALTER TABLE users
ALTER COLUMN id SET DEFAULT nextval('userId_sequence');

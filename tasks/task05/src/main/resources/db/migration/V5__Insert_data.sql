INSERT INTO locations(room, place)
VALUES(101, 1),
(101, 2),
(101, 3),
(101, 4),
(101, 5);

INSERT INTO computers(type, location_id)
VALUES('LOW', 101),
('LOW', 102),
('MID', 103),
('HIGH', 104),
('HIGH', 105);

INSERT INTO positions(name, salary)
VALUES('Админ', 800),
('Уборщица', 500),
('Директор', 2000),
('Сис-админ', 1000);

INSERT INTO employees(name, second_name, birthday, position_id, phone_number)
VALUES('Артем', 'Короткевич', '1997-03-30', 103, '+375111222'),
('Александр', 'Богословский', '2003-10-11', 101, '+3751434343'),
('Кристина', 'Добрушская', '2000-10-15', 102, '+3753434343'),
('Никита', 'Сычев', '1986-05-10', 103, '+375545435');

INSERT INTO games(genre, name)
VALUES('ММО РПГ', 'WoW'),
('Шутер', 'Battlefield 2042'),
('Стратегия', 'Manor Lords'),
('Шутер', 'CS 2'),
('MOBA', 'DOTA 2');

INSERT INTO users(name, second_name, birthday, phone_number)
VALUES('Иван', 'Белоножко', '2006-02-11', '+375265664'),
('Максим', 'Пармон', '2003-06-10', '+375366563'),
('Кирилл', 'Симаков', '2003-07-22', '+375546644'),
('Илья', 'Кривицкий', '2006-10-19', '+37535742');

INSERT INTO reservations(user_id, computer_id, date, time)
VALUES(101, 101, '2024-05-13', '18:00:00'),
(102, 102, '2024-05-10', '12:00:00'),
(103, 104, '2024-05-13', '19:00:00'),
(101, 101, '2024-05-15', '17:00:00');

INSERT INTO sessions(user_id, employee_id, game_id, computer_id, duration)
VALUES(103, 101, 101, 101, '2:00:00'),
(102, 101, 101, 102, '1:00:00'),
(103, 101, 104, 103, '3:00:00'),
(104, 101, 102, 104, '2:30:00');
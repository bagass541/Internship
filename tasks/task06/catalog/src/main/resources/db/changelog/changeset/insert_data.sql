INSERT INTO groups(name) VALUES ('Дом');

INSERT INTO categories(name, group_id) VALUES ('Ванная', 101), ('Детская', 101);

INSERT INTO subcategories(name, category_id)
VALUES ('Коврики', 101), ('Сантехника', 101),
('Шторы', 102), ('Мебель', 102);

INSERT INTO descriptions(weight, length, width)
VALUES (100, 200, 300), (110, 210, 310),
(120, 220, 320), (130, 230, 330),
(105, 205, 305);

INSERT INTO products(name, price, amount, delivery_time, description_id, subcategory_id, image_url)
VALUES ('HOMBER', 733, 1000, '2024-06-14 19:00:00', 101, 101, 'https://asset.cloudinary.com/duilhjcqb/63fe5a120ebdc95f374aae6c69a96926'),
('HURER', 800, 2000, '2024-06-25 12:00:00', 105, 101, 'https://asset.cloudinary.com/duilhjcqb/c031d49e44188e9399918a7053fa8a71'),
('Душевая стойка', 1200, 1500, '2024-06-14 15:00:00', 102, 102, 'https://asset.cloudinary.com/duilhjcqb/3bc7f266b01432003481b529174e2c85'),
('Фатька', 2000, 1200, '2024-06-17 18:00:00', 103, 103, 'https://asset.cloudinary.com/duilhjcqb/b2dd9b3470b5c005242aa7543eed2db4'),
('Бейби стул', 1100, 1150, '2024-07-01 12:00:00', 104, 104, 'https://asset.cloudinary.com/duilhjcqb/6377017e5aa3664ac96d00ec7c58a671');


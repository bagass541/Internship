INSERT INTO groups(name) VALUES ('Дом');

INSERT INTO categories(name, group_id) VALUES ('Ванная', 1), ('Детская', 1);

INSERT INTO subcategories(name, category_id)
VALUES ('Коврики', 1), ('Сантехника', 1),
('Шторы', 2), ('Мебель', 2);

INSERT INTO descriptions(weight, length, width)
VALUES (100, 200, 300), (110, 210, 310),
(120, 220, 320), (130, 230, 330),
(105, 205, 305);

INSERT INTO products(name, price, delivery_time, description_id, subcategory_id, image_url)
VALUES ('HOMBER', 733, '2024-06-14 19:00:00', 1, 1, 'https://asset.cloudinary.com/duilhjcqb/63fe5a120ebdc95f374aae6c69a96926'),
('HURER', 800, '2024-06-25 12:00:00', 5, 1, 'https://asset.cloudinary.com/duilhjcqb/c031d49e44188e9399918a7053fa8a71'),
('Душевая стойка', 1200, '2024-06-14 15:00:00', 2, 2, 'https://asset.cloudinary.com/duilhjcqb/3bc7f266b01432003481b529174e2c85'),
('Фатька', 2000, '2024-06-17 18:00:00', 3, 3, 'https://asset.cloudinary.com/duilhjcqb/b2dd9b3470b5c005242aa7543eed2db4'),
('Бейби стул', 1100, '2024-07-01 12:00:00', 4, 4, 'https://asset.cloudinary.com/duilhjcqb/6377017e5aa3664ac96d00ec7c58a671');


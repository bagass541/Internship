ALTER TABLE categories
ADD CONSTRAINT fk_group_categories
FOREIGN KEY (group_id)
REFERENCES groups(id);

ALTER TABLE subcategories
ADD CONSTRAINT fk_category_subcategories
FOREIGN KEY (category_id)
REFERENCES categories(id);

ALTER TABLE products
ADD CONSTRAINT fk_subcategory_products
FOREIGN KEY (subcategory_id)
REFERENCES subcategories(id);

ALTER TABLE products
ADD CONSTRAINT fk_description_products
FOREIGN KEY (description_id)
REFERENCES descriptions(id);
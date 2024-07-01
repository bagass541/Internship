ALTER TABLE groups
ALTER COLUMN id SET DEFAULT nextval('group_id_sequence');

ALTER TABLE categories
ALTER COLUMN id SET DEFAULT nextval('category_id_sequence');

ALTER TABLE subcategories
ALTER COLUMN id SET DEFAULT nextval('subcategory_id_sequence');

ALTER TABLE products
ALTER COLUMN id SET DEFAULT nextval('product_id_sequence');

ALTER TABLE descriptions
ALTER COLUMN id SET DEFAULT nextval('description_id_sequence');
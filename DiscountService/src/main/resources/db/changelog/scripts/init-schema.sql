create table discount_rules (
dr_id serial primary key,
user_type varchar(75) not null,
product_category varchar(50) not null,
min_order_price decimal(10,2) default 0,
discount_value decimal(8,2) not null,
discount_type varchar(20) not null,
create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
INSERT INTO discount_rules
(user_type, product_category, min_order_price, discount_value, discount_type)
VALUES
        ('JURIDICAL', 'ELECTRONIC', 5000, 10, 'PERCENT'),
        ('JURIDICAL', 'CLOTHES', 5000, 15, 'PERCENT'),
        ('JURIDICAL', 'ANY', 20000, 3000, 'FIXED'),
        ('INDIVIDUAL', 'ELECTRONIC', 5000, 5, 'PERCENT'),
        ('INDIVIDUAL', 'CLOTHES', 10000, 10, 'PERCENT'),
        ('INDIVIDUAL', 'ANY', 20000, 1000, 'FIXED'),
        ('VIP', 'ELECTRONIC', 5000, 20, 'PERCENT'),
        ('VIP', 'CLOTHES', 10000, 20, 'PERCENT'),
        ('VIP', 'ANY', 20000, 5000, 'FIXED');

CREATE VIEW customer_invoices AS
SELECT c.id          AS customer_id,
       c.name        AS customer_name,
       o.id          AS order_id,
       SUM(op.price) AS total_price,
       o.created_at  AS order_date
FROM customer c
         JOIN `order` o ON c.id = o.customer_id
         JOIN order_product op ON o.id = op.order_id
GROUP BY o.id;

CREATE VIEW product_statistics AS
SELECT p.id               AS product_id,
       p.description      AS product_description,
       COUNT(op.order_id) AS total_orders,
       SUM(op.price)      AS total_revenue,
       AVG(op.price)      AS average_revenue_per_order
FROM product p
         JOIN order_product op ON p.id = op.product_id
GROUP BY p.id;

CREATE OR REPLACE VIEW discounted_orders AS
SELECT o.id                AS order_id,
       c.name              AS customer_name,
       SUM(op.price * 0.9) AS total_price,
       o.created_at        AS order_date
FROM `order` o
         JOIN customer c ON o.customer_id = c.id
         JOIN order_product op ON o.id = op.order_id
WHERE op.special_treatment LIKE '%discount%'
GROUP BY o.id;


CREATE VIEW customer_order_records AS
SELECT c.id          AS customer_id,
       c.name        AS customer_name,
       COUNT(o.id)   AS total_orders,
       SUM(op.price) AS total_spent,
       AVG(op.price) AS average_spent_per_order
FROM customer c
         JOIN `order` o ON c.id = o.customer_id
         JOIN order_product op ON o.id = op.order_id
GROUP BY c.id;

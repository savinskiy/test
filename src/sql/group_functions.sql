-- Вывести даты совершенных заказов (orderdate) из таблицы orders и описания 
-- территорий из таблицы territories, - только в тот день или в те дни 
-- orderdate), когда заказы были совершены на наибольшем числе территорий. (Чтобы
-- связать заказ  и территорию, используйте колонку territoryID из таблицы orders.)

SELECT o.order_date, t.territory_description
FROM Orders o
INNER JOIN territories t ON o.territory_id = t.territory_id
where o.order_date in 
    (select o_date from 
        (SELECT o.order_date as o_date, count(DISTINCT t.territory_description) t_count FROM Orders o
        INNER JOIN territories t ON o.territory_id = t.territory_id
        group by o.order_date) where t_count = 
            (SELECT max(count(DISTINCT t.territory_description))
            FROM Orders o
            INNER JOIN territories t ON o.territory_id = t.territory_id
            group by o.order_date));

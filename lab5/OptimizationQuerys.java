SELECT Orders.OrderID, SUM(OrderDetails.Quantity * OrderDetails.UnitPrice) AS TotalPrice
FROM Orders
JOIN OrderDetails ON Orders.OrderID = OrderDetails.OrderID
WHERE OrderDetails.Quantity > 10
GROUP BY Orders.OrderID;


/**
 * consideraciones 
 * debemos considerar que si usamos join debemos considerar que tengan indices las columnas principales
 * orderId, quantity y unitPrice debe estar indexado para optimizar la consulta
 * 
 * 
 * Consulta optimizada 
 * alias claros 
 * se agrega clausula  HAVING. Ahora se suma una columna condicional que cuenta cuántas veces se cumple Quantity > 10 
 */

SELECT  o.OrderID, SUM(od.Quantity * od.UnitPrice) AS TotalPrice
FROM Orders o
JOIN OrderDetails od ON o.OrderID = od.OrderID
GROUP BY o.OrderID
HAVING SUM(CASE WHEN od.Quantity > 10 THEN 1 ELSE 0 END) > 0;


***************************************************************************************************************************************************************************************

SELECT CustomerName FROM Customers WHERE City = 'London' ORDER BY CustomerName;

/**
 * consideraciones
 * la columna City y CustomerName deberia estar indexado 
 * considerar que los nombres de la ciudad no sean sensitive case
 * 
 * 
 * consulta optimizada 
 * 
 */

SELECT CustomerName 
FROM Customers 
WHERE City = 'London' 
ORDER BY CustomerName;

***************************************************************************************************************************************************************************************

db.posts
  .find({ status: "active" }, { title: 1, likes: 1 })
  .sort({ likes: -1 });

  db.posts.createIndex({ status: 1, likes: 1 });
/**
 * consideraciones 
 * el campo status y like deberian estar indexados 
 * 
 * 
 */

***************************************************************************************************************************************************************************************

db.users.aggregate([
  { $match: { status: "active" } },
  { $group: { _id: "$location", totalUsers: { $sum: 1 } } },
]);

db.users.createIndex({ status: 1, location: 1 });

/**
 * comnsideraciones
 * el campo status y _id deberian estar indexados 
 * validar que haya espacio o memoria suficiente por el tamaño de la conuslta 
 *pasos adicionales al pipeline de agregación, como ordenando con $sort, limitando con $limit, o añadiendo campos con $project.
 * 
 */
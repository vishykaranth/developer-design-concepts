
/* Simple Stored procedure with only IN parameter */
DROP procedure IF EXISTS `search_users_proc`$

CREATE PROCEDURE `search_users_proc` (IN name VARCHAR(50))
BEGIN
    SELECT *
    FROM USER
    WHERE user_name LIKE CONCAT('%', name , '%');
END$


/*  Simple Stored procedure with only IN and OUT parameters */
DROP procedure IF EXISTS `count_search_users_proc`$

CREATE PROCEDURE `count_search_users_proc` (
   IN  name VARCHAR(50),
   OUT total INT
)
BEGIN
  SELECT COUNT(id)
  INTO total
  FROM USER
  WHERE user_name LIKE CONCAT('%', name , '%');
  SELECT total AS users_found;
END

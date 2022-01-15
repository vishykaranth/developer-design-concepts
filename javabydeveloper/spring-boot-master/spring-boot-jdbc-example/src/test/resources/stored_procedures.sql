/* MySQL Stored Procedures/Functions */


/* Simple Stored procedure to get search results for users by name */
DROP procedure IF EXISTS `search_users_proc`$

CREATE PROCEDURE `search_users_proc` (IN name VARCHAR(50))
BEGIN
    SELECT *
    FROM USER
    WHERE username LIKE CONCAT('%', name , '%');
END$




/* Stored procedure to get search count for paged users by name */
DROP procedure IF EXISTS `count_search_users_proc`$

CREATE PROCEDURE `count_search_users_proc` (
   IN  order_by VARCHAR(50),
   IN  direction VARCHAR(4),
   IN  limit_ INT,
   IN  offset_ INT,
   OUT total INT
)
BEGIN
  SELECT COUNT(id)
  INTO total
  FROM USER;
  SELECT * from USER ORDER BY CONCAT(order_by,' ', direction) LIMIT limit_ OFFSET offset_;
END$





/* Stored procedure to get multple result sets */
DROP procedure IF EXISTS `search_weak_users_proc`$

CREATE PROCEDURE `search_weak_users_proc` (
   IN  user_type VARCHAR(50),
   OUT type_total INT
)
BEGIN

  SELECT COUNT(ID) INTO type_total from USER WHERE USERTYPE = user_type;

  SELECT * from USER 
  WHERE USERTYPE = user_type AND LENGTH(USERNAME) < 7;
  
  SELECT * from USER 
  WHERE USERTYPE = user_type AND LENGTH(PASSWORD) <= 8;
  
  UPDATE USER SET UPDATEDTIME = NOW() WHERE USERTYPE = user_type;
  
END$



/* Stored Function Example */
DROP function IF EXISTS `user_has_strongpwd_fn`$

CREATE FUNCTION `user_has_strongpwd_fn` (u_id BIGINT) RETURNS tinyint(1)
READS SQL DATA DETERMINISTIC
BEGIN
  DECLARE length_pwd INT;
  
  SELECT LENGTH(PASSWORD) INTO length_pwd FROM USER WHERE ID = u_id;
  
  IF (length_pwd >= 7 && length_pwd < 15) THEN
     RETURN FALSE;
  ELSE
     RETURN TRUE;
  END IF;
END$

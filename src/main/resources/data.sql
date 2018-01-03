-- This will create some database objects as well some data on server init,
-- Springboot will execute all DML/DDL commands it gets on data.sql
-- more: https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html (78.3 Initialize a database)

--Clean all objects
DROP ALL OBJECTS;


CREATE TABLE USERS (
  USERNAME VARCHAR(30) PRIMARY KEY,
  PASSWORD VARCHAR(50) NOT NULL
);

--Insert some batata's  data
INSERT INTO USERS(USERNAME, PASSWORD) VALUES ('POTATO', '44c7be48226ebad5dca8216674cad62b'); -- SECRET
INSERT INTO USERS(USERNAME, PASSWORD) VALUES ('USER1', '902fbdd2b1df0c4f70b4a5d23525e932');  -- ABC

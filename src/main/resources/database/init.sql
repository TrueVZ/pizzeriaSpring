CREATE TABLE IF NOT EXISTS users
(
    user_id SERIAL NOT NULL PRIMARY KEY ,
    username VARCHAR(256) NOT NULL ,
    password VARCHAR(256) NOT NULL ,
    email VARCHAR(256) NOT NULL,
    active BOOLEAN NOT NULL default true
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id INT NOT NULL PRIMARY KEY ,
    role_name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS products_type
(
    product_type_id SERIAL NOT NULL PRIMARY KEY ,
    product_type_name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS products
(
    product_id SERIAL NOT NULL PRIMARY KEY ,
    product_type_id INT ,
    product_name VARCHAR(256) NOT NULL ,
    price real NOT NULL ,
    description text NOT NULL,
    image_url VARCHAR(256) NOT NULL
);


INSERT INTO roles (role_id, role_name)
SELECT 1, 'ROLE_USER'
    WHERE NOT EXISTS (
        SELECT role_id FROM roles WHERE role_id = 1
    );

INSERT INTO roles (role_id, role_name)
SELECT 2, 'ROLE_ADMIN'
    WHERE NOT EXISTS (
        SELECT role_id FROM roles WHERE role_id = 2
    );

INSERT INTO products_type (product_type_id, product_type_name)
SELECT 1, 'PIZZA'
    WHERE NOT EXISTS(
        SELECT product_type_id FROM products_type WHERE product_type_id = 1
        );

INSERT INTO products_type (product_type_id, product_type_name)
SELECT 2, 'SNACKS'
WHERE NOT EXISTS(
        SELECT product_type_id FROM products_type WHERE product_type_id = 2
    );

INSERT INTO products_type (product_type_id, product_type_name)
SELECT 3, 'DRINKS'
WHERE NOT EXISTS(
    SELECT product_type_id FROM products_type WHERE product_type_id = 3
    );



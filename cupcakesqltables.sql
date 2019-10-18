DROP TABLE if exists completeorders;
DROP TABLE if exists users;
DROP TABLE if exists toppings;
DROP TABLE if exists buttoms;


CREATE TABLE users (
    idUsers INTEGER not null AUTO_INCREMENT unique,
    Username VARCHAR(45) unique not null,
    Password VARCHAR(200) not null,
    Balance DOUBLE not null DEFAULT 0,
    Name VARCHAR(45),
    Email VARCHAR(45),
    Role tinyint,
    primary key (idUsers)
);

CREATE TABLE completeorders (
	Ordernumber INTEGER not null AUTO_INCREMENT unique,
    idUsers INTEGER,
    OrderDate TIMESTAMP not null,
    Total_price DOUBLE,
    primary key (ordernumber),
    FOREIGN KEY (idUsers) REFERENCES users(idUsers)
);

CREATE TABLE toppings (
	idToppings INTEGER not null AUTO_INCREMENT,
    toppingName VARCHAR(45),
    Price DOUBLE,
    primary key (idToppings)
);

CREATE TABLE buttoms (
	idButtoms INTEGER not null AUTO_INCREMENT,
    toppingName VARCHAR(45),
    Price DOUBLE,
    primary key (idButtoms)
);

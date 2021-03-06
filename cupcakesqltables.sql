DROP TABLE if exists lineitems;
DROP TABLE if exists completeorders;
DROP TABLE if exists users;
DROP TABLE if exists toppings;
DROP TABLE if exists bottoms;


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
    idUser INTEGER,
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
    Total_price DOUBLE,
    primary key (ordernumber),
    FOREIGN KEY (idUser) REFERENCES users(idUsers)
);

CREATE TABLE toppings (
	idToppings INTEGER not null AUTO_INCREMENT,
    toppingName VARCHAR(45),
    Price DOUBLE,
    primary key (idToppings)
);

CREATE TABLE bottoms (
	idBottoms INTEGER not null AUTO_INCREMENT,
    bottomName VARCHAR(45),
    Price DOUBLE,
    primary key (idBottoms)
);

CREATE TABLE lineitems (
	OrdreNumber INTEGER,
	idBottom INTEGER,
    idTopping INTEGER,
    quantity INTEGER,
    Price DOUBLE,
    FOREIGN KEY (idBottom) REFERENCES bottoms(idBottoms),
    FOREIGN KEY (idTopping) REFERENCES toppings(idToppings),
    FOREIGN KEY (OrdreNumber) REFERENCES completeorders(Ordernumber)
);

insert into bottoms (bottomName, Price) values ("Chocolate", 5.00);
insert into bottoms (bottomName, Price) values ("Vanilla", 5.00);
insert into bottoms (bottomName, Price) values ("Nutmeg", 5.00);
insert into bottoms (bottomName, Price) values ("Pistacio", 6.00);
insert into bottoms (bottomName, Price) values ("Almond", 7.00);

insert into toppings (toppingName, Price) values ("Chocolate", 5.00);
insert into toppings (toppingName, Price) values ("Blueberry", 5.00);
insert into toppings (toppingName, Price) values ("Rasberry", 5.00);
insert into toppings (toppingName, Price) values ("Crispy", 6.00);
insert into toppings (toppingName, Price) values ("Strawberry", 6.00);
insert into toppings (toppingName, Price) values ("Rum/Raisin", 7.00);
insert into toppings (toppingName, Price) values ("Orange", 8.00);
insert into toppings (toppingName, Price) values ("Lemon", 8.00);
insert into toppings (toppingName, Price) values ("Blue cheese", 9.00);

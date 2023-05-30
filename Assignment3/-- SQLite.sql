-- SQLite
CREATE TABLE Client (
    id INTEGER PRIMARY KEY, 
    name TEXT,
    address TEXT,
    phone TEXT
);

CREATE TABLE Product (
    id INTEGER PRIMARY KEY,
    name TEXT,
    description TEXT,
    price REAL, 
    stock INTEGER
);

CREATE TABLE OrdersTable (
    id INTEGER PRIMARY KEY,
    client_id INTEGER,
    product_id INTEGER,
    quantity integer,
    date TEXT,
    FOREIGN KEY(client_id) REFERENCES Client(id),
    FOREIGN KEY(product_id) REFERENCES Product(id)
);
 CREATE TABLE authors (
     author_id SERIAL PRIMARY KEY,
                      author_name VARCHAR(255),
                      gender VARCHAR(255)
 );

CREATE TABLE categories(
    categories_id SERIAL PRIMARY KEY,
    categories_name VARCHAR(255)
);

CREATE TABLE books(
    book_id SERIAL PRIMARY KEY ,
    title  VARCHAR(255),
    published_date timestamp,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES authors(author_id)
);

CREATE TABLE book_details(
        id_detail SERIAL PRIMARY KEY ,
        book_id INT,
        category_id INT,
        FOREIGN KEY (book_id)  REFERENCES books(book_id) ON UPDATE CASCADE ON DELETE CASCADE ,
        FOREIGN KEY (category_id)  REFERENCES categories(categories_id) ON UPDATE CASCADE ON DELETE CASCADE

);
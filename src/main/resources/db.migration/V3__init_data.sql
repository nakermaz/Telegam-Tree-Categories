CREATE TABLE telega_category.category (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255),
    parent_id BIGINT,
    FOREIGN KEY (parent_id) REFERENCES telega_category.category(id) ON DELETE CASCADE
);

insert into telega_category.category (name) values ('Category1');

insert into telega_category.category(name, parent_id) VALUES ('Category2', 1);
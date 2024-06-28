CREATE TABLE universities
(
    id SERIAL PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    alpha_two_code VARCHAR (255) NOT NULL,
    country VARCHAR (255) NOT NULL,
    web_pages JSONB NOT NULL,
    state_province VARCHAR (255) default NULL,
    domains JSONB NOT NULL
);
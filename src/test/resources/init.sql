CREATE table company(
                        id SERIAL primary key ,
                        name VARCHAR
);


CREATE TABLE users(
                      id BIGSERIAL PRIMARY KEY,
                      username VARCHAR UNIQUE NOT NULL ,
                      first_name VARCHAR NOT NULL,
                      last_name VARCHAR NOT NULL,
                      birth_date DATE NOT NULL,
                      age INTEGER NOT NULL,
                      role VARCHAR NOT NULL,
                      company_id INTEGER NOT NULL REFERENCES company(id)
);
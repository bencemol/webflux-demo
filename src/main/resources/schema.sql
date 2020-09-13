CREATE TABLE user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    registered TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO user(name) VALUES ('József');
INSERT INTO user(name) VALUES ('Béla');


CREATE TABLE message (
    id SERIAL PRIMARY KEY,
    user_id INT,
    content VARCHAR(1024) NOT NULL,
    sent TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE message
    ADD FOREIGN KEY (user_id)
    REFERENCES user(id);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(id, username, email, password) VALUES(1, 'admin', 'admin@email.com', '$2y$10$rg2cJG4jS0mh0aCNXb1It.2FayI8gbDE16c4pBmcKW1qbZEZaaIWK');
INSERT INTO user_roles(user_id, role_id) VALUES (1, 3);
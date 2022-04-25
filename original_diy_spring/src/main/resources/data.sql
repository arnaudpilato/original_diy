INSERT INTO roles(id, name) VALUES(1, 'ROLE_USER');
INSERT INTO roles(id, name) VALUES(2, 'ROLE_ADMIN');

INSERT INTO users(id, username, email, password) VALUES(1, 'admin', 'admin@email.com', '$2y$10$u4wHC5Y2u8ZkJTCofwWCTOgumPYYlACOX1Sa8xPuGdkXxvw1zCmo6');
INSERT INTO user_roles(user_id, role_id) VALUES (1, 2);
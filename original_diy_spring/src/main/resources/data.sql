INSERT INTO roles(id, name) VALUES(1, 'ROLE_USER');
INSERT INTO roles(id, name) VALUES(2, 'ROLE_ADMIN');

INSERT INTO users(username, email, password) VALUES( 'admin', 'admin@email.com', '$2y$10$u4wHC5Y2u8ZkJTCofwWCTOgumPYYlACOX1Sa8xPuGdkXxvw1zCmo6');
INSERT INTO users(username, email, password) VALUES( 'user', 'user@email.com', 'user');

INSERT INTO user_roles(user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles(user_id, role_id) VALUES (2, 1);



INSERT INTO category(name) VALUE ('Aménagements intérieurs');
INSERT INTO category(name) VALUE ('Aménagements extérieurs');
INSERT INTO category(name) VALUE ('Les Animaux de compagnie');
INSERT INTO category(name) VALUE ('Les fêtes de l''année');



INSERT INTO sub_category(name, category_id) VALUE ('Arbre à papier toilette / Morpion', 1);
INSERT INTO sub_category(name, category_id) VALUE ('Table de chevet', 1);
INSERT INTO sub_category(name, category_id) VALUE ('Bibliothèque Skate', 1);
INSERT INTO sub_category(name, category_id) VALUE ('Patère Murale', 1);
INSERT INTO sub_category(name, category_id) VALUE ('Plateau de service', 1);
INSERT INTO sub_category(name, category_id) VALUE ('Zoo animaux', 1);
INSERT INTO sub_category(name, category_id) VALUE ('Maison pour hérissons', 2);
INSERT INTO sub_category(name, category_id) VALUE ('Maison pour oiseaux', 2);
INSERT INTO sub_category(name, category_id) VALUE ('Hôtel à insectes', 2);
INSERT INTO sub_category(name, category_id) VALUE ('Table de rempotage', 2);
INSERT INTO sub_category(name, category_id) VALUE ('Tablette de balcon', 2);
INSERT INTO sub_category(name, category_id) VALUE ('Jardinière original', 2);
INSERT INTO sub_category(name, category_id) VALUE ('Carré potager', 2);
INSERT INTO sub_category(name, category_id) VALUE ('Porte-accessoires', 3);
INSERT INTO sub_category(name, category_id) VALUE ('Panier de repos', 3);
INSERT INTO sub_category(name, category_id) VALUE ('Arbre à chat', 3);
INSERT INTO sub_category(name, category_id) VALUE ('Niche pour chien', 3);
INSERT INTO sub_category(name, category_id) VALUE ('Décorations de Noël', 4);
INSERT INTO sub_category(name, category_id) VALUE ('Décoration d''Halloween', 4);
INSERT INTO sub_category(name, category_id) VALUE ('Décoration d''Anniversaire', 4);
INSERT INTO sub_category(name, category_id) VALUE ('Décoration de Kermesse', 4);



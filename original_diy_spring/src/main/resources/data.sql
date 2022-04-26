INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(username, email, password) VALUES( 'admin', 'admin@email.com', 'admin');
INSERT INTO users(username, email, password) VALUES( 'user', 'user@email.com', 'user');

INSERT INTO user_roles(user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles(user_id, role_id) VALUES (2, 1);

INSERT INTO sub_category(name) VALUE ('Arbre à papier toilette / Morpion');
INSERT INTO sub_category(name) VALUE ('Table de chevet');
INSERT INTO sub_category(name) VALUE ('Bibliothèque Skate');
INSERT INTO sub_category(name) VALUE ('Patère Murale');
INSERT INTO sub_category(name) VALUE ('Plateau de service');
INSERT INTO sub_category(name) VALUE ('Zoo animaux');
INSERT INTO sub_category(name) VALUE ('Maison pour hérissons');
INSERT INTO sub_category(name) VALUE ('Maison pour oiseaux');
INSERT INTO sub_category(name) VALUE ('Hôtel à insectes');
INSERT INTO sub_category(name) VALUE ('Table de rempotage');
INSERT INTO sub_category(name) VALUE ('Tablette de balcon');
INSERT INTO sub_category(name) VALUE ('Jardinière original');
INSERT INTO sub_category(name) VALUE ('Carré potager');
INSERT INTO sub_category(name) VALUE ('Porte-accessoires');
INSERT INTO sub_category(name) VALUE ('Panier de repos');
INSERT INTO sub_category(name) VALUE ('Arbre à chat');
INSERT INTO sub_category(name) VALUE ('Niche pour chien');
INSERT INTO sub_category(name) VALUE ('Décorations de Noël');
INSERT INTO sub_category(name) VALUE ('Décoration d''Halloween');
INSERT INTO sub_category(name) VALUE ('Décoration d''Anniversaire');
INSERT INTO sub_category(name) VALUE ('Décoration de Kermesse');

INSERT INTO category(name) VALUE ('Aménagements intérieurs');
INSERT INTO category(name) VALUE ('Aménagements extérieurs');
INSERT INTO category(name) VALUE ('Les Animaux de compagnie');
INSERT INTO category(name) VALUE ('Les fêtes de l''année');

INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (1, 1);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (1, 2);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (1, 3);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (1, 4);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (1, 5);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (1, 6);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (2, 7);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (2, 8);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (2, 9);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (2, 10);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (2, 11);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (2, 12);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (2, 13);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (3, 14);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (3, 15);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (3, 16);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (3, 17);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (4, 18);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (4, 19);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (4, 20);
INSERT INTO category_sub_categories(diy_category_id, sub_categories_id) VALUES (4, 21);

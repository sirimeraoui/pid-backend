INSERT INTO roles (id,title,description,version) VALUES (nextval('hibernate_sequence'),'Client','read/write/:reservations read/write:contact_info',1);
INSERT INTO roles (id,title,description,version) VALUES (nextval('hibernate_sequence'),'Manager','read,create....',1);
INSERT INTO roles (id,title,description,version) VALUES (nextval('hibernate_sequence'),'Passenger','read:reservations ',1);
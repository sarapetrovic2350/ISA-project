INSERT INTO public.authority (name) VALUES ('ROLE_REGISTERED_USER');
INSERT INTO public.authority (name) VALUES ('ROLE_CENTER_ADMINISTRATOR');
INSERT INTO public.authority (name) VALUES ('ROLE_SYSTEM_ADMINISTRATOR');

INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Bulevar Oslobodjenja', '21');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Strazilovska', '52');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Temerin', 'Srbija', 'Novosadska', '371');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Beogradska', '10');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Nikole Tesle', '85');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Zeleznicka', '36');

INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id, image) VALUES (default, 'CenterNo1', 'Centar za transfuziju krvi no1', '5.0', '1', 'medicalCenter1.jpg');
INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id, image) VALUES (default, 'The Blood Connection', 'Centar za transfuziju krvi Blood Connection', '4.6', '5', 'medicalCenter2.jpg');
INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id, image) VALUES (default, 'CenterNo2', 'Drugi centar za transfuziju krvi', '5', '6', 'medicalCenter3.jpg');

-- REGISTERED_USER email: petrovicsara10@gmail.com password: sarap
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, penalties, user_type, address_id, authority_id, enabled) VALUES ('RegisteredUser', default, '2010000805045', 'Sara', 'Petrovic', '$2y$12$RU7O/mGpTgy4lD1GH2mf7.9bM54T2eoDlP7YbrU1P8lurHe3dQ/4q', 'K+IsDJLi43-jlR+Fiap1mG==', 'FEMALE', 'STUDENT', 'FTN', 'petrovicsara10@gmail.com', '061123435', '0', 'REGISTERED_USER', '3', '1', true);
-- REGISTERED_USER email: sveta@gmail.com password: lavrov
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, penalties, user_type, address_id, authority_id, enabled) VALUES ('RegisteredUser', default, '1511985800035', 'Sveta', 'Lavrov', '$2y$12$SmP2.gp8vyxVrASh91PDWeziiMvPd7hMdMfYjt1Ga0KDgJDRSBVXK', 'Al3sj7R4S5tT6iNc7W3mOK==', 'MALE', 'EMPLOYED', 'FTN', 'sveta@gmail.com', '0648893435', '0', 'REGISTERED_USER', '4', '1', true);
-- SYSTEM_ADMINISTRATOR email: vuk@gmail.com password: vuk123
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, user_type, address_id, authority_id, enabled) VALUES ('SystemAdministrator', default, '1703998800145', 'Vuk', 'Lakic', '$2y$12$hYlDDaqIQkXlRdCHjVDeoOhEpiCOUR7.DmRgLD5.qTA2ly8kzzm2q', 'ByCrDgSvdhj1vT-5J9aVbS==', 'MALE', 'EMPLOYED', 'Admin', 'vuk@gmail.com', '0642567895', 'SYSTEM_ADMINISTRATOR', '5', '3', true);
-- CENTER_ADMINISTRATOR email: una@gmail.com password: una
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, user_type, address_id, medical_center_id, authority_id, enabled) VALUES ('CenterAdministrator', default, '1403989800145', 'Una', 'Lazic', '$2y$12$63PJhWPMm4EU2Rheu7ZewOrsUN5g.qTQbIWQT5cNWN52PeHEZF.rG', 'l1oBGDi4sHgZxXz+7aVbBS==', 'FEMALE', 'EMPLOYED', 'CenterNo1', 'una@gmail.com', '0619336789', 'CENTER_ADMINISTRATOR', '2', '1', '2', true);
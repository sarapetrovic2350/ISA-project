INSERT INTO public.authority (name) VALUES ('ROLE_REGISTERED_USER');
INSERT INTO public.authority (name) VALUES ('ROLE_CENTER_ADMINISTRATOR');
INSERT INTO public.authority (name) VALUES ('ROLE_SYSTEM_ADMINISTRATOR');

INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Bulevar Oslobodjenja', '21');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Strazilovska', '52');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Temerin', 'Srbija', 'Novosadska', '371');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Beogradska', '10');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Nikole Tesle', '85');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Zeleznicka', '36');

INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id) VALUES (default, 'CenterNo1', 'Centar za transfuziju krvi no1', '5.0', '1');
INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id) VALUES (default, 'CenterNo2', 'Drugi centar za transfuziju krvi', '4.6', '6');

INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, gender, occupation, occupation_info, email, phone_number, penalties, user_type, address_id, authority_id) VALUES ('RegisteredUser', default, '2010000805045', 'Sara', 'Petrovic', 'sarap', 'FEMALE', 'STUDENT', 'FTN', 'petrovicsara10@gmail.com', '061123435', '0', 'REGISTERED_USER', '3', '1');
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, gender, occupation, occupation_info, email, phone_number, penalties, user_type, address_id, authority_id) VALUES ('RegisteredUser', default, '1511985800035', 'Sveta', 'Lavrov', 'lavrov', 'MALE', 'EMPLOYED', 'FTN', 'sveta@gmail.com', '0648893435', '0', 'REGISTERED_USER', '4', '1');
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, gender, occupation, occupation_info, email, phone_number, user_type, address_id, authority_id) VALUES ('SystemAdministrator', default, '1703998800145', 'Vuk', 'Lakic', 'vule', 'MALE', 'EMPLOYED', 'Admin', 'vuk@gmail.com', '0642567895', 'SYSTEM_ADMINISTRATOR', '5', '3');
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, gender, occupation, occupation_info, email, phone_number, user_type, address_id, medical_center_id, authority_id) VALUES ('CenterAdministrator', default, '1403989800145', 'Una', 'Lazic', 'una', 'FEMALE', 'EMPLOYED', 'CenterNo1', 'una@gmail.com', '0619336789', 'CENTER_ADMINISTRATOR', '2', '1', '2');


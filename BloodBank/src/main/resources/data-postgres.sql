INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Bulevar Oslobodjenja', '21');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Strazilovska', '52');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Temerin', 'Srbija', 'Novosadska', '371');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Beogradska', '10');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Nikole Tesle', '85');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Zeleznicka', '36');

INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id) VALUES (default, 'CenterNo1', 'Centar za transfuziju krvi no1', '5.0', '1');
INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id) VALUES (default, 'CenterNo2', 'Drugi centar za transfuziju krvi', '4.6', '6');

INSERT INTO public.user (dtype, user_id, name, surname, password, gender, email, phone_number, user_type, address_id) VALUES ('UnregisteredUser', default, 'Sara', 'Petrovic', 'sarap', 'FEMALE', 'petrovicsara10@gmail.com', '061123435', 'UNREGISTERED_USER', '3');
INSERT INTO public.user (dtype, user_id, name, surname, password, gender, email, phone_number, user_type, address_id) VALUES ('UnregisteredUser', default, 'Sveta', 'Lavrov', 'lavrov', 'MALE', 'sveta@gmail.com', '0648893435', 'UNREGISTERED_USER', '4');
INSERT INTO public.user (dtype, user_id, name, surname, password, gender, email, phone_number, user_type, address_id) VALUES ('SystemAdministrator', default, 'Vuk', 'Lakic', 'vule', 'MALE', 'vuk@gmail.com', '0642567895', 'SYSTEM_ADMINISTRATOR', '5');
INSERT INTO public.user (dtype, user_id, name, surname, password, gender, email, phone_number, user_type, address_id, medical_center_id) VALUES ('CenterAdministrator', default, 'Una', 'Lazic', 'una', 'FEMALE', 'una@gmail.com', '0619336789', 'CENTER_ADMINISTRATOR', '2', '1');


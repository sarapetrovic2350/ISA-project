INSERT INTO public.authority (name) VALUES ('ROLE_REGISTERED_USER');
INSERT INTO public.authority (name) VALUES ('ROLE_CENTER_ADMINISTRATOR');
INSERT INTO public.authority (name) VALUES ('ROLE_SYSTEM_ADMINISTRATOR');

INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Bulevar Oslobodjenja', '21');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Strazilovska', '52');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Temerin', 'Srbija', 'Novosadska', '371');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Beogradska', '10');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Nikole Tesle', '85');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Zeleznicka', '36');

INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id, image) VALUES (default, 'CenterNo1', 'Centar za transfuziju krvi no1', '5.0', '1', 'medicalCenter4.jpg');
INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id, image) VALUES (default, 'The Blood Connection', 'Centar za transfuziju krvi Blood Connection', '4.6', '5', 'medicalCenter2.jpg');
INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id, image) VALUES (default, 'CenterNo2', 'Drugi centar za transfuziju krvi', '4.8', '6', 'medicalCenter6.jpg');
INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id, image) VALUES (default, 'Medical Center Hope', 'Novi centar za transfuziju krvi', '4.8', '5', 'medicalCenter5.jpg');
INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id, image) VALUES (default, 'Save life', 'Centar za donaciju krvi Save Life', '5.0', '3', 'medicalCenter3.jpg');
INSERT INTO public.medical_center (center_id, name, description, average_grade, address_id, image) VALUES (default, 'Blood Bank', 'Medicinski centar Blood Bank', '3.6', '4', 'medicalCenter1.jpg');

-- REGISTERED_USER email: petrovicsara10@gmail.com password: sarap
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, penalties, user_type, address_id, authority_id, enabled) VALUES ('RegisteredUser', default, '2010000805045', 'Sara', 'Petrovic', '$2y$12$RU7O/mGpTgy4lD1GH2mf7.9bM54T2eoDlP7YbrU1P8lurHe3dQ/4q', 'K+IsDJLi43-jlR+Fiap1mG==', 'FEMALE', 'STUDENT', 'FTN', 'petrovicsara10@gmail.com', '061123435', '0', 'REGISTERED_USER', '3', '1', true);
-- REGISTERED_USER email: sveta@gmail.com password: lavrov
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, penalties, user_type, address_id, authority_id, enabled) VALUES ('RegisteredUser', default, '1511985800035', 'Sveta', 'Lavrov', '$2y$12$SmP2.gp8vyxVrASh91PDWeziiMvPd7hMdMfYjt1Ga0KDgJDRSBVXK', 'Al3sj7R4S5tT6iNc7W3mOK==', 'MALE', 'EMPLOYED', 'FTN', 'sveta@gmail.com', '0648893435', '0', 'REGISTERED_USER', '4', '1', true);
-- SYSTEM_ADMINISTRATOR email: vuk@gmail.com password: vuk123
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, user_type, address_id, authority_id, enabled) VALUES ('SystemAdministrator', default, '1703998800145', 'Vuk', 'Lakic', '$2y$12$hYlDDaqIQkXlRdCHjVDeoOhEpiCOUR7.DmRgLD5.qTA2ly8kzzm2q', 'ByCrDgSvdhj1vT-5J9aVbS==', 'MALE', 'EMPLOYED', 'Admin', 'vuk@gmail.com', '0642567895', 'SYSTEM_ADMINISTRATOR', '5', '3', true);
-- CENTER_ADMINISTRATOR email: una@gmail.com password: una
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, user_type, address_id, medical_center_id, authority_id, enabled) VALUES ('CenterAdministrator', default, '1403989800145', 'Una', 'Lazic', '$2y$12$63PJhWPMm4EU2Rheu7ZewOrsUN5g.qTQbIWQT5cNWN52PeHEZF.rG', 'l1oBGDi4sHgZxXz+7aVbBS==', 'FEMALE', 'EMPLOYED', 'CenterNo1', 'una@gmail.com', '0619336789', 'CENTER_ADMINISTRATOR', '2', '1', '2', true);
-- CENTER_ADMINISTRATOR email: marko@gmail.com password: marko
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, user_type, address_id, medical_center_id, authority_id, enabled) VALUES ('CenterAdministrator', default, '0911098800145', 'Marko', 'Markovic', '$2y$12$7f3SPOH3hy23KRYfngjsjeRbTGA295w/O0P1lHIoR/qtPwVOzseH2', 'l12NSVi4sHsZzXz+7aVbBS==', 'MALE', 'EMPLOYED', 'CenterNo2', 'marko@gmail.com', '0631212121', 'CENTER_ADMINISTRATOR', '3', '3', '2', true);
-- CENTER_ADMINISTRATOR email: milana@gmail.com password: milana
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, user_type, address_id, medical_center_id, authority_id, enabled) VALUES ('CenterAdministrator', default, '0911956800145', 'Milana', 'Milic', '$2y$12$WyfXpxifDHh/1wf2eedx9uwn7BLetFD1CzknC1F3V87skZLlP1COG', '34lvfDi4sHgZxXz+7aVbBS==', 'FEMALE', 'EMPLOYED', 'The Blood Connection', 'milana@gmail.com', '0645677650', 'CENTER_ADMINISTRATOR', '4', '2', '2', true);
-- CENTER_ADMINISTRATOR email: petar@gmail.com password: petar
INSERT INTO public.user (dtype, user_id, jmbg, name, surname, password, salt, gender, occupation, occupation_info, email, phone_number, user_type, address_id, medical_center_id, authority_id, enabled) VALUES ('CenterAdministrator', default, '1505000800145', 'Petar', 'Petrovic', '$2y$12$Wm7c0bzr1uu1rKjtU8qGCedUmDUjRjkW.AZFMbKPgRh3.vwycR7Wa', '98oZRNi4sHgZxXx+7aVbBS==', 'MALE', 'EMPLOYED', 'CenterNo1', 'petar@gmail.com', '0619999888', 'CENTER_ADMINISTRATOR', '5', '1', '2', true);

INSERT INTO public.appointment (appointment_id, date, duration, center_admin_id, user_id) VALUES (default,'2022-12-12 12:05:00', '10', '4', '1'); 
INSERT INTO public.appointment (appointment_id, date, duration, center_admin_id, user_id) VALUES (default,'2022-12-12 12:20:00', '10', '4', '1'); 

INSERT INTO public.complaint (complaint_id, date, text_complaint, text_answer, user_id, center_admin_id, medical_center_id, system_admin_id) VALUES (default, '2022-10-10 12:05:00','neki tekst', 'neki tekst', '1', null, '1', '3' ); 
INSERT INTO public.complaint (complaint_id, date, text_complaint, text_answer, user_id, center_admin_id, medical_center_id, system_admin_id) VALUES (default, '2022-11-11 12:05:00', 'neki tekst', 'neki tekst', '1', '4', null, '3'); 
INSERT INTO public.complaint (complaint_id, date, text_complaint, text_answer, user_id, center_admin_id, medical_center_id, system_admin_id) VALUES (default, '2022-09-09 12:05:00', 'neki tekst', 'neki tekst', '1', null, '2', '3');

INSERT INTO public.donor_questionnaire (id, age, general_good_health, has_period, normal_blood_pressure, recently_donated_blood, visited_dentist, skin_disorders, symptoms_of_illness, tattoo_or_piercing, under_medication, weight, user_id ) VALUES (default, '22', true, false, true, false, false, false, false, false, false, '56', '2' ); 

INSERT INTO public.bloods (blood_id, blood_type, quantaty) VALUES (default, 'A_POSITIVE', 1000);
INSERT INTO public.bloods (blood_id, blood_type, quantaty) VALUES (default, 'A_NEGATIVE', 1000);
INSERT INTO public.bloods (blood_id, blood_type, quantaty) VALUES (default, 'B_POSITIVE', 1000);
INSERT INTO public.bloods (blood_id, blood_type, quantaty) VALUES (default, 'B_NEGATIVE', 1000);
INSERT INTO public.bloods (blood_id, blood_type, quantaty) VALUES (default, 'ZERO_POSITIVE', 1000);
INSERT INTO public.bloods (blood_id, blood_type, quantaty) VALUES (default, 'ZERO_NEGATIVE', 1000);
INSERT INTO public.bloods (blood_id, blood_type, quantaty) VALUES (default, 'AB_POSITIVE', 1000);
INSERT INTO public.bloods (blood_id, blood_type, quantaty) VALUES (default, 'AB_NEGATIVE', 1000);

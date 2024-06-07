ALTER TABLE employer
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES code_bridge_user (user_id);

ALTER TABLE candidate
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES code_bridge_user (user_id);

insert into code_bridge_user (user_id, user_name, email, password, active) values (1, 'CodeSolutions', ' info@codesolutions.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into code_bridge_user (user_id, user_name, email, password, active) values (2, 'TechInnovators', 'info@techinnovators.net', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into code_bridge_user (user_id, user_name, email, password, active) values (3, 'DevMasters', 'info@devmasters.p', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into code_bridge_user (user_id, user_name, email, password, active) values (4, 'NextGenCoders', 'info@nextgencoders.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);

insert into code_bridge_user (user_id, user_name, email, password, active) values (5, 'Anna_Nowak', 'annanowak@gmail.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into code_bridge_user (user_id, user_name, email, password, active) values (6, 'Adam_Kowalski', 'adamkowalski@gmail.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into code_bridge_user (user_id, user_name, email, password, active) values (7, 'Katarzyna_Matejko', 'katarzynamatejko@gmail.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);


UPDATE employer SET user_id = 1 WHERE nip = '1234563218';
UPDATE employer SET user_id = 2 WHERE nip = '9876543210';
UPDATE employer SET user_id = 3 WHERE nip = '2345678901';
UPDATE employer SET user_id = 4 WHERE nip = '8765432109';

UPDATE candidate SET user_id = 5 WHERE phone = '600123456';
UPDATE candidate SET user_id = 6 WHERE phone = '601234867';
UPDATE candidate SET user_id = 7 WHERE phone = '602345678';

    insert into code_bridge_role (role_id, role) values (1, 'EMPLOYER'), (2, 'CANDIDATE');

insert into code_bridge_user_role (user_id, role_id) values (1, 1), (2, 1), (3, 1), (4, 1);
insert into code_bridge_user_role (user_id, role_id) values (5, 2), (6, 2), (7, 2);


ALTER TABLE employer
ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE candidate
ALTER COLUMN user_id SET NOT NULL;
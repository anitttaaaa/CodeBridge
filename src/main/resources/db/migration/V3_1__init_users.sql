ALTER TABLE employer
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES code_bridge_user (user_id);

ALTER TABLE candidate
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES code_bridge_user (user_id);

insert into code_bridge_user (user_id, user_name, email, password, active) values (1, 'CodeSolutions', 'info@codesolutions.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into code_bridge_user (user_id, user_name, email, password, active) values (2, 'TechInnovators', 'info@techinnovators.net', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into code_bridge_user (user_id, user_name, email, password, active) values (3, 'DevMasters', 'info@devmasters.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into code_bridge_user (user_id, user_name, email, password, active) values (4, 'NextGenCoders', 'info@nextgencoders.com', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);

insert into code_bridge_user (user_id, user_name, email, password, active) values (5, 'Anna_Nowak', 'annanowak@gmail.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into code_bridge_user (user_id, user_name, email, password, active) values (6, 'Adam_Kowalski', 'adamkowalski@gmail.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);
insert into code_bridge_user (user_id, user_name, email, password, active) values (7, 'Katarzyna_Matejko', 'katarzynamatejko@gmail.pl', '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true);


UPDATE employer SET user_id = 1 WHERE email = 'info@codesolutions.com';
UPDATE employer SET user_id = 2 WHERE email = 'info@techinnovators.net';
UPDATE employer SET user_id = 3 WHERE email = 'info@devmasters.pl';
UPDATE employer SET user_id = 4 WHERE email = 'info@nextgencoders.com';

UPDATE candidate SET user_id = 5 WHERE email = 'annanowak@gmail.pl';
UPDATE candidate SET user_id = 6 WHERE email = 'adamkowalski@gmail.pl';
UPDATE candidate SET user_id = 7 WHERE email = 'katarzynamatejko@gmail.pl';

insert into code_bridge_role (role_id, role) values (1, 'EMPLOYER'), (2, 'CANDIDATE');

insert into code_bridge_user_role (user_id, role_id) values (1, 1), (2, 1), (3, 1), (4, 1);
insert into code_bridge_user_role (user_id, role_id) values (5, 2), (6, 2), (7, 2);


ALTER TABLE employer
ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE candidate
ALTER COLUMN user_id SET NOT NULL;
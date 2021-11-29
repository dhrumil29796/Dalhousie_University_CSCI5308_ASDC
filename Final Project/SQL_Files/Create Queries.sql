USE CSCI5308_13_DEVINT;

CREATE TABLE IF NOT EXISTS security_question_user (
security_question_id INT AUTO_INCREMENT,
security_question VARCHAR(255) NOT NULL UNIQUE,
PRIMARY KEY (security_question_id)
);

CREATE TABLE IF NOT EXISTS user (
user_id INT AUTO_INCREMENT,
first_name TEXT NOT NULL,
last_name TEXT NOT NULL,
gender ENUM('M','F','O') NOT NULL,
date_of_birth DATE NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
password TEXT NOT NULL,
contact_number VARCHAR(10) NOT NULL UNIQUE,
blood_group ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-') NOT NULL,
address_first_line TEXT NOT NULL,
address_street TEXT NOT NULL,
address_city TEXT NOT NULL,
address_province TEXT NOT NULL,
address_zip_code VARCHAR(6) NOT NULL,
address_country TEXT NOT NULL,
account_active BOOLEAN NOT NULL DEFAULT true,
PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS user_security_question_user(
user_id INT NOT NULL,
security_question_id INT NOT NULL,
security_question_ans TEXT NOT NULL,
FOREIGN KEY (user_id) REFERENCES user(user_id),
FOREIGN KEY (security_question_id) REFERENCES security_question_user(security_question_id)
);

CREATE TABLE IF NOT EXISTS security_question_blood_bank (
security_question_id INT AUTO_INCREMENT,
security_question VARCHAR(255) NOT NULL UNIQUE,
PRIMARY KEY (security_question_id)
);

CREATE TABLE IF NOT EXISTS blood_bank (
blood_bank_id INT AUTO_INCREMENT,
name TEXT NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
password TEXT NOT NULL,
contact_number VARCHAR(10) NOT NULL UNIQUE,
address_first_line TEXT NOT NULL,
address_street TEXT NOT NULL,
address_city TEXT NOT NULL,
address_province TEXT NOT NULL,
address_zip_code VARCHAR(6) NOT NULL,
address_country TEXT NOT NULL,
account_active BOOLEAN NOT NULL DEFAULT true,
PRIMARY KEY (blood_bank_id)
);

CREATE TABLE IF NOT EXISTS blood_bank_security_question_blood_bank(
blood_bank_id INT NOT NULL,
security_question_id INT NOT NULL,
security_question_ans TEXT NOT NULL,
FOREIGN KEY (blood_bank_id) REFERENCES blood_bank(blood_bank_id),
FOREIGN KEY (security_question_id) REFERENCES security_question_blood_bank(security_question_id)
);

CREATE TABLE IF NOT EXISTS blood_donation_request (
request_id INT AUTO_INCREMENT,
user_id INT NOT NULL,
request_date VARCHAR(45) NOT NULL,
status_change_date VARCHAR(45) NOT NULL,
status ENUM('active', 'request', 'rejected', 'fulfilled'),
certificate_id VARCHAR(255),
PRIMARY KEY (request_id),
FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE IF NOT EXISTS blood_receiver_request (
request_id INT AUTO_INCREMENT,
user_id INT NOT NULL,
blood_group ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-') NOT NULL,
quantity INT NOT NULL,
request_date DATE NOT NULL,
status ENUM('active', 'request', 'rejected','fulfilled'),
status_change_date  DATE NOT NULL,
PRIMARY KEY (request_id),
FOREIGN KEY (user_id) REFERENCES user(user_id)
);

# SELECT tables command
/*
SELECT * FROM user_security_question_user;
SELECT * FROM blood_bank_security_question_blood_bank;
SELECT * FROM security_question_user;
SELECT * FROM security_question_blood_bank;
SELECT * FROM blood_bank;
SELECT * FROM camp;
SELECT * FROM blood_stock;
SELECT * FROM blood_donation_request;
SELECT * FROM blood_receiver_request;
SELECT * FROM user;
SELECT * FROM blood_bank_rating;
*/
update user set blood_group = "B-" where user_id = 8;

# DESC tables command
/*
DESC user_security_question_user;
DESC blood_bank_security_question_blood_bank;
DESC security_question_user;
DESC security_question_blood_bank;
DESC user;
DESC blood_bank;
*/

# DROP tables command
/*
DROP TABLE user_security_question_user;
DROP TABLE blood_bank_security_question_blood_bank;
DROP TABLE security_question_user;
DROP TABLE security_question_blood_bank;
DROP TABLE user;
DROP TABLE blood_bank;
DROP TABLE blood_donation_request;
DROP TABLE blood_receiver_request;
*/

DELETE FROM blood_bank
WHERE blood_bank_id = 1;

INSERT INTO blood_bank 
(name, email, password, contact_number, address_first_line, address_street, address_city, address_province,address_zip_code, address_country, account_active)
VALUES
("Raheja Blood Bank", "raheja@gmail.com", "raheja", 9323394665, "Skyline", "445 Downton", "Yarmouth", "NS", "B8V5A2", "Canada", 1),
("Cooper Blood Bank", "cooper@gmail.com", "cooper", 9892389061, "LakeView Buildings", "2302 Brook St", "Cape Breton Island", "NS", "B3S9R6", "Canada", 1),
("Elmsdale Blood Collection", "elmsdale@gmail.com", "elmsdale", 9028832245, "15 Commerce Ct", "City Center", "Elmsdale", "NS", "B2S3K5", "Canada", 0);

UPDATE blood_bank
SET
name="Oxford Blood Bank", address_first_line="Oasis Park", address_street="Garodia Nagar", address_city="Mumbai", address_province ="Maharashtra", address_zip_code="400077", address_country="India"
WHERE
blood_bank_id = 4;

INSERT INTO blood_donation_request
(user_id, request_date, status_change_date, status, certificate_id)
VALUES
(7, "2021-07-10", "2021-07-10", "active", ""),
(8, "2021-07-10", "2021-07-10", "active", ""),
(8, "2021-07-05", "2021-07-08", "rejected", ""),
(8, "2021-06-01", "2021-06-10", "fulfilled", "abcd123"),
(9, "2021-05-21", "2021-05-30", "request", "");

INSERT INTO blood_receiver_request
(user_id, blood_group, quantity, request_date, status, status_change_date)
VALUES
(7, "A+", 2, "2021-07-10", "active", "2021-07-10"),
(8, "B-", 1,"2021-07-10", "active", "2021-07-10" ),
(8, "AB+", 3,"2021-07-05","rejected", "2021-07-08"),
(8, "A-", 1,"2021-06-01", "fulfilled", "2021-06-10"),
(9, "O+", 1,"2021-05-21", "request","2021-05-30");
 


SELECT
request_id,
user_id,
request_date,
status_change_date,
status,
certificate_id
FROM
blood_donation_request
WHERE
user_id = 8
ORDER BY
request_id DESC LIMIT 1;

UPDATE
blood_donation_request
SET
status = "request",
status_change_date = "2021-07-13"
WHERE
user_id = 8 AND 
status = "active";

-------------------------------------------
SELECT * FROM blood_donation_request;
SELECT * FROM user;

SELECT
u.user_id,
bdr.request_id,
u.first_name,
u.last_name,
u.date_of_birth,
u.blood_group,
u.email,
u.contact_number,
u.address_first_line,
u.address_street,
u.address_city,
u.address_province,
u.address_zip_code,
u.address_country,
bdr.status
FROM
user AS u,
blood_donation_request AS bdr
WHERE
u.user_id = bdr.user_id AND
bdr.status = "active";
# AND
#u.blood_group IN ("AB+", "B+", "O+") AND
# u.address_province = "Nova Scotia";


show processlist;
kill db = "CSCI5308_13_DEVINT";
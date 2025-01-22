-- CREATE DATABASE msgboard_db;
-- CREATE USER msgboard_user WITH PASSWORD 'msgboard_user';
-- GRANT ALL PRIVILEGES ON DATABASE msgboard_db TO msgboard_user;

CREATE TABLE administrator (
    username char(15) PRIMARY KEY,
    password char(100) NOT NULL,
    password_key char(100) NOT NULL
);

CREATE TABLE branch (
    code SERIAL PRIMARY KEY,
    title char(50) NOT NULL
);

CREATE TABLE semester (
    code SERIAL PRIMARY KEY,
    title char(25) NOT NULL UNIQUE
);

CREATE TABLE student (
    roll_number char(15) PRIMARY KEY,
    first_name char(20) NOT NULL,
    last_name char(20) NOT NULL,
    email_id char(100) NOT NULL UNIQUE,
    password char(100) NOT NULL,
    password_key char(100) NOT NULL
);

CREATE TABLE student_semester_mapping (
    roll_number char(15),
    semester_code INTEGER,
    PRIMARY KEY (roll_number, semester_code),
    FOREIGN KEY (roll_number) REFERENCES student(roll_number),
    FOREIGN KEY (semester_code) REFERENCES semester(code)
);

CREATE TABLE student_branch_mapping (
    roll_number char(15),
    branch_code INTEGER,
    PRIMARY KEY (roll_number, branch_code),
    FOREIGN KEY (roll_number) REFERENCES student(roll_number),
    FOREIGN KEY (branch_code) REFERENCES branch(code)
);
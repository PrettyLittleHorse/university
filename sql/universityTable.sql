CREATE TABLE students (
                                   "id" serial NOT NULL,
                                   "student_name" varchar(25) NOT NULL,
                                   "course_id" integer NOT NULL,
                                   "group_number" integer NOT NULL,
                                   CONSTRAINT "students_pk" PRIMARY KEY ("id")
);



CREATE TABLE subjects (
                                   "id" serial NOT NULL,
                                   "subject_name" varchar NOT NULL,
                                   "start_date" DATE NOT NULL,
                                   "stop_date" DATE NOT NULL,
                                   "course_id" integer NOT NULL,
                                   "week_day" varchar NOT NULL,
                                   CONSTRAINT "subjects_pk" PRIMARY KEY ("id")
);



CREATE TABLE courses (
                                  "id" serial NOT NULL,
                                  "course_number" integer NOT NULL,
                                  "speciality_name" varchar NOT NULL,
                                  "faculty_name" varchar NOT NULL,
                                  CONSTRAINT "courses_pk" PRIMARY KEY ("id")
);



ALTER TABLE "students" ADD CONSTRAINT "students_fk0" FOREIGN KEY ("course_id") REFERENCES "courses"("id");

ALTER TABLE "subjects" ADD CONSTRAINT "subjects_fk0" FOREIGN KEY ("course_id") REFERENCES "courses"("id");

INSERT INTO courses
VALUES (10, 1, 'High mathematics', 'Computer science');
INSERT INTO courses
VALUES (11, 2, 'High mathematics', 'Computer science');
INSERT INTO courses
VALUES (12, 3, 'High mathematics', 'Computer science');
INSERT INTO courses
VALUES (13, 4, 'High mathematics', 'Computer science');

INSERT INTO students
VALUES (1, 'Maria Anders', 10, 1);
INSERT INTO students
VALUES (2, 'Kirk Biteck', 10, 1);
INSERT INTO students
VALUES (3, 'Alan Soers', 11, 2);
INSERT INTO students
VALUES (4, 'Vitaliy Chernov', 11, 2);
INSERT INTO students
VALUES (5, 'Arkadiy Birken', 12, 3);
INSERT INTO students
VALUES (6, 'Maria Scuters', 12, 3);
INSERT INTO students
VALUES (7, 'Mirna Biteck', 13, 4);
INSERT INTO students
VALUES (8, 'Erica Soers', 13, 4);



INSERT INTO subjects
VALUES (100, 'Informatics', '2022-1-1', '2022-6-1', 10, 'MONDAY');
INSERT INTO subjects
VALUES (101, 'Introduction to methods and means of information protection', '2022-6-1', '2022-12-1', 10, 'MONDAY');
INSERT INTO subjects
VALUES (102, 'Communication workshop', '2022-1-1', '2022-6-1', 11, 'MONDAY');
INSERT INTO subjects
VALUES (103, 'Computer modeling and programming systems', '2022-6-1', '2022-12-1', 11, 'MONDAY');
INSERT INTO subjects
VALUES (104, 'Computer networks and Internet technologies', '2022-1-1', '2022-6-1', 12, 'MONDAY');
INSERT INTO subjects
VALUES (105, 'Computer networks and their administration', '2022-6-1', '2022-12-1', 12, 'MONDAY');
INSERT INTO subjects
VALUES (106, 'Assistive Information and Communication Technologies', '2022-1-1', '2022-6-1', 13, 'MONDAY');
INSERT INTO subjects
VALUES (107, 'Physical culture and sports', '2022-6-1', '2022-12-1', 13, 'MONDAY');
INSERT INTO subjects
VALUES (108, 'Technical and audiovisual teaching aids', '2022-1-1', '2022-6-1', 10, 'TUESDAY');
INSERT INTO subjects
VALUES (109, 'Computer graphics', '2022-6-1', '2022-12-1', 10, 'TUESDAY');
INSERT INTO subjects
VALUES (110, 'Chemistry', '2022-1-1', '2022-6-1', 11, 'TUESDAY');
INSERT INTO subjects
VALUES (111, 'Mathematics', '2022-6-1', '2022-12-1', 11, 'TUESDAY');
INSERT INTO subjects
VALUES (112, 'History', '2022-1-1', '2022-6-1', 12, 'TUESDAY');
INSERT INTO subjects
VALUES (113, 'High mathematics', '2022-6-1', '2022-12-1', 12, 'TUESDAY');
INSERT INTO subjects
VALUES (114, 'Computer Engineering', '2022-1-1', '2022-6-1', 13, 'TUESDAY');
INSERT INTO subjects
VALUES (115, 'Computer Forensics.', '2022-6-1', '2022-12-1', 13, 'TUESDAY');
INSERT INTO subjects
VALUES (116, 'Computer Programming', '2022-1-1', '2022-6-1', 10, 'WEDNESDAY');
INSERT INTO subjects
VALUES (117, 'Computer Science', '2022-6-1', '2022-12-1', 10, 'WEDNESDAY');
INSERT INTO subjects
VALUES (118, 'Data Science', '2022-1-1', '2022-6-1', 11, 'WEDNESDAY');
INSERT INTO subjects
VALUES (119, 'Database Management', '2022-6-1', '2022-12-1', 11, 'WEDNESDAY');
INSERT INTO subjects
VALUES (120, 'Information Systems', '2022-1-1', '2022-6-1', 12, 'WEDNESDAY');
INSERT INTO subjects
VALUES (121, 'Information Technology', '2022-6-1', '2022-12-1', 12, 'WEDNESDAY');
INSERT INTO subjects
VALUES (122, 'Database management', '2022-1-1', '2022-6-1', 13, 'WEDNESDAY');
INSERT INTO subjects
VALUES (123, 'Network administration', '2022-6-1', '2022-12-1', 13, 'WEDNESDAY');
INSERT INTO subjects
VALUES (124, 'Software engineering', '2022-1-1', '2022-6-1', 10, 'THURSDAY');
INSERT INTO subjects
VALUES (125, 'Web development', '2022-6-1', '2022-12-1', 10, 'THURSDAY');
INSERT INTO subjects
VALUES (126, 'Project Management', '2022-1-1', '2022-6-1', 11, 'THURSDAY');
INSERT INTO subjects
VALUES (127, 'Database Server Administration', '2022-6-1', '2022-12-1', 11, 'THURSDAY');
INSERT INTO subjects
VALUES (128, 'Introduction to Data Science', '2022-1-1', '2022-6-1', 12, 'THURSDAY');
INSERT INTO subjects
VALUES (129, 'Data Visualization', '2022-6-1', '2022-12-1', 12, 'THURSDAY');
INSERT INTO subjects
VALUES (130, 'Data Analytics', '2022-1-1', '2022-6-1', 13, 'THURSDAY');
INSERT INTO subjects
VALUES (131, 'Data Structures and Algorithms', '2022-6-1', '2022-12-1', 13, 'THURSDAY');
INSERT INTO subjects
VALUES (132, 'Java programming', '2022-1-1', '2022-6-1', 10, 'FRIDAY');
INSERT INTO subjects
VALUES (133, 'C++ programming', '2022-6-1', '2022-12-1', 10, 'FRIDAY');
INSERT INTO subjects
VALUES (134, 'Automation with Python', '2022-1-1', '2022-6-1', 11, 'FRIDAY');
INSERT INTO subjects
VALUES (135, 'Economics', '2022-6-1', '2022-12-1', 11, 'FRIDAY');
INSERT INTO subjects
VALUES (136, 'Philosophy', '2022-1-1', '2022-6-1', 12, 'FRIDAY');
INSERT INTO subjects
VALUES (137, 'Media management', '2022-6-1', '2022-12-1', 12, 'FRIDAY');
INSERT INTO subjects
VALUES (138, 'Physics', '2022-1-1', '2022-6-1', 13, 'FRIDAY');
INSERT INTO subjects
VALUES (139, 'English language', '2022-6-1', '2022-12-1', 13, 'FRIDAY');








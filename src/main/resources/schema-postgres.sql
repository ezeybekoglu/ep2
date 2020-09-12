DROP TABLE IF EXISTS student_to_course,student,course CASCADE;
CREATE TABLE student(id serial PRIMARY KEY, name VARCHAR(255));
CREATE TABLE course(id serial PRIMARY KEY, name VARCHAR(255));
CREATE TABLE student_to_course(
id serial PRIMARY KEY,
course_id INT,
student_id INT,
CONSTRAINT fk_course
    FOREIGN KEY(course_id)
        REFERENCES course(id)
        ON DELETE CASCADE
,
CONSTRAINT fk_student
    FOREIGN KEY(student_id)
        REFERENCES student(id)
        ON DELETE CASCADE
);
CREATE TABLE students
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    studentNumber VARCHAR(255) NOT NULL,
);

INSERT INTO students (name, email, studentNumber) VALUES ('John Doe', 'john.doe@example.com', '123456');
INSERT INTO students (name, email, studentNumber) VALUES ('Jane Smith', 'jane.smith@example.com', '654321');
INSERT INTO students (name, email, studentNumber) VALUES ('Alice Johnson', 'alice.johnson@example.com', '789012');
INSERT INTO students (name, email, studentNumber) VALUES ('Bob Brown', 'bob.brown@example.com', '345678');
INSERT INTO students (name, email, studentNumber) VALUES ('Charlie Davis', 'charlie.davis@example.com', '901234');
CREATE TABLE student (
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL
);

INSERT INTO student (name, email) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO student (name, email) VALUES ('Jane Smith', 'jane.smith@example.com');
INSERT INTO student (name, email) VALUES ('Alice Johnson', 'alice.johnson@example.com');
INSERT INTO student (name, email) VALUES ('Bob Brown', 'bob.brown@example.com');
INSERT INTO student (name, email) VALUES ('Charlie Davis', 'charlie.davis@example.com');
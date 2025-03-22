CREATE TABLE car (
    car_id  SERIAL PRIMARY KEY,
    maker   VARCHAR(255),
    model   VARCHAR(255)
);

INSERT INTO car (maker, model) VALUES ('Ford', 'Fiesta'), ('Renault', 'Clio'), ('Volkswagen', 'Golf'), ('BMW', 'M3');
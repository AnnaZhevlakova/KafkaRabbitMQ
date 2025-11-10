create schema IF NOT exists netology;

CREATE TABLE IF NOT exists netology.credit_applications (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY ,
    amount DECIMAL(15,2) NOT NULL,
    term_months INT NOT NULL,
    income DECIMAL(15,2) NOT NULL,
    current_debt DECIMAL(15,2) NOT NULL,
    credit_score INT NOT NULL,
    status int NOT NULL
);



CREATE table IF NOT exists netology.outbox_events (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    event_type VARCHAR(100) NOT NULL,
    payload TEXT,
    published BOOLEAN DEFAULT false,
    created_at TIMESTAMP WITH TIME zone NOT NULL
);
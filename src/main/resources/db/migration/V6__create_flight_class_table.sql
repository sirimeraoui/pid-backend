CREATE TABLE FLIGHT_CLASS(
    id int primary key,
    name varchar(255) not null,
    price DECIMAL(6,2) not null,
    description varchar(255) not null
)
CREATE TABLE AIRPORT(
    id int primary key,
    name varchar(255) not null,
    city varchar(255) not null,
    country varchar(255) not null,
    latitude decimal(8,6) not null,
    longitude decimal(9,6) not null
)
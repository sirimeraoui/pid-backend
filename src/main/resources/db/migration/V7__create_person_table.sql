CREATE TABLE Person(
    id int primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    contact_id int references CONTACT_INFO(id)
);
CREATE TABLE person_role(
    person_id int references Person(id),
    role_id int references Roles(id),
    CONSTRAINT PK_PERSON_ROLE PRIMARY KEY (person_id,role_id)
)

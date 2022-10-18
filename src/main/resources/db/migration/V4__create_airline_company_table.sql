CREATE TABLE AIRLINE_COMPANY(
    id int primary key,
    name varchar(255) not null,
    motto varchar(255) ,
    contact_id int references CONTACT_INFO(id)
)
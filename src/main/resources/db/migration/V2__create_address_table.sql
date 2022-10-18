
-- CREATE TABLE CONTACT_INFO(
--   id int primary key,
--   phone_number int ,
--   email_address varchar(255)
-- );


CREATE TABLE Address (
  id int primary key,
  town varchar(255) not null,
  postal_code int not null,
  region varchar(255) not null,
  country varchar(255) not null
  -- contact_id int references CONTACT_INFO(id)
)





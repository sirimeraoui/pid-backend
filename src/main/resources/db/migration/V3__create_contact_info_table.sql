
CREATE TABLE CONTACT_INFO(
  id int primary key,
  phone_number int ,
  email_address varchar(255)
);
ALTER TABLE Address
ADD COLUMN contact_id int references CONTACT_INFO(id);
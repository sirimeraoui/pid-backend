INSERT INTO ADDRESS (id,town,postal_code,region,country,version) VALUES (nextval('hibernate_sequence'),'Uccle',1180,'Bruxelles','Belgique',1);

INSERT INTO CONTACT_INFO (id,phone_number,email_address,version) VALUES (nextval('hibernate_sequence'), 023321166,'eafc@uccle.be',1);
UPDATE ADDRESS SET contact_id =
(SELECT id from CONTACT_INFO where CONTACT_INFO.phone_number = 023321166 );
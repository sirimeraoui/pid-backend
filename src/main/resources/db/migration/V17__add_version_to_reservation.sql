ALTER TABLE RESERVATION ADD COLUMN version integer;

UPDATE RESERVATION SET version = 0 WHERE version IS NULL;

ALTER TABLE RESERVATION ALTER COLUMN version SET NOT NULL;
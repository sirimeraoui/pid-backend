ALTER TABLE ROLES ADD COLUMN version integer;

UPDATE ROLES SET version = 0 WHERE version IS NULL;

ALTER TABLE ROLES ALTER COLUMN version SET NOT NULL;
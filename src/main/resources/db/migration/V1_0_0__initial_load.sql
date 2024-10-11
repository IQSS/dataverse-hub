DROP TABLE IF EXISTS installation;
DROP TABLE IF EXISTS access_token;

CREATE SEQUENCE mysequence;
CREATE TABLE IF NOT EXISTS installation (
    dv_hub_id varchar NOT NULL PRIMARY KEY,
    name varchar,
    description varchar,
    latitude varchar,
    longitude varchar,
    hostname varchar,
    country varchar,
    continent varchar,
    launch_year integer,
    gdcc_member bool,
    doi_authority varchar,
    contact_email varchar
);

CREATE TABLE IF NOT EXISTS access_token (
  token_id varchar NOT NULL PRIMARY KEY,
  user_id integer
);

INSERT INTO access_token (token_id, user_id) VALUES ('257d4485-173f-4a6d-913d-ee20c9d7bc06', 0);
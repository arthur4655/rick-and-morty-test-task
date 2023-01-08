--liquibase formatted sql
--changeset <artmar>:<create-series-character-table>
CREATE TABLE IF NOT EXISTS public.series_character
(
    id bigint NOT NULL,
    external_id bigint NOT NULL,
    name character varying(256) NOT NULL,
    status character varying(256) NOT NULL,
    gender character varying(256) NOT NULL,
    type character varying(256) NOT NULL,
    CONSTRAINT series_character_pk PRIMARY KEY (id)
);

--rollback DROP TABLE series_character;

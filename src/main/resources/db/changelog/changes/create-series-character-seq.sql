--liquibase formatted sql
--changeset <artmar>:<create-series-character-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.series_character_id_seq INCREMENT 1 START 1 MINVALUE 1;

-- rollback DROP SEQUENCE public.series_character_id_seq;

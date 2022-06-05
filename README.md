ExtJS should be as separate project on the server.

DB has only one table:

CREATE TABLE IF NOT EXISTS public.movies
(
    id bigint NOT NULL DEFAULT nextval('movies_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    budget bigint,
    gross_worldwide bigint,
    release_date date,
    CONSTRAINT movies_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.movies
    OWNER to postgres;

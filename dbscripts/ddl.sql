CREATE TABLE applicant_table
(
    applicant_id bigint NOT NULL,
    applicant_full_name character varying NOT NULL,
    applicant_profession character varying,
    PRIMARY KEY (applicant_id)
);

CREATE SEQUENCE public.applicant_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999;
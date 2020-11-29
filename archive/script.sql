CREATE TABLE person_types
(
    id smallint NOT NULL DEFAULT nextval('person_types_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_person_types_id PRIMARY KEY (id)
);

INSERT INTO person_types (name)
VALUES 
('user'),
('moderator');

CREATE TABLE departments
(
    id smallint NOT NULL DEFAULT nextval('departments_id_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_departments_id PRIMARY KEY (id)
);

CREATE TABLE skills
(
    id smallint NOT NULL DEFAULT nextval('skills_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_skills_id PRIMARY KEY (id)
);

CREATE TABLE positions
(
    id smallint NOT NULL DEFAULT nextval('positions_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_positions_id PRIMARY KEY (id)
);

CREATE TABLE research_areas
(
    id smallint NOT NULL DEFAULT nextval('research_areas_id_seq'::regclass),
    name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_research_areas_id PRIMARY KEY (id)
);

CREATE TABLE publication_types
(
    id smallint NOT NULL DEFAULT nextval('publication_types_id_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_publication_types_id PRIMARY KEY (id)
);

CREATE TABLE publication_journal_types
(
    id smallint NOT NULL DEFAULT nextval('publication_journal_types_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_publication_journal_types PRIMARY KEY (id)
);

CREATE TABLE publication_journals
(
    id integer NOT NULL DEFAULT nextval('publication_journals_id_seq'::regclass),
    name character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    type_id smallint NOT NULL,
    quartile_wos smallint,
    quartile_scopus smallint,
    CONSTRAINT pk_publication_journals PRIMARY KEY (id),
    CONSTRAINT fk_publication_journals_type_id FOREIGN KEY (type_id)
        REFERENCES publication_journal_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE publications
(
    id integer NOT NULL DEFAULT nextval('publications_id_seq'::regclass),
    name character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    type_id smallint NOT NULL,
    research_area_id smallint NOT NULL,
    journal_id integer NOT NULL,
    date date,
    CONSTRAINT pk_publications PRIMARY KEY (id),
    CONSTRAINT fk_publications_journal_id FOREIGN KEY (journal_id)
        REFERENCES publication_journals (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE ,
    CONSTRAINT fk_publications_research_area_id FOREIGN KEY (research_area_id)
        REFERENCES research_areas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE ,
    CONSTRAINT fk_publications_type_id FOREIGN KEY (type_id)
        REFERENCES publication_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE 
);

CREATE TABLE persons
(
    id integer NOT NULL DEFAULT nextval('persons_id_seq'::regclass),
    phone character(11) COLLATE pg_catalog."default",
    first_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    second_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    middle_name character(30) COLLATE pg_catalog."default",
    date_of_birth timestamp without time zone,
    type_id smallint NOT NULL DEFAULT 1,
    position_id smallint,
    scopus_id integer,
    gender boolean,
    CONSTRAINT pk_persons_id PRIMARY KEY (id),
    CONSTRAINT fk_persons_position_id FOREIGN KEY (position_id)
        REFERENCES positions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_persons_type_id FOREIGN KEY (type_id)
        REFERENCES person_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT ck_persons_phone CHECK (phone ~ '\d{11}'::text),
    CONSTRAINT ck_persons_date_of_birth CHECK (date_of_birth < CURRENT_TIMESTAMP)
);

CREATE TABLE person_skills
(
    person_id integer NOT NULL,
    skill_id smallint NOT NULL,
    CONSTRAINT pk_person_skills_id PRIMARY KEY (person_id, skill_id),
    CONSTRAINT fk_person_skills_person_id FOREIGN KEY (person_id)
        REFERENCES persons (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_person_skills_skill_id FOREIGN KEY (skill_id)
        REFERENCES skills (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE person_departments
(
    person_id integer NOT NULL,
    department_id smallint NOT NULL,
    CONSTRAINT pk_person_departments PRIMARY KEY (person_id, department_id),
    CONSTRAINT fk_person_departments_department_id FOREIGN KEY (department_id)
        REFERENCES departments (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_person_departments_person_id FOREIGN KEY (person_id)
        REFERENCES persons (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
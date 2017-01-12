--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

-- Started on 2017-01-10 14:38:07 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
-- SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12403)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2176 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 16385)
-- Name: don; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE don (
    id integer NOT NULL,
    personne_id integer,
    valeur integer,
    projet_id integer NOT NULL
);


ALTER TABLE don OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16388)
-- Name: don_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE don_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE don_id_seq OWNER TO postgres;

--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 182
-- Name: don_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE don_id_seq OWNED BY don.id;


--
-- TOC entry 183 (class 1259 OID 16390)
-- Name: personne; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE personne (
    id integer NOT NULL,
    nom character varying(500),
    prenom character varying(500),
    datenaiss date,
    login character varying(100),
    mdp character varying(100)
);


ALTER TABLE personne OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16396)
-- Name: personne_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE personne_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE personne_id_seq OWNER TO postgres;

--
-- TOC entry 2178 (class 0 OID 0)
-- Dependencies: 184
-- Name: personne_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE personne_id_seq OWNED BY personne.id;


--
-- TOC entry 185 (class 1259 OID 16398)
-- Name: projet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE projet (
    id integer NOT NULL,
    nom character varying(100),
    objectif integer,
    description character varying(2000),
    project_type character varying,
    duration integer,
    film_name character varying,
    country character varying,
    vehicle character varying,
    kind character varying,
    scale integer
);


ALTER TABLE projet OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16404)
-- Name: projet_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE projet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE projet_id_seq OWNER TO postgres;

--
-- TOC entry 2179 (class 0 OID 0)
-- Dependencies: 186
-- Name: projet_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE projet_id_seq OWNED BY projet.id;


--
-- TOC entry 2042 (class 2604 OID 16406)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY don ALTER COLUMN id SET DEFAULT nextval('don_id_seq'::regclass);


--
-- TOC entry 2043 (class 2604 OID 16407)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personne ALTER COLUMN id SET DEFAULT nextval('personne_id_seq'::regclass);


--
-- TOC entry 2044 (class 2604 OID 16408)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY projet ALTER COLUMN id SET DEFAULT nextval('projet_id_seq'::regclass);


--
-- TOC entry 2046 (class 2606 OID 16410)
-- Name: don_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY don
    ADD CONSTRAINT don_pkey PRIMARY KEY (id);


--
-- TOC entry 2050 (class 2606 OID 16412)
-- Name: personne_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT personne_pkey PRIMARY KEY (id);


--
-- TOC entry 2052 (class 2606 OID 16414)
-- Name: projet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY projet
    ADD CONSTRAINT projet_pkey PRIMARY KEY (id);


--
-- TOC entry 2047 (class 1259 OID 16415)
-- Name: fki_dn_personne_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_dn_personne_id_fkey ON don USING btree (personne_id);


--
-- TOC entry 2048 (class 1259 OID 16435)
-- Name: fki_projet_don; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_projet_don ON don USING btree (projet_id);


--
-- TOC entry 2053 (class 2606 OID 16417)
-- Name: dn_personne_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY don
    ADD CONSTRAINT dn_personne_id_fkey FOREIGN KEY (personne_id) REFERENCES personne(id);


--
-- TOC entry 2054 (class 2606 OID 16430)
-- Name: fk_projet_don; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY don
    ADD CONSTRAINT fk_projet_don FOREIGN KEY (projet_id) REFERENCES projet(id);


--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-01-10 14:38:07 CET

--
-- PostgreSQL database dump complete
--


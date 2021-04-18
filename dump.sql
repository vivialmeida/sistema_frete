--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: frete; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA frete;


ALTER SCHEMA frete OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ft01a_cliente; Type: TABLE; Schema: frete; Owner: postgres
--

CREATE TABLE frete.ft01a_cliente (
                                     ft01a_id integer NOT NULL,
                                     ft01a_nome character varying(30) NOT NULL,
                                     ft01a_endereco character varying(30) NOT NULL,
                                     ft01a_telefone character varying(30) NOT NULL
);


ALTER TABLE frete.ft01a_cliente OWNER TO postgres;

--
-- Name: ft01a_cliente_ft01a_id_seq; Type: SEQUENCE; Schema: frete; Owner: postgres
--

CREATE SEQUENCE frete.ft01a_cliente_ft01a_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE frete.ft01a_cliente_ft01a_id_seq OWNER TO postgres;

--
-- Name: ft01a_cliente_ft01a_id_seq; Type: SEQUENCE OWNED BY; Schema: frete; Owner: postgres
--

ALTER SEQUENCE frete.ft01a_cliente_ft01a_id_seq OWNED BY frete.ft01a_cliente.ft01a_id;


--
-- Name: ft01b_cidade; Type: TABLE; Schema: frete; Owner: postgres
--

CREATE TABLE frete.ft01b_cidade (
                                    ft01b_id integer NOT NULL,
                                    ft01b_nome character varying(30) NOT NULL,
                                    ft01b_uf character varying(2) NOT NULL,
                                    ft01b_taxa numeric NOT NULL
);


ALTER TABLE frete.ft01b_cidade OWNER TO postgres;

--
-- Name: ft01b_cidade_ft01b_id_seq; Type: SEQUENCE; Schema: frete; Owner: postgres
--

CREATE SEQUENCE frete.ft01b_cidade_ft01b_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE frete.ft01b_cidade_ft01b_id_seq OWNER TO postgres;

--
-- Name: ft01b_cidade_ft01b_id_seq; Type: SEQUENCE OWNED BY; Schema: frete; Owner: postgres
--

ALTER SEQUENCE frete.ft01b_cidade_ft01b_id_seq OWNED BY frete.ft01b_cidade.ft01b_id;


--
-- Name: ft01c_frete; Type: TABLE; Schema: frete; Owner: postgres
--

CREATE TABLE frete.ft01c_frete (
                                   ft01c_id integer NOT NULL,
                                   ft01c_id_cliente integer NOT NULL,
                                   ft01c_id_cidade integer NOT NULL,
                                   ft01c_descricao character varying(30) NOT NULL,
                                   ft01c_peso numeric NOT NULL,
                                   ft01c_valor numeric
);


ALTER TABLE frete.ft01c_frete OWNER TO postgres;

--
-- Name: ft01c_frete_ft01c_id_seq; Type: SEQUENCE; Schema: frete; Owner: postgres
--

CREATE SEQUENCE frete.ft01c_frete_ft01c_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE frete.ft01c_frete_ft01c_id_seq OWNER TO postgres;

--
-- Name: ft01c_frete_ft01c_id_seq; Type: SEQUENCE OWNED BY; Schema: frete; Owner: postgres
--

ALTER SEQUENCE frete.ft01c_frete_ft01c_id_seq OWNED BY frete.ft01c_frete.ft01c_id;


--
-- Name: ft01a_cliente ft01a_id; Type: DEFAULT; Schema: frete; Owner: postgres
--

ALTER TABLE ONLY frete.ft01a_cliente ALTER COLUMN ft01a_id SET DEFAULT nextval('frete.ft01a_cliente_ft01a_id_seq'::regclass);


--
-- Name: ft01b_cidade ft01b_id; Type: DEFAULT; Schema: frete; Owner: postgres
--

ALTER TABLE ONLY frete.ft01b_cidade ALTER COLUMN ft01b_id SET DEFAULT nextval('frete.ft01b_cidade_ft01b_id_seq'::regclass);


--
-- Name: ft01c_frete ft01c_id; Type: DEFAULT; Schema: frete; Owner: postgres
--

ALTER TABLE ONLY frete.ft01c_frete ALTER COLUMN ft01c_id SET DEFAULT nextval('frete.ft01c_frete_ft01c_id_seq'::regclass);


--
-- Data for Name: ft01a_cliente; Type: TABLE DATA; Schema: frete; Owner: postgres
--

COPY frete.ft01a_cliente (ft01a_id, ft01a_nome, ft01a_endereco, ft01a_telefone) FROM stdin;
\.


--
-- Data for Name: ft01b_cidade; Type: TABLE DATA; Schema: frete; Owner: postgres
--

COPY frete.ft01b_cidade (ft01b_id, ft01b_nome, ft01b_uf, ft01b_taxa) FROM stdin;
\.


--
-- Data for Name: ft01c_frete; Type: TABLE DATA; Schema: frete; Owner: postgres
--

COPY frete.ft01c_frete (ft01c_id, ft01c_id_cliente, ft01c_id_cidade, ft01c_descricao, ft01c_peso, ft01c_valor) FROM stdin;
\.


--
-- Name: ft01a_cliente_ft01a_id_seq; Type: SEQUENCE SET; Schema: frete; Owner: postgres
--

SELECT pg_catalog.setval('frete.ft01a_cliente_ft01a_id_seq', 1, false);


--
-- Name: ft01b_cidade_ft01b_id_seq; Type: SEQUENCE SET; Schema: frete; Owner: postgres
--

SELECT pg_catalog.setval('frete.ft01b_cidade_ft01b_id_seq', 1, false);


--
-- Name: ft01c_frete_ft01c_id_seq; Type: SEQUENCE SET; Schema: frete; Owner: postgres
--

SELECT pg_catalog.setval('frete.ft01c_frete_ft01c_id_seq', 1, false);


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2 (Debian 17.2-1.pgdg120+1)
-- Dumped by pg_dump version 17.2

-- Started on 2025-03-19 12:34:06 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE IF EXISTS ONLY public.offermessdest DROP CONSTRAINT IF EXISTS offermessdest_idmessapp_fkey;
ALTER TABLE IF EXISTS ONLY public.offermessdest DROP CONSTRAINT IF EXISTS offermessdest_iddestoffer_fkey;
ALTER TABLE IF EXISTS ONLY public.offermessage DROP CONSTRAINT IF EXISTS offermessage_idoffer_fkey;
ALTER TABLE IF EXISTS ONLY public.offermessage DROP CONSTRAINT IF EXISTS offermessage_idmess_fkey;
ALTER TABLE IF EXISTS ONLY public.offerfields DROP CONSTRAINT IF EXISTS offerfields_offerid_fkey;
ALTER TABLE IF EXISTS ONLY public.offerfields DROP CONSTRAINT IF EXISTS offerfields_idfield_fkey;
ALTER TABLE IF EXISTS ONLY public.joboffer DROP CONSTRAINT IF EXISTS joboffer_qualification_fkey;
ALTER TABLE IF EXISTS ONLY public.joboffer DROP CONSTRAINT IF EXISTS joboffer_publisher_fkey;
ALTER TABLE IF EXISTS ONLY public.company DROP CONSTRAINT IF EXISTS company_mail_fkey;
ALTER TABLE IF EXISTS ONLY public.candidate DROP CONSTRAINT IF EXISTS candidate_mail_fkey;
ALTER TABLE IF EXISTS ONLY public.appmessdest DROP CONSTRAINT IF EXISTS appmessdest_idmessoffer_fkey;
ALTER TABLE IF EXISTS ONLY public.appmessdest DROP CONSTRAINT IF EXISTS appmessdest_iddestapp_fkey;
ALTER TABLE IF EXISTS ONLY public.applicationmessage DROP CONSTRAINT IF EXISTS applicationmessage_idmess_fkey;
ALTER TABLE IF EXISTS ONLY public.applicationmessage DROP CONSTRAINT IF EXISTS applicationmessage_idapp_fkey;
ALTER TABLE IF EXISTS ONLY public.applicationfields DROP CONSTRAINT IF EXISTS applicationfields_idfield_fkey;
ALTER TABLE IF EXISTS ONLY public.applicationfields DROP CONSTRAINT IF EXISTS applicationfields_appid_fkey;
ALTER TABLE IF EXISTS ONLY public.application DROP CONSTRAINT IF EXISTS application_qualification_fkey;
ALTER TABLE IF EXISTS ONLY public.application DROP CONSTRAINT IF EXISTS application_publisher_fkey;
ALTER TABLE IF EXISTS ONLY public.userapp DROP CONSTRAINT IF EXISTS userapp_pkey;
ALTER TABLE IF EXISTS ONLY public.qualificationlevel DROP CONSTRAINT IF EXISTS qualificationlevel_pkey;
ALTER TABLE IF EXISTS ONLY public.offermessdest DROP CONSTRAINT IF EXISTS offermessdest_pkey;
ALTER TABLE IF EXISTS ONLY public.offermessage DROP CONSTRAINT IF EXISTS offermessage_pkey;
ALTER TABLE IF EXISTS ONLY public.offerfields DROP CONSTRAINT IF EXISTS offerfields_pkey;
ALTER TABLE IF EXISTS ONLY public.message DROP CONSTRAINT IF EXISTS message_pkey;
ALTER TABLE IF EXISTS ONLY public.joboffer DROP CONSTRAINT IF EXISTS joboffer_pkey;
ALTER TABLE IF EXISTS ONLY public.field DROP CONSTRAINT IF EXISTS field_pkey;
ALTER TABLE IF EXISTS ONLY public.company DROP CONSTRAINT IF EXISTS company_pkey;
ALTER TABLE IF EXISTS ONLY public.candidate DROP CONSTRAINT IF EXISTS candidate_pkey;
ALTER TABLE IF EXISTS ONLY public.appuser DROP CONSTRAINT IF EXISTS appuser_pkey;
ALTER TABLE IF EXISTS ONLY public.appuser DROP CONSTRAINT IF EXISTS appuser_mail_key;
ALTER TABLE IF EXISTS ONLY public.appmessdest DROP CONSTRAINT IF EXISTS appmessdest_pkey;
ALTER TABLE IF EXISTS ONLY public.applicationmessage DROP CONSTRAINT IF EXISTS applicationmessage_pkey;
ALTER TABLE IF EXISTS ONLY public.applicationfields DROP CONSTRAINT IF EXISTS applicationfields_pkey;
ALTER TABLE IF EXISTS ONLY public.application DROP CONSTRAINT IF EXISTS application_pkey;
ALTER TABLE IF EXISTS public.qualificationlevel ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.offermessage ALTER COLUMN idmess DROP DEFAULT;
ALTER TABLE IF EXISTS public.message ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.joboffer ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.field ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.appuser ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.applicationmessage ALTER COLUMN idmess DROP DEFAULT;
ALTER TABLE IF EXISTS public.application ALTER COLUMN id DROP DEFAULT;
DROP TABLE IF EXISTS public.userapp;
DROP SEQUENCE IF EXISTS public.qualificationlevel_id_seq;
DROP TABLE IF EXISTS public.qualificationlevel;
DROP TABLE IF EXISTS public.offermessdest;
DROP SEQUENCE IF EXISTS public.offermessage_idmess_seq;
DROP TABLE IF EXISTS public.offermessage;
DROP TABLE IF EXISTS public.offerfields;
DROP SEQUENCE IF EXISTS public.message_id_seq;
DROP TABLE IF EXISTS public.message;
DROP SEQUENCE IF EXISTS public.joboffer_id_seq;
DROP TABLE IF EXISTS public.joboffer;
DROP SEQUENCE IF EXISTS public.field_id_seq;
DROP TABLE IF EXISTS public.field;
DROP TABLE IF EXISTS public.company;
DROP TABLE IF EXISTS public.candidate;
DROP SEQUENCE IF EXISTS public.appuser_id_seq;
DROP TABLE IF EXISTS public.appuser;
DROP TABLE IF EXISTS public.appmessdest;
DROP SEQUENCE IF EXISTS public.applicationmessage_idmess_seq;
DROP TABLE IF EXISTS public.applicationmessage;
DROP TABLE IF EXISTS public.applicationfields;
DROP SEQUENCE IF EXISTS public.application_id_seq;
DROP TABLE IF EXISTS public.application;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 229 (class 1259 OID 16763)
-- Name: application; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.application (
                                    id integer NOT NULL,
                                    cv character varying(255) NOT NULL,
                                    appdate date NOT NULL,
                                    publisher character varying(255),
                                    qualification integer
);


ALTER TABLE public.application OWNER TO pguser;

--
-- TOC entry 228 (class 1259 OID 16762)
-- Name: application_id_seq; Type: SEQUENCE; Schema: public; Owner: pguser
--

CREATE SEQUENCE public.application_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.application_id_seq OWNER TO pguser;

--
-- TOC entry 3495 (class 0 OID 0)
-- Dependencies: 228
-- Name: application_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pguser
--

ALTER SEQUENCE public.application_id_seq OWNED BY public.application.id;


--
-- TOC entry 232 (class 1259 OID 16800)
-- Name: applicationfields; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.applicationfields (
                                          appid integer NOT NULL,
                                          idfield integer NOT NULL
);


ALTER TABLE public.applicationfields OWNER TO pguser;

--
-- TOC entry 239 (class 1259 OID 16855)
-- Name: applicationmessage; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.applicationmessage (
                                           idmess integer NOT NULL,
                                           idapp integer
);


ALTER TABLE public.applicationmessage OWNER TO pguser;

--
-- TOC entry 238 (class 1259 OID 16854)
-- Name: applicationmessage_idmess_seq; Type: SEQUENCE; Schema: public; Owner: pguser
--

CREATE SEQUENCE public.applicationmessage_idmess_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.applicationmessage_idmess_seq OWNER TO pguser;

--
-- TOC entry 3496 (class 0 OID 0)
-- Dependencies: 238
-- Name: applicationmessage_idmess_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pguser
--

ALTER SEQUENCE public.applicationmessage_idmess_seq OWNED BY public.applicationmessage.idmess;


--
-- TOC entry 240 (class 1259 OID 16871)
-- Name: appmessdest; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.appmessdest (
                                    idmessoffer integer NOT NULL,
                                    iddestapp integer NOT NULL
);


ALTER TABLE public.appmessdest OWNER TO pguser;

--
-- TOC entry 218 (class 1259 OID 16484)
-- Name: appuser; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.appuser (
                                id integer NOT NULL,
                                mail character varying(50) NOT NULL,
                                password character varying(50) NOT NULL,
                                usertype character varying(50),
                                CONSTRAINT appuser_password_check CHECK ((length((password)::text) >= 4)),
                                CONSTRAINT appuser_usertype_check CHECK (((usertype)::text = ANY ((ARRAY['company'::character varying, 'candidate'::character varying])::text[]))),
    CONSTRAINT mailformat CHECK (((mail)::text ~* '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$'::text))
);


ALTER TABLE public.appuser OWNER TO pguser;

--
-- TOC entry 217 (class 1259 OID 16483)
-- Name: appuser_id_seq; Type: SEQUENCE; Schema: public; Owner: pguser
--

CREATE SEQUENCE public.appuser_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.appuser_id_seq OWNER TO pguser;

--
-- TOC entry 3497 (class 0 OID 0)
-- Dependencies: 217
-- Name: appuser_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pguser
--

ALTER SEQUENCE public.appuser_id_seq OWNED BY public.appuser.id;


--
-- TOC entry 222 (class 1259 OID 16724)
-- Name: candidate; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.candidate (
                                  mail character varying(255) NOT NULL,
                                  lastname character varying(255) NOT NULL,
                                  firstname character varying(255) NOT NULL
);


ALTER TABLE public.candidate OWNER TO pguser;

--
-- TOC entry 223 (class 1259 OID 16736)
-- Name: company; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.company (
                                mail character varying(255) NOT NULL,
                                denomination character varying(255) NOT NULL,
                                description character varying(255)
);


ALTER TABLE public.company OWNER TO pguser;

--
-- TOC entry 227 (class 1259 OID 16756)
-- Name: field; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.field (
                              id integer NOT NULL,
                              label character varying(255) NOT NULL
);


ALTER TABLE public.field OWNER TO pguser;

--
-- TOC entry 226 (class 1259 OID 16755)
-- Name: field_id_seq; Type: SEQUENCE; Schema: public; Owner: pguser
--

CREATE SEQUENCE public.field_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.field_id_seq OWNER TO pguser;

--
-- TOC entry 3498 (class 0 OID 0)
-- Dependencies: 226
-- Name: field_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pguser
--

ALTER SEQUENCE public.field_id_seq OWNED BY public.field.id;


--
-- TOC entry 231 (class 1259 OID 16782)
-- Name: joboffer; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.joboffer (
                                 id integer NOT NULL,
                                 title character varying(255) NOT NULL,
                                 taskdescription character varying(255) NOT NULL,
                                 publicationdate date NOT NULL,
                                 publisher character varying(255),
                                 qualification integer
);


ALTER TABLE public.joboffer OWNER TO pguser;

--
-- TOC entry 230 (class 1259 OID 16781)
-- Name: joboffer_id_seq; Type: SEQUENCE; Schema: public; Owner: pguser
--

CREATE SEQUENCE public.joboffer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.joboffer_id_seq OWNER TO pguser;

--
-- TOC entry 3499 (class 0 OID 0)
-- Dependencies: 230
-- Name: joboffer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pguser
--

ALTER SEQUENCE public.joboffer_id_seq OWNED BY public.joboffer.id;


--
-- TOC entry 235 (class 1259 OID 16831)
-- Name: message; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.message (
                                id integer NOT NULL,
                                message character varying(255),
                                publicationdate date
);


ALTER TABLE public.message OWNER TO pguser;

--
-- TOC entry 234 (class 1259 OID 16830)
-- Name: message_id_seq; Type: SEQUENCE; Schema: public; Owner: pguser
--

CREATE SEQUENCE public.message_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.message_id_seq OWNER TO pguser;

--
-- TOC entry 3500 (class 0 OID 0)
-- Dependencies: 234
-- Name: message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pguser
--

ALTER SEQUENCE public.message_id_seq OWNED BY public.message.id;


--
-- TOC entry 233 (class 1259 OID 16815)
-- Name: offerfields; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.offerfields (
                                    offerid integer NOT NULL,
                                    idfield integer NOT NULL
);


ALTER TABLE public.offerfields OWNER TO pguser;

--
-- TOC entry 237 (class 1259 OID 16838)
-- Name: offermessage; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.offermessage (
                                     idmess integer NOT NULL,
                                     idoffer integer
);


ALTER TABLE public.offermessage OWNER TO pguser;

--
-- TOC entry 236 (class 1259 OID 16837)
-- Name: offermessage_idmess_seq; Type: SEQUENCE; Schema: public; Owner: pguser
--

CREATE SEQUENCE public.offermessage_idmess_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.offermessage_idmess_seq OWNER TO pguser;

--
-- TOC entry 3501 (class 0 OID 0)
-- Dependencies: 236
-- Name: offermessage_idmess_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pguser
--

ALTER SEQUENCE public.offermessage_idmess_seq OWNED BY public.offermessage.idmess;


--
-- TOC entry 241 (class 1259 OID 16886)
-- Name: offermessdest; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.offermessdest (
                                      idmessapp integer NOT NULL,
                                      iddestoffer integer NOT NULL
);


ALTER TABLE public.offermessdest OWNER TO pguser;

--
-- TOC entry 225 (class 1259 OID 16749)
-- Name: qualificationlevel; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.qualificationlevel (
                                           id integer NOT NULL,
                                           label character varying(255) NOT NULL
);


ALTER TABLE public.qualificationlevel OWNER TO pguser;

--
-- TOC entry 224 (class 1259 OID 16748)
-- Name: qualificationlevel_id_seq; Type: SEQUENCE; Schema: public; Owner: pguser
--

CREATE SEQUENCE public.qualificationlevel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.qualificationlevel_id_seq OWNER TO pguser;

--
-- TOC entry 3502 (class 0 OID 0)
-- Dependencies: 224
-- Name: qualificationlevel_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pguser
--

ALTER SEQUENCE public.qualificationlevel_id_seq OWNED BY public.qualificationlevel.id;


--
-- TOC entry 221 (class 1259 OID 16716)
-- Name: userapp; Type: TABLE; Schema: public; Owner: pguser
--

CREATE TABLE public.userapp (
                                mail character varying(255) NOT NULL,
                                password character varying(255) NOT NULL,
                                city character varying(255),
                                CONSTRAINT userapp_password_check CHECK ((length((password)::text) >= 4))
);


ALTER TABLE public.userapp OWNER TO pguser;

--
-- TOC entry 3263 (class 2604 OID 16918)
-- Name: application id; Type: DEFAULT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.application ALTER COLUMN id SET DEFAULT nextval('public.application_id_seq'::regclass);


--
-- TOC entry 3267 (class 2604 OID 16919)
-- Name: applicationmessage idmess; Type: DEFAULT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.applicationmessage ALTER COLUMN idmess SET DEFAULT nextval('public.applicationmessage_idmess_seq'::regclass);


--
-- TOC entry 3260 (class 2604 OID 16920)
-- Name: appuser id; Type: DEFAULT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.appuser ALTER COLUMN id SET DEFAULT nextval('public.appuser_id_seq'::regclass);


--
-- TOC entry 3262 (class 2604 OID 16921)
-- Name: field id; Type: DEFAULT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.field ALTER COLUMN id SET DEFAULT nextval('public.field_id_seq'::regclass);


--
-- TOC entry 3264 (class 2604 OID 16922)
-- Name: joboffer id; Type: DEFAULT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.joboffer ALTER COLUMN id SET DEFAULT nextval('public.joboffer_id_seq'::regclass);


--
-- TOC entry 3265 (class 2604 OID 16923)
-- Name: message id; Type: DEFAULT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.message ALTER COLUMN id SET DEFAULT nextval('public.message_id_seq'::regclass);


--
-- TOC entry 3266 (class 2604 OID 16924)
-- Name: offermessage idmess; Type: DEFAULT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.offermessage ALTER COLUMN idmess SET DEFAULT nextval('public.offermessage_idmess_seq'::regclass);


--
-- TOC entry 3261 (class 2604 OID 16925)
-- Name: qualificationlevel id; Type: DEFAULT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.qualificationlevel ALTER COLUMN id SET DEFAULT nextval('public.qualificationlevel_id_seq'::regclass);


--
-- TOC entry 3477 (class 0 OID 16763)
-- Dependencies: 229
-- Data for Name: application; Type: TABLE DATA; Schema: public; Owner: pguser
--

INSERT INTO public.application VALUES (1, 'cv.pdf', '2025-03-19', 'candidate1@test.fr', 3) ON CONFLICT DO NOTHING;
INSERT INTO public.application VALUES (2, 'cv.pdf', '2025-03-19', 'candidate2@test.fr', 5) ON CONFLICT DO NOTHING;


--
-- TOC entry 3480 (class 0 OID 16800)
-- Dependencies: 232
-- Data for Name: applicationfields; Type: TABLE DATA; Schema: public; Owner: pguser
--

INSERT INTO public.applicationfields VALUES (1, 3) ON CONFLICT DO NOTHING;
INSERT INTO public.applicationfields VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO public.applicationfields VALUES (2, 25) ON CONFLICT DO NOTHING;
INSERT INTO public.applicationfields VALUES (2, 1) ON CONFLICT DO NOTHING;
INSERT INTO public.applicationfields VALUES (2, 15) ON CONFLICT DO NOTHING;


--
-- TOC entry 3487 (class 0 OID 16855)
-- Dependencies: 239
-- Data for Name: applicationmessage; Type: TABLE DATA; Schema: public; Owner: pguser
--



--
-- TOC entry 3488 (class 0 OID 16871)
-- Dependencies: 240
-- Data for Name: appmessdest; Type: TABLE DATA; Schema: public; Owner: pguser
--



--
-- TOC entry 3468 (class 0 OID 16484)
-- Dependencies: 218
-- Data for Name: appuser; Type: TABLE DATA; Schema: public; Owner: pguser
--

INSERT INTO public.appuser VALUES (1, 'g.a@imt.fr', '1234', 'candidate') ON CONFLICT DO NOTHING;
INSERT INTO public.appuser VALUES (2, 'ceo@google.fr', 'abcd', 'company') ON CONFLICT DO NOTHING;


--
-- TOC entry 3470 (class 0 OID 16724)
-- Dependencies: 222
-- Data for Name: candidate; Type: TABLE DATA; Schema: public; Owner: pguser
--

INSERT INTO public.candidate VALUES ('candidate1@test.fr', 'Candidate lastname', 'Candidate firstname') ON CONFLICT DO NOTHING;
INSERT INTO public.candidate VALUES ('candidate2@test.fr', 'Candidate2 lastname', 'Candidate2 firstname') ON CONFLICT DO NOTHING;


--
-- TOC entry 3471 (class 0 OID 16736)
-- Dependencies: 223
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: pguser
--

INSERT INTO public.company VALUES ('company1@test.fr', 'Company 1', 'Company 1 desc') ON CONFLICT DO NOTHING;
INSERT INTO public.company VALUES ('company2@test.fr', 'Company 2', 'Company 2 Desc') ON CONFLICT DO NOTHING;


--
-- TOC entry 3475 (class 0 OID 16756)
-- Dependencies: 227
-- Data for Name: field; Type: TABLE DATA; Schema: public; Owner: pguser
--

INSERT INTO public.field VALUES (1, 'Purchase/Logistic') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (2, 'Administration') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (3, 'Agriculture') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (4, 'Agrofood') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (5, 'Insurance') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (6, 'Audit/Advise/Expertise') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (7, 'Public works/Real estate') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (8, 'Trade') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (9, 'Communication/Art/Media/Fashion') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (10, 'Accounting') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (11, 'Direction/Execution') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (12, 'Distribution/Sale') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (13, 'Electronic/Microelectronic') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (14, 'Environment') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (15, 'Finance/Bank') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (16, 'Training/Teaching') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (17, 'Hotel/Restaurant/Tourism') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (18, 'Industry/Engineering/Production') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (19, 'Computer science') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (20, 'Juridique/Fiscal/Droit') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (21, 'Marketing') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (22, 'Public/Parapublic') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (23, 'Human resources') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (24, 'Health/Social/Biology/HHumanitarian') ON CONFLICT DO NOTHING;
INSERT INTO public.field VALUES (25, 'Telecom/Networking') ON CONFLICT DO NOTHING;


--
-- TOC entry 3479 (class 0 OID 16782)
-- Dependencies: 231
-- Data for Name: joboffer; Type: TABLE DATA; Schema: public; Owner: pguser
--

INSERT INTO public.joboffer VALUES (1, 'Company 1 JobOffer 1', 'qkzjbdqzk bdqzkbd qzkbd qdqkbdzqkbd qizdb qzk', '2025-03-19', 'company1@test.fr', 3) ON CONFLICT DO NOTHING;
INSERT INTO public.joboffer VALUES (2, 'Company 1 JobOffer 2', 'qebdiqz ibqzd nnqidn qdnzqd qzdn ', '2025-03-19', 'company1@test.fr', 5) ON CONFLICT DO NOTHING;
INSERT INTO public.joboffer VALUES (3, 'Company2 JobOffer1', 'qkbdq zb iqdzkdqknn hqndonq do ', '2025-03-19', 'company2@test.fr', 4) ON CONFLICT DO NOTHING;
INSERT INTO public.joboffer VALUES (4, 'Company 2 Joboffer2', 'qibdz qjkzbd kzqndq dzlkdnqz ldnqz l dqz d', '2025-03-19', 'company2@test.fr', 3) ON CONFLICT DO NOTHING;


--
-- TOC entry 3483 (class 0 OID 16831)
-- Dependencies: 235
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: pguser
--



--
-- TOC entry 3481 (class 0 OID 16815)
-- Dependencies: 233
-- Data for Name: offerfields; Type: TABLE DATA; Schema: public; Owner: pguser
--

INSERT INTO public.offerfields VALUES (1, 5) ON CONFLICT DO NOTHING;
INSERT INTO public.offerfields VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO public.offerfields VALUES (1, 8) ON CONFLICT DO NOTHING;
INSERT INTO public.offerfields VALUES (2, 15) ON CONFLICT DO NOTHING;
INSERT INTO public.offerfields VALUES (3, 5) ON CONFLICT DO NOTHING;
INSERT INTO public.offerfields VALUES (3, 25) ON CONFLICT DO NOTHING;
INSERT INTO public.offerfields VALUES (3, 1) ON CONFLICT DO NOTHING;
INSERT INTO public.offerfields VALUES (4, 3) ON CONFLICT DO NOTHING;
INSERT INTO public.offerfields VALUES (4, 15) ON CONFLICT DO NOTHING;


--
-- TOC entry 3485 (class 0 OID 16838)
-- Dependencies: 237
-- Data for Name: offermessage; Type: TABLE DATA; Schema: public; Owner: pguser
--



--
-- TOC entry 3489 (class 0 OID 16886)
-- Dependencies: 241
-- Data for Name: offermessdest; Type: TABLE DATA; Schema: public; Owner: pguser
--



--
-- TOC entry 3473 (class 0 OID 16749)
-- Dependencies: 225
-- Data for Name: qualificationlevel; Type: TABLE DATA; Schema: public; Owner: pguser
--

INSERT INTO public.qualificationlevel VALUES (1, 'Professional level') ON CONFLICT DO NOTHING;
INSERT INTO public.qualificationlevel VALUES (2, 'A-diploma') ON CONFLICT DO NOTHING;
INSERT INTO public.qualificationlevel VALUES (3, 'Licence') ON CONFLICT DO NOTHING;
INSERT INTO public.qualificationlevel VALUES (4, 'Master') ON CONFLICT DO NOTHING;
INSERT INTO public.qualificationlevel VALUES (5, 'PhD') ON CONFLICT DO NOTHING;


--
-- TOC entry 3469 (class 0 OID 16716)
-- Dependencies: 221
-- Data for Name: userapp; Type: TABLE DATA; Schema: public; Owner: pguser
--

INSERT INTO public.userapp VALUES ('company1@test.fr', 'test', 'Brest') ON CONFLICT DO NOTHING;
INSERT INTO public.userapp VALUES ('company2@test.fr', 'test', 'Rennes') ON CONFLICT DO NOTHING;
INSERT INTO public.userapp VALUES ('candidate1@test.fr', 'test', 'Brest') ON CONFLICT DO NOTHING;
INSERT INTO public.userapp VALUES ('candidate2@test.fr', 'test', 'Rennes') ON CONFLICT DO NOTHING;


--
-- TOC entry 3503 (class 0 OID 0)
-- Dependencies: 228
-- Name: application_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pguser
--

SELECT pg_catalog.setval('public.application_id_seq', 2, true);


--
-- TOC entry 3504 (class 0 OID 0)
-- Dependencies: 238
-- Name: applicationmessage_idmess_seq; Type: SEQUENCE SET; Schema: public; Owner: pguser
--

SELECT pg_catalog.setval('public.applicationmessage_idmess_seq', 1, false);


--
-- TOC entry 3505 (class 0 OID 0)
-- Dependencies: 217
-- Name: appuser_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pguser
--

SELECT pg_catalog.setval('public.appuser_id_seq', 2, true);


--
-- TOC entry 3506 (class 0 OID 0)
-- Dependencies: 226
-- Name: field_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pguser
--

SELECT pg_catalog.setval('public.field_id_seq', 25, true);


--
-- TOC entry 3507 (class 0 OID 0)
-- Dependencies: 230
-- Name: joboffer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pguser
--

SELECT pg_catalog.setval('public.joboffer_id_seq', 4, true);


--
-- TOC entry 3508 (class 0 OID 0)
-- Dependencies: 234
-- Name: message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pguser
--

SELECT pg_catalog.setval('public.message_id_seq', 1, false);


--
-- TOC entry 3509 (class 0 OID 0)
-- Dependencies: 236
-- Name: offermessage_idmess_seq; Type: SEQUENCE SET; Schema: public; Owner: pguser
--

SELECT pg_catalog.setval('public.offermessage_idmess_seq', 1, false);


--
-- TOC entry 3510 (class 0 OID 0)
-- Dependencies: 224
-- Name: qualificationlevel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pguser
--

SELECT pg_catalog.setval('public.qualificationlevel_id_seq', 5, true);


--
-- TOC entry 3287 (class 2606 OID 16770)
-- Name: application application_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.application
    ADD CONSTRAINT application_pkey PRIMARY KEY (id);


--
-- TOC entry 3291 (class 2606 OID 16804)
-- Name: applicationfields applicationfields_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.applicationfields
    ADD CONSTRAINT applicationfields_pkey PRIMARY KEY (appid, idfield);


--
-- TOC entry 3299 (class 2606 OID 16860)
-- Name: applicationmessage applicationmessage_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.applicationmessage
    ADD CONSTRAINT applicationmessage_pkey PRIMARY KEY (idmess);


--
-- TOC entry 3301 (class 2606 OID 16875)
-- Name: appmessdest appmessdest_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.appmessdest
    ADD CONSTRAINT appmessdest_pkey PRIMARY KEY (idmessoffer, iddestapp);


--
-- TOC entry 3273 (class 2606 OID 16493)
-- Name: appuser appuser_mail_key; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.appuser
    ADD CONSTRAINT appuser_mail_key UNIQUE (mail);


--
-- TOC entry 3275 (class 2606 OID 16491)
-- Name: appuser appuser_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.appuser
    ADD CONSTRAINT appuser_pkey PRIMARY KEY (id);


--
-- TOC entry 3279 (class 2606 OID 16730)
-- Name: candidate candidate_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.candidate
    ADD CONSTRAINT candidate_pkey PRIMARY KEY (mail);


--
-- TOC entry 3281 (class 2606 OID 16742)
-- Name: company company_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (mail);


--
-- TOC entry 3285 (class 2606 OID 16761)
-- Name: field field_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.field
    ADD CONSTRAINT field_pkey PRIMARY KEY (id);


--
-- TOC entry 3289 (class 2606 OID 16789)
-- Name: joboffer joboffer_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.joboffer
    ADD CONSTRAINT joboffer_pkey PRIMARY KEY (id);


--
-- TOC entry 3295 (class 2606 OID 16836)
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- TOC entry 3293 (class 2606 OID 16819)
-- Name: offerfields offerfields_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.offerfields
    ADD CONSTRAINT offerfields_pkey PRIMARY KEY (offerid, idfield);


--
-- TOC entry 3297 (class 2606 OID 16843)
-- Name: offermessage offermessage_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.offermessage
    ADD CONSTRAINT offermessage_pkey PRIMARY KEY (idmess);


--
-- TOC entry 3303 (class 2606 OID 16890)
-- Name: offermessdest offermessdest_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.offermessdest
    ADD CONSTRAINT offermessdest_pkey PRIMARY KEY (idmessapp, iddestoffer);


--
-- TOC entry 3283 (class 2606 OID 16754)
-- Name: qualificationlevel qualificationlevel_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.qualificationlevel
    ADD CONSTRAINT qualificationlevel_pkey PRIMARY KEY (id);


--
-- TOC entry 3277 (class 2606 OID 16723)
-- Name: userapp userapp_pkey; Type: CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.userapp
    ADD CONSTRAINT userapp_pkey PRIMARY KEY (mail);


--
-- TOC entry 3306 (class 2606 OID 16771)
-- Name: application application_publisher_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.application
    ADD CONSTRAINT application_publisher_fkey FOREIGN KEY (publisher) REFERENCES public.candidate(mail);


--
-- TOC entry 3307 (class 2606 OID 16776)
-- Name: application application_qualification_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.application
    ADD CONSTRAINT application_qualification_fkey FOREIGN KEY (qualification) REFERENCES public.qualificationlevel(id);


--
-- TOC entry 3310 (class 2606 OID 16805)
-- Name: applicationfields applicationfields_appid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.applicationfields
    ADD CONSTRAINT applicationfields_appid_fkey FOREIGN KEY (appid) REFERENCES public.application(id);


--
-- TOC entry 3311 (class 2606 OID 16810)
-- Name: applicationfields applicationfields_idfield_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.applicationfields
    ADD CONSTRAINT applicationfields_idfield_fkey FOREIGN KEY (idfield) REFERENCES public.field(id);


--
-- TOC entry 3316 (class 2606 OID 16866)
-- Name: applicationmessage applicationmessage_idapp_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.applicationmessage
    ADD CONSTRAINT applicationmessage_idapp_fkey FOREIGN KEY (idapp) REFERENCES public.application(id);


--
-- TOC entry 3317 (class 2606 OID 16861)
-- Name: applicationmessage applicationmessage_idmess_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.applicationmessage
    ADD CONSTRAINT applicationmessage_idmess_fkey FOREIGN KEY (idmess) REFERENCES public.message(id);


--
-- TOC entry 3318 (class 2606 OID 16881)
-- Name: appmessdest appmessdest_iddestapp_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.appmessdest
    ADD CONSTRAINT appmessdest_iddestapp_fkey FOREIGN KEY (iddestapp) REFERENCES public.application(id);


--
-- TOC entry 3319 (class 2606 OID 16876)
-- Name: appmessdest appmessdest_idmessoffer_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.appmessdest
    ADD CONSTRAINT appmessdest_idmessoffer_fkey FOREIGN KEY (idmessoffer) REFERENCES public.offermessage(idmess);


--
-- TOC entry 3304 (class 2606 OID 16731)
-- Name: candidate candidate_mail_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.candidate
    ADD CONSTRAINT candidate_mail_fkey FOREIGN KEY (mail) REFERENCES public.userapp(mail);


--
-- TOC entry 3305 (class 2606 OID 16743)
-- Name: company company_mail_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_mail_fkey FOREIGN KEY (mail) REFERENCES public.userapp(mail);


--
-- TOC entry 3308 (class 2606 OID 16790)
-- Name: joboffer joboffer_publisher_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.joboffer
    ADD CONSTRAINT joboffer_publisher_fkey FOREIGN KEY (publisher) REFERENCES public.company(mail);


--
-- TOC entry 3309 (class 2606 OID 16795)
-- Name: joboffer joboffer_qualification_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.joboffer
    ADD CONSTRAINT joboffer_qualification_fkey FOREIGN KEY (qualification) REFERENCES public.qualificationlevel(id);


--
-- TOC entry 3312 (class 2606 OID 16825)
-- Name: offerfields offerfields_idfield_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.offerfields
    ADD CONSTRAINT offerfields_idfield_fkey FOREIGN KEY (idfield) REFERENCES public.field(id);


--
-- TOC entry 3313 (class 2606 OID 16820)
-- Name: offerfields offerfields_offerid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.offerfields
    ADD CONSTRAINT offerfields_offerid_fkey FOREIGN KEY (offerid) REFERENCES public.joboffer(id);


--
-- TOC entry 3314 (class 2606 OID 16844)
-- Name: offermessage offermessage_idmess_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.offermessage
    ADD CONSTRAINT offermessage_idmess_fkey FOREIGN KEY (idmess) REFERENCES public.message(id);


--
-- TOC entry 3315 (class 2606 OID 16849)
-- Name: offermessage offermessage_idoffer_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.offermessage
    ADD CONSTRAINT offermessage_idoffer_fkey FOREIGN KEY (idoffer) REFERENCES public.joboffer(id);


--
-- TOC entry 3320 (class 2606 OID 16896)
-- Name: offermessdest offermessdest_iddestoffer_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.offermessdest
    ADD CONSTRAINT offermessdest_iddestoffer_fkey FOREIGN KEY (iddestoffer) REFERENCES public.joboffer(id);


--
-- TOC entry 3321 (class 2606 OID 16891)
-- Name: offermessdest offermessdest_idmessapp_fkey; Type: FK CONSTRAINT; Schema: public; Owner: pguser
--

ALTER TABLE ONLY public.offermessdest
    ADD CONSTRAINT offermessdest_idmessapp_fkey FOREIGN KEY (idmessapp) REFERENCES public.applicationmessage(idmess);


-- Completed on 2025-03-19 12:34:06 UTC

--
-- PostgreSQL database dump complete
--


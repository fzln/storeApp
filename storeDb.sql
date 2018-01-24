--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.6
-- Dumped by pg_dump version 9.6.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: description; Type: TABLE; Schema: public; Owner: store
--

CREATE TABLE description (
    serialnumber character(6) NOT NULL,
    description character varying(100),
    name character varying(50) NOT NULL,
    productiondate date
);


ALTER TABLE description OWNER TO store;

--
-- Name: item; Type: TABLE; Schema: public; Owner: store
--

CREATE TABLE item (
    idorder integer NOT NULL,
    lineid integer NOT NULL,
    quantity integer,
    serialnumber character(6)
);


ALTER TABLE item OWNER TO store;

--
-- Name: orderitems; Type: TABLE; Schema: public; Owner: store
--

CREATE TABLE orderitems (
    idorder integer NOT NULL,
    createddate date NOT NULL,
    customeraddress character varying(100),
    customername character varying(50),
    totalamount integer
);


ALTER TABLE orderitems OWNER TO store;

--
-- Data for Name: description; Type: TABLE DATA; Schema: public; Owner: store
--

COPY description (serialnumber, description, name, productiondate) FROM stdin;
000001	descr1	name1	2018-01-17
000004	descr4	name4	2017-07-07
000005	descr5	name5	2018-05-09
000000	description	name	2018-01-01
000002	description2	name2	2018-02-02
000010	description	name	2018-01-01
000012	description2	name2	2018-02-02
000013	description3	name3	2018-03-03
000201	Очень тёплая обувь	Валенки	2018-01-01
000202	Современная посуда	Чайник	2018-02-02
000003	Защищают от УФ	Солнцезащитные очки	2018-03-03
000300	46 дюймов	Телевизор	0018-01-01
000301	Лада-Веста	Автомобиль	2018-02-02
000303	Письменный	Стол	2018-01-01
\.


--
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: store
--

COPY item (idorder, lineid, quantity, serialnumber) FROM stdin;
700	3	7	\N
11	1	4	\N
11	6	7	\N
11	8	9	\N
700	7	15	\N
700	4	6	\N
102	3	3	000005
102	4	3	000005
500	1	3	\N
102	7	8	000004
102	10	2	000002
102	9	3	000003
102	12	5	\N
700	5	4	000000
700	1	5	000002
700	2	6	000005
500	2	2	000005
500	3	3	\N
900	1	2	000201
900	2	3	000202
101	1	2	000301
101	2	1	000300
101	3	1	000303
\.


--
-- Data for Name: orderitems; Type: TABLE DATA; Schema: public; Owner: store
--

COPY orderitems (idorder, createddate, customeraddress, customername, totalamount) FROM stdin;
102	2018-02-02	Владивосток	Иванов	75
10	2018-01-21	Красноярск	Сидоров	200
500	2018-01-21	сосед	Санёк	15
700	2018-01-21	с 5 дома	Андрюха	25
101	2018-05-02	Камчатка	Соколов	300
11	2018-01-21	Красноярск	Сидоров А.В.	200
900	2018-01-23	В центре города	BMW	30
\.


--
-- Name: description description_pkey; Type: CONSTRAINT; Schema: public; Owner: store
--

ALTER TABLE ONLY description
    ADD CONSTRAINT description_pkey PRIMARY KEY (serialnumber);


--
-- Name: item item_pkey; Type: CONSTRAINT; Schema: public; Owner: store
--

ALTER TABLE ONLY item
    ADD CONSTRAINT item_pkey PRIMARY KEY (idorder, lineid);


--
-- Name: orderitems orderitems_pkey; Type: CONSTRAINT; Schema: public; Owner: store
--

ALTER TABLE ONLY orderitems
    ADD CONSTRAINT orderitems_pkey PRIMARY KEY (idorder);


--
-- Name: item fk2kp535rcbo830h357mx2pmn7f; Type: FK CONSTRAINT; Schema: public; Owner: store
--

ALTER TABLE ONLY item
    ADD CONSTRAINT fk2kp535rcbo830h357mx2pmn7f FOREIGN KEY (serialnumber) REFERENCES description(serialnumber);


--
-- Name: item fkp88fw6dxbnwog7g3a9e7to13i; Type: FK CONSTRAINT; Schema: public; Owner: store
--

ALTER TABLE ONLY item
    ADD CONSTRAINT fkp88fw6dxbnwog7g3a9e7to13i FOREIGN KEY (idorder) REFERENCES orderitems(idorder);


--
-- PostgreSQL database dump complete
--


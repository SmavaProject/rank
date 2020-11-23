
Deploy app to Heroku:
https://www.youtube.com/watch?v=CCX64hQHjdY


DDL for tables:

-- public.company definition

-- Drop table

-- DROP TABLE public.company;

CREATE TABLE public.company (
	company_id serial NOT NULL DEFAULT nextval('company_company_id_seq'::regclass),
	company_name varchar NULL,
	market_cap varchar NULL,
	ticker varchar NOT NULL DEFAULT 'NaN'::character varying,
	CONSTRAINT company_pk PRIMARY KEY (company_id),
	CONSTRAINT company_un UNIQUE (ticker)
);



-- public."rank" definition

-- Drop table

-- DROP TABLE public."rank";

CREATE TABLE public."rank" (
	id serial NOT NULL DEFAULT nextval('rank_id_seq'::regclass),
	company_id int4 NOT NULL,
	"date" date NOT NULL,
	"rank" int4 NULL,
	zacks_rank_text varchar NULL,
	dividend_yield float4 NULL,
	volatility float4 NULL,
	pe_ratio float4 NULL,
	ask float4 NULL,
	CONSTRAINT rank_pk PRIMARY KEY (id)
);


-- public."rank" foreign keys

ALTER TABLE public."rank" ADD CONSTRAINT rank_fk FOREIGN KEY (company_id) REFERENCES company(company_id);

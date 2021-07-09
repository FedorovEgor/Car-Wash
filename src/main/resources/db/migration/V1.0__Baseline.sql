CREATE TABLE public.users
(
    user_id bigint NOT NULL,
    password character varying(255) COLLATE pg_catalog."default",
    user_name character varying(255) COLLATE pg_catalog."default",
    role_role_id bigint,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT fkruo12mi6hchjfi06jhln9tdkt FOREIGN KEY (role_role_id)
        REFERENCES public.roles (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.roles
(
    role_id bigint NOT NULL,
    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (role_id)
);

CREATE TABLE public.order_types
(
	type_id bigint NOT NULL,
	description character varying(255) COLLATE pg_catalog."default" NOT NULL,
   	duration_mins bigint NOT NULL,
   	price_dollars bigint NOT NULL,
    	CONSTRAINT order_types_pkey PRIMARY KEY (type_id)
);

CREATE TABLE public.orders
(
    order_id bigint NOT NULL,
    date date,
    order_type_type_id bigint,
    user_user_id bigint,
    CONSTRAINT orders_pkey PRIMARY KEY (order_id),
    CONSTRAINT fk38709695otpk064vi3y92u08s FOREIGN KEY (user_user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkir86lse3wjayjf16934qne134 FOREIGN KEY (order_type_type_id)
        REFERENCES public.order_types (type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
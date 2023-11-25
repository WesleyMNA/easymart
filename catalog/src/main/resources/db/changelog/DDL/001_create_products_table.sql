DROP TABLE IF EXISTS "product";
DROP SEQUENCE IF EXISTS product_id_seq;

CREATE SEQUENCE product_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE TABLE "public"."product" (
    "id" bigint DEFAULT nextval('product_id_seq') NOT NULL,
    "price" real NOT NULL,
    "title" character varying(255) NOT NULL,
    CONSTRAINT "product_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_qka6vxqdy1dprtqnx9trdd47c" UNIQUE ("title")
) WITH (oids = false);

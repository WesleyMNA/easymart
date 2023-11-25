DROP TABLE IF EXISTS "em_product";
DROP SEQUENCE IF EXISTS em_product_id_seq;

CREATE SEQUENCE em_product_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;
CREATE TABLE "public"."em_product" (
    "id" bigint DEFAULT nextval('em_product_id_seq') NOT NULL,
    "price" real NOT NULL,
    "title" character varying(255) NOT NULL,
    CONSTRAINT "em_product_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_qka6vxqdy1dprtqnx9trdd47c" UNIQUE ("title")
) WITH (oids = false);

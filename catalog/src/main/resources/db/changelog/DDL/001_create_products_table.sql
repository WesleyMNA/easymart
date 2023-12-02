DROP TABLE IF EXISTS "em_product";
CREATE TABLE "public"."em_product" (
    "id" uuid NOT NULL,
    "price" real NOT NULL,
    "title" character varying(255) NOT NULL,
    CONSTRAINT "em_product_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_fqjm0aw2lbcwk2xf76nrtmhd1" UNIQUE ("title")
) WITH (oids = false);
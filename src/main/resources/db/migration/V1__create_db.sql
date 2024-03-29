CREATE SEQUENCE IF NOT EXISTS palabras_id_seq;
CREATE TABLE  IF NOT EXISTS "public"."palabras"
(
    "id" int8 NOT NULL DEFAULT nextval('palabras_id_seq'::regclass),
    "palabra" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    CONSTRAINT "palabras_pkey" PRIMARY KEY ("id"));
CREATE UNIQUE INDEX IF NOT EXISTS "ak_palabra" ON "public"."palabras" USING btree (
                                                                                   "palabra" COLLATE "pg_catalog"."default"
                                                                                   "pg_catalog"."text_ops" ASC NULLS LAST);

CREATE TABLE IF NOT EXISTS "public"."tablero" (
                                                  "id" uuid NOT NULL,
                                                  "tablero" text COLLATE "pg_catalog"."default" NOT NULL,
                                                  CONSTRAINT "tablero_pkey" PRIMARY KEY ("id"));

CREATE TABLE IF NOT EXISTS "public"."tablero_palabras" (
                                                           "id" uuid NOT NULL,
                                                           "id_tablero" uuid NOT NULL,
                                                           "id_palabras" int4 NOT NULL,
                                                           "resuelto" bool NOT NULL,
                                                           "sr" int4,
                                                           "sc" int4,
                                                           "er" int4,
                                                           "ec" int4,
                                                           "direccion" text COLLATE "pg_catalog"."default");

--INSERTAR METADATOS PARA LA TABLA PALABRAS
INSERT INTO palabras (id, palabra) VALUES (2, 'uva') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (4, 'mango') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (5, 'patata') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (6, 'coco') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (7, 'manzana') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (8, 'pera') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (11, 'naranja') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (13, 'acelga') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (14, 'apio') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (15, 'brócoli') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (3, 'plátano') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (9, 'limón') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (10, 'sandía') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (12, 'aceitunas') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (16, 'coliflor') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (17, 'cebolla') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (18, 'remolacha') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (19, 'espinaca') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (20, 'nabo') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (21, 'zanahoria') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (22, 'zapallo') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (23, 'calabaza') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (24, 'banana') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (25, 'ciruela') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (26, 'durazno') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (27, 'mandarina') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (28, 'melón') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (30, 'pomelo') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (31, 'frambuesa') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (32, 'cereza') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (33, 'tomate') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (34, 'mora') ON CONFLICT DO NOTHING;
INSERT INTO palabras (id, palabra) VALUES (35, 'franbuezas') ON CONFLICT DO NOTHING;
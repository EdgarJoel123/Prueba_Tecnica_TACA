/*
 Navicat Premium Dump SQL

 Source Server         : SixstarHotelsLocal
 Source Server Type    : PostgreSQL
 Source Server Version : 150007 (150007)
 Source Host           : localhost:5432
 Source Catalog        : postgres
 Source Schema         : prueba_tecnica

 Target Server Type    : PostgreSQL
 Target Server Version : 150007 (150007)
 File Encoding         : 65001

 Date: 05/08/2025 22:08:05
*/


-- ----------------------------
-- Create schema if not exists
-- ----------------------------
CREATE SCHEMA IF NOT EXISTS "prueba_tecnica";
SET search_path TO "prueba_tecnica";


-- ----------------------------
-- Sequence structure for scsegt_account_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "prueba_tecnica"."scsegt_account_seq";
CREATE SEQUENCE "prueba_tecnica"."scsegt_account_seq"
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for scsegt_customer_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "prueba_tecnica"."scsegt_customer_seq";
CREATE SEQUENCE "prueba_tecnica"."scsegt_customer_seq"
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for scsegt_movement_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "prueba_tecnica"."scsegt_movement_seq";
CREATE SEQUENCE "prueba_tecnica"."scsegt_movement_seq"
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for scsegt_persona_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "prueba_tecnica"."scsegt_persona_seq";
CREATE SEQUENCE "prueba_tecnica"."scsegt_persona_seq"
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for pt_account
-- ----------------------------
DROP TABLE IF EXISTS "prueba_tecnica"."pt_account";
CREATE TABLE "prueba_tecnica"."pt_account" (
  "id_account" int8 NOT NULL DEFAULT nextval('"prueba_tecnica".scsegt_account_seq'::regclass),
  "name_account" varchar(255) COLLATE "pg_catalog"."default",
  "type_account" varchar(255) COLLATE "pg_catalog"."default",
  "initial_balance" numeric(12,2),
  "status" bool,
  "custumer_id" int8
)
;

-- ----------------------------
-- Records of pt_account
-- ----------------------------

-- ----------------------------
-- Table structure for pt_customer
-- ----------------------------
DROP TABLE IF EXISTS "prueba_tecnica"."pt_customer";
CREATE TABLE "prueba_tecnica"."pt_customer" (
  "id_customer" int8 NOT NULL DEFAULT nextval('"prueba_tecnica".scsegt_customer_seq'::regclass),
  "person_id" int8,
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(1) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of pt_customer
-- ----------------------------
INSERT INTO "prueba_tecnica"."pt_customer" VALUES (13, 13, '7854512', '1');

-- ----------------------------
-- Table structure for pt_movement
-- ----------------------------
DROP TABLE IF EXISTS "prueba_tecnica"."pt_movement";
CREATE TABLE "prueba_tecnica"."pt_movement" (
  "id_movement" int8 NOT NULL DEFAULT nextval('"prueba_tecnica".scsegt_movement_seq'::regclass),
  "date" date,
  "type_movement" varchar(255) COLLATE "pg_catalog"."default",
  "value" numeric(12,2),
  "balance" numeric(12,2),
  "account_id" int8
)
;

-- ----------------------------
-- Records of pt_movement
-- ----------------------------

-- ----------------------------
-- Table structure for pt_person
-- ----------------------------
DROP TABLE IF EXISTS "prueba_tecnica"."pt_person";
CREATE TABLE "prueba_tecnica"."pt_person" (
  "id_person" int8 NOT NULL DEFAULT nextval('"prueba_tecnica".scsegt_persona_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "gender" varchar(10) COLLATE "pg_catalog"."default",
  "age" int8,
  "ci" varchar(11) COLLATE "pg_catalog"."default",
  "address" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(11) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of pt_person
-- ----------------------------
INSERT INTO "prueba_tecnica"."pt_person" VALUES (12, 'JOEL', 'FEMENINO', 20, '185555', 'ADSDSA', '098889');
INSERT INTO "prueba_tecnica"."pt_person" VALUES (13, 'pepe', 'masculino', 20, '1850201169', 'Ambato', '0989353272');

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"prueba_tecnica"."scsegt_account_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"prueba_tecnica"."scsegt_customer_seq"', 13, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"prueba_tecnica"."scsegt_movement_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"prueba_tecnica"."scsegt_persona_seq"', 13, true);

-- ----------------------------
-- Primary Key structure for table pt_account
-- ----------------------------
ALTER TABLE "prueba_tecnica"."pt_account" ADD CONSTRAINT "pt_account_pkey" PRIMARY KEY ("id_account");

-- ----------------------------
-- Primary Key structure for table pt_customer
-- ----------------------------
ALTER TABLE "prueba_tecnica"."pt_customer" ADD CONSTRAINT "pt_customer_pkey" PRIMARY KEY ("id_customer");

-- ----------------------------
-- Primary Key structure for table pt_movement
-- ----------------------------
ALTER TABLE "prueba_tecnica"."pt_movement" ADD CONSTRAINT "pt_movement_pkey" PRIMARY KEY ("id_movement");

-- ----------------------------
-- Primary Key structure for table pt_person
-- ----------------------------
ALTER TABLE "prueba_tecnica"."pt_person" ADD CONSTRAINT "pt_persona_pkey" PRIMARY KEY ("id_person");

-- ----------------------------
-- Foreign Keys structure for table pt_account
-- ----------------------------
ALTER TABLE "prueba_tecnica"."pt_account" ADD CONSTRAINT "fk_cliente" FOREIGN KEY ("custumer_id") REFERENCES "prueba_tecnica"."pt_customer" ("id_customer") ON DELETE RESTRICT ON UPDATE RESTRICT;

-- ----------------------------
-- Foreign Keys structure for table pt_customer
-- ----------------------------
ALTER TABLE "prueba_tecnica"."pt_customer" ADD CONSTRAINT "fk_person" FOREIGN KEY ("person_id") REFERENCES "prueba_tecnica"."pt_person" ("id_person") ON DELETE RESTRICT ON UPDATE RESTRICT;

-- ----------------------------
-- Foreign Keys structure for table pt_movement
-- ----------------------------
ALTER TABLE "prueba_tecnica"."pt_movement" ADD CONSTRAINT "fk_account" FOREIGN KEY ("account_id") REFERENCES "prueba_tecnica"."pt_account" ("id_account") ON DELETE RESTRICT ON UPDATE RESTRICT;

create table if not exists "xdisx-security".users (
                                                      "id" numeric(10) primary key,
    "username" varchar(50) not null,
    "password_hash" varchar(1000) not null
    );

CREATE SEQUENCE if not exists user_id_seq START 1;
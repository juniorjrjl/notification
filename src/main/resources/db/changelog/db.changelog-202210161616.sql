--liquibase formatted sql
--changeset junior:202209011411
--comment: notification table create

CREATE TABLE TB_NOTIFICATIONS(
                         id uuid not null primary key,
                         user_id uuid not null not null,
                         title varchar(150) not null,
                         message varchar(255) not null,
                         creation_date timestamp not null,
                         notification_status varchar(255) not null
);

--rollback DROP TABLE TB_NOTIFICATIONS;
create table messages
(
    message_id bigint generated by default as identity,
    message    varchar(255) not null,
    uuid       varchar(255) not null,
    name       varchar(255)
);
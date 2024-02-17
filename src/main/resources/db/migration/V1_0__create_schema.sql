create table cliente
(
    id     serial  not null
        constraint cliente_pk primary key,
    limite integer not null,
    saldo  integer not null
);

create table transacao
(
    id           serial  not null
        constraint transacao_pk primary key,
    cliente_id   integer not null,
    valor        integer not null,
    tipo         char    not null,
    descricao    varchar(10),
    realizada_em timestamp
);

alter table transacao
    add constraint transacao_cliente_fk
        foreign key (cliente_id) references cliente;

create index transacao_cliente_index
    on transacao (cliente_id);

insert into cliente
values (1, 100000, 0);
insert into cliente
values (2, 80000, 0);
insert into cliente
values (3, 1000000, 0);
insert into cliente
values (4, 10000000, 0);
insert into cliente
values (5, 500000, 0);
create sequence hibernate_sequence start 1 increment 1;
create table component
(
    id             bigserial not null,
    ean            varchar(255),
    bp_price       numeric(19, 2),
    bp_price_promo numeric(19, 2),
    category       varchar(255),
    name           varchar(255),
    pn             varchar(255),
    sub_category   varchar(255),
    primary key (id)
);
create table component_model
(
    id           int8 not null,
    comment      varchar(255),
    component_id int8 not null,
    model_id     int8 not null,
    primary key (id)
);
create table model
(
    id int8 not null,
    pn varchar(255),
    primary key (id)
);
create table notebook
(
    id             int8 not null,
    adapter        varchar(255),
    backlit        varchar(255),
    base           varchar(255),
    battery        varchar(255),
    bp_price       numeric(19, 2),
    bp_price_pln   numeric(19, 2),
    camera         varchar(255),
    card_reader    varchar(255),
    color          varchar(255),
    cup            varchar(255),
    ean_code       varchar(255),
    frp            varchar(255),
    graphics       varchar(255),
    hdd            varchar(255),
    keyboard       varchar(255),
    memory         varchar(255),
    odd            varchar(255),
    os             varchar(255),
    panel          varchar(255),
    pn             varchar(255),
    product_family varchar(255),
    product_series varchar(255),
    srp_price      numeric(19, 2),
    ssd            varchar(255),
    status         varchar(255),
    warranty       varchar(255),
    wlan           varchar(255),
    wwan           varchar(255),
    model_id       int8,
    primary key (id)
);
create table warranty
(
    id            int8 not null,
    base_warranty varchar(255),
    bp_price      numeric(19, 2),
    description   varchar(255),
    pn            varchar(255),
    model_id      int8,
    primary key (id)
);
alter table component_model
    add constraint FKdysxnhs07emu4vofulp7romrs foreign key (component_id) references component;
alter table component_model
    add constraint FKg075rx66cuns5uwjr6pmkvwuv foreign key (model_id) references model;
alter table notebook
    add constraint FKrcuayo9pxjm1l0fb9wyprtidj foreign key (model_id) references model;
alter table warranty
    add constraint FKg2awa07o753wb0k57x1ppame0 foreign key (model_id) references model;

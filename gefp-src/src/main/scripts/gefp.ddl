
    create table authorities (
        user_id int8 not null,
        authority varchar(255)
    );

    create table cells (
        id int8 not null,
        plan_id int8,
        runway_id int8,
        stage_id int8,
        primary key (id)
    );

    create table checkpoints (
        id int8 not null,
        deleted boolean not null,
        description varchar(255),
        index int4 not null,
        info varchar(255),
        cell_id int8,
        primary key (id)
    );

    create table departments (
        id int8 not null,
        name varchar(255),
        plan_id int8,
        primary key (id)
    );

    create table plans (
        id int8 not null,
        name varchar(255),
        published_date timestamp,
        department_id int8,
        primary key (id)
    );

    create table roles (
        id int8 not null,
        description varchar(255),
        role varchar(255),
        primary key (id)
    );

    create table runways (
        id int8 not null,
        index int4 not null,
        name varchar(255),
        plan_id int8,
        primary key (id)
    );

    create table stages (
        id int8 not null,
        index int4 not null,
        name varchar(255),
        plan_id int8,
        primary key (id)
    );

    create table user_checkpoints (
        user_id int8 not null,
        checkpoint_id int8 not null
    );

    create table users (
        id int8 not null,
        cin varchar(255),
        contact varchar(255),
        email varchar(255),
        enabled boolean not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        username varchar(255) not null,
        department_id int8,
        plan_id int8,
        primary key (id)
    );

    alter table users 
        add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);

    alter table authorities 
        add constraint FK_s21btj9mlob1djhm3amivbe5e 
        foreign key (user_id) 
        references users;

    alter table cells 
        add constraint FK_82alcyb3cn7jaipx28v49axwg 
        foreign key (plan_id) 
        references plans;

    alter table cells 
        add constraint FK_8dw4ib1a0lhu8xdh7s4s4tha 
        foreign key (runway_id) 
        references runways;

    alter table cells 
        add constraint FK_4l75xv25nciaskmgvbxdcd424 
        foreign key (stage_id) 
        references stages;

    alter table checkpoints 
        add constraint FK_rrcdicx35xhsc6677jvgmrmp9 
        foreign key (cell_id) 
        references cells;

    alter table departments 
        add constraint FK_teos0btacmvhp1xyob6dx4m16 
        foreign key (plan_id) 
        references plans;

    alter table plans 
        add constraint FK_ouxfwiwm7coqe4bbw5jugqlun 
        foreign key (department_id) 
        references departments;

    alter table runways 
        add constraint FK_q60k75ar7b235heurk8knuu9 
        foreign key (plan_id) 
        references plans;

    alter table stages 
        add constraint FK_cufcysn96nfomawq58s2wraul 
        foreign key (plan_id) 
        references plans;

    alter table user_checkpoints 
        add constraint FK_to4y5oilg0c79s52l5dh3u9tk 
        foreign key (checkpoint_id) 
        references checkpoints;

    alter table user_checkpoints 
        add constraint FK_t1q6bdp8kteiy9pt35cy7pug5 
        foreign key (user_id) 
        references users;

    alter table users 
        add constraint FK_7phkg3qghukhuw9kj3ahkmw 
        foreign key (department_id) 
        references departments;

    alter table users 
        add constraint FK_km7rd8sgwa1qls24gkxoh2b2i 
        foreign key (plan_id) 
        references plans;

    create sequence hibernate_sequence;

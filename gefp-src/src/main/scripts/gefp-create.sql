create sequence hibernate_sequence minvalue 100;

	create table departments (
        id int8 not null,
        name varchar(255),
        plan_id int8,
        primary key (id)
    );
    
insert into departments (id, name, plan_id) values (1, 'IT and Science', 1);
insert into departments (id, name, plan_id) values (2, 'Engineering', 2);



    create table plans (
        id int8 not null,
        name varchar(255),
        published_date timestamp default current_timestamp,
        department_id int8,
        primary key (id)
    );

insert into plans (id, name, department_id) values (1, 'GEFP-Computer Science', 1);
insert into plans (id, name, department_id) values (2, 'GEFP-Electrical Engineering', 2);



   create table runways (
        id int8 not null,
        name varchar(255),
        plan_id int8,
        index int4,
        primary key (id)
    );

insert into runways (id, name, plan_id, index) values (1, 'Academics', 1,0);
insert into runways (id, name, plan_id, index) values (2, 'Career Preparation', 1,1);
insert into runways (id, name, plan_id, index) values (3, 'Leadership & Community Engagement', 1,2);

insert into runways (id, name, plan_id, index) values (4, 'Academics', 2,0);
insert into runways (id, name, plan_id, index) values (5, 'Career Preparation', 2,1);
insert into runways (id, name, plan_id, index) values (6, 'Leadership & Community Engagement', 2,2);


	
	create table stages (
        id int8 not null,
        name varchar(255),
        plan_id int8,
        index int4,
        primary key (id)
    );
    
insert into stages (id, name, plan_id, index) values (1, 'Pre-College (pre-flight checklist)', 1,0);
insert into stages (id, name, plan_id, index) values (2, 'Pre-College (pre-flight checklist)', 2,0);


    create table cells (
        id int8 not null,
        plan_id int8,
        runway_id int8,
        stage_id int8,
        primary key (id)
    );
    
insert into cells (id, plan_id, runway_id, stage_id) values (1, 1, 1, 1);
insert into cells (id, plan_id, runway_id, stage_id) values (2, 1, 2, 1);
insert into cells (id, plan_id, runway_id, stage_id) values (3, 1, 3, 1);
insert into cells (id, plan_id, runway_id, stage_id) values (4, 2, 4, 2);
insert into cells (id, plan_id, runway_id, stage_id) values (5, 2, 5, 2);
insert into cells (id, plan_id, runway_id, stage_id) values (6, 2, 6, 2);



    create table checkpoints (
        id int8 not null,
        description varchar(2500),
        info varchar(255),
        cell_id int8,
        deleted boolean not null default false,
        index int4,
        primary key (id)
    );

insert into checkpoints(id, description, info, cell_id, index) values (1, 'Algebra (before Yr1)', 'www.google.com', 1,0);
insert into checkpoints(id, description, info, cell_id, index) values (2, 'Pre-calculus (before Yr1)', 'www.google.com', 1,1);
insert into checkpoints(id, description, info, cell_id, index) values (3, 'Apply for financial aid and scholarships', 'www.google.com', 2,0);
insert into checkpoints(id, description, info, cell_id, index) values (4, 'Make a list of questions to ask during orientation and ask them!', 'www.google.com', 3,0);

insert into checkpoints(id, description, info, cell_id, index) values (5, 'Algebra (before Yr1)', 'www.google.com', 4,0);
insert into checkpoints(id, description, info, cell_id, index) values (6, 'Pre-calculus (before Yr1)', 'www.google.com', 5,0);
insert into checkpoints(id, description, info, cell_id, index) values (7, 'Take a personal assessment', 'www.google.com', 6,0);


     create table users (
        id int8 not null,
        cin varchar(255),
        contact varchar(255),
        email varchar(255),
        enabled boolean default true,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        username varchar(255) not null,
        department_id int8,
        plan_id int8,
        primary key (id)
    );
    
insert into users(id, cin, password, username, first_name, last_name, email, contact) values (1, 100000, md5('abcd'), 'cysun', 'Chengyu', 'Sun', 'csun@calstatela.edu', '123 123 1234');  
insert into users(id, cin, password, username, department_id, plan_id, first_name, last_name, email, contact) values (2, 100001, md5('abcd'), 'tfox', 1, 1, 'T', 'Fox', 'tfox@email.com', '123 123 1234');
insert into users(id, cin, password, username, department_id, plan_id, first_name, last_name, email, contact) values (3, 100002, md5('abcd'), 'jdoe1', 1, 1, 'Jdoe1', 'Jdoe', 'jdoe1@email.com', '123 123 1234');
insert into users(id, cin, password, username, department_id, plan_id, first_name, last_name, email, contact) values (4, 100003, md5('abcd'), 'jdoe2', 2, 2, 'Jdoe2', 'Jdoe', 'jdoe2@email.com', '123 123 1234');



   create table user_checkpoints (
        user_id int8 not null,
        checkpoint_id int8 not null
    );
insert into user_checkpoints(user_id, checkpoint_id) values (3, 1);
insert into user_checkpoints(user_id, checkpoint_id) values (3, 2); 
insert into user_checkpoints(user_id, checkpoint_id) values (3, 3); 
insert into user_checkpoints(user_id, checkpoint_id) values (3, 4);

insert into user_checkpoints(user_id, checkpoint_id) values (4, 5);
insert into user_checkpoints(user_id, checkpoint_id) values (4, 6);
insert into user_checkpoints(user_id, checkpoint_id) values (4, 7);
    
    create table authorities (
        user_id int8 not null,
        username varchar(255) not null,
        authority varchar(255)
    );
    
insert into authorities(user_id, authority, username) values (1, 'ROLE_ADMIN', 'cysun');
insert into authorities(user_id, authority, username) values (2, 'ROLE_ADVIS', 'tfox');  
insert into authorities(user_id, authority, username) values (3, 'ROLE_USER', 'jdoe1');
insert into authorities(user_id, authority, username) values (4, 'ROLE_USER', 'jdoe2');

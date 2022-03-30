create sequence hibernate_sequence start 1 increment 1;
create table employee (id int8 not null, email varchar(255), last_name varchar(255), name varchar(255), primary key (id));

drop table poll_choices if exists;
drop table poll if exists;

create table poll (
    code integer not null,
    subject varchar(100) not null,
    primary key (code)
);

create table poll_choices (
    poll_code integer not null,
    num integer not null,
    choice varchar(100) not null,
    vote_count integer default 0,
    primary key (poll_code, num),
    constraint poll_code_fk foreign key(poll_code)
    references poll(code)
);

insert into poll values ('1', 'Which color do you like?');
insert into poll_choices values ('1', 0, 'Red', 0);
insert into poll_choices values ('1', 1, 'Green', 0);
insert into poll_choices values ('1', 2, 'Blue', 0);
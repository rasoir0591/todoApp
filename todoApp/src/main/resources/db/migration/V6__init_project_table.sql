create table projects(
                         id int primary key auto_increment,
                         description varchar(100) not null
);
alter table task_groups add column projects_id int null;
alter table task_groups add foreign key (projects_id) references projects(id);
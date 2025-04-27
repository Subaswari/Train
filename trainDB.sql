use train;
SELECT USER, HOST,PLUGIN FROM mysql.user;
show databases;
show tables;
desc users;
alter table users add column role varchar(20) not null default 'USER';
show index from users where non_unique=0;
alter table users drop index UKr43af9ap4edm43mmtq01oddj6;

alter table reservation add constraint fku foreign key(user_id) references users(id);
alter table reservation add constraint fkt foreign key(train_id) references trains(id);
desc reservation;
truncate reservation;
select * from users;
select * from trains;
select * from reservation;

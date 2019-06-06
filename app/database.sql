create table users (
  id serial primary key not null,
  username text not null unique,
  password text not null,
  created_date timestamp default now()
);
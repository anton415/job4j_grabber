create table if not exists post (
    id serial,
    name text,
	text text,
	link text,
    created timestamp,
    primary key (id, link)
);
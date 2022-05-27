-- TABLE company
CREATE TABLE if not exists company (
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

-- TABLE person
CREATE TABLE if not exists person (
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT into company (id, name) values (1, 'Apple');
INSERT into company (id, name) values (2, 'IBM');
INSERT into company (id, name) values (3, 'Nokia');
INSERT into company (id, name) values (4, 'Job4j');
INSERT into company (id, name) values (5, 'Google');

INSERT into person (id, name, company_id) values (1, 'Anton', 1);
INSERT into person (id, name, company_id) values (2, 'Ivan', 2);
INSERT into person (id, name, company_id) values (3, 'Pavel', 3);
INSERT into person (id, name, company_id) values (4, 'Petr', 4);
INSERT into person (id, name, company_id) values (5, 'Alex', 5);

-- names of all persons who are not in the company with id = 5;
-- company name for each person.
select p.name as person, c.name as company
from company c
inner join person p
on c.id = p.company_id
where c.id != 5;

-- Choose the name of the company with the maximum number of people
-- + the number of people in this company
select c.name as company, count(p.name) as number_of_people
from person p
join company as c
on c.id = p.company_id
group by c.name
order by number_of_people desc



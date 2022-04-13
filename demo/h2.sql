-- CREATE database products;
-- use products;

CREATE table market
(
    id int(10) not null auto_increment,
    name varchar(70) not null,    
    primary key (id)
);

CREATE table technologies
(
    id int(10) not null auto_increment,
    name varchar(70) not null,    
    primary key (id)
);

CREATE table product
(
    id int(10) not null auto_increment,
    name varchar(70) not null,
    description varchar(200),
    primary key (id),
    marketId int(10) references market(id)
);

CREATE table product_tech
(
    product_id int(10) not null,
    tech_id int (10) not null,
    FOREIGN KEY (product_id) references product (id),
    FOREIGN KEY (tech_id) references technologies(id),
    unique (product_id, tech_id)
);

insert into market(id, name) values(null, 'Moveis');
insert into technologies(id, name) values(null, 'Java');
insert into product(id, name, description, marketId) values (null, 'Amazon', 'Integração com Amazon', 1);
insert into market(id, name) values(null, 'Eletrodomesticos');
insert into technologies(id, name) values(null, 'Kotlin');
insert into market(id, name) values(null, 'Eletronicos');
insert into technologies(id, name) values(null, 'C#');

insert into product(id, name, description, marketId) values (null, 'Carrefour', 'Integração com Carrefour', 1);

insert into product(id, name, description, marketId) values (null, 'Magalu', 'Integração com Magalu', 1);

insert into product(id, name, description, marketId) values (null, 'Magazine Luiza', 'Integração com Magazine Luiza', 2);

insert into product(id, name, description, marketId) values (null, 'Muffato', 'Integração com Muffato', 2);

insert into product(id, name, description, marketId) values (null, 'Bis2bis', 'Integração com Bis2bis', 3);

insert into product(id, name, description, marketId) values (null, 'Mercado Livre', 'Integração com Mercado Livre', 3);

insert into product(id, name, description, marketId) values (null, 'Pernambucanas', 'Integração com Pernambucanas', 3);


insert into product_tech(product_id, tech_id) values (1, 1);
insert into product_tech(product_id, tech_id) values (2, 1);
insert into product_tech(product_id, tech_id) values (3, 1);
insert into product_tech(product_id, tech_id) values (3, 2);
insert into product_tech(product_id, tech_id) values (3, 3);
insert into product_tech(product_id, tech_id) values (4, 1);
insert into product_tech(product_id, tech_id) values (4, 2);
insert into product_tech(product_id, tech_id) values (5, 1);
insert into product_tech(product_id, tech_id) values (6, 1);
insert into product_tech(product_id, tech_id) values (6, 3);
insert into product_tech(product_id, tech_id) values (7, 3);
insert into product_tech(product_id, tech_id) values (7, 2);
insert into product_tech(product_id, tech_id) values (8, 1);
insert into product_tech(product_id, tech_id) values (8, 2);
insert into product_tech(product_id, tech_id) values (8, 3);
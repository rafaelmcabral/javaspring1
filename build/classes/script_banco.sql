    
create table tasks (
	id BIGINT NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255),
	finalizada BOOLEAN,
	dataFinalizacao DATE,
	primary key (id)
);

create table usuarios (login VARCHAR(255),	senha VARCHAR(255));

insert into usuarios(login, senha) values ('joao', '1234');
insert into usuarios(login, senha) values ('maria', '5678');
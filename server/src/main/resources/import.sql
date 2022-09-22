insert into autores (nome, descricao, created_At) values ('Sophia Angelides', null, NOW());
insert into autores (nome, descricao, created_At) values ('Mário Pedrosa', null, NOW());
insert into autores (nome, descricao, created_At) values ('Emílio F. Moran', null, NOW());
insert into autores (nome, descricao, created_At) values ('Jack R. Greene', null, NOW());
insert into autores (nome, descricao, created_At) values ('Antônio Chaves', null, NOW());

-- --INSERT INTO tb_record (game_id, name, age, moment) VALUES (3, 'Tulio', 44, TIMESTAMP WITH TIME ZONE '2020-07-21T20:59:19Z');
insert into livros (titulo, ano_lancamento, importado, prazo_entrega, preco) values ('BTCHEKHOV: Cartas para uma Poética',1995,true,296,43);
insert into livros (titulo, ano_lancamento, importado, prazo_entrega, preco) values ('MODERNOS - Textos Escolhidos 3', 2002,false,31,47);
insert into livros (titulo, ano_lancamento, importado, prazo_entrega, preco) values ('HUMANA: Uma Introdução à Antropologia', 1998,false,400,42);
insert into livros (titulo, ano_lancamento, importado, prazo_entrega, preco) values ('TRABALHO POLICIAL', 2000,false,265,26);
insert into livros (titulo, ano_lancamento, importado, prazo_entrega, preco) values ('ADOÇÃO INTERNACIONAL e o Tráfico de Crianças', 1998, false,49,51);

insert into autores_livros (cod_autor, cod_livro) values (1,1);
insert into autores_livros (cod_autor, cod_livro) values (2,2);
insert into autores_livros (cod_autor, cod_livro) values (3,3);
insert into autores_livros (cod_autor, cod_livro) values (4,4);
insert into autores_livros (cod_autor, cod_livro) values (5,5);
insert into autores_livros (cod_autor, cod_livro) values (5,4);
insert into autores_livros (cod_autor, cod_livro) values (5,3);
insert into autores_livros (cod_autor, cod_livro) values (3,5);
insert into autores_livros (cod_autor, cod_livro) values (1,2);
insert into autores_livros (cod_autor, cod_livro) values (2,5);
